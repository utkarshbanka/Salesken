package com.salesken.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesken.exception.StudentException;
import com.salesken.exception.SubjectDetailsException;
import com.salesken.DTO.StudentDto;
import com.salesken.DTO.SubjectsDetailsDto;
import com.salesken.model.Student;
import com.salesken.model.SubjectsDetails;
import com.salesken.repository.StudentRepo;
import com.salesken.repository.SubjectDetailsRepo;

@Service
public class StudentImpl implements StudentInterface{

    @Autowired
	private StudentRepo studentRepo;
    
    @Autowired
    private SubjectDetailsRepo subjectsDetailsRepo;
    
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public Student registerStudent(StudentDto studentDto) throws StudentException {
		// TODO Auto-generated method 
		Student student = this.modelMapper.map(studentDto, Student.class);
		return studentRepo.save(student);
		 
	}

	@Override
	public List<StudentDto> getAllStudent() throws StudentException {
		// TODO Auto-generated method stub
		List<Student> list = this.studentRepo.findAll();
		List<StudentDto>  allStudent = list.stream().map((stud)-> this.modelMapper.map(list,StudentDto.class))
		.collect(Collectors.toList());
		
		return allStudent;
	}

	@Override
	public StudentDto findStudentById(Integer studentId) throws StudentException{
		// TODO Auto-generated method stub
		Student student = this.studentRepo.findById(studentId).orElseThrow(()-> new StudentException("Student not found : "+studentId));
		return this.modelMapper.map(student, StudentDto.class);
		
	}

	

}
