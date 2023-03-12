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
public class TransactionParams {
    Double amount;
    String email;
    String firstName;
    String description;
    Double balance;
}
