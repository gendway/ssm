<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username" />
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>


			<c:forEach items="${sessionScope.parentPermission}" var="permission">
				<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
						<span>${permission.permissionName}</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<c:if test="${!empty permission.child}">
						<ul class="treeview-menu">
							<c:forEach items="${permission.child}" var="cp">
							<li id="system-setting"><a
								href="${pageContext.request.contextPath}${cp.url}">
									<i class="fa fa-circle-o"></i>${cp.permissionName}
							</a>
							</li>
							</c:forEach>
						</ul>
					</c:if>
				</li>
			</c:forEach>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>