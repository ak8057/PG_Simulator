package com.payu.payu_sim.service;

import org.springframework.stereotype.Service;

import com.payu.payu_sim.cache.TransactionCache;
import com.payu.payu_sim.model.Transaction;
import com.payu.payu_sim.model.TransactionRequest;
import com.payu.payu_sim.model.TransactionResponse;
import com.payu.payu_sim.processor.TransactionProcessor;

import java.util.ArrayList;
import java.util.List;

import com.payu.payu_sim.model.TransactionSummary;
import com.payu.payu_sim.util.CardMaskingUtil;
import com.payu.payu_sim.model.TransactionStatus;

@Service
public class TransactionService {

    private final TransactionProcessor processor;
    private final TransactionCache cache;

    public TransactionService(
            TransactionProcessor processor,
            TransactionCache cache) {

        this.processor = processor;
        this.cache = cache;
    }

    public TransactionResponse process(TransactionRequest request) {

        String txnId = processor.process(request);

        Transaction txn = cache.get(txnId);

        String status = txn.getStatus().name();

        return new TransactionResponse(
                txnId,
                status,
                "Transaction " + status);
    }

    public TransactionResponse getStatus(String txnId) {

        Transaction txn = cache.get(txnId);

        if (txn == null) {

            return new TransactionResponse(
                    txnId,
                    "NOT_FOUND",
                    "Transaction not found");
        }

        return new TransactionResponse(
                txnId,
                txn.getStatus().name(),
                "Transaction status retrieved successfully");
    }

    public List<TransactionSummary> getAllTransactions() {

        List<TransactionSummary> summaries = new ArrayList<>();

        for (Transaction txn : cache.getAll().values()) {

            String maskedCard = CardMaskingUtil.mask(txn.getCardNumber());

            summaries.add(

                    new TransactionSummary(
                            txn.getTransactionId(),
                            maskedCard,
                            txn.getAmount(),
                            txn.getStatus().name(),
                            txn.getTimestamp()));
        }

        return summaries;
    }

    public TransactionResponse complete3DS(String txnId) {

        Transaction txn = cache.get(txnId);

        if (txn == null) {

            return new TransactionResponse(
                    txnId,
                    "NOT_FOUND",
                    "Transaction not found");
        }

        if (txn.getStatus() != TransactionStatus.THREE_DS_REQUIRED) {

            return new TransactionResponse(
                    txnId,
                    txn.getStatus().name(),
                    "3DS not required for this transaction");
        }

        txn.setStatus(TransactionStatus.APPROVED);

        return new TransactionResponse(
                txnId,
                "APPROVED",
                "3DS authentication completed successfully");
    }

}
