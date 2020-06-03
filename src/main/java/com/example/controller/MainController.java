package com.example.controller;

import java.util.List;

import com.example.domain.Review;
import com.example.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Event;
import com.example.domain.News;
import com.example.service.EventService;
import com.example.service.NewsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class MainController {// 모든 경로를 관리


	
	@Autowired 
	private EventService eventService;

	@Autowired 
	private NewsService newsService;

	@Autowired
	private DetailService detailService;


	@GetMapping("/")
	public String index() {
		return "index"; // templates/index.html
	}

//	@GetMapping("/register")
//	public String register() {
//		return "register";
//	}



	@GetMapping("/new")
	public String news(@PageableDefault Pageable pageable, Model model) {
		Page<News> list = newsService.getNewsList(pageable);
		model.addAttribute("news", list);
		return "new_paging";
	}

	
	@GetMapping("/new/{store}")
	public String news(@PathVariable String store, Model model) {
		List<News> list = newsService.findByStore(store);
		model.addAttribute("news", list);
		return "new"; // templates/display.html
	}
	
//페이징 하기 전
//	@GetMapping("/event")
//	public String event(Model model) {
//
//		List<Event> list = eventService.findEvents();
//		model.addAttribute("events", list);
//		return "event";
//	}


	//페이징
	@GetMapping("/event")
	public String event(@PageableDefault Pageable pageable, Model model) {

		Page<Event> list = eventService.getEventList(pageable);
		model.addAttribute("events", list);

//		Page<BoardEntity> boardList = boardService.getBoardList(pageable);
//		model.addAttribute("boardList", boardList);

//		List<Event> list = eventService.findEvents();
//		model.addAttribute("events", list);
		return "event_paging";
	}




	@GetMapping("/event/{store}")
	public String event(@PathVariable String store, Model model) {

		//페이징을 하려면 service - repository를 설계해야 한다.
//		Page<Event> list = eventService.getEventList(pageable);
//		model.addAttribute("events", list);


		List<Event> list = eventService.findByStore(store);
		model.addAttribute("events", list);

		return "event"; // templates/display.html

	}


	// detail
	@GetMapping("/detail/{newsNum}")
	public String detail(@PathVariable Long newsNum, Model model) {

		News news = newsService.findByNewsNum(newsNum);
		model.addAttribute("news", news);

		List<Review> list = detailService.findReviewByNews(newsNum);
		model.addAttribute("reviews", list);

	 return "detail";
}





		//시도 하고 있는 것
		@GetMapping("/review/{newsNum}")
		public String review(@PathVariable long newsNum,
		@RequestParam(value = "reviewComment") String reviewComment,
		@RequestParam(value = "reviewLike") String reviewLike,
		Authentication authentication
	) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String email = userDetails.getUsername();

			detailService.insertReview(newsNum, email, reviewComment, reviewLike);


			return "redirect:/detail/{newsNum}";
	}






//springSecurity

// 회원가입 페이지
	@GetMapping("/register")
	public String register() {
		return "register"; // templates/register.html
	}

//	// 회원가입 처리
//	@PostMapping("/register")
//	public String execSignup(MemberDto memberDto) {
//		memberService.joinUser(memberDto);
//
//		return "redirect:/login";
//	}


	// 로그인 페이지
	//login
	@GetMapping("/login")
	public void login() {
	}







	// 로그인 결과 페이지
	@GetMapping("/loginSuccess")
	public String dispLoginResult() {
		return "/loginSuccess";
	}

	// 로그아웃 결과 페이지
	@GetMapping("/logout")
	public String dispLogout() {
		return "/logout";
	}

	// 접근 거부 페이지
	@GetMapping("/accessDenied")
	public String dispDenied() {
		return "/accessDenied";
	}

//	// 내 정보 페이지
//	@GetMapping("/user/info")
//	public String dispMyInfo() {
//		return "/myinfo";
//	}
//
//	// 어드민 페이지
//	@GetMapping("/admin")
//	public String dispAdmin() {
//		return "/admin";
//	}









