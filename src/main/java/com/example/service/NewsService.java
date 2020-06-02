package com.example.service;

import java.util.List;

import com.example.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.News;
import com.example.repository.NewsRepository;


@Service
@Transactional(readOnly = true)
public class NewsService {
	
	@Autowired
	private NewsRepository newsRepository;

	
	public List<News> findNews() {
		return newsRepository.findAll();
	}


	public List<News> findByStore(String store) {
		return newsRepository.findByStore(store);
	}


	public News findByNewsNum(Long newsNum) {
		return newsRepository.findByNewsNum(newsNum);
	}


//	public List<News> findByNews_id(int num) {
//		return newsRepository.findByNews_id(num);
//	}





	public Page<News> getNewsList(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page,9);
		return newsRepository.findAll(pageable);
	}

}
