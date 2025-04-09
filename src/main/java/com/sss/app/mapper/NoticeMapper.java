package com.sss.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sss.app.domain.Notice;

@Mapper
public interface NoticeMapper {
	
	List<Notice> selectNotices() throws Exception;
	List<Notice> selectLatestNotices() throws Exception;
	Notice selectNoticeById(Integer noticeId) throws Exception;
	void insert(Notice notice) throws Exception;
	void update(Notice notice) throws Exception;
	void delete(Integer noticeId) throws Exception;

}
