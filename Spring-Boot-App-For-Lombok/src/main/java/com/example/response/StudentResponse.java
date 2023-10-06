package com.example.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@Data // is equal  @Getter @Setter @ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

	@JsonIgnore
	private long id;

	@JsonProperty("first_name")
	private String firstName;

	private String lastName;

//	public StudentResponse(long id, String firstName, String lastName) {
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}

//	public StudentResponse() {
//	}
}
