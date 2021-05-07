package org.goal.rgas.mission;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goal.rgas.member.Member;
import org.goal.rgas.member.MemberServiceImpl;
import org.goal.rgas.payment.Payment;
import org.goal.rgas.payment.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/mission")
public class MissionController {
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private MissionServiceImpl missionServiceImpl;

	@Autowired
	private MemberServiceImpl memberServiceImpl;

	@Autowired
	private PaymentServiceImpl paymentServiceImpl;

	// 미션 등록 폼
	@GetMapping("/form")
	public ModelAndView missionRegisterForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mission/register");
		
		try {
			List<Category> categoryList = missionServiceImpl.categoryList();
			mv.addObject("categoryList", categoryList);
			System.out.println(categoryList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	// 미션 등록 처리
	@PostMapping
	public ModelAndView missionRegister(@RequestParam("img") MultipartFile file, Mission mission,
			@RequestParam("merchantUid") String merchantUid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/mission");

		try {
			if (mission != null) {
				mission = missionServiceImpl.missionRegister(file, mission);
				paymentServiceImpl.paymentRegister(mission, merchantUid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	// 내 미션 목록 조회
	@GetMapping
	public ModelAndView missionList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mission/list");

		Member member = (Member) httpSession.getAttribute("memberValue");

		try {
			Mission mission = new Mission();
			mission.setMemberNo(member.getNo());

			List<Mission> missionList = missionServiceImpl.missionList(mission);
			List<Member> memberList = memberServiceImpl.memberList(new Member());

			mv.addObject("missionList", missionList);
			mv.addObject("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 미션 상세 조회
	@GetMapping("/{no}")
	public ModelAndView missionInquiry(Mission mission) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("redirect:/mission");
		try {
			if (mission != null) {
				Mission missionValue = missionServiceImpl.missionInquiry(mission);
				if (missionValue.getMemberNo() == ((Member)httpSession.getAttribute("memberValue")).getNo()) {
					List<Category> categoryList = missionServiceImpl.categoryList();
					
					mv.setViewName("mission/inquiry");
					
					mv.addObject("mission", missionValue);
					mv.addObject("categoryList", categoryList);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	// 미션 정보 수정
	@PutMapping
	public ModelAndView missionModify(Mission mission) {
		ModelAndView mv = new ModelAndView(new RedirectView("/mission"));

		try {
			if (mission != null) {
				missionServiceImpl.missionModify(mission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	// 미션 삭제
	@DeleteMapping
	public ModelAndView missionDelete(Mission mission) {
		ModelAndView mv = new ModelAndView(new RedirectView("/mission"));

		try {
			if (mission != null) {
				Payment payment = new Payment();
				payment.setMissionNo(mission.getNo());
				
				if (paymentServiceImpl.paymentCancel(payment)) {
					missionServiceImpl.missionDelete(mission);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	// 이미지 출력
	@GetMapping("/photo/{no}")
	public void photoView(Mission mission, HttpServletResponse response) {
		try {
			byte[] byteToFile = missionServiceImpl.photoView(mission);

			response.setContentType("image/jpg");
			response.getOutputStream().write(byteToFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
