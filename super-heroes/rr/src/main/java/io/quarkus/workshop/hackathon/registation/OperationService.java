package io.quarkus.workshop.hackathon.registration;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Map;

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

    @Transactional(SUPPORTS)
    public Operation persistOperation(@Valid Operation operation) {
        Operation.persist(operation);
        return operation;
    }

    @Transactional(SUPPORTS)
    public Operation updateOperation(@Valid Operation operation) {
        Operation entity = Operation.findById(operation.id);
        entity.listHistory.add(entity.dataValues.size());
        entity.dataValues = operation.dataValues;

        if (entity.dataValues.size() == entity.dataKeys.size())
            enqueueOperation(operation);

        return entity;
    }

    @Transactional(SUPPORTS)
    public Operation stepBack(@Valid Operation operation) {
        Operation entity = Operation.findById(operation.id);
        if (entity.listHistory.isEmpty()) {
            Operation.deleteById(entity.id);
            return null;
        }

        Integer newLen = entity.listHistory.get(entity.listHistory.size() - 1);
        entity.listHistory.remove(entity.listHistory.size() - 1);
        while (entity.listHistory.size() > newLen)
            entity.dataValues.remove(newLen);
        
        return entity;
    }

    private void enqueueOperation(Operation operation) {
        System.out.println("ENQUEUING " + operation);
    }
}
