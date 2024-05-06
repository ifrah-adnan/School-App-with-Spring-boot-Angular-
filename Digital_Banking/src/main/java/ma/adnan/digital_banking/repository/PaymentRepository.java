package ma.adnan.digital_banking.repository;

import ma.adnan.digital_banking.entite.Payment;
import ma.adnan.digital_banking.entite.PaymentStatus;
import ma.adnan.digital_banking.entite.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
