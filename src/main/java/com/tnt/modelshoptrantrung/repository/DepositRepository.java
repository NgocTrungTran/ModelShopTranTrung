package com.tnt.modelshoptrantrung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tnt.modelshoptrantrung.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
