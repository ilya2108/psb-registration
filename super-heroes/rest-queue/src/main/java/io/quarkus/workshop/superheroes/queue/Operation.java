package io.quarkus.workshop.superheroes.queue;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.ElementCollection;

@Entity
public class Operation extends PanacheEntity {
    public String sourceAccount;
    public String destinationAccount;
    public String currency;
    public Double exchangeRate;
    public Double amount;


}
