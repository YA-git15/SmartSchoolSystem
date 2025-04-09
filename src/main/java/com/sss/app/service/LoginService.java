package com.sss.app.service;

public interface LoginService {

	boolean isCorrectEmailAndPasswordTeacher(String email, String password) throws Exception;

	boolean isCorrectEmailAndPasswordGuardian(String email, String password) throws Exception;

}
