package org.goal.rgas.payment;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.goal.rgas.mission.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;

	@Autowired
	private HttpServletResponse httpServletResponse;

	//결제 처리
	@PostMapping("/form/{merchantUid}")
	public IamportRequest paymentProcess(@RequestBody @Valid Mission mission, Errors errors, @PathVariable String merchantUid) {
		IamportRequest iamportRequest = null;
		
		try {
			if (errors.hasErrors()) {
				httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} else {
				httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
			}
			iamportRequest = paymentServiceImpl.paymentProcess(mission, merchantUid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return iamportRequest;
	}
}
