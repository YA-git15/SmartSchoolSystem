package com.sss.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sss.app.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final NoticeService service;

	//HOME画面表示
	@GetMapping("/home")
	public String showHomeAndNoticeList(Model model) throws Exception{
		model.addAttribute("notices", service.getLatestNotices());
		return "home";
	}

	//ログアウト
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//セッションを破棄し、アカウント種別選択画面へ
		session.invalidate();
		return "logout";
	}

}
