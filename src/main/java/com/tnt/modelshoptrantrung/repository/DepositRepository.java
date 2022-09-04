package com.tnt.modelshoptrantrung.repository;

import com.tnt.modelshoptrantrung.model.dto.HistoryDepositDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tnt.modelshoptrantrung.model.Deposit;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query("SELECT NEW com.tnt.modelshoptrantrung.model.dto.HistoryDepositDTO(" +
            "d.id, " +
            "d.createdBy, " +
            "u.fullName, " +
            "d.transactionAmount, " +
            "d.createdAt " +
            ") FROM Deposit AS d " +
            "JOIN User AS u " +
            "ON d.user.id = u.id " +
            "WHERE u.id = :userid")
    List<HistoryDepositDTO> getHistoryDepositByUserID(@Param("userid") Long userid);
}
