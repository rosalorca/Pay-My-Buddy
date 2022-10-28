package com.openclassrooms.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "balance")
    private int balance;

    @ManyToMany(
            mappedBy = "accounts",
            cascade = CascadeType.ALL

    )
    List<Operation> operations = new ArrayList<>();

    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.getAccounts().add(this);
    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
        operation.getAccounts().remove(this);
    }

}
