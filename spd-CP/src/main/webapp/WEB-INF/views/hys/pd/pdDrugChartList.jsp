 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品表管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<style>
		.btnBox h4 span{font-size:13px;font-weight:400;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>

	<div class="right-main-box">
		<div class="btnBox">
			<h4>采购金额排名表<span>(单位：万元)</span></h4>
		</div>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>排名</th>
						<th>医院名称</th>
						<th>采购金额</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
					</tr>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>