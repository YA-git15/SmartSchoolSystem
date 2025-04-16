package com.sss.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sss.app.domain.Inquery;
import com.sss.app.service.InqueryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/inqueries")
@RequiredArgsConstructor
public class InqueryController {

	private final InqueryService service;

	//1-1.お知らせに対する問い合わせ一覧の表示
	@GetMapping
	public String noticeInqueryAndEventList(Model model) throws Exception {
		Map<String, List<Inquery>> groupedNoticeInqueries = service.groupedInqueriesByNoticeTitle();
		Map<String, List<Inquery>> groupedEventInqueries = service.groupedInqueriesByEventTitle();

		model.addAttribute("groupedNoticeInqueries", groupedNoticeInqueries);
		model.addAttribute("groupedEventInqueries", groupedEventInqueries);
		return "inqueries/listInquery";
	}

	//3.問い合わせの新規作成
	//3-1.新規作成画面遷移
	@GetMapping("/addInquery")
	public String addInqueryGet(
			@RequestParam("id") Integer noticeId,
			@RequestParam("title") String noticeTitle,
			Model model) throws Exception {	
		Inquery inquery = new Inquery();
		inquery.setNoticeId(noticeId);
		inquery.setNoticeTitle(noticeTitle);
		model.addAttribute("inquery", inquery);
		return "inqueries/addInquery";
	}

	//3-2.内容入力
	@PostMapping("/addInqueryConf")
	public String addInqueryConf(
			@Valid @ModelAttribute("inquery") Inquery inquery,
			Errors errors,
			Model model) throws Exception {
		//3-2-1.エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			return "inqueries/addInquery";

		}
		//3-2-2.エラーなし→確認画面へ
		model.addAttribute("inquery", inquery);
		return "inqueries/addInqueryConf";
	}

	//3-3.確認画面→入力画面に戻る(修正)
	@PostMapping("/addInquery")
	public String backToAddInquery(
			@ModelAttribute("inquery") Inquery inquery,
			Model model) throws Exception {
		model.addAttribute("inquery", inquery);
		return "inqueries/addInquery";
	}

	//3-4.確認画面→追加実行
	@PostMapping("/addInqueryDone")
	public String addInqueryPost(Inquery inquery, Model model) throws Exception {
		LocalDateTime currentDate = LocalDateTime.now();
		inquery.setInqueryDate(currentDate);
		model.addAttribute("inquery", inquery);
		service.addInquery(inquery);
		return "inqueries/addInqueryDone";
	}

	//4.問い合わせの編集
	//4-1.編集画面遷移
	//	@GetMapping("/editInquery/{id}")
	//	public String editInqueryGet(@PathVariable Integer id, Model model) throws Exception {
	//		model.addAttribute("inquery", service.getInqueryById(id));
	//		return "inqueries/addInquery";
	//
	//	}

	//4-2.編集内容入力
	@PostMapping("/editInqueryConf/{id}")
	public String editInqueryConf(
			@PathVariable Integer id,
			@Valid @ModelAttribute("inquery") Inquery inquery,
			Errors errors,
			Model model) throws Exception {
		//4-2-1.エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			return "inqueries/editInquery";
		}

		//4-2-2.エラーなし→確認画面へ
		model.addAttribute("inquery", inquery);
		return "inqueries/editInqueryConf";

	}

	//4-3.確認画面→入力画面に戻る(修正)
	@PostMapping("/editInquery/{id}")
	public String backToEditInquery(
			@ModelAttribute("inquery") Inquery inquery,
			Model model) throws Exception {
		model.addAttribute("inquery", inquery);
		return "inqueries/editInquery";
	}

	//4-4.確認画面→追加実行
	@PostMapping("/editInqueryDone")
	public String editInqueryPost(
			Inquery inquery,
			Model model) throws Exception {
		model.addAttribute("inquery", inquery);
		service.editInquery(inquery);
		return "inqueries/editInqueryDone";
	}

	//5.問い合わせの削除	
	//5-1.削除対象確認
	//	@GetMapping("/deleteInqueryConf/{id}")
	//	public String deleteInqueryConf(@PathVariable Integer id, Model model)
	//			throws Exception {
	//		model.addAttribute("inquery", service.getInqueryById(id));
	//		return "inqueries/deleteInqueryConf";
	//	}

	//5-2.削除実行
	@PostMapping("/deleteInqueryDone/{id}")
	public String deleteEvent(@PathVariable Integer id) throws Exception {
		service.deleteInquery(id);
		return "inqueries/deleteInqueryDone";
	}

	//6-1.回答入力
	@GetMapping("/addAnswer/{id}")
	public String addAnswer(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("inquery", service.getInqueryListById(id));
		return "inqueries/addAnswer";
	}

	//6-2.回答内容確認
	@PostMapping("/addAnswerConf/{id}")
	public String addAnswerConf(
			@ModelAttribute("inquery") Inquery inquery,
			@RequestParam("inqueryId") Integer inqueryId,
			Model model) throws Exception {

		LocalDateTime currentDate = LocalDateTime.now();
		inquery.setAnswerDate(currentDate);
		model.addAttribute("inquery", inquery);

		return "inqueries/addAnswerConf";
	}

	//6-3.確認画面→入力画面に戻る(修正)
	@PostMapping("/addAnswer/{id}")
	public String backToAddAnswer(
			@ModelAttribute("inquery") Inquery inquery,
			Model model) throws Exception {
		model.addAttribute("inquery", inquery);
		return "inqueries/addAnswer";
	}

	//6-4.確認画面→追加実行
	@PostMapping("/addAnswerDone")
	public String addAnswerPost(
			Inquery inquery,
			Model model) throws Exception {
		model.addAttribute("inquery", inquery);
		service.editInquery(inquery);
		return "inqueries/addAnswerDone";
	}

//	//4.問い合わせの編集
//	//7-1. 編集画面遷移
//	@GetMapping("/editAnswer/{id}")
//	public String editAnswerGet(@PathVariable Integer id, Model model) throws Exception {
//				model.addAttribute("inquery", service.getInqueryListById(id));
//				return "inqueries/addInquery";		
//			}
//
//	//7-2.編集内容入力
//	@PostMapping("/editAnswerConf/{id}")
//	public String editAnswerConf(
//			@PathVariable Integer id,
//			@ModelAttribute("inquery") Inquery inquery,
//			Model model) throws Exception {
//		model.addAttribute("inquery", inquery);
//		return "inqueries/addInqueryConf";
//
//	}
//
//	//7-3.確認画面→入力画面に戻る(修正)
//	@PostMapping("/editAnswer/{id}")
//	public String backToEditAnswer(
//			@ModelAttribute("inquery") Inquery inquery,
//			Model model) throws Exception {
//		model.addAttribute("inquery", inquery);
//		return "inqueries/editInquery";
//	}
//
//	//7-4.確認画面→追加実行
//	@PostMapping("/editAnswerDone")
//	public String editAnswerPost(
//			Inquery inquery,
//			Model model) throws Exception {
//		LocalDateTime currentDate = LocalDateTime.now();
//		inquery.setAnswerDate(currentDate);
//		model.addAttribute("inquery", inquery);
//		service.editInquery(inquery);
//		return "inqueries/editInqueryDone";
//	}

}
