package cmu.youchun.recommender.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "iconUrl cannot be null")
    private String iconUrl;

    @NotNull(message = "Priority cannot be null")
    private Integer priority;
}
