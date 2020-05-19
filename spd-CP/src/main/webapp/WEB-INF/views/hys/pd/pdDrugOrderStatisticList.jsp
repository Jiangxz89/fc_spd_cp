<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品订单及配送查询</title>
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
			<h4>药品订单及配送查询</h4>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcgzd" action="${ctx}/hys/medstoYpcgzd/drugOrderStatistic" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>订单日期</label>
					<form:input path="queryDate" htmlEscape="false" id="inHomeTime"  readonly="readonly" maxlength="64" class="input-medium"/>
				</div>
				<div>
					<label>订单编号</label>
					<form:input path="djh" htmlEscape="false" maxlength="18" class="input-medium"/>
				</div>
				<div>
					<label>订单状态</label>
					<form:select path="jlzt" class="input-medium">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictList('drug_order_status') }" itemLabel="label" itemValue="value"/>
					</form:select>
				</div>
				<div>
					<label>药品名称</label>
					<form:input path="medstoYpcgmx.ypmc" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				</br>
				<div>
					<label>药品编码</label>
					<form:input path="medstoYpcgmx.ypdm" htmlEscape="false" maxlength="18" class="input-medium"/>
				</div>
				<div>
					<label>生产企业</label>
					<form:input path="cjmc" htmlEscape="false" maxlength="64" class="input-medium"/>
				</div>
				<input id="btnSubmit" style="height:inherit;line-height:1.5 ;" class="hcy-btn hcy-search" type="submit" value="查询"/>
				<input type="button" class="hcy-btn hcy-reset" style="line-height:1.5;height: inherit;" value="重置"/>
			</form:form>
		</div>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<th>订单日期</th>
						<th>订单编号</th>
						<th>药品编码</th>
						<th>药品名称</th>
						<th>药品规格 </th>
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
					<c:forEach items="${page.list}" var="order">
						<tr>
							<td>
								<%-- <fmt:formatDate value="${order.cjrq}" pattern="yyyy-MM-dd"/> --%>
								${order.cjrq}
							</td>
							<td>
								${order.djh}
							</td>
							<td>
								${order.medstoYpcgmx.ypdm}
							</td>
							<td>
								${order.medstoYpcgmx.ypmc}
							</td>
							<td>
								${order.medstoYpcgmx.ypgg}
							</td>
							<td>
								${order.medstoYpcgmx.medstoYpcdmlk.cjmc}
							</td>
							<td>
								${order.medstoYpcgmx.cgsl}
							</td>
							<td>
								${order.medstoYpcgmx.rksl}
							</td>
							<td>
								${order.medstoYpcgmx.ypjj}
							</td>
							<td>
								
							</td>
							<td>
								${order.ksdm}
							</td>
							<td>
								
							</td>
							<td>
								<%-- <fmt:formatDate value="${order.cjrq}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
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