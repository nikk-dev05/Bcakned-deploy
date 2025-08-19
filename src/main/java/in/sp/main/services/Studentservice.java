package in.sp.main.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.main.Entity.Student;
import in.sp.main.Entity.Studentinfo;
import in.sp.main.repo.Studentrepo;

@Service
public class Studentservice implements UserDetailsService{
	@Autowired
	private Studentrepo studentrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Student> stu=studentrepo.findByUsername(username);
		if(!stu.isPresent()) {
			throw new UsernameNotFoundException("user not found !!!!!!!!");
		}
	
		return  new Studentinfo(stu.get());
	}
	
	public Student addStudent(Student student) {
		 if (studentrepo.existsByUsername(student.getUsername()) || 
			        studentrepo.existsByEmail(student.getEmail())) {
			        throw new IllegalArgumentException("Email or username already exists");
			    }
		
	student.setPassword(passwordEncoder.encode(student.getPassword()));
		return studentrepo.save(student);
		
	}


}
