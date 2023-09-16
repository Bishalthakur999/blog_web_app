package com.bishal.blog.payloads;



import java.util.HashSet;
import java.util.Set;

import com.bishal.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min =4, message="user name must be of 4 character")
	private String name;
	@NotEmpty
	@Email(message ="email address is not valid")
	private String email;
	@NotEmpty
	@Size(min =3 ,max =10,message="password must be min of 3 char and maximum of 10 chars")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

}
