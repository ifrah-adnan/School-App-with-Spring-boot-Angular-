package ma.adnan.digital_banking;

import ma.adnan.digital_banking.entite.Payment;
import ma.adnan.digital_banking.entite.PaymentStatus;
import ma.adnan.digital_banking.entite.PaymentType;
import ma.adnan.digital_banking.entite.Student;
import ma.adnan.digital_banking.repository.PaymentRepository;
import ma.adnan.digital_banking.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner start(StudentRepository studentRepository, PaymentRepository paymentRepository){

        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("adnan").lastName("ifrah").code("9876543").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("soumia").lastName("tamrani").code("1234455").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("tst").lastName("issam").code("0977").programId("sdia").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("ayoub").lastName("khalid").code("876").programId("DS").build());
PaymentType[]paymentTypes=PaymentType.values();
            Random random=new Random();

            studentRepository.findAll().forEach(e->{
    for (int i=0;i<10;i++){
        int index= random.nextInt(paymentTypes.length);
        paymentRepository.save(Payment.builder().amount(1000/Math.random()+20000).type(paymentTypes[index]).status(PaymentStatus.CREATED).date(LocalDate.now()).student(e).build());
    }

});
        };
    }

}
