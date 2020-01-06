package cmu.youchun.recommender.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ShopForm {
    @NotBlank(message = "Merchant name can not be empty")
    private String name;

    @NotNull(message = "Price per consumer cannot be empty")
    private Integer pricePerConsumer;

    @NotNull(message = "Latitute cannot be empty")
    private BigDecimal latitude;

    @NotNull(message = "Longtitute cannot be empty")
    private BigDecimal longitude;

    @NotNull(message = "Service category cannot be empty")
    private Integer categoryId;

    private String tags;

    @NotBlank(message = "Business open time cannot be empty")
    private String startTime;

    @NotBlank(message = "Business close time cannot be empty")
    private String endTime;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Seller id cannot be empty")
    private Integer sellerId;

    @NotBlank(message = "icon cannot be empty")
    private String iconUrl;
}
