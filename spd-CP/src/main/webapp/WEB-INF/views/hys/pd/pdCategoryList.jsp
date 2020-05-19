<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<style>
		.highProList .hightTop,.lowProList .lowTop{height: 32px;line-height: 32px; padding-left: 32px;margin:8px 0;}
		.highProList .hightTop>h3,.lowProList .lowTop>h3{font-size:13px;color:#666;font-weight:400;float:left;cursor:pointer;}
		.highProList .hightTop>#addNewHigh,.lowProList .lowTop>#addNewLow{float:right;}
		.highProList .highBottom,.lowProList .lowBottom{padding:0 15px;}
		.highProList .highBottom>ul,.lowProList .lowBottom>ul{margin:0;}
		.highProList .highBottom>ul>li,.lowProList .lowBottom>ul>li{height:40px;line-height:40px;background:#F2FAFF;}
		.highProList .highBottom>ul>li:nth-child(2n),.lowProList .lowBottom>ul>li:nth-child(2n){background:#fff}
		.highProList .highBottom>ul>li:hover,.lowProList .lowBottom>ul>li:hover{background:#D3EDFF;}
		.highProList .highBottom>ul>li>span,.lowProList .lowBottom>ul>li>span{padding-left:33px;float:left;}
		.highProList .highBottom>ul>li>a,.lowProList .lowBottom>ul>li>a{float:right;margin: 6px 20px 0 0;}
		.fa-sort-down{vertical-align: text-top; padding-left: 5px;}
		.fa-sort-up{vertical-align: text-bottom; padding-left: 5px;}
	</style>
	<title>产品分类管理</title>
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
	
	<!--<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdCategory" action="${ctx}/hys/pdGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form> -->
	<input type="hidden" name="delKey" value="${delKey }" />
	<div class="right-main-box">
		<div class="btnBox" style="border-bottom:1px solid #ccc;padding-bottom:5px;">
			<h4 style="height:36px;">产品分类管理</h4>
		</div>
		<div class="tableBox">
			
		</div>
		
	</div>
	<sys:message content="${message}"/>
	<!-- ...添加高值耗材 -->
	<div class="addHighBox" style="display: none;">
		<form:form id="inputForm" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/hys/pdCategory/save?type=1" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	
	<!-- ...修改高值耗材 -->
	<div class="updateClassifyBox" style="display: none;">
		<form:form id="inputForm2" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/hys/pdCategory/update" method="post" class="form-horizontal">
			<input type="hidden" id="categoryId" name="id" />
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addClassifyInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	
	<!-- ...添加低值耗材 -->
	<div class="addLowBox" style="display: none;">
		<form:form id="inputForm3" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/hys/pdCategory/save?type=0" method="post" class="form-horizontal">
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	
	<!-- ...修改低值耗材 -->
	<div class="updateLowBox" style="display: none;">
		<form:form id="inputForm4" modelAttribute="pdCategory" style="padding:20px 0 0 15px" action="${ctx}/hys/pdCategory/update" method="post" class="form-horizontal">
			<input type="hidden" id="categoryId" name="id" />
			<label class="addClassifyLab">分类名称</label>
			<input type="text" class="addLowInp" style="width:160px;height:30px;border:1px solid #ccc;" name="name" required/>
			<span class="mustIcon" style="display: none;">请填写分类名称</span>
		</form:form>
	</div>
	<div class="listBox">
		<div class="highProList">
			<div class="hightTop">
				<h3>高值耗材<i class="fa fa-sort-down"></i></h3>
				<%--<a href="###" id="addNewHigh" class="hcy-btn hcy-btn-primary">新增分类</a>--%>
			</div>
			<div class="highBottom">
				<ul>
					<c:forEach items="${list }" var="list">
						<c:if test="${list.type eq '1'}">
							<li>
								<input type="hidden" name="id" value="${list.id }" />
								<span>${list.name }</span>
								<%--<a href="###" data-id="${list.id}"   class="hcy-btn hcy-btn-o delHighBtn">删除</a>--%>
								<%--<a href="###" class="hcy-btn hcy-btn-o modifyHighBtn">修改</a>--%>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="lowProList">
			<div class="lowTop">
				<h3>低值耗材<i class="fa fa-sort-down"></i></h3>
				<%--<a href="###" id="addNewLow" class="hcy-btn hcy-btn-primary">新增分类</a>--%>
			</div>
			<div class="lowBottom">
				<ul>
					<c:forEach items="${list }" var="list">
						<c:if test="${list.type eq '0'}">
							<li>
								<input type="hidden" name="id" value="${list.id }" />
								<span>${list.name }</span>
								<%--<a href="###" data-id="${list.id }"   class="hcy-btn hcy-btn-o delLowBtn">删除</a>--%>
								<%--<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn">修改</a>--%>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- <div class="pagination">${page}</div> -->
	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
			})
			
			//删除标记判断，error删除失败
			var delKey = $("[name = delKey]").val();
			if(delKey == 'error'){
				layer.alert("该分类有关联产品，不可删除！",{icon:0},function(index){
					layer.close(index);
				});
			}
			
			//高值耗材切换隐藏
			$(".highProList>.hightTop>h3").click(function(){
				$(".highBottom>ul").toggle();
				if($(".highBottom>ul").is(":hidden")){
					$(this).find(".fa").attr("class","fa fa-sort-up")
				}else{
					$(this).find(".fa").attr("class","fa fa-sort-down")
				}
			})
			//高值耗材    新增分类
			$("#addNewHigh").click(function(){
				$(".addClassifyInp").val("");
				layer.open({
					type:1,
					title:"新增产品分类",
					content:$(".addHighBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addClassifyInp").val();
						if(addInpText!="" && addInpText!=undefined){
							var newLi='<li>'+
										'<span>'+addInpText+'</span>'+
										'<a href="###" class="hcy-btn hcy-btn-o delHighBtn">删除</a>'+
										'<a href="###" class="hcy-btn hcy-btn-o modifyHighBtn">修改</a>'+
									'</li>'
							$(".highProList>.highBottom>ul").append(newLi);
							$(".mustIcon").hide();
							
							
							var a = layero.find("[name = name]").val();
							layero.find(".addClassifyInp").val("")
							window.location = "${ctx}/hys/pdCategory/save?name="+a+"&type=1";
							//$("#inputForm").submit();
							layer.closeAll();
						}else{
							$(".mustIcon").show();
							return false;
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			//高值耗材  删除
		$(document).on("click",".delHighBtn",function(){
				var that=$(this);
				var href="${ctx}/hys/pdCategory/delete";
				var id=$(this).attr("data-id");
				//${ctx}/hys/pdGroup/delete?id=${pdGroup.id}
				layer.confirm(
					"确定要删除该分类吗？",//这里写询问层里面的文本
					{
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index,layero){
					//	layero.find(that.parent().remove())
						layer.close(index);
						//alert(href+"?id="+id);
						window.location.href=href+"?id="+id
					},
					function(index){
						layer.close(index);
					}
				)
			})
 			//高值耗材   修改
			$(document).on("click",'.modifyHighBtn',function(){
				var modifyVal=$(this).parent().find("span").text();
				var ind=$(this).parent().index();
				$(".addClassifyInp").val(modifyVal);
				layer.open({
					type:1,
					title:"修改产品分类",
					content:$(".updateClassifyBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addClassifyInp").val();
						if(addInpText!="" && addInpText!=undefined){
							$(".highProList>.highBottom>ul>li").eq(ind).find("span").text(addInpText);
							var id = $(".highProList>.highBottom>ul>li").eq(ind).find("input").val();
							$(".mustIcon").hide();
							
							window.location = "${ctx}/hys/pdCategory/update?id="+id+"&type=1&name="+addInpText;
							
							layer.closeAll();
						}else{
							$(".mustIcon").hide();
							layer.closeAll();
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			
			//低值耗材切换隐藏
			$(".lowTop>h3").click(function(){
				$(".lowBottom>ul").toggle();
				if($(".lowBottom>ul").is(":hidden")){
					$(this).find(".fa").attr("class","fa fa-sort-up")
				}else{
					$(this).find(".fa").attr("class","fa fa-sort-down")
				}
			})
			//低值耗材    新增分类
			$("#addNewLow").click(function(){
				$(".addLowInp").val("");
				layer.open({
					type:1,
					title:"新增产品分类",
					content:$(".addLowBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addLowInp").val();
						if(addInpText!="" && addInpText!=undefined){
							var newLi='<li>'+
										'<span>'+addInpText+'</span>'+
										'<a href="###" class="hcy-btn hcy-btn-o delLowBtn">删除</a>'+
										'<a href="###" class="hcy-btn hcy-btn-o modifyLowBtn">修改</a>'+
									'</li>'
							$(".lowBottom>ul").append(newLi);
							$(".mustIcon").hide();
							layero.find(".addClassifyInp").val("")
							//$("#inputForm").submit();
							
							var a = layero.find("[name = name]").val();
							window.location = "${ctx}/hys/pdCategory/save?name="+a+"&type=0";
							layer.closeAll();
						}else{
							$(".mustIcon").show();
							return false;
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			//低值耗材  删除
			$(document).on("click",".delLowBtn",function(){
				var that=$(this);
				var href="${ctx}/hys/pdCategory/delete";
				var id=$(this).attr("data-id");
				layer.confirm(
					"确定要删除吗？",//这里写询问层里面的文本
					{
						icon:3,
						title:"提示",
						btn:["确定","取消"]
					},
					function(index,layero){
					//	layero.find(that.parent().remove())
						layer.close(index);
						window.location.href=href+"?id="+id
					},
					function(index){
						layer.close(index);
					}
				)
			})
			//低值耗材   修改
			$(document).on("click",'.modifyLowBtn',function(){
				var modifyVal=$(this).parent().find("span").text();
				var ind=$(this).parent().index();
				$(".addLowInp").val(modifyVal);
				layer.open({
					type:1,
					title:"修改产品分类",
					content:$(".updateLowBox"),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["确定","取消"],
					yes:function(index,layero){
						var addInpText=layero.find(".addLowInp").val();
						if(addInpText!="" && addInpText!=undefined){
							$(".lowBottom>ul>li").eq(ind).find("span").text(addInpText);
							var id = $(".lowBottom>ul>li").eq(ind).find("input").val();
							$(".mustIcon").hide();
							window.location = "${ctx}/hys/pdCategory/update?id="+id+"&type=0&name="+addInpText;
							layer.closeAll();
						}else{
							$(".mustIcon").hide();
							layer.closeAll();
						}
					},
					btn2:function(){
						$(".mustIcon").hide();
						layer.closeAll();
					}
				})
			});
			
			
		})
	</script>
</body>
</html>