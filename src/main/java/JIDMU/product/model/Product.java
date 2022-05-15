package JIDMU.product.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private UUID id;
    private String productName;
    private String imgLink;
    private String price;
    private String category;
    private int stock;
    private Instant createdAt;

}
