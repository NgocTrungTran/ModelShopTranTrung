package com.tnt.modelshoptrantrung.service.account.user;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import com.tnt.modelshoptrantrung.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User>, UserDetailsService {

    List<UserDTO> findAllUsersDTO();

    List<UserDTO> findAllUsersDTODeleted();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    void addCoin(Long userId, BigDecimal amountMoney);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Optional<User> getByUsername(String username);

    void blockUser(Long userId);

    User findByBlockedIsFalseAndId(Long id);

    void deleteData(Long userId);
}
