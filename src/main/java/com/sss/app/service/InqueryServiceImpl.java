package com.sss.app.service;

import java.util.List;

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
	public Inquery getInqueryById(Integer inqueryId) throws Exception{
		return inqueryMapper.selectInqueryById(inqueryId);
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
