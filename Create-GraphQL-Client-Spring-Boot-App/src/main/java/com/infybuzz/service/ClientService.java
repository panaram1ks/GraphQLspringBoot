package com.infybuzz.service;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infybuzz.response.StudentResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService {

	@Autowired
	GraphQLWebClient graphQLWebClient;

	public StudentResponse getStudent (Integer id) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("id", id);

		String queryStr = "query student($id : Int) {\n" +
				"    student(id : $id){\n" +
				"        id\n" +
				"        firstName\n" +
				"        lastName\n" +
				"        learningSubjects(subjectNameFilter: ALL) {\n" +
				"          id\n" +
				"          subjectName\n" +
				"          marksObtained\n" +
				"        }\n" +
				"          fullName\n" +
				"        }\n" +
				"}";

		GraphQLRequest request = GraphQLRequest.builder()
				.query(queryStr)
				.variables(variables)
				.build();

		GraphQLResponse response = graphQLWebClient.post(request).block();

		StudentResponse student = response.get("student", StudentResponse.class);
		return student;
	}
}
