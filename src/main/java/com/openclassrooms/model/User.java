package com.openclassrooms.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "Firstname is mandatory")
    @Column(name = "firstname")
    private String name;

    @NotBlank(message = "Lastname is mandatory")
    @Column(name = "lastname")
    private String lastname;

    @NotBlank(message = "E-mail is mandatory")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "contact",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id")
    )
    private List<User> contact;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "balance")
    private double balance;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "user_transactions",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
            })
    private Set<Transaction> transactions;

}
