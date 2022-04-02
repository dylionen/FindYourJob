package com.job.findyourjob.modules.users;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationDTO {

    @NotNull
    @Size(min = 4,max = 64)
    private String userName;

    @NotNull
    @Size(min = 8,max = 64)
    private String password;

    @NotNull
    @Size(min = 8,max = 64)
    private String repeatPassword;

    @NotNull
    @Email(message = "Email should be valid")
    private String mailAddress;

    @NotNull
    private String role;

}
