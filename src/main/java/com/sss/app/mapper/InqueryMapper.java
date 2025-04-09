package com.sss.app.mapper;

import java.util.List;

import com.sss.app.domain.Inquery;

public interface InqueryMapper {
	
	List<Inquery> selectInqueries() throws Exception;
	Inquery selectInqueryById(Integer inqueryId) throws Exception;
	void insert(Inquery inquery) throws Exception;
	void update(Inquery inquery) throws Exception;
	void delete(Integer inqueryId) throws Exception;

}
