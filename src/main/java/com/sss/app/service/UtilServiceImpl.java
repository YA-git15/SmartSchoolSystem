package com.sss.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.mapper.UtilMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UtilServiceImpl implements UtilService {
	
	private final UtilMapper utilmapper;

	@Override
	public List<String> getTargetNames() throws Exception {
		return utilmapper.fetchTargetNames();
	}

}
