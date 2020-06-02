package com.example.dto;

import com.example.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {



    private String email;
    private Long id;

    private String nickname;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .id(id)
                .nickname(nickname)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto( String email, Long id, String nickname, String password) {
        this.email = email;
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}