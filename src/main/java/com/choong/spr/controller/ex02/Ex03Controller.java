package com.choong.spr.controller.ex02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choong.spr.domain.ex02.Book;

@RequestMapping("ex03")
//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
public class Ex03Controller {

	@RequestMapping("sub01")
	public String method01() {
		return "string data";
	}
	
	@RequestMapping("sub02")
	public Book method02() {
		Book b = new Book();
		b.setTitle("soccer");
		b.setWriter("jimin");
		
		return b;
	}
	
	@RequestMapping("sub03")
	public String method03() {
		System.out.println("ex03/sub03 일함!!!");
		
		return "hello ajax";
	}
	
	@RequestMapping("sub04")
	public void method04() {
		System.out.println("ex03/sub04 일함@@");
		
		
	}
}
