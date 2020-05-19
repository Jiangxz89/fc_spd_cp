<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材订单详情</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css" />
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
			<h4>耗材订单详情</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdConsumablesOrder" action="" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<%-- <div>
					<label>订单日期</label>
					<input id="orderDate" type="text" htmlEscape="false" maxlength="64" class="input-medium" value="<fmt:formatDate value="${pdConsumablesOrder.orderDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
				</div>
				<div>
					<label>订单编号</label>
					<input name="number" htmlEscape="false" value = " ${pdConsumablesOrder.number}" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>下单医院</label>
					<input name="hospital" htmlEscape="false"  value = " ${pdConsumablesOrder.hospital}" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>供应商</label>
					<input name="supplierName" type="text"  value = " ${pdConsumablesOrder.supplierName}" htmlEscape="false" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>订单状态 </label>
					<input name="orderState" htmlEscape="false" maxlength="64" class="input-medium" value="${fns:getDictLabel(pdConsumablesOrder.orderState, 'order_type', '')}" readonly="readonly"/>
				</div> --%>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>订单日期</th>
						<th>订单编号</th>
						<th>医院名称</th>
						<th>订货量 </th>
						<th>订单金额 </th>
						<th>订单状态</th>
						<th>供应商 </th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pdConsumablesAparts}" var="pdConsumablesApart">
						<tr>
							<td>
								<fmt:formatDate value="${pdConsumablesOrder.orderDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${pdConsumablesOrder.number}
							</td>
							<td>
								${pdConsumablesOrder.hospitalName}
							</td>
							<td>
								${pdConsumablesApart.orderQuantity}
							</td>
							<td>
								<fmt:formatNumber value="${pdConsumablesApart.orderAmount}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
							</td>
							<td>
								${fns:getDictLabel(pdConsumablesOrder.orderState, 'order_type', '')}
							</td>
							<td>
								${pdConsumablesApart.supplierName}
							</td>
							<shiro:hasPermission name="hys:pdConsumablesOrder:view"><td>
								<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/pdConsumablesOrder/pdConsumablesApartDetail?id=${pdConsumablesApart.id}&number=${pdConsumablesOrder.number}">查看</a>
							</td></shiro:hasPermission>
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
    		<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
    	</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){

		})
	</script>
</body>
</html>