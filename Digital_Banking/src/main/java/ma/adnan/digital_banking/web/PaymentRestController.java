package ma.adnan.digital_banking.web;

import lombok.AllArgsConstructor;
import ma.adnan.digital_banking.entite.Payment;
import ma.adnan.digital_banking.entite.PaymentStatus;
import ma.adnan.digital_banking.entite.PaymentType;
import ma.adnan.digital_banking.entite.Student;
import ma.adnan.digital_banking.repository.PaymentRepository;
import ma.adnan.digital_banking.repository.StudentRepository;
import ma.adnan.digital_banking.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
    @GetMapping("/payments")
    public List<Payment> allPayement(){
        return paymentRepository.findAll();
    }
    @GetMapping("/student/{code}/payments")
    public List<Payment> paymentByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping("/paymentStatus")
    public List<Payment> paymentByStatut(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @GetMapping("/paymentType")
    public List<Payment> paymentByStatut(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping("/payment/{id}")
    public Payment payment( @PathVariable(name = "id") Long id){
        return paymentRepository.findById(id).orElse(null);
    }

    @GetMapping("/students")
    public List<Student> allStudent(){
        return studentRepository.findAll();
    }
    @GetMapping("/student/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }
    @GetMapping("/studentByProgramID")
    public List<Student> getStudentByProgramId( @RequestParam String programID){
        return studentRepository.findByProgramId(programID);
    }
    @PutMapping("/payment/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){

        return this.paymentService.updatePaymentStatus(status,id);
    }
    @PostMapping(value = "/payments",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date,double amount, String studentCode,PaymentType type) throws IOException {
      return   this.paymentService.savePayment(file,date,amount,studentCode,type);
    }
    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentsFile(@PathVariable Long paymentId) throws IOException {
       return this.paymentService.getPaymentsFile(paymentId);

    }

}
