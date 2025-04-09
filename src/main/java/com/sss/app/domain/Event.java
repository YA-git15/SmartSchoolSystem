package com.sss.app.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Event {

	private Integer eventId;

	@NotBlank
	private String eventTitle;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
	private LocalDateTime eventStartDatetime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
	private LocalDateTime eventEndDatetime;
	
	private String eventPlace;
	
	@NotBlank
	private String eventTarget;

	private String eventDetail;

}
