package com.sss.app.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.domain.Inquery;
import com.sss.app.mapper.InqueryMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class InqueryServiceImpl implements InqueryService {
	
	private final InqueryMapper inqueryMapper;
	
	@Override
	public List<Inquery> getInqueryList() throws Exception{
		return inqueryMapper.selectInqueries();
	}
	
	@Override
	public List<Inquery> filterInqueriesByNoticeId() throws Exception{
		return inqueryMapper.selectInqueriesByNoticeId();
	}
    
	@Override
	public List<Inquery> filterInqueriesByEventId() throws Exception{
		return inqueryMapper.selectInqueriesByEventId();
	}
	
	@Override
	public Map<String, List<Inquery>> groupedInqueriesByNoticeTitle() throws Exception{
	    List<Inquery> inqueries = inqueryMapper.selectInqueriesByNoticeId(); // noticeIdで絞ったリスト
	    return inqueries.stream()
	        .collect(Collectors.groupingBy(Inquery::getNoticeTitle)); // または getNotice().getTitle() など
	}
	
	@Override
	public Map<String, List<Inquery>> groupedInqueriesByEventTitle() throws Exception{
	    List<Inquery> inqueries = inqueryMapper.selectInqueriesByEventId(); // eventIdで絞ったリスト
	    return inqueries.stream()
	        .collect(Collectors.groupingBy(Inquery::getEventTitle)); // または getEvent().getTitle()
	}
	
	@Override
	public void addInquery(Inquery inquery) throws Exception{
		inqueryMapper.insert(inquery);
	}

	@Override
	public void editInquery(Inquery inquery) throws Exception{
		inqueryMapper.update(inquery);
	}

	@Override
	public void deleteInquery(Integer inqueryId) throws Exception{
		inqueryMapper.delete(inqueryId);
	}


}
