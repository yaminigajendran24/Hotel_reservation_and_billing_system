package com.spring.wmh.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

	@NotNull(message = "username required")
	@Size(min = 3, message = "username should not empty")
	private String userName;
	@NotNull(message = "password required")
	@Size(min = 3, message = "password should not empty not min 3")
	private String password;
}
