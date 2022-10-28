package com.openclassrooms.model;

import lombok.Data;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Operation")
@Data
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "operation_id")
    private int transfer_id;

    @Column(name = "account1_id")
    private int account1_id;

    @Column(name = "account2_id")
    private int account2_id;

    @Column(name = "amount")
    private int amount;

   @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "operation_account",
            joinColumns = @JoinColumn(name = "account1_id"),
            inverseJoinColumns = @JoinColumn(name = "account2_id")
    )
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.getOperations().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getOperations().remove(this);
    }
}
