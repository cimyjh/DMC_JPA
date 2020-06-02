package com.example.service;

import com.example.domain.Member;
import com.example.domain.Role;
import com.example.dto.MemberDto;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
//	@Transactional
//	public Long join(Member member) {
//		validateDuplicateMember(member);
//		memberRepository.save(member);
//		return member.getUserid();
//	}
	
	
	//중복 회원 검색
//	private void validateDuplicateMember(Member member) {
//		List<Member> findMembers = memberRepository.findByUserid(member.getId());
//		if(!findMembers.isEmpty()) {
//			throw new IllegalStateException("이미 존재하는 회원입니다.");
//		}
//	}
	
	
//	public List<Member> findMembers(){
//		return memberRepository.findAll();
//	}



	@Transactional
	public String joinUser(MemberDto memberDto) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

		return memberRepository.save(memberDto.toEntity()).getEmail();
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Member> userEntityWrapper = Optional.ofNullable(memberRepository.findByEmail(userEmail));
		Member userEntity = userEntityWrapper.get();

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
}


