package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
	@Query("select n from News n where n.store = :store")
	List<News> findByStore(@Param("store") String store);


	@Query("select n from News n where n.newsNum = :newsNum")
	News findByNewsNum(@Param("newsNum") Long newsNum);


//	@Query("select n from News n where exists(select r from review r where r.new = n and r.num = :num")
//	List<News> findByNews_id(@Param("num") Integer num);


}
