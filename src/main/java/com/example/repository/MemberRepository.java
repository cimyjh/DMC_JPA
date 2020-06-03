package com.example.repository;

import com.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User, Long> {

	//List<Member> findById(Long id);


	User findByEmail(String userEmail);

	User findByNickname(String nicknamed);


	User findById(String memberId);





}
