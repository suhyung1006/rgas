<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/layout/top.jsp" />
<body class="horizontal-layout page-header-light horizontal-menu preload-transitions 2-columns   " data-open="click" data-menu="horizontal-menu" data-col="2-columns">
  <!-- BEGIN: Page Main-->
	<div id="main">
		<div class="row">
			<div class="col s12">
				<div class="container">
					<div id="contact-us" class="seaction">
						<div class="app-wrapper">
							<div class="card">
								<div class="card-content">
									<p class="caption mb-0" style="font-weight: bold">기부 단체 정보</p>
								</div>
							</div>
							<!-- Form Advance -->
							<div id="sidebar-list" class="row contact-sidenav">
								<div class="contact-form margin-top-contact">
									<div class="row">
										<div class="col s12 m12 l12">
											<div id="Form-advance"
												class="card card card-default scrollspy">
												<div class="card-content">
													<form method="post" action="/charity" id="charityForm">
														<input type="hidden" name="_method" value="PUT" /> <input
															type="hidden" name="no" id="no" value="${charity.no}" />
														<div class="row">
															<div class="input-field col s12">
																<input type="text" name="name" id="name"
																	value="${charity.name}" required /> <label for="name"
																	style="font-weight: bold;">기부단체
																	명</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<select id="bank" name="bank"
																	class="select-1 form-control" required>
																	<option value="${charity.bank}" selected>${bankName}</option>
																	<option value="004">KB국민은행</option>
																	<option value="023">SC제일은행</option>
																	<option value="003">기업은행</option>
																	<option value="011">농협</option>
																	<option value="002">산업은행</option>
																	<option value="045">새마을금고</option>
																	<option value="007">수협</option>
																	<option value="088">신한은행</option>
																	<option value="048">신협</option>
																	<option value="005">외환은행</option>
																	<option value="020">우리은행</option>
																	<option value="071">우체국</option>
																	<option value="090">카카오뱅크</option>
																	<option value="089">케이뱅크</option>
																	<option value="081">하나은행</option>
																</select> <label for="bank"
																	style="font-weight: bold;">은행
																	명</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<input type="text" name="account" id="account"
																	value="${charity.account}" required /> <label
																	for="account"
																	style="font-weight: bold;">계좌
																	번호</label>
															</div>

														</div>
													</form>
													<div class="row">
														<div class="input-field col m6 s12">
															<c:forEach var="donationTransfer" items="${donationTransferList}">
		            											<c:if test="${charity.no eq donationTransfer.charityNo}">
		            												 <c:set var="flag" value="fail" />
																</c:if>
															</c:forEach>
															<c:if test="${flag ne 'fail'}">
																<form method="post" action="/charity" id="charityDelete">
																	<input type="hidden" name="_method" value="DELETE" /> <input
																		type="hidden" name="no" id="no" value="${charity.no}" />
																	<button class="btn red waves-effect waves-light left"
																		type="submit" style="font-weight:bold;">
																		삭제 <i class="material-icons right">delete</i>
																	</button>
																</form>
															</c:if>
														</div>
														<div class="input-field col m6 s12">
															<button class="btn blue waves-effect waves-light right"
																style="font-weight:bold;" id="btn" type="submit" name="btn" form="charityForm">
																수정 <i class="material-icons right">edit</i>
															</button>
														</div>
													</div>
												</div> 
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
    	document.title = "기부 단체 | CHEERUP" ;
	</script>
	<jsp:include page="/WEB-INF/jsp/layout/footer.jsp" />