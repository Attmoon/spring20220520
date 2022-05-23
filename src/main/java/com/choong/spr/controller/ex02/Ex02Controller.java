package com.choong.spr.controller.ex02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choong.spr.domain.ex02.Book;

@Controller
@RequestMapping("ex02")
public class Ex02Controller {
	
	@RequestMapping("sub01")
	public String method01() {
		
		return "hello";
	}
	
	@RequestMapping("sub02")
	@ResponseBody // view가 아닌 data자체로 해석
	public String method02() {
		
		return "hello";
	}
	
	@RequestMapping("sub03")
	@ResponseBody
	public String method03() {
		
		return "{\"title\": \"java\", \"writer\": \"son\"}";
	}
	
	@RequestMapping("sub04")
	@ResponseBody
	public Book method04() {
		Book b = new Book();
		b.setTitle("spring");
		b.setWriter("son");
		
		return b;
	}
}
