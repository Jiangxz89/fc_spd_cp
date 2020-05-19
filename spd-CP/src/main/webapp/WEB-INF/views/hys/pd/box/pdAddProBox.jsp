<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/addProduct.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/newSearchBox.css">
	<title>添加产品</title>
</head>
<body>
	<form:form id="searchForm" modelAttribute="pdProduct" method="post" action="${ctx}/hys/pdProduct/chooseProductList?remarks=${pdProduct.remarks }">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<div class="newSearchBox" style="text-align: left;">
			<label for="">产品编号</label>
			<input type="text" autocomplete="off" id="productNameInput" name="number" value="${pdProduct.number }"/>
			<label for="">产品分类</label>
			<form:select path="categoryId" class="input-medium">
				<form:option value="">全部</form:option>
				<form:options items="${spd:findPdCategoryList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<label for="" style="margin-left:5px;">产品组别</label>
			<form:select path="groupId"  class="input-medium">
				<form:option value="" label="全部"/>
				<form:options items="${spd:findPdGroupList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			</br>
			<label for="">产品名称</label>
			<input class="select2_material form-control" style="width: 260px" id="productNameSelect" name="name" >
			<label for="" style="margin-left:40px;" >生产厂家</label>
			<form:select path="venderId" class="input-medium" style="width: 260px;">
				<form:option value="" label="全部(qb)"/>
				<form:options items="${spd:findPdVenderList() }" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			</br>
			<%--<c:if test="${fns:getUser().storeroomType == '0'}">--%>
				<%--<label for="" >供应商</label>--%>
				<%--<form:select path="supplierId" class="input-medium" style="width: 260px;">--%>
					<%--<form:option value="" label="全部(qb)"/>--%>
					<%--<form:options items="${spd:findSupplierList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--<label style="margin-left:25px;" for="" >注册证号</label>--%>
				<%--<input type="text" autocomplete="off" id="productRegistrationInput" name="registration" value="${pdProduct.registration }"/>--%>
				<%--</br>--%>
			<%--</c:if>--%>
			<%--<c:if test="${fns:getUser().storeroomType == '1'}">--%>
				<%--<label  for="" >注册证号</label>--%>
				<%--<input type="text" autocomplete="off" id="productRegistrationInput" name="registration" value="${pdProduct.registration }"/>--%>
			<%--</c:if>--%>
			<label for="" >产品规格</label>
			<input type="text" autocomplete="off" id="productSpecInput" name="spec" value="${pdProduct.spec }"/>
			<label for="" >产品型号</label>
			<input type="text" autocomplete="off" id="productVersionInput" name="version" value="${pdProduct.version }"/>
			<label for="" >所属医院</label>
			<input type="text" autocomplete="off" id="hospitalNameInput" name="hospitalName" value="${pdProduct.hospitalName }"/>
			<c:forEach items="${pdProduct.hospitalNumberList}" var="cart" step="1" varStatus="varStatus">
				<input id="hospitalNumberList" name="hospitalNumberList[${varStatus.index}]" type="hidden" value="${cart}"/>
			</c:forEach>
			<input type="submit"  onclick="return page();" value="查询" class="hcy-btn hcy-btn-primary" />
			<a href="###" onclick="clearConditions()" class="hcy-btn hcy-reset" >重置</a>
		</div>
	</form:form>
		<div class="showTableBox" style="width:100%;overflow:auto;">
			<div class="tableBox">
				<table class="hcy-public-table" id="addContentTab">
					<thead>
						<tr>
							<th><input type="checkbox" id="allchoose"/></th>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>产品分类</th>
							<th>产品组别</th>
							<th>单位</th>
							<th>规格</th>
							<th>型号</th>
							<%--<th>注册证号</th>--%>
							<th>进阶</th>
							<th>生产厂家</th>
							<th>所属医院</th>
							<%--<c:if test="${fns:getUser().storeroomType == '0'}">--%>
								<%--<th>供应商</th>--%>
							<%--</c:if>--%>
						</tr>
					</thead>
					<tbody id= "tdodyID">
						<c:forEach items="${page.list }" var="prod">
							<tr style="cursor:pointer"  data-id="${prod.id }" data-number="${prod.number }" data-supplierId="${prod.supplierId }"
								data-name="${prod.name }" data-unit="${prod.unit }" data-spec="${prod.spec }"
								data-version="${prod.version }"	data-price="${prod.sellingPrice }" data-inprice="${prod.purPrice }"
								data-vender="${prod.venderNameShow }"  data-category="${prod.categoryNameShow }"
								data-hospitalnumber="${prod.hospitalNumber }" data-hospitalname="${prod.hospitalName }">
								<td><input type="checkbox" name="checkboxTable" /></td>
								<input type="hidden" class="addProId" value="${prod.id }" />
								<%--<td class="addProNum"><a href="###" title="${prod.number }" >${prod.number }</a></td>
								<td class="addProName"><a href="###"  title="${prod.name }">${prod.name }</a></td> 2019年7月1日10:53:25 输入优化--%>
								<td class="addProNum">${prod.number }</td>
								<td class="addProName">${prod.name }</td>
								<td class="addProType">${prod.categoryNameShow }</td>
								<td class="addProGroup">${prod.groupNameShow }</td>
								<td class="addProUnit">${prod.unit }</td>
								<td class="addProformat">${prod.spec }</td>
								<td class="addVersion">${prod.version }</td>
								<td class="addPurPrice">${prod.purPrice }</td>
								<%--<td class="addProFoc">${prod.registration }</td>--%>
								<td class="addProFoc">${prod.venderNameShow }</td>
								<td class="addHospitalName">${prod.hospitalName }</td>
								<%--<c:if test="${fns:getUser().storeroomType == '0'}">--%>
									<%--<td class="addProFoc">${prod.supplierNameShow }</td>--%>
								<%--</c:if>--%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		<div class="pagination">${page }</div>
	</div>
	<!--  -->
	<%--<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>--%>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script src="${ctxStatic}/spd-CP/js/pinying.js"></script>
	<script type="text/javascript">
		$(function(){

            var select2 = function () {
                $("#productNameSelect").select2({
                    dropdownParent: $('.modal-content'),
                    /*placeholder: "产品名称",*/
                    allowClear: true,
                    ajax: {
                        url: '${ctx}/hys/pdProduct/findList',
                        dataType: 'json',
                        data: function (params) {
                            var query = {
                                name: params //params.term 搜索参数值
                            }
                            return query;
                        },
                        results: function (data) {
                            //返回最终数据data 给dataArray
                            var dataArray = [];
                            for (var i = 0; i < data.length; i++) {
                                var dataObj = {};
                                dataObj.id = data[i].name;
                                dataObj.text = data[i].name ;
                                dataArray.push(dataObj);
                            }
                            return {
                                results: dataArray
                            };
                        },
                        error: function (error) {
                        }
                    }
                });
			}
            select2();


            $("#tdodyID tr").click(function () {
                var input = $(this).find("input[name='checkboxTable']");//获取checkbox
                if(input.attr("checked")){
                    input.attr("checked",false);
                }else{
                    input.attr("checked",true);
                }
            });

            //checkbox冒泡事件
            $("input[name='checkboxTable']").click(function(e) {
                e.stopPropagation();
            })
                //全选与反选
            $("#allchoose").click(function(){
                if($(this).attr('checked')){
                    $("input[name='checkboxTable']").attr('checked','true');
                }else{
                    $("input[name='checkboxTable']").removeAttr('checked');
                }
            });

            $("#checkboxInput").on("click",function(){
                if($("#checkboxInput").is(':checked')){
                    $("[name='isLimitDown']").val("true");
                }else{
                    $("[name='isLimitDown']").val("false");
                }
            });

            //add by jiangxz 20190905 用于查询后回显是否勾选了超出库房下限产品
            var isLimitDown = '${isLimitDown}';
            if(isLimitDown == "true"){
                $("#checkboxInput").attr('checked', true);
                $("[name='isLimitDown']").val("true");
            }else{
                $("#checkboxInput").attr('checked', false);
                $("[name='isLimitDown']").val("false");
            }
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		//新增产品  本方法不是共用方法，若要使用请新建一个方法。add by jiangxz 20191031
		function compositeHtmlForSupplier(index){
            // var chObj = $("input[type='checkbox']:gt(0):checked");
            var chObj = $("input[name='checkboxTable']:checked");// modified by jiangxz 20190904 不建议用gt(0)来遍历元素，gt(0)不是精确的
			if(chObj.length < 1)
				return 0;
			var html = '';
			var indexVue = null;
			var resultHtml = "";
			chObj.each(function(i,v){
				indexVue = Number(index) + Number(i);
				var $this = $(this).parent().parent();
				if($.inArray($this.data('id'), parent.prods) > -1){
					return true;//继续下一循环，类似for里的continue;
				}

				var b = window.parent.repetitionPro($this.find(".addProId").val());

				if(b != 'success') {
					return true;
				}

				html += '<tr class="checkOnePro remove_'+$this.data("id")+'" data-id="'+$this.data("id")+'">'
                        +   '<input type="hidden" class="proId" name="pdProductList['+indexVue+'].id" value="'+$this.data("id")+'">'
						+		'<td><input type="checkbox" name="chkList"  class="checkOne"/></td>'
						+		'<td>'+$this.data("number")+'</td>'
						+       '<td>'+$this.data("name")+'</td>'
				    	+       '<td>'+$this.data("category")+'</td>'
						+		'<td>'+$this.data("unit")+'</td>'
						+		'<td>'+$this.data("spec")+'</td>'
						+		'<td>'+$this.data("version")+'</td>'
						+       '<td>'+$this.data("inprice")+'</td>'
						+       '<td>'+$this.data("vender")+'</td>'
						+       '<td>'+$this.data("hospitalname")+'</td>'
						+       '<td><a class="delTr" >删除</a></td>'
					 	// +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodId" value="'+$this.data("id")+'"/>'
					 	// +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].prodNo" value="'+$this.data("number")+'"/>'
						// +      '<input type="hidden" name="purchaseDetailList['+indexVue+'].hospitalNumber" value="'+$this.data("hospitalnumber")+'"/>'
					 	+'</tr>';
					 // parent.prods.push($this.data("id"));
			});

			return html;

		}
		//-申领单新增产品

        //清空查询条件
        function clearConditions(){
            $("#productNameInput").val("")
            $("#productNameSelect").val("")
            $("#productNameSelect").select2("data", "");
            $("#categoryId").select2("val", "");
            $("#groupId").select2("val", "");
            $("#venderId").select2("val", "");
            $("#supplierId").select2("val", "");
            $("#productSpecInput").val("");
            $("#productVersionInput").val("");
            // $("#productRegistrationInput").val("");
            $("#checkboxInput").attr('checked', false);
            $("[name='isLimitDown']").val("");
			$("#hospitalNameInput").val("");
        }
	</script>
</body>
</html>