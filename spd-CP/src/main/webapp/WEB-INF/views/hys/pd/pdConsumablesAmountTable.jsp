<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>耗材汇总统计</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/print.css" media="print">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<style>
		.tableContainer{
			width: 100%;
			height: 100%;
		}
		.table-style {
		  table-layout: fixed;
		  border: 1px solid #ccc;
		  border-collapse: collapse;
		  float: left;
		}

		.table-style tr td {
		  border: 1px solid #e9e9e9;
		  text-align: center;
		  line-height: 40px;
		  padding: 0 5px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#searchForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
					//printFn();
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
	<div class="right-main-box">
		<div class="tableContainer"></div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		$(function(){
			
			function createTables(data, context) {
			    // 判断是否为数组
			    if (Object.prototype.toString.call(data).slice(8, -1).toLowerCase() === "array") {
			        var length = data.length;
			        var dom = document.querySelector(context);
			        var tableCols = 3,
			            heads = ["排名", "医院名称", "采购金额"],
			            tableHead = "<thead><tr>",
			            tableBody = '<tbody>',
			            col = 1,
			            table;

			        for (var i = 0; i < heads.length; i++) {
			            tableHead += '<td>' + heads[i] + '</td>';
			        }
			        tableHead += '</tr></thead>';
			        for (var i = 0; i < length; i++, col++) {
			            tableBody += '<tr>';
			            for (var j = 0; j < heads.length; j++) {
			                tableBody += '<td>' + data[i][j] + '</td>';
			            }
			            tableBody += '</tr>';
			            col = col % tableCols;
			            if (col === 0) {
			                tableBody += '</tbody>';
			                table = document.createElement("table");
			                table.className = "table-style";
			                table.innerHTML = tableHead + tableBody;
			                dom.appendChild(table);
			                tableBody = '<tbody>';
			            }
			        }
			        
			    }
			    console.log($(".tableContainer"));
			}
			init();
			function init(){
				$.ajax({
					url:"${ctx}/hys/pdProductGather/pdConsumablesAmountData",
					type:"POST",
					data:{
						time : '${time}'
					},
					async:false,
					dataType:"json",
					success:function(data){
						if(data.length>0){
							var arrayList = [];
							for(var i = 0 ;i<data.length;i++){
								var array = [];
								array.push(i+1);
								array.push(data[i].hospitalName);
								array.push(data[i].totalAmount);
								arrayList.push(array);
							}
							console.log(arrayList);
							createTables(arrayList,".tableContainer");
						}
					}
				})
			}
		})
		
	</script>							
</body>
</html>