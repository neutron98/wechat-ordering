package com.service.mart.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Validate the order's parameters
 */
@Data
public class OrderForm {

    /** Consumer's first name */
    @NotEmpty(message = "First name is required")
    private String firstName;

    /** Consumer's last name */
    @NotEmpty(message = "Last name is required")
    private String lastName;

    /** Consumer's phone number */
    @NotEmpty(message = "Phone number is required")
    private String phone;

    /** Consumer's address */
    @NotEmpty(message = "Address is required")
    private String address;

    /** Consumer's WeChat openid */
    @NotEmpty(message = "openid is required")
    private String openid;

    /** Shopping cart */
    private String items;
}
