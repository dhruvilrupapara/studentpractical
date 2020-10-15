package com.example.Student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.Student.Model.Student;


@Repository
@EnableJpaRepositories
public interface StudentRepository  extends JpaRepository<Student, Long>{

	Student findOneById(Long id);

}
