package com.sss.app.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sss.app.domain.Notice;
import com.sss.app.service.NoticeService;
import com.sss.app.service.UtilService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService service;
	private final UtilService utilService;

	//1.お知らせ一覧の表示
	@GetMapping
	public String list(Model model) throws Exception {
		model.addAttribute("notices", service.getNoticeList());
		return "notices/listNotice";
	}

	//2.お知らせ詳細の表示
	@GetMapping("/detailNotice/{id}")
	public String detail(@PathVariable Integer id, Model model)
			throws Exception {
		model.addAttribute("notice", service.getNoticeById(id));
		return "notices/detailNotice";
	}

	//3.お知らせの新規作成
	//3-1.新規作成画面遷移
	@GetMapping("/addNotice")
	public String addNoticeGet(Model model) throws Exception {
		model.addAttribute("notice", new Notice());
		model.addAttribute("dropdownValues", utilService.getTargetNames());
		model.addAttribute("dropdownTitles", utilService.getEventTitles());
		return "notices/addNotice";
	}

	//3-2.内容入力
	@PostMapping("/addNoticeConf")
	public String addNoticeConf(
			@Valid @ModelAttribute("notice") Notice notice,
			Errors errors,
			@RequestParam(value = "formattedTitle", required = false) String formattedTitle,
			@RequestParam(value = "eventId", required = false) Integer eventId,
			Model model) throws Exception {
		//3-2-1.エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			model.addAttribute("dropdownValues", utilService.getTargetNames());
			model.addAttribute("dropdownTitles", utilService.getEventTitles());
			return "notices/addNotice";
		}
		//3-2-2.エラーなし→確認画面へ
		notice.setFormattedTitle(formattedTitle);
		notice.setEventId(eventId);
		LocalDateTime currentDate = LocalDateTime.now();
		notice.setNoticeDate(currentDate);
		notice.setLastUpdate(currentDate);
		model.addAttribute("notice", notice);

		return "notices/addNoticeConf";
	}

	//3-3.確認画面→入力画面に戻る(修正)
	@PostMapping("/addNotice")
	public String backToAddNotice(
			@ModelAttribute("notice") Notice notice,
			Model model) throws Exception {
		model.addAttribute("notice", notice);
		model.addAttribute("dropdownValues", utilService.getTargetNames());
		model.addAttribute("dropdownTitles", utilService.getEventTitles());
		return "notices/addNotice";
	}

	//3-4.確認画面→追加実行
	@PostMapping("/addNoticeDone")
	public String addNoticePost(Notice notice, Model model) throws Exception {
		model.addAttribute("notice", notice);
		service.addNotice(notice);
		return "notices/addNoticeDone";
	}

	//4.お知らせの編集
	//4-1.編集画面遷移
	@GetMapping("/editNotice/{id}")
	public String editNoticeGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("notice", service.getNoticeById(id));
		return "/notices/editNotice";
	}

	//4-2.編集内容入力
	@PostMapping("/editNoticeConf/{id}")
	public String eDitNoticeConf(
			@PathVariable Integer id,
			@Valid @ModelAttribute("notice") Notice notice,
			Errors errors,
			Model model) throws Exception {
		//4-2-1.エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			return "/editNotice";
		}

		//4-2-2.エラーなし→確認画面へ
		LocalDateTime currentDate = LocalDateTime.now();
		notice.setLastUpdate(currentDate);
		model.addAttribute("notice", notice);
		return "/notices/editNoriceConf";
	}

	//4-3.確認画面→入力画面に戻る(修正)
	@PostMapping("/editNotice/{id}")
	public String backToEditNotice(
			@ModelAttribute("notice") Notice notice,
			Model model) throws Exception {
		model.addAttribute("notice", notice);
		model.addAttribute("dropdownValues", utilService.getTargetNames());
		return "notices/editNorice";
	}

	//4-4.確認画面→追加実行
	@PostMapping("/editNoticeDone")
	public String editNoticePost(
			Notice notice,
			Model model) throws Exception {
		model.addAttribute("notice", notice);
		service.editNotice(notice);
		return "notices/editNoticeDone";
	}

	//5.お知らせの削除	
	//5-1.削除対象確認
	@GetMapping("/deleteNoticeConf/{id}")
	public String deleteNoticeConf(@PathVariable Integer id, Model model)
			throws Exception {
		model.addAttribute("notice", service.getNoticeById(id));
		return "notices/deleteNoticeConf";
	}

	//5-2.削除実行
	@PostMapping("/deleteNoticeDone/{id}")
	public String deleteEvent(@PathVariable Integer id) throws Exception {
		service.deleteNotice(id);
		return "notices/deleteNoticeDone";
	}

}
