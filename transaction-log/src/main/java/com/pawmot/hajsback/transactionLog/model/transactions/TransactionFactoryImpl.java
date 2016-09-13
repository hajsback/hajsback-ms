package com.pawmot.hajsback.transactionLog.model.transactions;

import org.springframework.stereotype.Component;

@Component
class TransactionFactoryImpl implements TransactionFactory {
    @Override
    public Transaction create(String sourceEmail, String targetEmail, int amount) {
        return new Transaction(sourceEmail, targetEmail, amount);
    }
}