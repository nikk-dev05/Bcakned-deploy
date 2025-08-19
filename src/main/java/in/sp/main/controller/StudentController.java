package in.sp.main.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.Entity.Student;

import in.sp.main.services.Jwtservice;
import in.sp.main.services.Studentservice;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private Studentservice studentservice;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private Jwtservice jwtservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> create_student(@RequestBody Student student) {
		if(student.getUsername().isBlank()||student.getUsername().isEmpty()&&student.getEmail().isBlank()||student.
				getEmail().isEmpty()||student.getPassword().isBlank()||student.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("please fill all the fields");
		}
		
		
		Student stu=studentservice.addStudent(student);
		
		
		       
		return  ResponseEntity.status(HttpStatus.CREATED).body("user added suceessfully");
	}
	@PostMapping("/login")
	public String verify_user(@RequestBody Student student) {
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtservice.generateToken(student.getUsername());
		}
		else {
			throw new UsernameNotFoundException("Invalid credentials ");
		}
		
	}

}
