package com.example.Student.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.Model.Student;
import com.example.Student.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	public void saveOrUpdate(Student student) {

		studentRepository.save(student);

	}

	


	public void deleteById(Long id) {

		
		studentRepository.deleteById(id);

	}

	public Student findbyId(Long i) {

		return studentRepository.findOneById(i);
		
	}




	public List<Student> findall() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

}
