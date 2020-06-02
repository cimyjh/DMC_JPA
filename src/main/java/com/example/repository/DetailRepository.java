package com.example.repository;

import com.example.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends JpaRepository<Review, Integer> {

	@Query(value = "select * from Review where news_id =:newsNum", nativeQuery = true)
	List<Review> findReviewByNews(@Param("newsNum") Long newsNum);


//	@Query(value = "insert into review(reviewNum, review_comment, news_id, member_id, review_like) values(1L, :reviewComment, :newsNum, :userid, :reviewLike) ", nativeQuery = true)
//	void ReviewInsert(@Param("newsNum") Long newsNum,
//					  @Param("reviewComment") String reviewComment ,
//					  @Param("userid") String userid ,
//					  @Param("reviewLike") String reviewLike);



//	@Query(value = "insert into review(REVIEWNUM, review_comment, news_id, member_id, review_like) values(10, :reviewComment, :newsNum, :userid, :reviewLike) ", nativeQuery = true)
//	void ReviewInsert(@Param("newsNum") Long news_id,
//					  @Param("reviewComment") String review_comment ,
//					  @Param("userid") String member_id ,
//					  @Param("reviewLike") String review_like);



}
