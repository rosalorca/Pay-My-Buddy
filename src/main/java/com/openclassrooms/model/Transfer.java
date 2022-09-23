package com.openclassrooms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfer")
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "transferId")
    private int transferId;

    @Column(name = "user1_Id")
    private int user1_Id;

    @Column(name = "user2_Id")
    private int user2_Id;

    @Column(name = "amount")
    private int amount;


}
