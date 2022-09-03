package com.tnt.modelshoptrantrung.repository;

import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tnt.modelshoptrantrung.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
    @Query("SELECT new com.tnt.modelshoptrantrung.model.dto.UserDTO (" +
            "u.id, " +
            "u.username, " +
            "u.fullName, " +
            "u.email, " +
            "u.phone, " +
            "u.coin, " +
            "u.avatar, " +
            "u.blocked, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u WHERE u.deleted = false and u.role.id = 2"
    )
    List<UserDTO> findAllUsersDTO();

    @Query("SELECT new com.tnt.modelshoptrantrung.model.dto.UserDTO (" +
            "u.id, " +
            "u.username, " +
            "u.fullName, " +
            "u.email, " +
            "u.phone, " +
            "u.coin, " +
            "u.avatar, " +
            "u.blocked, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u WHERE u.deleted = true and u.role.id = 2"
    )
    List<UserDTO> findAllUsersDTODeleted();
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    User findByBlockedIsFalseAndId(Long id);

    @Modifying
    @Query("UPDATE User AS u " +
            "SET u.coin = u.coin + :amountMoney " +
            "WHERE u.id = :userId")
    void addCoin(@Param("userId") Long userId, @Param("amountMoney") BigDecimal amountMoney);

    @Query("SELECT NEW com.tnt.modelshoptrantrung.model.dto.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User AS u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);

    @Modifying
    @Query("UPDATE User AS u " +
            "SET u.blocked = 1 " +
            "WHERE u.id = :userId")
    void blockUser(@Param("userId") Long userId);
    @Modifying
    @Query("DELETE FROM User AS u WHERE (u.id = :userId)")
    void deleteData(@Param("userId") Long userId);

//    DELETE FROM `modelshop`.`users` WHERE (`id` = '6');
}
