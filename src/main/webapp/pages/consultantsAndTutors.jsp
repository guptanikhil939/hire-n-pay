<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="portfolio-modal modal fade" id="consultantsAndTutors"
	tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-content">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="modal-body">
						<div class="col-lg-12">
							<div class="col-lg-1" data-dismiss="modal">
								<a href="#"> <span
									class="glyphicon glyphicon-circle-arrow-left"></span>
								</a>
							</div>
							<div class="col-lg-10">
								<h2>Consultants & Tutors</h2>
								<hr class="star-primary">
							</div>
						</div>
						<ul class="list-inline item-details">
							<c:forEach items="${serviceList}" var="services">
								<c:if test="${services.serviceCategoryId==4}">
									<li><strong><button
												class="btn btn-default btn-xs"
												id='catService<c:out
										value="${services.serviceId}" />'
												onClick='selectedService(<c:out
										value="${services.serviceId}" />)'>
												<c:out value="${services.serviceName}" /></strong></li>
								</c:if>
							</c:forEach>
						</ul>
						<div class="row">&nbsp;</div>
						<div>We understand your concern. Give us sometime, we are
							preparing ourselves for you.</div>
					</div>
					<!--  <button class="btn btn-success btn-default" id="eaoConfirmRequest">Confirm
						Request</button> -->
				</div>
			</div>
		</div>
	</div>
</div>