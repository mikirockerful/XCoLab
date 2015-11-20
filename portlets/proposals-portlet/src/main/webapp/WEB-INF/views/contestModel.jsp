<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	version="2.0">

	<jsp:directive.include file="./init_contest.jspx" />
	
	<div class="proposal-head">
		<div class="inner">
			<div class='headline'>
				<div class="contest-image">
					<img src="/image/contest?img_id=${contest.contestLogoId}" width="52" height="52" alt="Contest Image" />
				</div>
				<div class='proposal-title'>
					<h1>Model <proposalsPortlet:contestLink contest="${contest}" text="${contest.contestShortName} " /></h1>
				</div>
			</div>
		</div>
		<!-- /inner -->
	</div>
	<!-- /proposal-head -->	
	<div id="content">
	<c:if test="${not empty availableModels }">
		<proposalsPortlet:modelPicker availableModels="${availableModels  }" contestPK="${contest.contestPK }" />
	</c:if>
			<modeling:simulationEdit  modelId="${modelId }" contestModelDefaultSetting="${contest.defaultModelSettings}"/>
	</div>
	
</jsp:root>