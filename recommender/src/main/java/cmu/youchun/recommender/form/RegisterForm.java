package cmu.youchun.recommender.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterForm {
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "Nickname cannot be empty")
    private String nickName;
    @NotNull(message = "Gender cannot be empty")
    private Integer gender;
}