package org.goal.rgas.payment;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.goal.rgas.member.Member;
import org.goal.rgas.member.MemberMapper;
import org.goal.rgas.mission.Mission;
import org.goal.rgas.mission.MissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siot.IamportRestHttpClientJava.IamportClient;
import com.siot.IamportRestHttpClientJava.request.CancelData;
import com.siot.IamportRestHttpClientJava.response.IamportResponse;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private MissionMapper missionMapper;

	@Autowired
	private HttpSession httpSession;

	// 결제 창 띄워주기
	@Override
	public IamportRequest paymentProcess(Mission mission, String merchantUid) throws Exception {
		IamportRequest iamportRequest = new IamportRequest();

		if (mission != null && merchantUid != null) {
			// 결제 정보
			iamportRequest.setMerchantUid("rgas" + merchantUid);
			iamportRequest.setPaymentName(mission.getTitle());
			iamportRequest.setAmount(mission.getEntryFee());

			// 화원 이메일 찾기
			Member member = (Member) httpSession.getAttribute("memberValue");

			iamportRequest.setBuyerName(member.getName());
			iamportRequest.setBuyerEmail(member.getEmail());

			return iamportRequest;
		}

		return null;
	}

	// 결제 내역 등록
	@Override
	public void paymentRegister(Mission mission, String merchantUid) throws Exception {
		if (mission != null && merchantUid != null) {
			Mission missionValue = missionMapper.select(mission);

			Payment payment = new Payment();
			payment.setPaymentCode("rgas" + merchantUid);
			payment.setDeposit(missionValue.getEntryFee());
			payment.setPaymentDate(LocalDate.now());
			payment.setMemberNo(missionValue.getMemberNo());
			payment.setMissionNo(missionValue.getNo());

			paymentMapper.insert(payment);
		}
	}

	// 결제 내역 목록 조회
	@Override
	public List<Payment> paymentList(Payment payment) throws Exception {
		List<Payment> list = paymentMapper.list(payment);

		return list;
	}

	// 결제 내역 단일 조회
	@Override
	public Payment paymentInquiry(Payment payment) throws Exception {
		Payment result = paymentMapper.select(payment);

		return result;
	}

	// 결제 취소 (전액 환불)
	@Override
	public boolean paymentCancel(Payment payment) throws Exception {
		if (payment != null) {
			payment = paymentMapper.select(payment);

			// 미션 정보 찾기
			Mission mission = new Mission();
			mission.setNo(payment.getMissionNo());
			mission = missionMapper.select(mission);

			IamportClient iamportClient = new IamportClient("1722439638143134",
					"tV7DKdiRXz5pX53kU9Ohg7Lb17DIiSUMN2pxfIpdhuCezFzuPnL5vwgwEUfXMaJzc97sRwF91ioBXX5N");
			IamportResponse<com.siot.IamportRestHttpClientJava.response.Payment> iamportResponse = iamportClient
					.cancelPayment(new CancelData(payment.getPaymentCode(), false));
			if (0 == iamportResponse.getCode()) {
				paymentMapper.delete(payment);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
