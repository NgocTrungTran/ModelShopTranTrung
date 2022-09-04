package com.tnt.modelshoptrantrung.service.deposit;

import com.tnt.modelshoptrantrung.model.Deposit;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.model.dto.HistoryDepositDTO;
import com.tnt.modelshoptrantrung.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DepositService extends IGeneralService<Deposit> {
    User doDeposit(Deposit deposit);

    List<HistoryDepositDTO> getHistoryDepositByUserID(Long userid);

}
