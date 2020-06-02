package com.example.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
	
	@Id @GeneratedValue
	@Column(name = "review_id")
	private Long reviewNum;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="news_id")
	private News news;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="member_id")
	private Member member;
	
	private String reviewComment;
	private String reviewLike;



	//private LocalDateTime reviewDate;

//	@Builder
//	public Review(Long reviewNum, String reviewComment, String reviewLike) {
//		this.reviewNum = reviewNum;
//		this.reviewComment = reviewComment;
//		this.reviewLike = reviewLike;
//	}


//	public Review(long reviewNum, String reviewComment, String reviewLike,  long newsNum, String userid) {
//		this.reviewNum =reviewNum;
//		this.reviewComment = reviewComment;
//		this.reviewLike = reviewLike;
//		this.getMember().getId();
//		this.getNews().getNewsNum();
//	}

	//연관관계 편의 메소드
	public void setMember(Member member){
		this.member = member;
		member.getReviews().add(this);
	}

	public void setNews(News news){
		this.news = news;
		news.getReviews().add(this);
	}


	public static Review createReview(Member member, News news, String reviewComment, String reviewLike){
		Review review = new Review();
		review.setMember(member);
		review.setNews(news);
		review.setReviewComment(reviewComment);
		review.setReviewLike(reviewLike);
		//review.setReviewDate(LocalDateTime.now());
		return review;

	}
}
