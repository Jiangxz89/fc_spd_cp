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
		<div class="btnBox">
			<h4>药品管理</h4>
			<!-- <a href="###" class="hcy-btn hcy-btn-primary" id="">添加药品</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="import">批量导入</a> -->
		</div>
		<div class="searchBox">
			<form:form id="searchForm" modelAttribute="medstoYpcdmlk" action="${ctx}/spdcp/medstoYpcdmlk/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<div>
					<label>药品编号</label>
					<input type="text" name="" />
				</div>
				<div>
					<label>药品名称</label>
					<input type="text" name="" />
				</div>
				<div>
					<label>药品规格</label>
					<input type="text" name="" />
				</div>
				<div>
					<label>国家基药标志</label>
					<select>
						<option value="">是</option>
						<option value="">否</option>
					</select>
				</div>
				<div>
					<label>抗生素级别</label>
					<select>
						<option value="">非限制使用级</option>
						<option value="">否</option>
					</select>
				</div>
				<div>
					<label>处方药品</label>
					<select>
						<option value="">是</option>
						<option value="">否</option>
					</select>
				</div>
				<div>
					<label>厂家名称</label>
					<input type="text" name="" />
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
						<th>药品编号</th>
						<th>药品名称</th>
						<th>药品规格</th>
						<th>国家基药标志</th>
						<th>抗生素级别</th>
						<th>处方药品</th>
						<th>生产厂家</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="drug">
						<tr>
							<td>${drug.ypdm }</td>
							<td>${drug.ypmc }</td>
							<td>${drug.ypgg }</td>
							<td>${fns:getDictLabel(drug.basicdrugFlag, 'yes_no', '') }</td>
							<td>${drug.kssjb }</td>
							<td>${fns:getDictLabel(drug.cfyp, 'yes_no', '') }</td>
							<td>${drug.cjmc }</td>
							<td>
								<a class="hcy-btn hcy-btn-o" href="${ctx }/spdcp/medstoYpcdmlk/operation?id=${drug.idm }&oprt=view">查看</a>
								<%-- <a class="hcy-btn hcy-btn-o" href="${ctx }/spdcp/medstoYpcdmlk/operation?id=${drug.idm }">修改</a> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">${page}</div>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post" onsubmit="" style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx }/pd/pdProduct/importData" />
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//重置
			$(".hcy-reset").click(function(){
				$(".searchBox div input[type='text']").val("");
				$(".searchBox div select").val("");
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
					arr += th[i].innerText;
					continue;
				}else{
					arr += th[i].innerText+',';
				}
			}
			
			window.location = "${ctx}/hys/pdProduct/generateTemplate?head="+arr;
		}
		
		//全部导出
		function exportData(){
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
					arr += th[i].innerText;
					continue;
				}else{
					arr += th[i].innerText+',';
				}
			}
			
			$("#dataForm").attr("action","${ctx}/hys/pdProduct/exportData?head="+arr);
			$("#dataForm").submit();
		}
		
		//导入
		function importData(){
			//$("#importForm").submit();
			$("#submit").click();
		}
	</script>
</body>
</html>