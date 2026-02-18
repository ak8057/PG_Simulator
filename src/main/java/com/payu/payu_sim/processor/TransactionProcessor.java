package com.payu.payu_sim.processor;

import org.springframework.stereotype.Component;

import com.payu.payu_sim.bank.BankSimulator;
import com.payu.payu_sim.cache.TransactionCache;
import com.payu.payu_sim.model.TransactionRequest;
import com.payu.payu_sim.model.Transaction;
import com.payu.payu_sim.model.TransactionStatus;


@Component
public class TransactionProcessor {

    private final TransactionCache cache;
    private final BankSimulator bankSimulator;

    public TransactionProcessor(
            TransactionCache cache,
            BankSimulator bankSimulator) {

        this.cache = cache;
        this.bankSimulator = bankSimulator;
    }

    public String process(TransactionRequest request) {

    String txnId = "TXN" + System.currentTimeMillis();

    // Step 1: Create transaction with CREATED state
    Transaction txn = new Transaction(
            txnId,
            request.getCardNumber(),
            request.getAmount(),
            request.getExpiry(),
            TransactionStatus.CREATED,
            System.currentTimeMillis()
    );

    cache.put(txn);

    System.out.println("Transaction CREATED: " + txnId);


    // Step 2: Move to PROCESSING state
    txn.setStatus(TransactionStatus.PROCESSING);

    System.out.println("Transaction PROCESSING: " + txnId);


    // Step 3: Bank authorization
    String bankResponse = bankSimulator.authorize(
            request.getCardNumber(),
            request.getAmount()
    );


    // Step 4: Final state assignment
    TransactionStatus finalStatus;

    switch (bankResponse) {

        case "APPROVED":
            finalStatus = TransactionStatus.APPROVED;
            break;

        case "DECLINED":
            finalStatus = TransactionStatus.DECLINED;
            break;

        case "3DS_REQUIRED":
            finalStatus = TransactionStatus.THREE_DS_REQUIRED;
            break;

        default:
            finalStatus = TransactionStatus.DECLINED;
    }


    txn.setStatus(finalStatus);

    System.out.println("Transaction FINAL STATUS: " +
            txnId + " → " + finalStatus);

    return txnId;
}


}
