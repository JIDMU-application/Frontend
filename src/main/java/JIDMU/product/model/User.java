package JIDMU.product.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

import JIDMU.product.config.AttributeEncryptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @Convert(converter = AttributeEncryptor.class)
    private String username;
    private String password;
    @Convert(converter = AttributeEncryptor.class)
    private String firstName;
    @Convert(converter = AttributeEncryptor.class)
    private String lastName;
    private Instant createdAt;
    private String email;
    private String role;
}
