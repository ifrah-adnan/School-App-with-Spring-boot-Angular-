package ma.adnan.digital_banking.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.adnan.digital_banking.entite.Payment;
import ma.adnan.digital_banking.entite.PaymentStatus;
import ma.adnan.digital_banking.entite.PaymentType;
import ma.adnan.digital_banking.entite.Student;
import ma.adnan.digital_banking.repository.PaymentRepository;
import ma.adnan.digital_banking.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Service
@Transactional
@AllArgsConstructor
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public Payment savePayment(MultipartFile file, LocalDate date, double amount, String studentCode, PaymentType type) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName + ".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder().date(date).type(type).student(student).amount(amount).file(filePath.toUri().toString()).status(PaymentStatus.CREATED).type(type).build();
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(PaymentStatus status,  Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
    public byte[] getPaymentsFile( Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
}
