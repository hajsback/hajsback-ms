package com.pawmot.hajsback.transactionLog.validation;

import com.pawmot.hajsback.transactionLog.dto.transactions.RepayDebtRequest;
import org.springframework.stereotype.Component;

@Component
class RepayDebtRequestValidatorImpl implements RepayDebtRequestValidator {
    @Override
    public boolean validate(RepayDebtRequest request, String userEmail) {
        return request.getAmount() > 0 &&
                (request.getPayeeEmail().equals(userEmail) || request.getPayerEmail().equals(userEmail));
    }
}