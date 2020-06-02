package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	//List<Member> findById(Long id);


	Member findByEmail(String userEmail);

	Member findByNickname(String nicknamed);


	Member findById(String memberId);





}
