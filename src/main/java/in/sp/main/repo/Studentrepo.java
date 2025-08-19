package in.sp.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.Entity.Student;

@Repository
public interface Studentrepo extends JpaRepository<Student, Integer> {
	Optional<Student> findByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);

}
