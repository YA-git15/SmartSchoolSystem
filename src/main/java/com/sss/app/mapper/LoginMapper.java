package com.sss.app.mapper;

import com.sss.app.domain.Login;

public interface LoginMapper {

	Login selectByEmailTeacher(String email);

	Login selectByEmailGuardian(String email);

}
