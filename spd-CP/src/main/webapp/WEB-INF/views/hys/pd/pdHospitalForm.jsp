<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/addProduct.css" />
	<style>
		
	</style>
	<title>添加医院</title>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					number: {remote: "${ctx}/hys/pdHospital/checkSupplierNameOrNumber?id=" + encodeURIComponent('${hospital.id}')},
					name: {remote: "${ctx}/hys/pdHospital/checkSupplierNameOrNumber?id=" + encodeURIComponent('${hospital.id}')}
				},
				messages: {
					number: {remote: "医院编号已存在"},
					name: {remote: "医院名称已存在"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="pdProduct" enctype="multipart/form-data" method="post">
		<input type="hidden" id="flag" name="flag" value="${flag }" />
		<input type="hidden" name="id" value="${hospital.id }" />
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="addProductBox">
				<label for="">医院编号<span class="mustIcon"  >*</span></label>
				<input type="text" name="number"  id="hospitalCode" value="${hospital.number }" <%--readonly--%> required/>
				<label for="">医院名称<span class="mustIcon">*</span></label>
				<input type="text" name="name" id="hospitalName"  value="${hospital.name }"  required/><br />
				<label for="">所属地区<span class="mustIcon"  >*</span></label>
				<div id='reg_elem' class="newP_reg_area selectArea" style='border:none;display:inline-block;'>
					<select id="selectProvince" data-code="${hospital.areaProvince}"  required class='newP_reg_margin selectProvince' name="areaProvince" onchange="SetMultiLevelSelect('${ctx}/sys/area/citiesByProvinceCode',this,'selectCity');">
					</select>
					<select id="selectCity" data-code="${hospital.areaCity}"  required class='newP_reg_margin selectCity' name="areaCity" onchange="SetMultiLevelSelect('${ctx}/sys/area/countiesByCityCode',this,'selectArea');">
					</select>
					<select id="selectArea" data-code="${hospital.areaTown}" required class='newP_reg_margin selectArea' name="areaTown">
					</select>
				</div><br>
				<label for="">详细地址</label>
				<input type="text" id="proSpm" name="address" value="${hospital.address }"/><br />
				<label for="">联系人<span class="mustIcon"  >*</span></label>
				<input type="text" name="person" value="${hospital.person }" required/>
				<label for="">联系电话<span class="mustIcon">*</span></label>
				<input type="text" name="tel" onblur="limit(this.value)" value="${hospital.tel }" required/><br />
				<label for="">联系人邮箱</label>
				<input type="text" name="mail" onblur="checkEamil(this.value)" value="${hospital.mail }"/>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<c:if test="${flag != 'see' }">
					<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
				</c:if>
       			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
       		</div>
		</div>
	</form:form>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script type="text/javascript">
		var pJson = JSON.parse('${provincelist}');
		$(function(){
			initFirstSelectOnJson("selectProvince",pJson);//初始化省份
			
			//是否可输入
			var flag = $("#flag").val();
			if(flag == 'see'){
				$("input").attr("disabled","disabled");
				$("#selectDiv select").attr("disabled","disabled");
			}
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
		
		function sub(){
			var flag = $("#flag").val();
			if(flag == "add"){
				$("#inputForm").attr("action","${ctx}/hys/pdHospital/save");
			}else if(flag == 'update'){
				$("#inputForm").attr("action","${ctx}/hys/pdHospital/update");
			}
		}
		
		//手机
		function limit(value){
			if(value){
				var phone = value;
				var reg =/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
				var regTel1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//带区号的固定电话
			    var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/;//不带区号的固定电话
			    if(phone.length==11){
			    	if(reg.test(phone) == false){
						layer.alert('联系电话格式不正确',{icon:0});
						$("input[name='tel']").val("");
					 }
			    }else{
			    	if(!regTel1.test(phone) && !regTel2.test(phone)){
			    		layer.alert('联系电话格式不正确',{icon:0});
			    		$("input[name='tel']").val("");
			    	}
			    }
			}
		}
		//邮箱
		function checkEamil(value){
			if(value){
				var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
				if(!reg.test(value)){
					layer.alert('邮箱格式不正确',{icon:0});
					$("input[name='mail']").val("");
				}
			}
		}
		
		
		//生成医院编号
		if($("#hospitalCode").val() == "" || $("#hospitalCode").val() == null){
			$.ajax({
				type:"POST",
				url:"${ctx}/hys/pdHospital/getHospitalCode",
				dataType:"json",
				success:function(data){
					var data = eval(data);
					$("#hospitalCode").val(data);
				}
			})
		}
	</script>
</body>
</html>