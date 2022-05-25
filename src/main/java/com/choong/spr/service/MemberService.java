package com.choong.spr.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

public class MemberService {

	@Autowired
	private MemberMapper mapper;
	
	public boolean addMemeber(MemberDto member) {
		return mapper.insertMember(member);
	}

}
