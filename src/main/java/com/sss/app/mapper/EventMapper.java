package com.sss.app.mapper;

import java.util.List;

import com.sss.app.domain.Event;

public interface EventMapper {
	
	List<Event> selectEvents() throws Exception;
	Event selectEventById(Integer eventId) throws Exception;
	void insert(Event event) throws Exception;
	void update(Event event) throws Exception;
	void delete(Integer eventId) throws Exception;

}
