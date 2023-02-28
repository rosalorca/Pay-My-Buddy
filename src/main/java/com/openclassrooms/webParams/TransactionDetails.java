package com.openclassrooms.webParams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
    Integer id;
    Double amount;
    String email;
    String description;
}
