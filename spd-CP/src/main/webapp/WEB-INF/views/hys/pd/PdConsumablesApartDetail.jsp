<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材订单拆分后详情</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css" />
	<style>
		.otherText{margin:20px 0;}
		.otherText>.remarkArea{width:400px;height: 100px;border:1px solid #ccc;padding-left:5px;vertical-align: text-top;margin:0;}
		.otherText>h3{font-weight:400;float:left;padding:0 10px 0 5px;width:73px;font-size:14px;color:#666;line-height:30px;}
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
			<h4>耗材订单拆分后详情</h4>
		</div>
		<div class="searchBox">
			<input id="formType" type="hidden" value="${formType }"/>
			<form:form id="searchForm" modelAttribute="pdConsumablesApart" action="${ctx}/hys/pdConsumablesOrder/list" method="post" class="breadcrumb form-search">
				<input id="id" name="id" type="hidden" value="${pdConsumablesApart.id}"/>
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>订单日期</label>
					<input id="orderDate" type="text" htmlEscape="false" maxlength="64" class="input-medium" value="<fmt:formatDate value="${pdConsumablesApart.orderDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
				</div>
				<div>
					<label>订单编号</label>
					<input name="number" htmlEscape="false" value = " ${pdConsumablesApart.number}" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>下单医院</label>
					<input name="hospitalName" htmlEscape="false"  value = " ${pdConsumablesApart.hospitalName}" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>供应商</label>
					<input name="supplierName" type="text"  value = " ${pdConsumablesApart.supplierName}" htmlEscape="false" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>订单状态 </label>
					<input name="orderState" htmlEscape="false" maxlength="64" class="input-medium" value="${fns:getDictLabel(pdConsumablesApart.orderState, 'order_type', '')}" readonly="readonly"/>
				</div>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>耗材名称</th>
						<th>产品编码</th>
						<th>包装规格 </th>
						<th>单位 </th>
						<th>生产企业</th>
						<th>订货量 </th>
						<th>单价 </th>
						<th>金额 </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pdConsumablesApart.pdConsumablesApartRelevances}" var="pdConsumablesApartRelevance">
						<tr>
							<td>
								${pdConsumablesApartRelevance.name}
							</td>
							<td>
								${pdConsumablesApartRelevance.consumablesNumber}
							</td>
							<td>
								${pdConsumablesApartRelevance.spec}
							</td>
							<td>
								${pdConsumablesApartRelevance.unit}
							</td>
							<td>
								${pdConsumablesApartRelevance.venderName}
							</td>
							<td>
								${pdConsumablesApartRelevance.orderQuantity}
							</td>
							<td>
								${pdConsumablesApartRelevance.price}
							</td>
							<td>
								<fmt:formatNumber value="${pdConsumablesApartRelevance.amount}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
							</td>
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
			//申购时间
			laydate.render({
				elem: '#inHomeTime'
			});
		})
	</script>
</body>
</html>