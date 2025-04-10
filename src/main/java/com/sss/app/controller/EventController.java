package com.sss.app.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sss.app.domain.Event;
import com.sss.app.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService service;

	//	//行事一覧をカレンダーに表示
	//	@GetMapping
	//	public String list(Model model) throws Exception {
	//		model.addAttribute("events", service.getEventList());
	//		return "events/listEvent";
	//	}
	// 行事一覧をカレンダーに表示

	@GetMapping
	public String list(Model model) throws Exception {
		List<Event> events = service.getEventList();
		model.addAttribute("events", events); // 既存のイベントデータをそのまま渡す
		return "events/listEvent"; // カレンダー表示用のビューを返す
	}

	// イベントデータをJSON形式で返す
	@GetMapping("/json")
	@ResponseBody
	public List<Map<String, Object>> getEventJson() throws Exception {
		List<Event> events = service.getEventList();
		List<Map<String, Object>> eventList = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

		for (Event event : events) {
			Map<String, Object> eventMap = new HashMap<>();
			eventMap.put("id", event.getEventId());
			eventMap.put("title", event.getEventTitle());
			eventMap.put("start", event.getEventStartDatetime().format(dtf));

			// 開始と終了の年月日が異なり、終了時刻が00:00:00の場合
			if (!event.getEventStartDatetime().toLocalDate().isEqual(event.getEventEndDatetime().toLocalDate()) &&
					event.getEventEndDatetime().getHour() == 0 &&
					event.getEventEndDatetime().getMinute() == 0 &&
					event.getEventEndDatetime().getSecond() == 0) {
				// 終了日時に1日加算
				eventMap.put("end", event.getEventEndDatetime().plusDays(1).format(dtf));
			} else {
				// 他の場合は終了日時そのまま
				eventMap.put("end", event.getEventEndDatetime().format(dtf));
			}

			eventMap.put("target", event.getEventTarget());
			eventList.add(eventMap);
		}
		return eventList;
	}

	//行事詳細の表示
	@GetMapping("/detailEvent/{id}")
	public String detailEvent(@PathVariable Integer id,
			@RequestParam(name = "fragment", required = false) Boolean isFragment,
			Model model) throws Exception {
		model.addAttribute("event", service.getEventById(id));
		if (Boolean.TRUE.equals(isFragment)) {
			return "events/detailEvent :: detail"; // フラグメントのみ返す
		} else {
			return "events/detailEvent"; // ページ全体を表示
		}
	}

	//行事の追加・編集
	//追加画面遷移
	@GetMapping("/addEvent")
	public String addEventGet(Model model) throws Exception {
		model.addAttribute("event", new Event());
		return "events/addEvent";
	}

	//追加内容入力
	@PostMapping("/addEventConf")
	public String addEventConf(
			@Valid @ModelAttribute("event") Event event,
			Errors errors,
			Model model) throws Exception {
		//エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			return "events/addEvent";
		}

		//エラーなし→確認画面へ
		model.addAttribute("event", event);
		return "events/addEventConf";

	}

	//確認画面→入力画面に戻る(修正)
	@PostMapping("/addEvent")
	public String backToAddEvent(
			@ModelAttribute("event") Event event,
			Model model) throws Exception {
		model.addAttribute("event", event);
		return "/events/addEvent";
	}

	//確認画面→追加実行
	@PostMapping("/addEventDone")
	public String addEventPost(Event event, Model model) throws Exception {
		model.addAttribute("event", event);
		service.addEvent(event);
		return "events/addEventDone";
	}

	//編集画面表示
	@GetMapping("/editEvent/{id}")
	public String editEventGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("event", service.getEventById(id));
		return "events/editEvent";
	}

	//編集内容入力
	@PostMapping("/editEventConf/{id}")
	public String editEventConf(
			@PathVariable Integer id,
			@Valid @ModelAttribute("event") Event event,
			Errors errors,
			Model model) throws Exception {
		//エラーあり→フォーム再表示
		if (errors.hasErrors()) {
			model.addAttribute("event", event);
			return "events/editEvent";
		}

		//エラーなし→確認画面へ
		event.setEventId(id);
		model.addAttribute("event", event);
		return "events/editEventConf";

	}

	//確認画面→入力画面に戻る(修正)
	@PostMapping("/editEvent/{id}")
	public String backToEditSaveEvent(
			@ModelAttribute("event") Event event,
			Model model) throws Exception {
		model.addAttribute("event", event);
		return "/events/editEvent";
	}

	//確認画面→追加実行
	@PostMapping("/editEventDone")
	public String editEventPost(
			Event event,
			Model model) throws Exception {
		model.addAttribute("event", event);
		service.editEvent(event);
		return "events/editEventDone";
	}

	//行事の削除
	//削除対象確認
	@GetMapping("/deleteEventConf/{id}")
	public String deleteEventConf(@PathVariable Integer id, Model model)
			throws Exception {
		model.addAttribute("event", service.getEventById(id));
		return "events/deleteEventConf";
	}

	//削除実行
	@PostMapping("/deleteEventDone/{id}")
	public String deleteEvent(@PathVariable Integer id) throws Exception {
		service.deleteEvent(id);
		return "events/deleteEventDone";
	}

}
