<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />

<section>
	<div class="container">
		<div class="row">&nbsp;</div>
		<div class="col-md-12">
			<div class="media-body"
				style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
				<h3 class="media-heading">My Requests</h3>
				<div>&nbsp;</div>
				<c:if test="${orderDetailsDTOList.size() > 0 }">
					<table class="table table-striped table-bordered table-condensed table-hover">
						<thead>
							<tr>
								<th>Request ID</th>
								<th>Service Name</th>
								<th>Service Day/Time</th>
							</tr>
						</thead>
						<c:forEach items="${orderDetailsDTOList}" var="orderDetails">
							<tr id="row_${orderDetails.orderId}">
								<td width="10%"><c:out
										value="${orderDetails.orderId}" /></td>
								<td width="60%"><c:out value="${orderDetails.serviceName}" /></td>
								<td width="30%"><c:out value="${orderDetails.pickUpDateString}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${orderDetailsDTOList.size() == 0 }">
					<div>No Requests yet.</div>
				</c:if>
			</div>
			<div class="row">&nbsp;</div>
		</div>
	</div>
</section>