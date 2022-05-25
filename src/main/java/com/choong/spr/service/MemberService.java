package com.choong.spr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper mapper;
	
	public boolean addMemeber(MemberDto member) {
		
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

}
