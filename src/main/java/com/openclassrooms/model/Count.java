package com.openclassrooms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "count")
@Data
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "countId")
    private int countId;

    @Column(name = "userId")
    private int userId;

}
