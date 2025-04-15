package com.sss.app.service;

import java.util.List;
import java.util.Map;

import com.sss.app.domain.Inquery;

public interface InqueryService {

	List<Inquery> getInqueryList() throws Exception;
	Inquery getInqueryListById(Integer inqueryId) throws Exception;
	List<Inquery> filterInqueriesByNoticeId() throws Exception;
    List<Inquery> filterInqueriesByEventId() throws Exception; 
	
	Map<String, List<Inquery>> groupedInqueriesByNoticeTitle() throws Exception;

	Map<String, List<Inquery>> groupedInqueriesByEventTitle() throws Exception;

	void addInquery(Inquery inquery) throws Exception;

	void editInquery(Inquery inquery) throws Exception;

	void deleteInquery(Integer inqueryId) throws Exception;


}
