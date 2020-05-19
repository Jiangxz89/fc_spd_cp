<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
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
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcgzd" action="${ctx}/hys/medstoYpcgzd/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>订单日期</label>
					<%-- <input type="text" name="queryDate" id="purchaseDate" style="width:160px;" value="${medstoYpcgzd.queryDate }"/> --%>
					<form:input path="queryDate" htmlEscape="false" id="purchaseDate"  readonly="readonly" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>订单编号</label>
					<form:input path="djh" />
				</div>
				</br>
				<div>
					<label>订单状态</label>
					<form:select path="jlzt">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('drug_order_status') }" itemLabel="label" itemValue="value"/>
					</form:select>
				</div>
				<div>
					<label>下单医院</label>
					<form:select path="hospitalCode">
						<form:option value="">全部</form:option>
						<form:options items="${spd:findHospitalList() }" itemLabel="name" itemValue="number"/>
					</form:select>
				</div>
				<c:if test="${empty fns:getUser().supplierId }">
					<div>
						<label>供应商</label>
						<form:select path="ghdwMc">
							<form:option value="">全部</form:option>
							<form:options items="${spd:findSupplierList() }" itemLabel="name" itemValue="name"/>
						</form:select>
					</div>
				</c:if>
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
						<th>下单医院</th>
						<th>订货量</th>
						<th>订单金额</th>
						<!-- <th>供应商</th> -->
						<th>订单状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="drug">
						<tr>
							<td>${drug.cjrq }</td>
							<td>${drug.djh }</td>
							<td>${drug.hospitalName }</td>
							<td>${drug.orderQuantity }</td>
							<td>${drug.orderAmount }</td>
							<!-- <td>-A供应商-</td> -->
							<td>${fns:getDictLabel(drug.jlzt, 'drug_order_status', '') }</td>
							<shiro:hasPermission name="hys:medstoYpcgzd:view"><td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/hys/medstoYpcgzd/pdDrugApartDetail?xh=${drug.xh }&oprt=view">查看</a>
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
			//日期范围
			laydate.render({
			  elem: '#purchaseDate',
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