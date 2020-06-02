package com.example.service;

import com.example.domain.Member;
import com.example.domain.News;
import com.example.domain.Review;
import com.example.repository.DetailRepository;
import com.example.repository.MemberRepository;
import com.example.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class DetailService {

	@Autowired
	private DetailRepository detailRepository;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private MemberRepository memberRepository;


	public List<Review> findReviewByNews(Long newsNum) {
		return detailRepository.findReviewByNews(newsNum);
	}


	@Transactional
	public Review insertReview(long newsNum, String email, String reviewComment, String reviewLike) {
		News news = newsRepository.findByNewsNum(newsNum);
		Member member = memberRepository.findByEmail(email);

		Review review = Review.createReview(member, news, reviewComment, reviewLike);

		return detailRepository.save(review);

		//return review.getReviewNum();
	}
}
