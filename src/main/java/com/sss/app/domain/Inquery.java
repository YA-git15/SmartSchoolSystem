package com.sss.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Inquery {
	
	private Integer inqueryId;
	
	@NotBlank
	private String inqueryDetail;
	
	private String answer;
	
	@NotBlank
	private String status;
	
	private LocalDate inqueryDate;
	
	private LocalDate answerDate;
	
	private Integer notriceId;
	
	private Integer eventId;

}
