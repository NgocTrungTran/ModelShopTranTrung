package com.tnt.modelshoptrantrung.controller.rest;

import com.tnt.modelshoptrantrung.exception.*;
import com.tnt.modelshoptrantrung.model.Deposit;
import com.tnt.modelshoptrantrung.model.LocationRegion;
import com.tnt.modelshoptrantrung.model.Role;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.model.dto.DepositDTO;
import com.tnt.modelshoptrantrung.model.dto.HistoryDepositDTO;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import com.tnt.modelshoptrantrung.service.account.user.UserService;
import com.tnt.modelshoptrantrung.service.deposit.DepositService;
import com.tnt.modelshoptrantrung.service.location.LocationRegionService;
import com.tnt.modelshoptrantrung.service.role.RoleService;
import com.tnt.modelshoptrantrung.util.AppUtil;
import com.tnt.modelshoptrantrung.util.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private AppUtil appUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private LocationRegionService locationRegionService;

    @Autowired
    private IValidator validator;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> users = userService.findAllUsersDTO ();

        if (users.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/active-list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUserActive() {
        List<UserDTO> users = userService.findAllUsersDTODeletedFalseAndActiveTrue ();

        if (users.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/block-list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUserBlock() {
        List<UserDTO> users = userService.findAllUsersDTODeletedFalseAndActiveFalse ();

        if (users.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/trash")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUserTrash() {
        List<UserDTO> users = userService.findAllUsersDTODeleted ();

        if (users.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/check-block/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getUserByIdAndBlockFalse(@PathVariable String userId) {
        if ( !validator.isIntValid ( userId ) ) {
            throw new DataInputException ( "User ID invalid!" );
        }
        Long user_id = Long.parseLong ( userId );

        Optional<User> userOptional = userService.findById(user_id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        if ( userOptional.get ().isBlocked () ) {
            throw new AccountInputException ( "Account has been block!" );
        }


        return new ResponseEntity<>(userOptional.get().toUserDTO (), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {

        if ( !validator.isIntValid ( userId ) ) {
            throw new DataInputException ( "User ID invalid!" );
        }
        Long user_id = Long.parseLong ( userId );

        Optional<User> userOptional = userService.findById(user_id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        return new ResponseEntity<>(userOptional.get().toUserDTO (), HttpStatus.OK);
    }

    @PostMapping("/create-admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doCreateAdmin(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors())
            return appUtil.mapErrorToResponse(bindingResult);

        Boolean existsUsername = userService.existsByUsername ( userDTO.getUsername () );

        if (existsUsername) {
            throw new AttributesExistsException ("Username already exists");
        }

        Boolean existsEmail = userService.existsByEmail ( userDTO.getEmail () );
        if (existsEmail) {
            throw new DataInputException ("Email already exists");
        }

        userDTO.getLocationRegion ().setId ( 0L );
        LocationRegion locationRegion = locationRegionService.save ( userDTO.getLocationRegion ().toLocationRegion () );
        userDTO.setLocationRegion ( locationRegion.toLocationRegionDTO () );
        try {
            User admin = userDTO.toAdmin ();
            Optional<Role> role = roleService.findById ( 1L );
            if (!role.isPresent()) {
                throw new AttributesExistsException ("Role not exists");
            }

            admin.setRole ( role.get () );
            userService.save(admin);

            return new ResponseEntity<>( HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/edit-admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doEditAdmin(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors())
            return appUtil.mapErrorToResponse(bindingResult);

        Optional<User> userOptional = userService.findById ( userDTO.getId () );

        if ( !userOptional.isPresent () ) {
            throw new ResourceNotFoundException ( "Admin not exists!" );
        }

        Boolean existsUsername = userService.existsByUsername ( userDTO.getUsername () );

        if (existsUsername && !userDTO.getUsername ().equals ( userOptional.get ().getUsername () ) ) {
            throw new AttributesExistsException ("Username already exists");
        }

        Boolean existsEmail = userService.existsByEmail ( userDTO.getEmail () );
        if (existsEmail && !userDTO.getEmail ().equals ( userOptional.get ().getEmail () )) {
            throw new DataInputException ("Email already exists");
        }

        Boolean existsPhone = userService.existsByPhone ( userDTO.getPhone () );
        if ( existsPhone && !userDTO.getPhone ().equals ( userOptional.get ().getPhone () ) ) {
            throw new DataOutputException ( "Phone already exists" );
        }

        try {
            userDTO.setPassword ( userOptional.get ().getPassword () );
            userDTO.setAvatar ( userOptional.get ().getAvatar () );

            LocationRegion locationRegionBefore = userOptional.get ().getLocationRegion ();
            LocationRegion locationRegionAfter = userDTO.getLocationRegion ().toLocationRegion ();
            locationRegionAfter.setId ( locationRegionBefore.getId () );

            LocationRegion locationRegionUpdate = locationRegionService.save ( locationRegionAfter );

            userDTO.setLocationRegion ( locationRegionUpdate.toLocationRegionDTO () );

            User admin = userDTO.toAdmin ();

            Role role = userOptional.get ().getRole ();

            admin.setRole ( role );
            userService.save(admin);

            return new ResponseEntity<>( HttpStatus.ACCEPTED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doDeposit(@Validated @RequestBody DepositDTO depositDTO, BindingResult bindingResult) {

        new DepositDTO().validate(depositDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Optional<User> userCreat = userService.getByUsername ( depositDTO.getCreateBy () );
        if (!userCreat.isPresent()) {
            throw new ResourceNotFoundException("Admin not exists!");
        }

        Long userId = depositDTO.getUserId ();

        Optional<User> userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User invalid");
        }

        if ( userOptional.get ().isBlocked () ) {
            throw new DataInputException ( "Account has been block!" );
        }

        depositDTO.setId(0L);

        Deposit deposit = depositDTO.toDeposit(userOptional.get());

        try {
            User user = depositService.doDeposit (deposit);

            return new ResponseEntity<>(user.toUserDTO (), HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataInputException ("Invalid deposit information");
        }
    }

    @GetMapping("/deposit/{userId}")
    public ResponseEntity<?> getDepositByUserId (@PathVariable String userId) {
        if ( !validator.isIntValid ( userId ) ){
            throw new DataInputException ( "User not exists!" );
        }

        Long user_id = Long.parseLong ( userId );

        List<HistoryDepositDTO> historyDepositDTOList = depositService.getHistoryDepositByUserID ( user_id );

        if ( historyDepositDTOList.isEmpty () ) {
            throw new ResourceNotFoundException ( "No data" );
        }


        return new ResponseEntity<> (historyDepositDTOList ,HttpStatus.OK );
    }

    @PatchMapping("/blocked")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doBlock(@Validated @RequestBody UserDTO userDTO) {

        Optional<User> userOptional = userService.findById(userDTO.getId ());

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        User user = userOptional.get();

        user.setBlocked ( true);
        userService.save ( user );

        User userBlocked = userService.findById ( user.getId () ).get ();
        return new ResponseEntity<>(userBlocked.toUserDTO (), HttpStatus.OK);
    }

    @PatchMapping("/active")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doActive(@Validated @RequestBody UserDTO userDTO) {

        Optional<User> userOptional = userService.findById(userDTO.getId ());

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        User user = userOptional.get();

        user.setBlocked ( false);
        userService.save ( user );

        User userBlocked = userService.findById ( user.getId () ).get ();
        return new ResponseEntity<>(userBlocked.toUserDTO (), HttpStatus.OK);
    }

    @PatchMapping("/remove")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doRemove(@Validated @RequestBody UserDTO userDTO) {

        Optional<User> userOptional = userService.findById(userDTO.getId ());

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        User user = userOptional.get();

        user.setDeleted ( true);
        userService.save ( user );

        User userBlocked = userService.findById ( user.getId () ).get ();
        return new ResponseEntity<>(userBlocked.toUserDTO (), HttpStatus.OK);
    }

    @PatchMapping("/restore")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doRestore(@Validated @RequestBody UserDTO userDTO) {

        Optional<User> userOptional = userService.findById(userDTO.getId ());

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        User user = userOptional.get();

        user.setDeleted ( false);
        userService.save ( user );

        User userBlocked = userService.findById ( user.getId () ).get ();
        return new ResponseEntity<>(userBlocked.toUserDTO (), HttpStatus.OK);
    }

}
