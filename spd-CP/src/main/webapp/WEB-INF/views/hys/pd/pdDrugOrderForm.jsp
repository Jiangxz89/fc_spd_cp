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
	<title>采购审核单详情</title>
</head>
<body>
	<div class="right-main-box">
		<div class="btnBox">
			<h4>药品订单详情</h4>
		</div>
		<form:form id="searchForm">
			<div class="searchBox">
				<label for="">订单日期</label>
				<input type="text" style="width:170px;" value="${medstoYpcgzd.cjrq }" readonly="readonly"/>
				<label for="">订单编号</label>
				<input type="text" value="${medstoYpcgzd.djh }" readonly="readonly"/>
				<label for="">下单医院</label>
				<input type="text" value="${medstoYpcgzd.hospitalName }" readonly="readonly"/>
			</div>
		</form:form>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>药品名称</th>
						<th>药品编码</th>
						<th>药品规格</th>
						<th>生产企业</th>
						<th>订货量</th>
						<th>单价</th>
						<th>金额</th>
						<th>供应商</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${detailList }" var="li">
						<tr>
							<td>${li.ypmc }</td>
							<td>${li.ypdm }</td>
							<td>${li.ypgg }</td>
							<td>${li.cjmc }</td>
							<td>${li.cgsl }</td>
							<td>${li.ypjj }</td>
							<td>${li.ypjjje }</td>
							<td>${li.ghdwMc }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${medstoYpcgzd.jlzt eq 2 }">
			<div class="refuseBox otherText">
				<h3>拒绝理由</h3>
				<textarea id="refuseArea" class="remarkArea" readonly="readonly">${medstoYpcgzd.refuseReason }</textarea>
			</div>
		</c:if>
		<div class="pagination">${page}</div>
		<c:if test="${medstoYpcgzd.jlzt eq 0 }">
			<div class="refuseBox" style="display:none;">
				<textarea id="refuseArea" style="width:100%;height:130px;margin-top:20px;" maxlength="100"></textarea>
			</div>
		</c:if>
		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
   				<c:if test="${oprt=='audit' }">
   				<%-- <a href="${ctx }/hys/medstoYpcgzd/auditDrugOrder?xh=${medstoYpcgzd.xh }&jlzt=1" class="hcy-btn hcy-btn-primary" id="printBtn">通过</a> --%>
   				<%-- <a href="${ctx }/hys/medstoYpcgzd/auditDrugOrder?xh=${medstoYpcgzd.xh }&jlzt=2" class="hcy-btn hcy-btn-primary" id="refuseBtn">拒绝</a> --%>
   				<a href="###" class="hcy-btn hcy-back" id="printBtn" style="background:red;color:#fff;">通过</a>
  				<a href="###" class="hcy-btn hcy-back" id="refuseBtn" style="background:red;color:#fff;">拒绝</a>
   			</c:if>
   			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
   		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
			
	
		//通过
		$('#printBtn').click(function(){
			auditApplyOrder('1', null);
		});
		
		//拒绝
		$("#refuseBtn").click(function(){
			layer.open({
				type:1,
				title:"拒绝药品订单审核",
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
				url:'${ctx}/hys/medstoYpcgzd/auditDrugOrder',
				type:'post',
				data:{xh:'${medstoYpcgzd.xh }',djh:'${medstoYpcgzd.djh }',jlzt:status,refuseReason:reason},
				success:function(data){
					if(data.code==200){
						layer.alert('操作成功', {icon:1});
						window.location.href = '${ctx}/hys/medstoYpcgzd/list';
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