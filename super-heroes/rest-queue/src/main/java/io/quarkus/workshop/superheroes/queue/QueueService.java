package io.quarkus.workshop.superheroes.queue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import io.quarkus.workshop.superheroes.queue.Operation;

@ApplicationScoped
@Transactional(REQUIRED)
public class QueueService {
    
    @Inject
    @Channel("queue")
    Emitter<Operation> emitter;

    @Transactional(SUPPORTS)
    public List<Operation> findAllOperations() {
        return Operation.listAll();
    }

    @Transactional(SUPPORTS)
    public Operation findOperationById(Long id) {
        return Operation.findById(id);
    }

    public Operation persistOperation(@Valid Operation operation) {
        //Operation.persist(operation);
        emitter.send(operation);
        return operation;
    }

    public Operation deleteOperation(Long id) {
        Operation operation = findOperationById(id);
        operation.delete();
        return operation;
    }

    // public Operation updateOperation(@Valid Operation operation) {
    //     Operation entity = Operation.findById(operation.id);
    //     history.add(String.join(",", entity.emptyParameters()));
    //     System.out.println(history);

    //     if (operation.sourceAccount != null)
    //         entity.sourceAccount = operation.sourceAccount;
    //     if (operation.destinationAccount != null)
    //         entity.destinationAccount = operation.destinationAccount;
    //     if (operation.currency != null)
    //         entity.currency = operation.currency;
    //     if (operation.exchangeRate != null)
    //         entity.exchangeRate = operation.exchangeRate;
    //     if (operation.amount != null)
    //         entity.amount = operation.amount;

    //     return entity;
    // }

    // public Operation goBack(@Valid Operation operation) {
    //     Operation entity = Operation.findById(operation.id);

    //     lastAddedParams().forEach(param -> {
    //         entity.nullify(param);
    //     });
        
    //     popHistory();

    //     return entity;
    // }

    // public List<String> lastAddedParams() {
    //     if (history.isEmpty())
    //         return List.of("sourceAccount", "destinationAccount", "currency", "exchangeRate", "amount");
    //     return Arrays.asList(history.get(history.size() - 1).split(","));
    // }

    // public void popHistory() {
    //     history.remove(history.size() - 1);
    // }
}
