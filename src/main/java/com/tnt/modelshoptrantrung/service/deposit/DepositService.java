package com.tnt.modelshoptrantrung.service.deposit;

import com.tnt.modelshoptrantrung.model.Deposit;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.service.IGeneralService;

import java.math.BigDecimal;

public interface DepositService extends IGeneralService<Deposit> {
    User doDeposit(Deposit deposit);



}
