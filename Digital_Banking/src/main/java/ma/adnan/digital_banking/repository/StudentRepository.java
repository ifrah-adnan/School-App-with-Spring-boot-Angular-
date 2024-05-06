package ma.adnan.digital_banking.repository;

import ma.adnan.digital_banking.entite.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
public interface StudentRepository extends JpaRepository<Student,String> {

Student findByCode(String code);
List<Student> findByProgramId(String programId);

}
