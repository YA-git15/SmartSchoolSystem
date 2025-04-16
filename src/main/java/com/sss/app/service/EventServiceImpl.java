package com.sss.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.domain.Event;
import com.sss.app.mapper.EventMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper eventMapper;

	@Override
	public List<Event> getEventList() throws Exception {
		return eventMapper.selectEvents();
	}

	@Override
	public Event getEventById(Integer eventId) throws Exception {
		return eventMapper.selectEventById(eventId);
	}
	
	@Override
	public List<Event> getEventListByThisMonth() throws Exception{
		return eventMapper.selectEventsByThisMonth();
	}

	@Override
	public void addEvent(Event event) throws Exception {
		eventMapper.insert(event);
	}

	@Override
	public void editEvent(Event event) throws Exception {
		eventMapper.update(event);
	}

	@Override
	public void deleteEvent(Integer eventId) throws Exception {
		eventMapper.delete(eventId);
	}

}
