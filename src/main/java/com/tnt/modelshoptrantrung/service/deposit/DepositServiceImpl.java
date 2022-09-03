package com.tnt.modelshoptrantrung.service.deposit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tnt.modelshoptrantrung.model.Deposit;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.repository.DepositRepository;
import com.tnt.modelshoptrantrung.service.account.user.UserService;

import java.util.Optional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService{
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserService userService;

    @Override
    public Iterable<Deposit> findAll() {
        return null;
    }
    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty ();
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save ( deposit );
    }

    @Override
    public Deposit getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        depositRepository.deleteById ( id );
    }

    @Override
    public User doDeposit(Deposit deposit) {
        depositRepository.save ( deposit );

        User user = deposit.getUser ();

        userService.addCoin ( user.getId (), deposit.getTransactionAmount () );

        return userService.getByUsername ( user.getUsername () ).get ();
    }
}
