package com.sss.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Inquery {
	
	private Integer inqueryId;
	
	private String inqueryTitle;
	
	@NotBlank
	private String inqueryDetail;
	
	private String answer;
	
	@NotBlank
	private String status;
	
	private LocalDateTime inqueryDate;
	
	private LocalDateTime answerDate;
	
	private Integer noticeId;
	
	private Integer eventId;

}
