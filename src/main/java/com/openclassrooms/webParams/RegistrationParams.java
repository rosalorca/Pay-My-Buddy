package com.openclassrooms.webParams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistrationParams {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
