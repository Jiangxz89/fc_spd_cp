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
	<title>添加供应商</title>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/hys/pdSupplier/checkSupplierName?id=" + encodeURIComponent('${pdSupplier.id}')},
					loginName: {remote: "${ctx}/sys/user/checkSupplierLoginName?oldLoginName=" + encodeURIComponent('${pdSupplier.account}')}
				},
				messages: {
					name: {remote: "供应商已存在"},
					loginName: {remote: "用户登录名已存在"}
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
	<form:form id="inputForm" modelAttribute="pdSupplier" action="" enctype="multipart/form-data" method="post">
		<input type="hidden" id="flag" name="flag" value="${flag }" />
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="relatedBox" style="height:36px;">
				<h3 style="padding-left:0;">添加供应商</h3>
			</div>
			<div class="addProductBox" style="padding-top:10px;padding-bottom:10px;border-bottom:1px solid #ccc;">
				<input type="hidden" name="id" required value="${pdSupplier.id }" />
				<label for="">供应商名称<span class="mustIcon">*</span></label>
				<input type="text" name="name"  value="${pdSupplier.name }"  required/>
				<label for="">联系人<span class="mustIcon">*</span></label>
				<input type="text" name="person" required value="${pdSupplier.person }" /><br>
				<label for="">联系电话<span class="mustIcon">*</span></label>
				<input type="text" name="tel" onblur="limit(this.value)" required value="${pdSupplier.tel }" />
				<label for="">联系人邮箱 </label>
				<input type="text" name="mail" onblur="checkEamil(this.value)" value="${pdSupplier.mail }" /><br>
				<label for="">联系人地址 </label>
				<input type="text" name="address" value="${pdSupplier.address }" />
				<label for="">登录账号<span class="mustIcon">*</span></label>
				<input type="text" name="loginName"  value="${pdSupplier.account }" class="required userName"/>
			</div>
			<div class="relatedBox" style="padding-top:10px;padding-bottom:10px;border-bottom:1px solid #ccc;">
				<h3>关联医院</h3>
				<div class="tableBox">
					<c:if test="${flag ne 'see' }">
						<a class="hcy-btn hcy-btn-primary" id="addHosBtn">选择医院</a>
						<%--<a class="hcy-btn hcy-btn-primary" onclick="del()">删除</a>--%>
					</c:if>
					<table id="contentTable" class="hcy-public-table" style="padding:0;">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
								<th>医院编号</th>
								<th>医院名称 </th>
								<th>所属地区 </th>
								<th>联系人 </th>
								<th>联系电话</th>
								<c:if test="${flag ne 'see' }">
								<th>操作</th>
								</c:if>
							</tr>
						</thead>
						<tbody id="hosTb">
							<c:forEach items="${pdSupplier.hospitalList }" var="hos" varStatus="a">
								<tr>
									<input type="hidden" class="hosId" name="hospitalList[${a.index }].id" value="${hos.id }" />
									<td><input type="checkbox" name="chkList" class="checkOne"/></td>
									<td class="hospitalNumber">${hos.number }</td>
									<td>${hos.name }</td>
									<td>${spd:getAreaName(hos.areaProvince) }${spd:getAreaName(hos.areaCity) }${spd:getAreaName(hos.areaTown) }</td>
									<td>${hos.person }</td>
									<td>${hos.tel }</td>
									<c:if test="${flag ne 'see' }">
									<td><a class="delTr" >删除</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="relatedBox" style="padding-top:10px;padding-bottom:10px;border-bottom:1px solid #ccc;">
				<h3>关联产品</h3>
				<div class="tableBox">
					<c:if test="${flag ne 'see' }">
						<a class="hcy-btn hcy-btn-primary" id="addProBtn">选择产品</a>
						<%--<a class="hcy-btn hcy-btn-primary" onclick="delPro()">删除</a>--%>
					</c:if>
					<table id="contentTablePro" class="hcy-public-table" style="padding:0;">
						<thead>
						<tr>
							<th><input type="checkbox" id="checkAllPro"></th>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>产品分类</th>
							<th>单位</th>
							<th>规格</th>
							<th>型号</th>
							<th>进价</th>
							<th>生产厂家</th>
							<th>所属医院</th>
							<c:if test="${flag ne 'see' }">
							<th>操作</th>
							</c:if>
						</tr>
						</thead>
						<tbody id="proTb">
						<c:forEach items="${pdSupplier.pdProductList}" var="pro" varStatus="b">
							<tr>
								<input type="hidden" class="proId" name="pdProductList[${b.index }].id" value="${pro.id }" />
								<td><input type="checkbox" name="chkList" class="checkOnePro"/></td>
								<td>${pro.number }</td>
								<td>${pro.name }</td>
								<td>${pro.categoryNameShow }</td>
								<td>${pro.unit }</td>
								<td>${pro.spec }</td>
								<td>${pro.version }</td>
								<td>${pro.purPrice }</td>
								<td>${pro.venderNameShow }</td>
								<td>${pro.hospitalName }</td>
								<c:if test="${flag ne 'see' }">
								<td><a class="delTr" >删除</a></td>
								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<c:if test="${flag ne 'see' }">
					<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
				</c:if>
       			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
       		</div>
		</div>
	</form:form>
	<input type="hidden" id="rowsRecord" value="0"/>
	<input type="hidden" id="rowsRecordPro" value="0"/>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script type="text/javascript">
	$(function(){
		
		if($("#flag").val() == 'see' || $("#flag").val() == 'update'){
			$("[name = account]").attr("disabled","disabled");
		}
		
		if($("#flag").val() == 'see'){
			$("input").attr("disabled","disabled");
		}
		
		$("#addHosBtn").click(function(){
			//	showData();
			layer.open({
				type:2,
				title:"添加医院",
				content:'${ctx}/hys/pdHospital/getList',
				area:["965px","527px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					var vue = $('#hosTb>tr').length;
					var childWindow = layero.find('iframe')[0].contentWindow;
					var result = childWindow.compositeHtml(vue);
					if(result === 0){
						layer.alert('请选择医院',{icon: 0});
					}else{
						$('#hosTb').append(result);
						console.log(result)
						$('#rowsRecord').val($('#hosTb>tr').length);
						layer.close(index);
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});

		$("#addProBtn").click(function(){
            var hosLength = $('#hosTb>tr').length;
            if(hosLength <= 0){
                layer.alert('请先选择医院',{icon: 0});
                return;
            }

            var hospitalNumberList = new Array();
            $("#hosTb>tr").each(function(i){
                var hospitalNumber = $(this).find(".hospitalNumber").text();
                if(hospitalNumber){
                    hospitalNumberList[i] = hospitalNumber;
                }
            })

			layer.open({
				type:2,
				title:"添加产品",
				content:'${ctx}/hys/pdProduct/chooseProductList?hospitalNumberList='+hospitalNumberList,
				area:["965px","527px"],
				shade: [0.8, '#393D49'],
				btn:["确定","取消"],
				yes:function(index,layero){
					var vue = $('#proTb>tr').length;
					var childWindow = layero.find('iframe')[0].contentWindow;
					var result = childWindow.compositeHtmlForSupplier(vue);

					if(result === 0){
						layer.alert('请选择产品',{icon: 0});
					}else if(result.startsWith("-")){
						layer.alert(result.substring(1,result.length),{icon: 0});
					}else{
						$('#proTb').append(result);
						$('#rowsRecordPro').val($('#proTb>tr').length);
                        layer.close(index);
					}
				},
				btn2:function(){
					layer.closeAll();
				}
			})
		});

		$("#hosTb").on("click",".delTr",function(){
			$(this).parent().parent().remove();
		});
		$("#proTb").on("click",".delTr",function(){
			$(this).parent().parent().remove();
		});
	})
	
	
	function sub(){
		var flag = $("#flag").val();
		if(flag == 'update'){
			$("#inputForm").attr("action","${ctx }/hys/pdSupplier/update");
		}else if(flag == 'add'){
			$("#inputForm").attr("action","${ctx }/hys/pdSupplier/save");
		}
	}
		
	//重复医院不能添加
	function repetitionDel(target){
		var target = target;
		var f = 0;
		$("#hosTb>tr").each(function(){
			var id = $(this).find(".hosId").val();
			if(id == target){
				f = $(this).children('td').eq(2).text();
				layer.alert(f+' 已被添加。',{icon: 0});
			//	alert('产品 '+f+' 已被添加。');
			}
		})
		if(f!=0){
			return f;
		}else{
			return 'success';
		}
	}

	//重复产品不能添加
    function repetitionPro(target){
        var target = target;
        var f = 0;
        $("#proTb>tr").each(function(){
            var id = $(this).find(".proId").val();

            if(id == target){
                f = $(this).children('td').eq(2).text();
                layer.alert(f+' 已被添加。',{icon: 0});
                //	alert('产品 '+f+' 已被添加。');
            }
        })
        if(f!=0){
            return f;
        }else{
            return 'success';
        }
    }

	$("#checkAll").click(function(){
		if($(this).prop("checked")){
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",true);
		}else{
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",false);
		}
	});

	$("#checkAllPro").click(function(){
		if($(this).prop("checked")){
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",true);
		}else{
			$(this).parents("thead").siblings("tbody").find("input[type=checkbox]").attr("checked",false);
		}
	});

	// function del(){
	//
	// 	if($("#checkAll").prop("checked")){
	// 		$("#checkAll").parents("thead").siblings("tbody").find("tr").remove();
	// 		$("#checkAll").attr("checked",false);
	// 	}
	//
	// 	$(".checkOne").each(function(){
	// 		if($(this).prop("checked")){
	// 			$(this).parent().parent().remove();
	// 		}
	// 	})
	// }
	//
	// function delPro(){
	//
	// 	if($("#checkAllPro").prop("checked")){
	// 		$("#checkAllPro").parents("thead").siblings("tbody").find("tr").remove();
	// 		$("#checkAllPro").attr("checked",false);
	// 	}
	//
	// 	$(".checkOnePro").each(function(){
	// 		if($(this).prop("checked")){
	// 			$(this).parent().parent().remove();
	// 		}
	// 	})
	// }

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
	</script>
</body>
</html>