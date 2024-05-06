package ma.adnan.digital_banking.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.digital_banking.entite.PaymentStatus;
import ma.adnan.digital_banking.entite.PaymentType;
import ma.adnan.digital_banking.entite.Student;

import java.time.LocalDate;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class PaymentDto {


    private Long id;
    private LocalDate date;
    private  double amount;
    private PaymentType type;
    private PaymentStatus status;
    private Student student;
}
