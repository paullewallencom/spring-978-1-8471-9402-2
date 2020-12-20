<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><spring:message code="ui.manage.list"/></title>
	<%@ include file="/WEB-INF/jsp/decorators/header.jsp"%>
</head>
<body>
	<div id="container">
	<%@ include file="/WEB-INF/jsp/decorators/header.jsp"%>
	<%@ include file="/WEB-INF/jsp/decorators/boxSx.jsp"%>
	<div id="principale">
		<div class="box"><decorator:body /></div>
	</div>
	<!-- foo comment for IE -->
	<%@ include file="/WEB-INF/jsp/decorators/footer.jsp"%>
	</div>
</body>
</html>