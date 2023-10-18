package com.example.demo.query;

import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.request.SampleRequest;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private StudentService studentService;

	public String firstQuery () {
		return "First Query";
	}
	
	public String secondQuery () {
		return "Second Query";
	}
	
	public String fullName (SampleRequest sampleRequest) {
		return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
	}

	public StudentResponse getStudent(long id){
		return new StudentResponse(studentService.getStudentById(id));
	}

}
