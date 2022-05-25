package com.choong.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
