package com.sss.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Notice {

	private Integer noticeId;

	@NotBlank
	@Size(max = 45)
	private String noticeTitle;

	private Integer eventId;

	@NotBlank
	@Size(max = 300)
	private String noticeDetail;

	private LocalDateTime noticeDate;

	private LocalDateTime lastUpdate;
	
	private String updateStatus;

}
