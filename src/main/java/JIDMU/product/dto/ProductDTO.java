package JIDMU.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private String price;
    private String category;
    private int stock;
}
