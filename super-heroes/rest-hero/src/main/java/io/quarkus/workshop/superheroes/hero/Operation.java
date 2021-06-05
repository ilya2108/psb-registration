package io.quarkus.workshop.superheroes.hero;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Operation extends PanacheEntity {
    public String sourceAccount;
    public String destinationAccount;
    public String currency;
    public Double exchangeRate;
    public Double amount;
}
