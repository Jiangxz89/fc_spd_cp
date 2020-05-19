<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材订单管理</title>
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
		resetTip();	
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<h4>耗材订单管理</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="pdConsumablesOrder" action="${ctx}/hys/pdConsumablesOrder/list" method="post" class="breadcrumb form-search">
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
				</br>
				<div>
					<label>订单状态 </label>
					<form:select path="orderState" class="input-medium" >
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('order_type') }" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div>
					<label>下单医院</label>
					<form:input path="hospitalName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<%-- <div>
					<label>供应商</label>
					<form:input path="supplierName" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div> --%>
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
						<th>下单医院 </th>
						<th>订货量 </th>
						<th>订单金额</th>
						<th>订单状态 </th>
						<!-- <th>供应商</th> -->
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="pdConsumablesOrder">
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
								${pdConsumablesOrder.orderQuantity}
							</td>
							<td>
								<fmt:formatNumber value="${pdConsumablesOrder.orderAmount}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
							</td>
							<td>
								${fns:getDictLabel(pdConsumablesOrder.orderState, 'order_type', '')}
							</td>
							<%-- <td>
								<a href="###" title="${pdConsumablesOrder.remarks }" style="width:100px;" class="overLook">${pdConsumablesOrder.remarks }</a>
							</td> --%>
							<shiro:hasPermission name="hys:pdConsumablesOrder:view"><td>
							<c:choose>
								<c:when test="${pdConsumablesOrder.orderState == 1}">
									<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/pdConsumablesOrder/viewIn?id=${pdConsumablesOrder.id}">查看</a>
								</c:when>
								<c:when test="${pdConsumablesOrder.orderState == 3}">
									<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/pdConsumablesOrder/viewIn?id=${pdConsumablesOrder.id}">查看</a>
								</c:when>
								<c:otherwise>
									<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/pdConsumablesOrder/examine?id=${pdConsumablesOrder.id}&formType=1">查看</a>
								</c:otherwise>
								</c:choose>
								<c:if test="${pdConsumablesOrder.orderState == 0}">
								<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/pdConsumablesOrder/examine?id=${pdConsumablesOrder.id}&formType=2">审核</a>
								</c:if>
						</td></shiro:hasPermission>
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

			//科学计数法
			function toNonExponential(num) {
				var m = num.toExponential().match(/\d(?:\.(\d*))?e([+-]\d+)/);
				return num.toFixed(Math.max(0, (m[1] || '').length - m[2]));
			}
		})
	</script>
</body>
</html>