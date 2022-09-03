package com.tnt.modelshoptrantrung.model.dto;

import com.tnt.modelshoptrantrung.model.Deposit;
import com.tnt.modelshoptrantrung.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DepositDTO implements Validator {
    private Long id;
    private Long userId;
    private String createBy;
    private BigDecimal transactionAmount;

    public Deposit toDeposit(User user) {
        return (Deposit) new Deposit ()
                .setUser ( user )
                .setTransactionAmount ( transactionAmount )
                .setCreatedBy ( createBy );
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepositDTO.class.isAssignableFrom ( clazz );
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepositDTO depositDTO = (DepositDTO) target;

        BigDecimal checkTransactionAmount = depositDTO.getTransactionAmount();

//        String transactionAmountStr = checkTransactionAmount.toString ();
//
//        if (transactionAmountStr == null) {
//            errors.rejectValue("transactionAmount", "transactionAmount.null", "The transaction amount not null");
//            return;
//        }
//
//        if (transactionAmountStr.trim ().isEmpty()) {
//            errors.rejectValue("transactionAmount", "transactionAmount.isEmpty", "The transaction amount not empty");
//            return;
//        }
//
//        if (!transactionAmountStr.matches("(^$|[0-9]*$)")){
//            errors.rejectValue("transactionAmount", "transactionAmount.matches", "The transaction amount only digit");
//            return;
//        }

//        BigDecimal transactionAmount = new BigDecimal(Long.parseLong(transactionAmountStr));
        BigDecimal min = new BigDecimal(100000L);
        BigDecimal max = new BigDecimal(100000000L);

        if (checkTransactionAmount.compareTo(min) < 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.min", "The transaction amount min 100.000 VND");
            return;
        }

        if (checkTransactionAmount.compareTo(max) > 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.max", "The transaction amount max 100.000.000 VND");
        }
    }
}
