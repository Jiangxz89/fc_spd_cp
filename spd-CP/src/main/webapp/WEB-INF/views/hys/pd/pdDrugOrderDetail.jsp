<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<style>
		.addRoomBox{line-height: 40px;padding:10px 5px;margin-bottom: 20px;}
		.addRoomBox label{width:75px;display: inline-block;text-align: left;}
		.addRoomBox>input[type='text'],.addRoomBox>select{display:inline-block;width: 160px;height:30px;border:1px solid #ccc;margin:0 10px 0 5px;}
		.totalText{text-align: right;height: 50px;line-height: 50px;}
		#allMoney,#allNum{padding:0 50px 0 10px;font-size:15px;color:#000;font-weight: 600;}
		#refuseBtn{background:red;border-color: red;}
		.otherText>.remarkArea{width:280px;height: 60px;border:1px solid #ccc;padding-left:5px;vertical-align:text-top;}
		.otherText>h3{font-weight:400;display:inline-block;padding:3px 10px 0 5px;font-size:12px;color:#666;width:70px;}
	</style>
	<title>药品订单详情</title>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>药品订单详情</h4>
		</div>
		<form:form id="searchForm">
			<div class="searchBox">
				
			</div>
		</form:form>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>订单日期</th>
						<th>订单编号</th>
						<th>医院名称</th>
						<th>订货量</th>
						<th>订单金额</th>
						<th>订单状态</th>
						<th>供应商</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${medstoYpcgmxcfs }" var="medstoYpcgmxcf">
						<tr>
							<td>${medstoYpcgzd.cjrq }</td>
							<td>${medstoYpcgzd.djh }</td>
							<td>${medstoYpcgzd.hospitalName }</td>
							<td>${medstoYpcgmxcf.orderQuantity }</td>
							<td>${medstoYpcgmxcf.orderAmount }</td>
							<td>${fns:getDictLabel(medstoYpcgzd.jlzt, 'drug_order_status', '') }</td>
							<td>${medstoYpcgmxcf.supplierName }</td>
							<shiro:hasPermission name="hys:medstoYpcgzd:view"><td>
								<a class="hcy-btn hcy-btn-o" href="${ctx}/hys/medstoYpcgzd/pdDrugApartDetail?xh=${medstoYpcgmxcf.xh}&djh=${medstoYpcgzd.djh}">查看</a>
							</td></shiro:hasPermission>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
   		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>