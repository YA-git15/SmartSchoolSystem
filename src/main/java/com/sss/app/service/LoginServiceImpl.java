package com.sss.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.app.domain.Login;
import com.sss.app.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

	private final LoginMapper mapper;
	
	@Override
	public boolean isCorrectEmailAndPasswordTeacher(String email, String password) throws Exception{
		Login login = mapper.selectByEmailTeacher(email);
		
		//ログインemailが正しいかチェック
		//⇒emailが正しくなければ、管理者データは取得されない
		if(login==null) {
			return false;
		}
		
		//パスワードが正しいかチェック
		if(!BCrypt.checkpw(password, login.getPassword())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean isCorrectEmailAndPasswordGuardian(String email, String password) throws Exception{
		Login login = mapper.selectByEmailGuardian(email);
		
		//ログインemailが正しいかチェック
		//⇒emailが正しくなければ、管理者データは取得されない
		if(login==null) {
			return false;
		}
		
		//パスワードが正しいかチェック
		if(!BCrypt.checkpw(password, login.getPassword())) {
			return false;
		}
		
		return true;
	}
		
}
