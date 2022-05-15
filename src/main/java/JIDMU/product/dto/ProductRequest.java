package JIDMU.product.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    @NotBlank
    private String productName;

    @NotNull
    @Range(min=1)
    private Integer price;

    @NotBlank
    private String category;

    @NotNull
    @Range(min=1, max=999)
    private Integer stock;
}
