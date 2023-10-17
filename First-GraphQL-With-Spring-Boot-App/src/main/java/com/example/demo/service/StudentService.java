package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Subject;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.CreateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);
        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());
        address = addressRepository.save(address);
        student.setAddress(address);
        student = studentRepository.save(student);
        List<Subject> subjectsList = new ArrayList<Subject>();
        if (createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);
                subjectsList.add(subject);
            }
            subjectRepository.saveAll(subjectsList);
        }
        student.setLearningSubjects(subjectsList);
        return student;
    }

}
