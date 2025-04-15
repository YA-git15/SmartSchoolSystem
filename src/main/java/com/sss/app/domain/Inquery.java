package com.sss.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

	private String noticeTitle;

	private LocalDateTime noticeDate;

	private Integer eventId;

	private String eventTitle;

	private LocalDateTime eventStartDatetime;
	
	private LocalDateTime eventEndDatetime;
	
	
	public String getFmtNoticeDate() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M月d日");
		return this.noticeDate.format(fmt);
		
	}
	
	public String getFmtEventDatePeriod() {
		LocalDate startDate = eventStartDatetime.toLocalDate();
		LocalDate endDate = eventEndDatetime.toLocalDate();
		LocalTime startTime = eventStartDatetime.toLocalTime();
		LocalTime endTime = eventEndDatetime.toLocalTime();
		
		DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("M月d日");
		DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("M月d日 HH:mm");
		DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
		
		if(eventStartDatetime.equals(eventEndDatetime) && startTime.equals(LocalTime.MIDNIGHT)) {
			return eventStartDatetime.format(dateFmt);
		}
		
		if(startDate.equals(endDate)) {
			return eventStartDatetime.format(dateTimeFmt) + "-" + endTime.format(timeFmt);
		}
		
		if(!startDate.equals(endDate) && startTime.equals(LocalTime.MIDNIGHT)) {
			return eventStartDatetime.format(dateFmt) + "-" + eventStartDatetime.format(dateFmt);
		}
		
		return eventStartDatetime.format(dateTimeFmt) + "-" + eventStartDatetime.format(dateTimeFmt);
	}
	
}
