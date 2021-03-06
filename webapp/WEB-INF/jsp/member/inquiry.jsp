<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/layout/top.jsp" />
<body class="horizontal-layout page-header-light horizontal-menu preload-transitions 2-columns   " data-open="click" data-menu="horizontal-menu" data-col="2-columns">
	<div id="main"> 
		<div class="row">
			<div class="col s12">
				<div class="container">
					<div id="contact-us" class="seaction">
						<div class="app-wrapper">
							<div class="card">
								<div class="card-content">
									<p class="caption mb-0" style="font-weight: bold">회원 정보</p>
								</div>
							</div>
							<div id="sidebar-list" class="row contact-sidenav">
								<div class="contact-form margin-top-contact">
									<div class="row">
										<div class="col s12 m12 l12">
											<div id="prefixes" class="card card card-default scrollspy">
												<div class="card-content" style="padding-bottom: 20px;">
													<form>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">account_circle</i> <input
																	type="text" id="name" name="name"
																	value="${member.name}" style="color: black;" disabled>
																<label style=" font-weight: bold;">이름</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">email</i> <input
																	type="text" id="email" name="email"
																	value="${member.email}" style="color: black;" disabled>
																<label style=" font-weight: bold;">이메일</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">account_circle</i> <input
																	type="text" id="nickname" name="nickname"
																	value="${member.nickname}" style="color: black;"
																	disabled> <label
																	style=" font-weight: bold;">닉네임</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">local_atm</i> <input
																	type="text" id="bank" name="bank" value="${bankName}"
																	style="color: black;" disabled> <label
																	style=" font-weight: bold;">은행</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">local_atm</i> <input
																	type="text" id="account" name="account"
																	value="${member.account}" style="color: black;"
																	disabled> <label
																	style=" font-weight: bold;">계좌
																	번호</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">stars</i>
																<c:if test="${member.grade == 'C'}">
																	<input type="text" value="일반" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.grade == 'B'}">
																	<input type="text" value="브론즈" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.grade == 'S'}">
																	<input type="text" value="실버" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.grade == 'G'}">
																	<input type="text" value="골드" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.grade == 'P'}">
																	<input type="text" value="프로" style="color: black;"
																		disabled>
																</c:if>
																<label style=" font-weight: bold;">등급</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">offline_pin</i>
																<c:if test="${member.auth == 'A'}">
																	<input type="text" value="관리자" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.auth == 'C'}">
																	<input type="text" value="회원" style="color: black;"
																		disabled>
																</c:if>
																<label style=" font-weight: bold;">권한</label>
															</div>
														</div>
														<div class="row">
															<div class="input-field col s12">
																<i class="material-icons prefix">verified_user</i>
																<c:if test="${member.status == 'A'}">
																	<input type="text" value="활성" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.status == 'D'}">
																	<input type="text" value="탈퇴" style="color: black;"
																		disabled>
																</c:if>
																<c:if test="${member.status == 'S'}">
																	<input type="text" value="정지" style="color: black;"
																		disabled>
																</c:if>
																<label style=" font-weight: bold;">상태</label>
															</div>
														</div>
														<div class="row">
															<div class="row">
																<div class="input-field col s12">
																	<button type="submit" form="memberForm"
																		class="btn red waves-effect waves-light right" style="font-weight:bold;">
																		정지<i class="material-icons right">do_not_disturb_alt</i>
																	</button>
																</div>
															</div>
														</div>
													</form>
													<form method="post" action="/member" id="memberForm">
														<input type="hidden" name="_method" value="PUT" /> <input
															type="hidden" name="no" id="no" value="${member.no}" />
													</form>
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
    	document.title = "회원 정보 관리 | CHEERUP" ;
	</script>
	<jsp:include page="/WEB-INF/jsp/layout/footer.jsp" />