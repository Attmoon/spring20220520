package com.choong.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("signup")
	public void singupForm() {
		
	}
	
	@PostMapping("signup")
	public void signupProcess(MemberDto member) {
		boolean success = service.addMemeber(member);
		
		if (success) {
			
		} else {
			
		}
	}
	
	@GetMapping(path = "check", params = "id") // 경로 = check, requestparameter에 id가 붙어서옴
	@ResponseBody
	public String idCheck(String id) {
		
		boolean exist = service.hasMemeberId(id);
		
		if (exist) {
			return "notOk";
		} else {
			return "ok";
		}
	}
	
	@GetMapping(path = "check", params = "email") // 경로 = check, requestparameter에 id가 붙어서옴
	@ResponseBody
	public String emailCheck(String email) {
		
		boolean exist = service.hasMemeberEmail(email);
		
		if (exist) {
			return "notOk";
		} else {
			return "ok";
		}
	}
	
	@GetMapping(path = "check", params = "nickName") // 경로 = check, requestparameter에 id가 붙어서옴
	@ResponseBody
	public String nickNameCheck(String nickName) {
		
		boolean exist = service.hasMemeberNickName(nickName);
		
		if (exist) {
			return "notOk";
		} else {
			return "ok";
		}
	}
}
