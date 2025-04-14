package com.sss.app.mapper;

import java.util.List;
import java.util.Map;

public interface UtilMapper {
	

	List<String> fetchTargetNames() throws Exception;
	List<Map<String, Object>> fetchEventTitles() throws Exception;
	

}