//	 Map<String, Object> map2 = new HashMap<String, Object>();
//	 map2.put("n_num", n_num);
//	 this.detailService.selectDetailReview(map2);
//	 List<Review> list2 = (List<Review>) map2.get("resultsReview");
//	 model.addAttribute("review", list2);


	
	
	
	/*
	 * 
	 * // 행사제품
	 * 
	 * // event 작업
	 * 
	 * @GetMapping("/event") public String event(Model model) { Map<String, Object>
	 * map = new HashMap<String, Object>(); this.eventService.selectAllEvent(map);
	 * List<EventVO> list = (List<EventVO>) map.get("results");
	 * model.addAttribute("events", list); return "event"; }
	 * 
	 * @GetMapping("/event/{e_store}") public String event(@PathVariable String
	 * e_store, Model model) { Map<String, Object> map = new HashMap<String,
	 * Object>();
	 * 
	 * map.put("e_store", e_store);
	 * 
	 * this.eventService.selectAllEvent_store(map); List<EventVO> list =
	 * (List<EventVO>) map.get("results"); EventVO event = list.get(0);
	 * model.addAttribute("events", list); return "event"; // templates/display.html
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @GetMapping("/list") public String list(Model model) { List<MemberVO> list =
	 * null; model.addAttribute("member", list); return "list"; //
	 * template/list.html }
	 * 
	 * // detail
	 * 
	 * @GetMapping("/detail/{n_num}") public String detail(@PathVariable int n_num,
	 * Model model) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("n_num",
	 * n_num); this.detailService.selectDetailNew(map); List<NewVO> list =
	 * (List<NewVO>) map.get("result"); NewVO news = list.get(0);
	 * model.addAttribute("news", news);
	 * 
	 * Map<String, Object> map2 = new HashMap<String, Object>(); map2.put("n_num",
	 * n_num); this.detailService.selectDetailReview(map2); List<ReviewVO> list2 =
	 * (List<ReviewVO>) map2.get("resultsReview"); model.addAttribute("review",
	 * list2);
	 * 
	 * for (ReviewVO reviewVO : list2) { log.info("review={}", reviewVO); }
	 * 
	 * return "detail";
	 * 
	 * }
	 * 
	 * @GetMapping("/event/form") public String event_form(@RequestParam(value =
	 * "e_evt") String e_evt,
	 * 
	 * @RequestParam(value = "e_product1") String e_product1, Model model) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("e_evt",
	 * e_evt); map.put("e_product1", e_product1);
	 * this.eventService.select_Event_form(map);
	 * 
	 * List<EventVO> list = (List<EventVO>) map.get("results");
	 * model.addAttribute("events", list);
	 * 
	 * log.info(" " + list.size()); log.info("" + e_evt); log.info("" + e_product1);
	 * 
	 * return "event";
	 * 
	 * }
	 * 
	 * @GetMapping("/event/search") public String event_search(@RequestParam(value =
	 * "e_name") String e_name, Model model) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("e_name",
	 * e_name); this.eventService.select_Textbox(map); ;
	 * 
	 * List<EventVO> list = (List<EventVO>) map.get("results");
	 * model.addAttribute("events", list);
	 * 
	 * log.info(" " + list.size()); log.info("" + e_name);
	 * 
	 * return "event";
	 * 
	 * }
	 * 
	 * @GetMapping("/new/form") public String new_form(@RequestParam(value =
	 * "n_product1") String n_product1, Model model) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("n_product1", n_product1); this.newService.select_new_form(map);
	 * 
	 * List<NewVO> list = (List<NewVO>) map.get("results");
	 * model.addAttribute("news", list); return "new";
	 * 
	 * }
	 * 
	 * //////////////// new 상품 검색
	 * 
	 * @GetMapping("/new/search") public String new_search(@RequestParam(value =
	 * "n_name") String n_name,
	 * 
	 * Model model) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("n_name",
	 * n_name);
	 * 
	 * this.newService.search_new_name(map);
	 * 
	 * List<NewVO> list = (List<NewVO>) map.get("results");
	 * model.addAttribute("news", list);
	 * 
	 * return "new"; }
	 * 
	 * // //login // @GetMapping("/login") // public String rogin() { // return
	 * "login"; // templates/register.html // } // // // @PostMapping("/login") //
	 * public String rogin(MemberVO member) { // log.info("member={}", member); //
	 * this.memberService.insertMember(member); // return "redirect:/"; // }
	 */
}
