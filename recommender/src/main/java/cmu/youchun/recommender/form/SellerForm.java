package cmu.youchun.recommender.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class SellerForm {
    @NotBlank(message = "Seller name cannot be empty")
    private String name;
}
