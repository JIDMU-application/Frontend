package JIDMU.product.dto;

import lombok.Data;
import java.util.UUID;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewRequest {
    private UUID productId;


    private String username;

    @NotBlank
    private String reviewText;
}
