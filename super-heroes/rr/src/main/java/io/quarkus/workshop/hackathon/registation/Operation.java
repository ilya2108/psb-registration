package io.quarkus.workshop.hackathon.registration;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.Type;

import com.vladmihalcea.hibernate.type.array.StringArrayType;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
@TypeDefs({
@TypeDef(
    name = "string-array",
    typeClass = StringArrayType.class
)
})
@Table(name="Operation", schema="public")
public class Operation extends PanacheEntity {
    public static enum State {
        REGISTRATION, PROCESSING, COMPLETED, CANDELLED 
    }

    public List<String> currentMetaData() {
        List<String> emptyParams = new ArrayList<>();
        for (int i = dataValues.size(); i < dataKeys.size(); i++)
            emptyParams.add(dataKeys.get(i));
        return emptyParams;
    }

    public State state = State.REGISTRATION;

    @ElementCollection
    public List<Integer> listHistory = new ArrayList<>();

    @NotNull
    @Column(name="opName")
    public String opName;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<KeyValuePair> data;

}
