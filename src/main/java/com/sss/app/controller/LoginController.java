package com.sss.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sss.app.domain.Login;
import com.sss.app.service.LoginServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

	private final LoginServiceImpl service;

	//アカウント種別選択画面
	@GetMapping
	public String selectLoginAccount() {
		return "login/selectLogin";
	}

	//教師アカウント遷移
	@GetMapping("/teacherLogin")
	public String teacherLogin(Model model) {
		model.addAttribute("login", new Login());
		return "login/teacherLogin";
	}

	//教師ログイン画面
	@PostMapping("/teacherLogin")
	public String loginTeacher(
			@Valid Login login,
			Errors errors,
			HttpSession session,
			Model model) throws Exception{
		//入力に不備がある
		if(errors.hasErrors()) {
			return "login/teacherLogin";
		}
			
		//emailかパスワードが正しくない
		if(!service.isCorrectEmailAndPasswordTeacher(login.getEmail(), login.getPassword())) {
			errors.rejectValue("email", "error.incorrect_email_password");
			return "login/teacherLogin";
		}
		
		//正しいemailとパスワード
		//⇒ セッションにemailを格納しHOME画面へ
		session.setAttribute("accountType", "teacher");
		return "redirect:/home";
	}

	//保護者アカウント遷移
		@GetMapping("/guardianLogin")
		public String guardianLogin(Model model) {
			model.addAttribute("login", new Login());
			return "login/guardianLogin";
		}
	
	//保護者ログイン画面
	@PostMapping("/guardianLogin")
	public String loginGuardian(
			@Valid Login login,
			Errors errors,
			HttpSession session,
			Model model) throws Exception{
		//入力に不備がある
		if(errors.hasErrors()) {
			return "login/guardianLogin";
		}
			
		//emailかパスワードが正しくない
		if(!service.isCorrectEmailAndPasswordGuardian(login.getEmail(), login.getPassword())) {
			errors.rejectValue("email", "error.incorrect_email_password");
			return "login/guardianLogin";
		}
		
		//正しいemailとパスワード
		//⇒ セッションにemailを格納しHOME画面へ
		session.setAttribute("accountType", "guardian");
		return "redirect:/home";
	}
	
	

}
