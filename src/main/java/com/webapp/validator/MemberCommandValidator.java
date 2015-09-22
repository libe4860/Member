package com.webapp.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webapp.command.MemberCommand;

public class MemberCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberCommand member = (MemberCommand) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "required");
		
		/*
		 * Email Length Check 64 이하  
		 */
		//matches : 정규표현식의 시작과 끝이 들어가 있음
		if(member.getEmail().matches("^.{65,}$")){
			errors.rejectValue("email", "length", new Object[]{64}, null);
		}
		
		/*
		 * Email Pattern Check xxx@yyy.co.kr  
		 */
//		.을 문자열로 표현하기 위해 앞에 \\를 붙여준다. | 한 자리 이상 연결 + 
//		String pattern = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+\\.[A-Za-z]{2,3}$"; //xxx@yyy.co.kr 적용 표현
		//xxx_1@yyy.com, xxx_1@yyy.co.kr, xxx-1@yyy.com, xxx_1@yyy.co.kr (_언더바, -대쉬 까지 허용 가능)
		String pattern = "^[A-Za-z0-9_\\-]+@([A-Za-z0-9]+\\.){1,2}[A-Za-z]{2,3}$"; 
		if(!member.getEmail().matches(pattern)){
			errors.rejectValue("email", "pattern");
		}
		/*
		 * Password Length Check 8자리 이상  
		 */
		if(!member.getPassword().matches("^.{8,64}$")){
			errors.rejectValue("password", "length", new Object[]{8,64}, null);
		}
		
		/*
		 * Password Pattern Check 64 이하  
		 * qw12er34 ==> valid
		 * qwer ==> invalid
		 * pwer1234 ==> invalid
		 * 1234 ==> invalid
		 * 
		 * 1. 영문 & 숫자 
		 * 2. 영문 연속3자리 이상 X
		 * 3. 숫자 연속3자리 이상 X
		 */
		if (!Pattern.compile("[A-Za-z0-9]")
				   .matcher(member.getPassword())
				   .find())
			errors.rejectValue("password", "pattern");
		
		else if (Pattern.compile("[A-Za-z]{3,}")
				   .matcher(member.getPassword())
				   .find() && !errors.hasFieldErrors("password"))
			errors.rejectValue("password", "pattern");
		
		else if (Pattern.compile("[0-9]{3,}")
				   .matcher(member.getPassword())
				   .find() && !errors.hasFieldErrors("password"))
			errors.rejectValue("password", "pattern");
		
		else if (Pattern.compile("[ㄱ-힣]")
				   .matcher(member.getPassword())
				   .find())
			errors.rejectValue("password", "pattern");
		
		
		/*
		 * Name Length Check 64 이하  
		 */
		if(member.getName().matches("^.{13,}$")){
			errors.rejectValue("name", "length", new Object[]{12}, null);
		}
		
		/*
		 * Comment Length Check 255자 이상 입력 금지   
		 */
		if(member.getComment().length() > 255){
			errors.rejectValue("comment", "length", new Object[]{255}, null);
		}
	}

}
