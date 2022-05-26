package com.choong.spr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public boolean addMemeber(MemberDto member) {
		System.out.println(member);
		// 평문암호를 암호화(encoding)
		String encodedPassword = passwordEncoder.encode(member.getPassword());
		
		// 암호화된 암호를 다시 세팅
		member.setPassword(encodedPassword);
		
		return mapper.insertMember(member) == 1;
	}

	public boolean hasMemeberId(String id) {
		
		return mapper.countMemberId(id) > 0;
	}

	public boolean hasMemeberEmail(String email) {
		
		return mapper.countMemberEmail(email) > 0;
	}

	public boolean hasMemeberNickName(String nickName) {
		
		return mapper.countMemberNickName(nickName) > 0;
	}

	public List<MemberDto> listMember() {
		return mapper.selectAllMamber();
	}

	public MemberDto getMemberById(String id) {
		return mapper.selectMemberById(id);
	}

	public boolean removeMember(MemberDto dto) {
		MemberDto member = mapper.selectMemberById(dto.getId());
		
		String rawPW = dto.getPassword();
		String encodedPW = member.getPassword();
		
		if (passwordEncoder.matches(rawPW, encodedPW)) {
			return mapper.deleteMemberById(dto.getId()) == 1;
		}
		
		return false;
	}

	public boolean modifyMember(MemberDto dto, String oldPassword) {
		// db에서 member 읽어서
		MemberDto oldMember = mapper.selectMemberById(dto.getId());
		
		String encodedPW = oldMember.getPassword();
		
		if (passwordEncoder.matches(oldPassword, encodedPW)) {
			
			// 암호 인코딩
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			return mapper.updateMember(dto) == 1;
		}
		
		return false;
	}

}
