 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品表管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
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
			$("#searchForm").submit();
        	return false;
        }
		resetTip();	
	</script>
</head>
<body>

	<div class="right-main-box">
		<div class="btnBox">
			<h4>产品管理</h4>
			<%--<a href="${ctx}/hys/pdProduct/form?flag=add" class="hcy-btn hcy-btn-primary">添加耗材</a>--%>
			<%--<a href="###" class="hcy-btn hcy-btn-primary" id="import">批量导入</a>--%>
		</div>
		<sys:message content="${message}"/>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdProduct" action="${ctx}/hys/pdProduct" method="post" class="breadcrumb form-search">
			<div class="searchBox">
				<div>
					<label for="">产品编号</label>
					<input type="text" id="number" name="number" value="${pdProduct.number }"/>
				</div>
				<div>
					<label for="">产品名称</label>
					<input type="text" id="name" name="name" value="${pdProduct.name }"/>
				</div>
				<div>
					<label for="">产品分类</label>
					<select id="categoryId" name="categoryId">
						<option value="">--全部--</option>
						<c:forEach var="pdCategory" items="${categoryList}">  
	                        <option value="${pdCategory.id}" <c:if test="${pdProduct.categoryId eq pdCategory.id}">selected</c:if>>${pdCategory.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品组别</label>
					<select id="groupId" name="groupId">
						<option value="">--全部--</option>
						<c:forEach var="pdGroup" items="${groupList}">  
	                        <option value="${pdGroup.id}" <c:if test="${pdProduct.groupId eq pdGroup.id}">selected</c:if>>${pdGroup.name}</option>  
	                    </c:forEach>
					</select>
				</div>
				<div>
					<label for="">产品型号</label>
					<input type="text" id="version" name="version" value="${pdProduct.version }"/>
				</div>
				<div>
					<label for="">生产厂家</label>
					<select id="venderId" name="venderId" style="width:346px;">
						<option value="">--全部--</option>
						<c:forEach var="pdVender" items="${venderList}">  
	                        <option value="${pdVender.id}" <c:if test="${pdProduct.venderId eq pdVender.id}">selected</c:if>>${pdVender.name}</option>  
	                    </c:forEach> 
					</select>
				</div>
				<div>
					<label for="">医院名称</label>
					<input type="text" id="hospitalName" name="hospitalName" value="${pdProduct.hospitalName }"/>
				</div>
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
				<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset">重置</a>
			</div>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		</form:form>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>产品分类</th>
						<th>产品组别</th>
						<th>单位</th>
						<th>规格</th>
						<th>型号</th>
						<th>生产厂家</th>
						<!-- <th>供应商</th> -->
						<th>注册证号</th>
						<th>出货单价</th>
						<th>医院名称</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<form:form id="dataForm" modelAttribute="productExcelList" method="post" class="breadcrumb form-search">
						<c:forEach items="${page.list }" var="pdProduct" varStatus="a">
							<tr>
								<input type="hidden" value="${pdProduct.id }">
								<td>${pdProduct.number }</td>
								<td>${pdProduct.name }</td>
								<td>${pdProduct.categoryNameShow}</td>
								<td>${pdProduct.groupNameShow}</td>
								<td>${pdProduct.unit }</td>
								<td>${pdProduct.spec }</td>
								<td>${pdProduct.version }</td>
								<td>${pdProduct.venderNameShow}</td>
								<%-- <td>${spd:getSupplierNames(pdProduct.supplierId) }</td> --%>
								<td>${pdProduct.registration }</td>
								<td>${pdProduct.sellingPrice }</td>
								<td>${pdProduct.hospitalName }</td>
								<td>${pdProduct.description }</td>
								<td>
									<a href="${ctx }/hys/pdProduct/toUpdate?id=${pdProduct.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
									<%--<a href="${ctx }/hys/pdProduct/toUpdate?id=${pdProduct.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>--%>
									<%--<a href="${ctx }/hys/pdProduct/delete?id=${pdProduct.id}&number=${pdProduct.number}" data-id="${pdProduct.id}" class="hcy-btn hcy-btn-o" onclick="return confirmx('确认要删除该耗材信息吗？', this.href)">删除</a>--%>
								</td>
							</tr>
						</c:forEach>
					</form:form>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post" onsubmit="" style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx }/hys/pdProduct/importData" />
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		
		//清空查询条件
		function clearConditions(){
			$("#number").val("");
			$("#name").val("");
			$("#categoryId").val("");
			$("#groupId").val("");
			$("#version").val("");
			$("#venderId").val("");
			$("#power").val("");
		}
		$(function(){
			$(document).on("change",".layui-layer-content input[name='file']",function(){
				$(document).find(".layui-layer-content #submit").click();

			})
			//批量导入
			$("#import").click(function(){
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
			});
		})
		
		
		//下载模板
		function template(){
			var th = $("th");
			
			var arr = '';
			for(var i = 0 ; i < th.length ; i ++){
				if(i == 0){
					continue;
				}
				if(i == th.length - 1){
					continue;
				}
				if(i == th.length - 2){
					arr += th[i].textContent;
					continue;
				}else{
					arr += th[i].textContent+',';
				}
			}

			window.location = "${ctx}/hys/pdProduct/generateTemplate?head="+arr;
		}
		//导入
		function importData(){
			//$("#importForm").submit();
			$("#submit").click();
		}
	
	</script>
</body>
</html>