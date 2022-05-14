package JIDMU.product.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ReviewRequest {
    private UUID productId;
    private String username;
    private String reviewText;
}
