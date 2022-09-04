package com.tnt.modelshoptrantrung.service.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.model.UserPrinciple;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import com.tnt.modelshoptrantrung.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll ();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById ( id );
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save ( user );
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById ( id );
    }

    @Override
    public List<UserDTO> findAllUsersDTO() {
        return userRepository.findAllUsersDTO ();
    }

    @Override
    public List<UserDTO> findAllUsersDTODeleted() {
        return userRepository.findAllUsersDTODeleted ();
    }

    @Override
    public List<UserDTO> findAllUsersDTODeletedFalseAndActiveTrue() {
        return userRepository.findAllUsersDTODeletedFalseAndActiveTrue ();
    }

    @Override
    public List<UserDTO> findAllUsersDTODeletedFalseAndActiveFalse() {
        return userRepository.findAllUsersDTODeletedFalseAndActiveFalse ();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername ( username );
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail ( email );
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone ( phone );
    }

    @Override
    public void addCoin(Long userId, BigDecimal amountMoney) {
        userRepository.addCoin ( userId, amountMoney );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername ( username );
    }

    @Override
    public void blockUser(Long userId) {
        userRepository.blockUser ( userId );
    }

    @Override
    public User findByBlockedIsFalseAndId(Long id) {
        return userRepository.findByBlockedIsFalseAndId ( id );
    }

    @Override
    public void deleteData(Long userId) {
        userRepository.deleteData ( userId );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }
}
