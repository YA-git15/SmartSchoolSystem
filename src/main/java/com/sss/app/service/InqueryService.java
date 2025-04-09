package com.sss.app.service;

import java.util.List;

import com.sss.app.domain.Inquery;

public interface InqueryService {

	List<Inquery> getInqueryList() throws Exception;

	Inquery getInqueryById(Integer inqueryId) throws Exception;

	void addInquery(Inquery inquery) throws Exception;

	void editInquery(Inquery inquery) throws Exception;

	void deleteInquery(Integer inqueryId) throws Exception;

}
