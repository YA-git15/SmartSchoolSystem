package com.sss.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Login {
	
	private Integer loginId;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

}
