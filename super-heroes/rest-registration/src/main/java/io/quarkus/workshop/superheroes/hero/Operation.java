package io.quarkus.workshop.superheroes.hero;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Operation extends PanacheEntity {
    public String sourceAccount;
    public String destinationAccount;
    public String currency;
    public Double exchangeRate;
    public Double amount;

    List<String> emptyParameters() {
        List<String> empty = new ArrayList<>();
        if (sourceAccount == null)
            empty.add("sourceAccount");
        if (destinationAccount == null)
            empty.add("destinationAccount");
        if (currency == null)
            empty.add("currency");
        if (exchangeRate == null)
            empty.add("exchangeRate");
        if (amount == null)
            empty.add("amount");

        return empty;
    }
}
