<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css" />
	<style>
		.layui-layer-content{text-align:center;}
		#submit{width:80px;height:32px;opacity:0;position:absolute;}
		.layui-layer-content>h4{font-size:14px;font-weight: 400;line-height: 60px;color:#666;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/hys/pdSupplier/");
			$("#searchForm").submit();
        	return false;
        }
/* 		function query(){
			$("#pageNo").val(1);
			$("#searchForm").attr("action","${ctx}/hys/pdSupplier/");
			$("#searchForm").submit();
	    	return false;
	    } */
		resetTip();	
	</script>
</head>
<body>
	<div class="right-main-box"> 
		<div class="btnBox">
			<%-- <a href="${ctx}/hys/pdSupplier/" class="hcy-btn hcy-btn-primary">供应商信息列表</a> --%>
			<h4>供应商信息管理</h4>
			<shiro:hasPermission name="hys:pdSupplier:edit"><a href="${ctx}/hys/pdSupplier/form?flag=add" class="hcy-btn hcy-btn-primary">供应商信息添加</a></shiro:hasPermission>
			<a href="###" class="hcy-btn hcy-btn-primary" id="allImport">批量导入</a>
		</div>
		<div class="searchBox">
			<form:form id="searchForm" style="padding:0;background:none;margin:0;" modelAttribute="pdSupplier" action="${ctx}/hys/pdSupplier/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<!-- <label style="width:80px;">供应商编号：</label>
				<input name="number" htmlEscape="false" maxlength="100" class="input-medium"/> -->
				<label style="width:80px;">供应商名称：</label>
				<input name="name" htmlEscape="false" maxlength="100" class="input-medium"/>
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height:inherit;line-height:1.5 ;" type="submit" value="查询"/>
			<!-- 	<input class="hcy-btn hcy-btn-primary"" type="button" style="height:inherit;line-height:1.5 ;" onclick="batchDelete()" value="批量删除"/>   -->
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table id="contentTable" class="hcy-public-table" style="padding:0;">
				<thead>
					<tr>
						<!-- <th>供应商编号</th> -->
						<th>供应商名称</th>
						<!-- <th>供应商代码</th> -->
						<th>联系人</th>
						<th>联系电话</th>
						<shiro:hasPermission name="hys:pdSupplier:edit"><th>操作</th></shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="pdSupplier">
					<tr>
						<%-- <td>${pdSupplier.number }</td> --%>
						<td>${pdSupplier.name}</td>
						<%-- <td>${pdSupplier.code}</td> --%>
						<td>${pdSupplier.person}</td>
						<td>${pdSupplier.tel}</td>
						<shiro:hasPermission name="hys:pdSupplier:edit"><td>
							<a href="${ctx}/hys/pdSupplier/toUpdate?id=${pdSupplier.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
		    				<a href="${ctx}/hys/pdSupplier/toUpdate?id=${pdSupplier.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>
							<a href="${ctx}/hys/pdSupplier/delete?id=${pdSupplier.id}" class="hcy-btn hcy-btn-o" onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
						</td></shiro:hasPermission>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post"  style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx}/hys/pdSupplier/importData" >
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			//批量导入
			$("#allImport").click(function(){
				layer.open({
					type:1,
					title:"批量导入",
					content:$(".importBox").html(),
					area:["400px","300px"],
					shade: [0.8, '#393D49'],
					btn:["关闭"],
					yes:function(){
						layer.closeAll();
					}
				})
			})
		})
	//全选 
	function cli(Obj)
	 {
		 var collid = document.getElementById("all")
	 	 var coll = document.getElementsByName(Obj)
	 	 if (collid.checked){
	  	 	for(var i = 0; i < coll.length; i++)
	  		coll[i].checked = true;
	 	}else{
	  		for(var i = 0; i < coll.length; i++)
	  		coll[i].checked = false;
	 	}
	 }
	//批量删除
	 function batchDelete(){
		 var text="";  
	      $("input[name=find_watch]").each(function() {  
	         if ($(this).prop("checked")) {  
	             text += ","+$(this).val();  
	         }  
	      }); 
	      if(text != null && text != ''){
	    	  $("#searchForm").attr("action","${ctx}/hys/pdSupplier/batchDelete?pdSupplierIds="+text);
			  $("#searchForm").submit();
	      }else{
	    	  layer.alert("请选择供应商！",{icon:2},function(index){
					layer.close(index);
				});
				return false;
	      }
	      return false;
	 }
	
		$(document).on("change",".layui-layer-content input[name='file']",function(){
			$(document).find(".layui-layer-content #submit").click();

		})
	
	//生成模板
	function template(){
		window.location = "${ctx}/hys/pdSupplier/generateTemplate";
	}
	</script>
</body>
</html>