package com.choong.spr.controller.ex02;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public String method04() {
		System.out.println("ex03/sub04 일함@@");
		
		return "hello";
	}
	
	@GetMapping("sub05")
	public String method05() {
		System.out.println("ex03/sub05 get 방식 일함!@!@");
		
		return null;
	}
	
	@PostMapping("sub06")
	public String method06() {
		System.out.println("ex03/sub06 post 방식 일함~~~~");
		
		return null;
	}
	
	@DeleteMapping("sub07")
	public String method07() {
		System.out.println("ex03/sub07 delete 방식 일함~!!!~!");
		
		return null;
	}
	
	@PutMapping("sub08")
	public String method08() {
		System.out.println("ex03/sub08 put 방식 일함%%%%%%%");
		
		return null;
	}
	
}
