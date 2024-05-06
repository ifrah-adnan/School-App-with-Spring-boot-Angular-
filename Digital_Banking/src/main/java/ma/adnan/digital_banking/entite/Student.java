package ma.adnan.digital_banking.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String code;
    private String programId;
    private String photo;
}
