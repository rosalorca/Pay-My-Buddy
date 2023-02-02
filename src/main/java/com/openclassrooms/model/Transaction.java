package com.openclassrooms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@ToString
@DynamicUpdate
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "transaction_id")
    private Integer transactionId;
    @Column(name = "user1_id")
    private Integer user1Id;

    @Column(name = "user2_id")
    private Integer user2Id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transaction_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate transaction_date;

    @Column(name = "transaction_description")
    private String transaction_description;

}
