package com.sss.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Guardian {

	private Integer guardianId;

	@NotBlank
	@Size(max = 20)
	private String name;

	@NotBlank
	@Size(max = 45)
	private String email;

}
