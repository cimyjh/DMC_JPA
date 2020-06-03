package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.domain.user.User;
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
	private User user;
	
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
	public void setUser(User user){
		this.user = user;
		user.getReviews().add(this);
	}

	public void setNews(News news){
		this.news = news;
		news.getReviews().add(this);
	}


	public static Review createReview(User user, News news, String reviewComment, String reviewLike){
		Review review = new Review();
		review.setUser(user);
		review.setNews(news);
		review.setReviewComment(reviewComment);
		review.setReviewLike(reviewLike);
		//review.setReviewDate(LocalDateTime.now());
		return review;

	}
}
