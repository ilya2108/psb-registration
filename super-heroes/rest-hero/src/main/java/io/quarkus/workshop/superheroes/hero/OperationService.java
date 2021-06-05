package io.quarkus.workshop.superheroes.hero;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class OperationService {
    @Transactional(SUPPORTS)
    public List<Operation> findAllTransactions() {
        return Operation.listAll();
    }

    @Transactional(SUPPORTS)
    public Operation findTransactionById(Long id) {
        return Operation.findById(id);
    }

    public Operation persistTransaction(@Valid Operation transaction) {
        Operation.persist(transaction);
        return transaction;
    }

    public Operation updateTransaction(@Valid Operation transaction) {
        Operation entity = Operation.findById(transaction.id);
        if (transaction.sourceAccount != null)
            entity.sourceAccount = transaction.sourceAccount;
        if (transaction.destinationAccount != null)
            entity.destinationAccount = transaction.destinationAccount;
        if (transaction.currency != null)
            entity.currency = transaction.currency;
        if (transaction.exchangeRate != null)
            entity.exchangeRate = transaction.exchangeRate;
        if (transaction.amount != null)
            entity.amount = transaction.amount;

        return entity;
    }
}
