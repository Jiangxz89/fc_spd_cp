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
			<h4>医院管理</h4>
			<a href="${ctx}/hys/pdHospital/form?flag=add" class="hcy-btn hcy-btn-primary">添加医院</a>
			<a href="###" class="hcy-btn hcy-btn-primary" id="import">批量导入</a>
		</div>
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdHospital" action="${ctx}/hys/pdHospital" method="post" class="breadcrumb form-search">
			<div class="searchBox">
				<div>
					<label for="">医院编号</label>
					<input type="text" id="number" name="number" value="${pdHospital.number }"/>
				</div>
				<div>
					<label for="">医院名称</label>
					<input type="text" id="name" name="name" value="${pdHospital.name }"/>
				</div><br/>
				<div>
					<label for="">所属地区</label>
					<div style="display:inline-block;">
						<div id='reg_elem' class="newP_reg_area selectArea" style='border:none;width=610px'>
							<select id="selectProvince" data-code="${hospital.areaProvince}" class='newP_reg_margin selectProvince' name="areaProvince" onchange="SetMultiLevelSelect('${ctx}/sys/area/citiesByProvinceCode',this,'selectCity');">
							</select>
							<select id="selectCity" data-code="${hospital.areaCity}"  class='newP_reg_margin selectCity' name="areaCity" onchange="SetMultiLevelSelect('${ctx}/sys/area/countiesByCityCode',this,'selectArea');">
							</select>
							<select id="selectArea" data-code="${hospital.areaTown}" class='newP_reg_margin selectArea' name="areaTown">
							</select>
						</div>
					</div>
				</div>
				<input id="btnSubmit" class="hcy-btn hcy-search" style="height: inherit;line-height:1.5;" type="submit" value="查询"/>
			</div>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		</form:form>
		<sys:message content="${message}"/>
		<div class="tableBox">
			<table class="hcy-public-table">
				<thead>
					<tr>
						<th>医院编号</th>
						<th>医院名称</th>
						<th>所属地区</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="hos" varStatus="a">
						<tr>
							<td>${hos.number }</td>
							<td>${hos.name }</td>
							<td>${spd:getAreaName(hos.areaProvince) }${spd:getAreaName(hos.areaCity) }${spd:getAreaName(hos.areaTown) }</td>
							<td>${hos.person }</td>
							<td>${hos.tel }</td>
							<td>
								<a href="${ctx }/hys/pdHospital/form?id=${hos.id}&flag=see" class="hcy-btn hcy-btn-o">查看</a>
								<a href="${ctx }/hys/pdHospital/form?id=${hos.id}&flag=update" class="hcy-btn hcy-btn-o">修改</a>
								<a href="${ctx }/hys/pdHospital/delete?id=${hos.id}&number=${hos.number}" data-id="${pdProduct.id}" class="hcy-btn hcy-btn-o removeProBtn"onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<div class="importBox">
		<a href="###" style="margin-top: 80px;margin-right: 60px;" onclick="template()" class="hcy-btn hcy-btn-primary">下载模板</a>
		<div  style="margin-top: 80px;position:relative;" class="hcy-btn hcy-btn-primary">直接导入
			<form id="importForm" method="post" onsubmit="" style="margin:0;position:absolute;left:0;top:0;" enctype="multipart/form-data" action="${ctx }/hys/pdHospital/importData" />
				<input type="file" data-code='0' name="file" style="width:80px;height:32px;;margin:0;opacity:0;z-index:6666;"  class="hcy-btn hcy-btn-primary" value="直接导入"/>
				<input type="submit" id="submit" />
			</form>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
	var pJson = JSON.parse('${provincelist}');
	$(function(){
		initFirstSelectOnJson("selectProvince",pJson);//初始化省份
		//清空查询条件
		function clearConditions(){
			$("#number").val("");
			$("#name").val("");
			$("#areaTown").val("");
		}
	
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
		window.location = "${ctx}/hys/pdHospital/generateTemplate";
	}
	
	$(document).on("change",".layui-layer-content input[name='file']",function(){
		$(document).find(".layui-layer-content #submit").click();

	})
	
	//省份地市区县下拉联动-开始//初始化第一个select数据
	function initFirstSelectOnJson(className,selectlist){
		$("."+className).each(function(){
			var defaultValue = $(this).attr("data-code");
			var selector=$(this)[0];
			selector.innerHTML = "";
			/**==============空选项================**/
			//var nullOptionValue = -1;
			var nullOptionValue = "";
			var nullOptionText = "请选择";
			selector.options.add(new Option(nullOptionText,nullOptionValue));
			if(selectlist!=null&&selectlist.length>0){
		    	for(var i = 0 ; i<selectlist.length; i++){
		     		selector.options.add(new Option(selectlist[i].name,selectlist[i].id));
		     	}
			}
		 	if(selectlist!=null&&selectlist.length>0&&defaultValue!=null){
		 	    for(var i = 0 ; i<selectlist.length; i++){
		     		if(selectlist[i].id==defaultValue){
		     			selector.options[i+1].selected=true;
		     			break;
		     		}
		 	    }
		 	}
		 	if(defaultValue!="" && defaultValue!=null){
		 		SetMultiLevelSelect('${ctx}/sys/area/citiesByProvinceCode',this,'selectCity',$(this).closest('.newP_reg_area').find('.selectCity').attr("data-code"));
				SetMultiLevelSelect('${ctx}/sys/area/countiesByCityCode',$(this).closest('.newP_reg_area').find('.selectCity'),'selectArea',$(this).closest('.newP_reg_area').find('.selectArea').attr("data-code"));
		 	} 
		});
	}
	
	function initSelectOnJson(thisObj,next,selectlist,defaultValue){   //初始化联动下拉框数据
		var selector=$(thisObj).closest(".newP_reg_area").find("."+next)[0];
		selector.innerHTML = "";
		/**==============空选项================**/
			//var nullOptionValue = -1;
		var nullOptionValue = "";
		var nullOptionText = "请选择";
		selector.options.add(new Option(nullOptionText,nullOptionValue));
		if(selectlist!=null&&selectlist.length>0){
	    	for(var i = 0 ; i<selectlist.length; i++){
	     		selector.options.add(new Option(selectlist[i].name,selectlist[i].id));
	     	}
		}
	 	if(selectlist!=null&&selectlist.length>0&&defaultValue!=null){
	 	    for(var i = 0 ; i<selectlist.length; i++){
	     		if(selectlist[i].id==defaultValue){
	     			selector.options[i+1].selected=true;
	     			break;
	     		}
	 	    }
	 	}	
	}
	
	function SetMultiLevelSelect(url,thisObj,next,defaultValue){  //联动
		var selectObj = $(thisObj)[0];
		//省份改变时  清空县级城市
		console.log(next);
		console.log(selectObj.value);
		if(next=="selectCity"){
			if(selectObj.value==""){
				$("#selectCity").empty();
			}
			$("#selectArea").empty();
		}else if(next=="selectArea"){
			if(selectObj.value==""){
				$("#selectArea").empty();
			}
		}
		if(!selectObj.value==""){
			var urls=url+"?id="+selectObj.value;
			jQuery.ajax({
				type: "get",
				async: false,
				url:  urls,  //下拉数据来源请求action URL 
				data:'',
				complete: function() {
				},
				success:function(json) {
					//数据请求成功后，动态创建select中得 option ，数据类型定义为{value:'sel1',text:'one'}
					//createSelectObj(json, nextid);
					var data=eval(json);
					initSelectOnJson(thisObj,next,data,defaultValue);
				}
			});
		}
	}
	</script>
</body>
</html>