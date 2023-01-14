package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Account")
@Getter
@Setter
@ToString
@DynamicUpdate

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "balance")
    private double balance;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @ToString.Exclude
    private List<Transaction> transactions;

}
