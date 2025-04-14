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
		List<Notice> notices = noticeMapper.selectNotices();
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
				notice.setUpdateStatus("NEW");
			}
			// HTMLタグ除去 + 文字列省略処理
			String processedDetail = formatNoticeDetail(notice.getNoticeDetail(), 30);
			notice.setNoticeDetail(processedDetail); // 加工した結果をセット
		}
		return notices;

	}

	private String formatNoticeDetail(String input, int maxLength) {
		String strippedText = input.replaceAll("<[^>]*>", ""); // HTMLタグを除去
		return strippedText.length() > maxLength
				? strippedText.substring(0, maxLength) + "..."
				: strippedText; // 文字列省略処理
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
				notice.setUpdateStatus("NEW");
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
