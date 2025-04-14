package com.sss.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.mapper.UtilMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UtilServiceImpl implements UtilService {

	private final UtilMapper utilMapper;

	@Override
	public List<String> getTargetNames() throws Exception {
		return utilMapper.fetchTargetNames();
	}

	@Override
	public List<Map<String, Object>> getEventTitles() throws Exception {
		List<Map<String, Object>> eventTitles = utilMapper.fetchEventTitles();
		DateTimeFormatter dtfIn = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter dtfOut = DateTimeFormatter.ofPattern("MM.dd");

		List<Map<String, Object>> fmtEventTitles = new ArrayList<>();

		for (Map<String, Object> event : eventTitles) {
			try {
				String datePart = event.get("event_start_datetime").toString();
				String eventTitlePart = event.get("event_title").toString();

				LocalDateTime startDateTime = LocalDateTime.parse(datePart, dtfIn);
				String startDate = startDateTime.format(dtfOut);

				Map<String, Object> formattedEventMap = new HashMap<>();
				formattedEventMap.put("formattedTitle", startDate + " " + eventTitlePart);
				formattedEventMap.put("eventId", event.get("event_id").toString());

				fmtEventTitles.add(formattedEventMap);
			} catch (Exception e) {
				System.err.println("Error processing event: " + event);
				e.printStackTrace();
			}
		}

		return fmtEventTitles;
	}
	
}
