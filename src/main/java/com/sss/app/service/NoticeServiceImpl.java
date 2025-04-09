package com.sss.app.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.domain.Notice;
import com.sss.app.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper noticeMapper;

	@Override
	public List<Notice> getNoticeList() throws Exception {
		return noticeMapper.selectNotices();
	}

	@Override
	public List<Notice> getLatestNotices() throws Exception {
		// DBから最新の5件のNoticeを取得
		List<Notice> notices = noticeMapper.selectLatestNotices();
		LocalDateTime now = LocalDateTime.now();

		for (Notice notice : notices) {
			// 1. noticeDateとlastUpdateが同じでない、かつ、lastUpdateが今日から7日以内ならUPDATEを格納
			if (!notice.getNoticeDate().isEqual(notice.getLastUpdate()) &&
					Duration.between(notice.getLastUpdate(), now).toDays() >= 0 &&
					Duration.between(notice.getLastUpdate(), now).toDays() <= 7) {
				notice.setUpdateStatus("UPDATE");
			}
			// 2. 上記以外でlastUpdateが今日から7日以内ならNEWを格納
			else if (Duration.between(notice.getLastUpdate(), now).toDays() >= 0 &&
					Duration.between(notice.getLastUpdate(), now).toDays() <= 7) {
				notice.setUpdateStatus("!NEW!");
			}
		}
		return notices;
	}

	@Override
	public Notice getNoticeById(Integer noticeId) throws Exception {
		return noticeMapper.selectNoticeById(noticeId);
	}

	@Override
	public void addNotice(Notice notice) throws Exception {
		noticeMapper.insert(notice);
	}

	@Override
	public void editNotice(Notice notice) throws Exception {
		noticeMapper.update(notice);
	}

	@Override
	public void deleteNotice(Integer noticeId) throws Exception {
		noticeMapper.delete(noticeId);
	}

}
