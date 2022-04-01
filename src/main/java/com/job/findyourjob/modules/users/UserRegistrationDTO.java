package com.job.findyourjob.modules.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String registerUserName;
    private String registerUserPassword;
    private String repeatPassword;
    private String mailAddress;
    private String role;

}
