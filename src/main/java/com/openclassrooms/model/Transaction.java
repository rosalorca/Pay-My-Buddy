package com.openclassrooms.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Transfer")
@Data
@DynamicUpdate
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "transaction_id")
    private Integer transId;
    @Column(name = "account1_id")
    private int account1Id;

    @Column(name = "account2_id")
    private int account2Id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transfer_time")
    private long operation_date;

    @Column(name = "transfer_description")
    private String operation_description;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            }
    )
    @JoinTable(
            name = "transaction_account",
            joinColumns = @JoinColumn(name = "account1_id"),
            inverseJoinColumns = {@JoinColumn(name = "account2_id")}
                   /* @JoinColumn(name = "operation_description"),
                    @JoinColumn(name = "operation_time")}*/
    )
    private List<Account> accounts;

    public void addAccount(Account account) {
        accounts.add(account);
        account.getOperations().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getOperations().remove(this);
    }
}
