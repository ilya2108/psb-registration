package io.quarkus.workshop.hackathon.registration;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class KeyValuePair extends PanacheEntity {
    public String key;
    public String value;
}
