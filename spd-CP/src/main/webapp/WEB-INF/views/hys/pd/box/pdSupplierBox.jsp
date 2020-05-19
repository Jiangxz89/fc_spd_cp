<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<title>添加医院</title>
</head>
<body>
	<div class="addProBox">
		<form:form id="searchForm" style="padding:0;background:#fff;" modelAttribute="pdHospital" action="${ctx}//hys/pdHospital/getList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
		<table class="hcy-public-table" id="addContentTab" style="padding-top:0;">
			<thead>
				<tr>
					<th><input type="checkbox" id="chkAll" name="chkAll" /></th>
					<th>医院编号</th>
					<th>医院名称</th>
					<th>所属地区</th>
					<th>联系人</th>
					<th>联系电话</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${page.list }" var="hos">
					<tr>
						<td><input type="checkbox" name="chkList"  class="checkOne"/></td>
						<input type="hidden" class="addHosId" value="${hos.id }" />
						<td class="addHosNum">${hos.number }</td>
						<td class="addHosName">${hos.name }</td>
						<td class="addHosArea">${spd:getAreaName(hos.areaProvince) }${spd:getAreaName(hos.areaCity) }${spd:getAreaName(hos.areaTown) }</td>
						<td class="addHosPerson">${hos.person }</td>
						<td class="addHosTel">${hos.tel }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
		</form:form>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript">
		var pJson = JSON.parse('${provincelist}');
		$(function(){
			initFirstSelectOnJson("selectProvince",pJson);//初始化省份
			//全选与反选
			$("#chkAll").click(function(){
				if($(this).attr('checked')){
					$("input[type='checkbox']").attr('checked','true');
				}else{
					$("input[type='checkbox']").removeAttr('checked');
				}
			});
			
			//生产厂家改变
	/* 		$("[name=venderId]").change(function(){
				var name = $("[name=venderId]>option:checked").text();
				var id = $("[name=venderId]>option:checked").val();
				$("[name=venderName]").val(name);
				$("[name=venderId]").val(id);
			}) */
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function compositeHtml(index){
			var chObj = $("input[type='checkbox']:gt(0):checked");
			if(chObj.length < 1)
				return 0;
			var html = '', indexVue = null;
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1)
					return true;//继续下一循环，类似for里的continue;
					
				var b = window.parent.repetitionDel($this.find(".addHosId").val());
				
				if(b != 'success')
					return true;
				
				html+='<tr>'+
						'<input type="hidden" class="hosId" name="hospitalList['+indexVue+'].id" value="'+$this.find(".addHosId").val()+'">'+
						'<td><input type="checkbox" name="chkList"  class="checkOne"/></td>'+
						//'<td><input type="text" required name="hospitalList['+indexVue+'].midCode" style="width:160px" /></td>'+
						'<td class="hospitalNumber">'+$this.find(".addHosNum").text()+'</td>'+
						'<td>'+$this.find(".addHosName").text()+'</td>'+
						'<td>'+$this.find(".addHosArea").text()+'</td>'+
						'<td>'+$this.find(".addHosPerson").text()+'</td>'+
						'<td>'+$this.find(".addHosTel").text()+'</td>'+
                        '<td><a class="delTr" >删除</a></td>'+
						'</tr>'
			//$("#fixedNumTable>tbody").html("");
			});
			return html;
		}
		
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