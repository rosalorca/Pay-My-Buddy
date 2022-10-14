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

    @Column(name = "operationId")
    private int transferId;

    @Column(name = "user1_id")
    private int user1_Id;

    @Column(name = "user2_id")
    private int user2_Id;

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
            name = "operation_user",
            joinColumns = @JoinColumn(name = "userId")
    )
    private List<User> users = new ArrayList<>();

   @ManyToMany(
            mappedBy = "users"
    )
    private List<Account> accounts = new ArrayList<>();
}
