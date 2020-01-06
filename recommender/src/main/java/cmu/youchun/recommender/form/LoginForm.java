package cmu.youchun.recommender.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
