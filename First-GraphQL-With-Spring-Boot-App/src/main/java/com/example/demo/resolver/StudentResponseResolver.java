package com.example.demo.resolver;

//import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.enums.SubjectNameFilter;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.SubjectResponse;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {

    // method should be public, we should not invoke it manualy!!

    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, SubjectNameFilter subjectNameFilter) {
        List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();
        Student student = studentResponse.getStudent();
        if (student.getLearningSubjects() != null) {
            for (Subject subject : student.getLearningSubjects()) {
                if (subjectNameFilter.name().equalsIgnoreCase(SubjectNameFilter.ALL.name())) {
                    learningSubjects.add(new SubjectResponse(subject));
                } else if (subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName())) {
                    learningSubjects.add(new SubjectResponse(subject));
                }
            }
        }
        return learningSubjects;
    }

    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFullName() + " " + studentResponse.getLastName();
    }

}
