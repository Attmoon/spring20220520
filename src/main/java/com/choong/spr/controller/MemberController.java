package com.choong.spr.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String signupProcess(MemberDto member, RedirectAttributes rttr) {
		boolean success = service.addMemeber(member);
		
		if (success) {
			rttr.addFlashAttribute("message", "회원가입이 완료되었습니다.");
			return "redirect:/board/list";
		} else {
			rttr.addFlashAttribute("message", "회원가입이 실패하였습니다.");
			rttr.addFlashAttribute("member", member);
			
			return "redirect:/member/signup";
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
	
	@GetMapping("list")
	// jsp (id, password, email, nickName, inserted) table로 보여주세요.
	// ORDER BY inserted DESC
	public void list(Model model) {
		List<MemberDto> list = service.listMember();
		model.addAttribute("memberList", list);
	}
	
	@GetMapping("get")
	public String getMember(String id, 
			Principal principal,
			HttpServletRequest request,
			Model model) {
		
		if (hasAuthOrAdmin(id, principal, request)) {
			MemberDto member = service.getMemberById(id);
			model.addAttribute("member", member);
			
			return null;
		}
		
		return "redirect:/member/login";
	}
	
	private boolean hasAuthOrAdmin(String id, Principal principal, HttpServletRequest req) {
		return req.isUserInRole("ROLE_ADMIN") || 
				(principal != null && principal.getName().equals(id));
	}
	
	@PostMapping("remove")
	public String removeMember(MemberDto dto, 
			Principal principal,
			HttpServletRequest req,
			RedirectAttributes rttr) {
		
		if (hasAuthOrAdmin(dto.getId(), principal, req)) {
			boolean success = service.removeMember(dto);
			
			if (success) {
				rttr.addFlashAttribute("message", "회원 탈퇴 되었습니다.");
				return "redirect:/board/list";
			} else {
				rttr.addAttribute("id", dto.getId());
				return "redirect:/member/get";
			}
		} else {
			return "redirect:/member/login";
		}
	}
	
	@PostMapping("modify")
	public String modifyMember(MemberDto dto, 
			String oldPassword,
			Principal principal,
			HttpServletRequest req,
			RedirectAttributes rttr) {
		
		if(hasAuthOrAdmin(dto.getId(), principal, req)) {
			boolean success = service.modifyMember(dto, oldPassword);
			
			if (success) {
				rttr.addFlashAttribute("message", "회원 수정 되었습니다.");
			} else {
				rttr.addAttribute("message", "회원 정보가 수정되지 않았습니다.");
			}
			
			rttr.addFlashAttribute("member", dto); // model에 붙음
			rttr.addAttribute("id", dto.getId()); // query string에 붙음
			return "redirect:/member/get";
			
		} else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("login")
	public void loginPage() {
		
	}
	
	@GetMapping("initpw")
	public void initpwPage() {
		
	}
	
	@PostMapping("initpw")
	public String inintpwProcess(String id) {
		service.initPassword(id);
		
		return "redirect:/board/list";
		}
	}
	
