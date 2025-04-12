package com.sss.app.service;

import java.util.List;

import com.sss.app.domain.Notice;

public interface NoticeService {
	
	List<Notice> getNoticeList() throws Exception;
	List<Notice> getLatestNotices() throws Exception;
	Notice getNoticeById(Integer noticeId) throws Exception;
	void addNotice(Notice notice) throws Exception;
	void editNotice(Notice notice) throws Exception;
	void deleteNotice(Integer noticeId) throws Exception;
	

}
