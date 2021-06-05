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
    public List<Operation> findAllOperations() {
        return Operation.listAll();
    }

    @Transactional(SUPPORTS)
    public Operation findOperationById(Long id) {
        return Operation.findById(id);
    }

    public Operation persistOperation(@Valid Operation operation) {
        Operation.persist(operation);
        return operation;
    }

    public Operation updateOperation(@Valid Operation operation) {
        Operation entity = Operation.findById(operation.id);
        if (operation.sourceAccount != null)
            entity.sourceAccount = operation.sourceAccount;
        if (operation.destinationAccount != null)
            entity.destinationAccount = operation.destinationAccount;
        if (operation.currency != null)
            entity.currency = operation.currency;
        if (operation.exchangeRate != null)
            entity.exchangeRate = operation.exchangeRate;
        if (operation.amount != null)
            entity.amount = operation.amount;

        return entity;
    }
}
