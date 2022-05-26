package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.MemberDto;

public interface MemberMapper {

	int insertMember(MemberDto member);

	int countMemberId(String id);

	int countMemberEmail(String email);

	int countMemberNickName(String nickName);

	List<MemberDto> listMamber();

	List<MemberDto> selectAllMamber();

	MemberDto selectMemberById(String id);

	int deleteMemberById(String id);

	int updateMember(MemberDto dto);


}
