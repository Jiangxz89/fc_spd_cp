<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材订单审核</title>
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
			<h4 id="titleName">耗材订单审核</h4>
		</div>
		<div class="searchBox">
			<input id="formType" type="hidden" value="${formType }"/>
			<form:form id="searchForm" modelAttribute="pdConsumablesOrder" action="${ctx}/hys/pdConsumablesOrder/list" method="post" class="breadcrumb form-search">
				<input id="id" name="id" type="hidden" value="${pdConsumablesOrder.id}"/>
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>订单日期</label>
					<input id="orderDate" type="text" htmlEscape="false" maxlength="64" class="input-medium" value="<fmt:formatDate value="${pdConsumablesOrder.orderDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
				</div>
				<div>
					<label>订单编号</label>
					<input name="number" htmlEscape="false" value = " ${pdConsumablesOrder.number}" maxlength="64" class="input-medium" readonly="readonly"/>
				</div>
				<div>
					<label>下单医院</label>
					<input name="hospitalName" htmlEscape="false"  value = " ${pdConsumablesOrder.hospitalName}" maxlength="64" class="input-medium" readonly="readonly"/>
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
						<th>供应商</th>
						<th>订货量 </th>
						<th>单价 </th>
						<th>金额 </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pdConsumablesOrder.pdConsumablesOrderDetails}" var="pdConsumablesOrderDetail">
						<tr>
							<td>
								${pdConsumablesOrderDetail.name}
							</td>
							<td>
								${pdConsumablesOrderDetail.number}
							</td>
							<td>
								${pdConsumablesOrderDetail.spec}
							</td>
							<td>
								${pdConsumablesOrderDetail.unit}
							</td>
							<td>
								${pdConsumablesOrderDetail.venderName}
							</td>
							<td>
								${pdConsumablesOrderDetail.supplierName}
							</td>
							<td>
								${pdConsumablesOrderDetail.orderQuantity}
							</td>
							<td>
								${pdConsumablesOrderDetail.price}
							</td>
							<td>
								${pdConsumablesOrderDetail.amount}
							</td>
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
		<c:if test="${pdConsumablesOrder.orderState eq 2 }">
			<div class="refuseBox otherText">
				<h3>拒绝理由</h3>
				<textarea id="refuseArea" class="remarkArea" readonly="readonly">${pdConsumablesOrder.refuseReason }</textarea>
			</div>
		</c:if>
		<c:if test="${pdConsumablesOrder.orderState eq 0 }">
			<div class="refuseBox" style="display:none;">
				<textarea id="refuseArea" style="width:100%;height:130px;margin-top:20px;" maxlength="100"></textarea>
			</div>
		</c:if>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
  			<a href="###" class="hcy-btn hcy-btn-primary" id="printBtn">通过</a>
  			<a href="###" class="hcy-btn hcy-back" id="refuseBtn" style="background:red;color:#fff;">拒绝</a>
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
		//显示隐藏通关及其
		var formType = $("#formType").val();
		if(formType=="1"){
			$(".bottomBtn #printBtn").css('display','none');//隐藏
			$(".bottomBtn #refuseBtn").css('display','none');//隐藏
			$("#titleName").text("耗材订单详情");
		}

		//通过
		$('#printBtn').click(function(){
			var index=layer.msg('正在提交，请稍等...',{icon: 16,shade: 0.2,time:0});
			auditApplyOrder('1', null);
		});
		
		//拒绝
		$("#refuseBtn").click(function(){
			layer.open({
				type:1,
				title:"拒绝耗材订单审核",
				content:$(".refuseBox"),
				area:["400px","300px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					var reason = $.trim($('#refuseArea').val());
					if(!reason){
						layer.alert('请填写拒绝理由',{icon:0});
						return false;
					}
					auditApplyOrder('2',reason);
					layer.closeAll();
				},
				btn2:function(){
					layer.closeAll();
				}
			});
		});
		
		//审核申请单
		function auditApplyOrder(status, reason){
			$.ajax({
				url:'${ctx}/hys/pdConsumablesOrder/auditConsumablesOrder',
				type:'post',
				data:{id:'${pdConsumablesOrder.id}',number:'${pdConsumablesOrder.number}',orderState:status,refuseReason:reason},
				success:function(data){
					if(data.code==200){
						layer.alert('操作成功', {icon:1});
						window.location.href = '${ctx}/hys/pdConsumablesOrder/list';
					}else{
						if(data.info){
							layer.alert(data.info, {icon:2});
						}else{
							layer.alert('操作失败，请联系管理员', {icon:2});
						}
					}
				 }
			});
		}
	</script>
</body>
</html>