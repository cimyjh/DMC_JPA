package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@Entity
public class Member {

	@Id
	@Column(name = "member_id")
	private String email;


	@GeneratedValue
	private Long id;

	private String nickname;
	private String password;
	
	
	@OneToMany(mappedBy = "member")
	private List<Review> reviews = new ArrayList<>();


	@Builder
	public Member(Long id, String email, String nickname, String password) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}


}
