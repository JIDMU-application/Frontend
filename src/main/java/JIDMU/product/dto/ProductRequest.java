package JIDMU.product.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    @NotBlank
    private String productName;

    @NotBlank
    private String price;

    @NotBlank
    private String category;

    @NotNull
    private Integer stock;
}
