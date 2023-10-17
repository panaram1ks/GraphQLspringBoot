package com.example.demo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private StudentService studentService;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        return new StudentResponse(studentService.createStudent(createStudentRequest));
    }

}
