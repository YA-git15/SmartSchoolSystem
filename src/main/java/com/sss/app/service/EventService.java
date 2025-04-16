package com.sss.app.service;

import java.util.List;

import com.sss.app.domain.Event;

public interface EventService {
	
	List<Event> getEventList() throws Exception;
	Event getEventById(Integer eventId) throws Exception;
	List<Event> getEventListByThisMonth() throws Exception;
	void addEvent(Event event) throws Exception;
	void editEvent(Event event) throws Exception;
	void deleteEvent(Integer eventId) throws Exception;

}
