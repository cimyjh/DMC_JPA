package com.example.service;

import com.example.domain.user.User;
import com.example.domain.News;
import com.example.domain.Review;
import com.example.domain.user.UserRepository;
import com.example.repository.DetailRepository;
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
	private UserRepository userRepository;

	public List<Review> findReviewByNews(Long newsNum) {
		return detailRepository.findReviewByNews(newsNum);
	}


	@Transactional
	public Review insertReview(long newsNum, String name, String reviewComment, String reviewLike) {
		News news = newsRepository.findByNewsNum(newsNum);
		User user = userRepository.findByname(name);

		Review review = Review.createReview(user, news, reviewComment, reviewLike);

		return detailRepository.save(review);

		//return review.getReviewNum();
	}
}
