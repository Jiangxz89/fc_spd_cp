<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材订单及配送查询</title>
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
			<h4>耗材订单及配送查询</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdConsumablesOrderTime" action="${ctx}/hys/pdConsumablesOrder/detailList" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>订单日期</label>
					<form:input path="queryDate" htmlEscape="false" id="inHomeTime"  readonly="readonly" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>订单编号</label>
					<form:input path="number" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>医院名称</label>
					<form:input path="hospitalName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>供应商 </label>
					<form:input path="supplierName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				</br>
				<div>
					<label>耗材名称 </label>
					<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>产品编码 </label>
					<form:input path="productNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>生产企业</label>
					<form:input path="manufacturer" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>订单日期</th>
						<th>订单编号</th>
						<th>产品编码</th>
						<th>产品名称</th>
						<th>包装规格 </th>
						<th>单位 </th>
						<th>生产企业</th>
						<th>订货量 </th>
						<th>配送量 </th>
						<th>单价 </th>
						<th>金额 </th>
						<th>医院名称 </th>
						<th>供应商</th>
						<th>同步时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="pdConsumablesOrderTime">
						<tr>
							<td>
								<fmt:formatDate value="${pdConsumablesOrderTime.orderDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${pdConsumablesOrderTime.number}
							</td>
							<td>
								${pdConsumablesOrderTime.productNumber}
							</td>
							<td>
								${pdConsumablesOrderTime.productName}
							</td>
							<td>
								${pdConsumablesOrderTime.spec}
							</td>
							<td>
								${pdConsumablesOrderTime.unit}
							</td>
							<td>
								${pdConsumablesOrderTime.manufacturer}
							</td>
							<td>
								${pdConsumablesOrderTime.orderQuantity}
							</td>
							<td>
								${pdConsumablesOrderTime.psQuantity}
							</td>
							<td>
								${pdConsumablesOrderTime.price}
							</td>
							<td>
								${pdConsumablesOrderTime.amount}
							</td>
							<td>
								${pdConsumablesOrderTime.hospitalName}
							</td>
							<td>
								${pdConsumablesOrderTime.supplierName}
							</td>
							<td>
								<fmt:formatDate value="${pdConsumablesOrderTime.syncDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
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
			
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
			})
		})
	</script>
</body>
</html>