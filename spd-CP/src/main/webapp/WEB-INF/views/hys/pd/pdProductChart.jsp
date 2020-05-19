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
		.echart{width:100%;}
		.echartWrap{width:50%;height:470px;float: left;box-sizing: border-box;padding:10px;}
		.echartBox{width:100%;height:450px;border: 1px solid #ccc;}
		.echartBoxTop{width:100%;height: 50px;clear: both;border-bottom: 1px solid #ccc;}
		.echartBoxTop h1{float:left;font-size:14px;color:#000;line-height: 50px;margin-left:15px;}
		.echartBoxTop .moreBtn{float: right;cursor: pointer; font-size: 13px;color: #666; padding: 6px 6px; border: 1px solid #ccc;border-radius:3px;margin: 10px 15px 0 0;}
		.echartBoxTop .selectBtn{float: right;font-size: 13px;color: #666;width:120px;height:30px;margin:10px 15px 0 0;}
		.echartBoxBottom{width:100%;height:400px;}
		#chooseTime{width:200px;height:28px;padding:0 0 0 15px;margin:0 0 0 5px;}
		.selectArea select{margin:10px 5px 0 0;width:30%;height:30px;padding:0;}
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
		<div class="chooseTime" style="padding:15px 0 0 15px;">
			时间段<input type="text" id="chooseTime" value="<fmt:formatDate value="${gather.beginTime }" pattern="yyyy-MM-dd" /> - <fmt:formatDate value="${gather.endTime }" pattern="yyyy-MM-dd" />"/>
		</div>
		<div class="echart">
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>采购金额排名TOP10（单位：万元）</h1>
						<%--<a id="queryTable" class="moreBtn">查看表格</a>--%>
					</div>
					<div class="echartBoxBottom" id="demo1"></div>
				</div>
			</div>
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>企业配送金额比率</h1>
						<select class="selectBtn" id="supplierSelect">
							
						</select>
					</div>
					<div class="echartBoxBottom" id="demo2"></div>
				</div>
			</div>
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1 style="float:none;">区域采购情况分析</h1>
						<div>
							<div id='reg_elem' class="newP_reg_area selectArea" style='border:none;width:100%;padding-left:10px;'>
								<select id="selectProvince" data-code="${hospital.areaProvince}" class='newP_reg_margin selectProvince' name="areaProvince" onchange="SetMultiLevelSelect('${ctx}/sys/area/citiesByProvinceCode',this,'selectCity');">
								</select>
								<select id="selectCity" data-code="${hospital.areaCity}"  class='newP_reg_margin selectCity' name="areaCity" onchange="SetMultiLevelSelect('${ctx}/sys/area/countiesByCityCode',this,'selectArea');">
								</select>
								<select id="selectArea" data-code="${hospital.areaTown}" class='newP_reg_margin selectArea' name="areaTown">
								</select>
							</div>
						</div>
					
					<div class="echartBoxBottom" id="demo3"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/echarts.min.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script type="text/javascript">
		var pJson = JSON.parse('${provincelist}');
		$(function(){
			initFirstSelectOnJson("selectProvince",pJson);//初始化省份
			//时间插件
			laydate.render({
				elem: '#chooseTime',
				range: true,
				done:function(value, date, endDate){
					changeDate(value);
				}
			});
			
			//查看表格点击事件
			$("#queryTable").click(function (){
				var time = $("#chooseTime").val(); 
				window.location.href="${ctx}/hys/pdProductGather/consumablesAmountTable?time="+time;
			})
			
			//时间改变事件
			function changeDate(time){
				initPurchaseAmount(time);
				initSupplierList(time);
				$("#selectProvince").val("");
				$("#selectCity").val("");
				$("#selectArea").val("");
				$("#demo3").empty();//先清空避免显示bug
			}
			
			initSupplierList()//初始化供应商列表
			initPurchaseAmount();//初始化采购金额
			
			//初始化采购金额top10
			function initPurchaseAmount(time){
				if(!time){
					time = $("#chooseTime").val(); 
				}
				$.ajax({
					url:"${ctx}/hys/pdProductGather/findPurchaseAmount",
					data:{
						time :time
					},
					type:"POST",
					dataType:"json",
					success:function(data){
						if(data.length==0){
							$("#demo1").empty();//先清空避免显示bug	
						}
						//金额排名
						var syAxis=[];
						var aseries=[];
						for(var i = 0 ; i < data.length ; i ++){
							syAxis.push(data[i].hospitalName);//医院
							aseries.push(data[i].totalAmount);//金额
						}
						option.yAxis.data = syAxis;
						option.series[0].data = aseries;
						var myChart = echarts.init(document.getElementById('demo1'));
						myChart.setOption(option);
					}
				})
			}
			
			//初始化供应商列表
			function initSupplierList(time){
				if(!time){
					time = $("#chooseTime").val(); 
				}
				$.ajax({
					url:"${ctx}/hys/pdProductGather/findSupplierList",
					type:"POST",
					data:{
						time :time
					},
					dataType:"json",
					success:function(data){
						if(data.length==0){
							$("#demo2").empty();//先清空避免显示bug	
						}
						var html = "";
						for(var i=0;i<data.length;i++){
							html += "<option value="+data[i].supplierId+" selected>"+data[i].supplierName+"</option>";
						}
						if(data.length>0){
							$("#supplierSelect").empty().append(html).val(data[0].id);	
							$("#supplierSelect").trigger("change");//触发点击事件
						}
					}
				})
			}
			
			//供应商改变事件
			$("#supplierSelect").change(function(){
				initDistribution();
			})
			
			//初始化配送金额所占比例
			function initDistribution(){
				var supplierId = $("#supplierSelect").val();
				var time = $("#chooseTime").val();
				$.ajax({
					url:"${ctx}/hys/pdProductGather/findPsAmount",
					type:"POST",
					data:{
						time :time,
						supplierId : supplierId
					},
					dataType:"json",
					success:function(data){
						var slegend=[];
						var bseries=[];
						for(var i = 0 ; i < data.length ; i ++){//数据push进数组
							slegend.push(data[i].hospitalName);
							bseries.push({value:data[i].totalAmount,name:data[i].hospitalName});
						}
						option2.legend.data = slegend;
						option2.series[0].data = bseries;
						var myChart2 = echarts.init(document.getElementById('demo2'));
						myChart2.setOption(option2);
					}
				})
			}
			
			//区域改变事件
			
			$("#selectArea").change(function(){
				initCgAmountSituation();
			})
			
			//初始化区域采购金额情况分析
			function initCgAmountSituation(){
				var selectProvince = $("#selectProvince").val();
				var selectCity = $("#selectCity").val();
				var selectArea = $("#selectArea").val();
				var time = $("#chooseTime").val();
				$.ajax({
					url:"${ctx}/hys/pdProductGather/findCgAmountSituation",
					type:"POST",
					data:{
						time :time,
						areaProvince : selectProvince,
						areaCity : selectCity,
						areaTown : selectArea
					},
					dataType:"json",
					success:function(data){
						var sxAxis = [];
						var xseries = [];
						var yseries = [];
						for(var i = 0 ; i < data.length ; i ++){
							sxAxis.push(data[i].name);
							xseries.push(data[i].totalAmount);
							yseries.push(data[i].speciesSize);
						}
						option3.xAxis[0].data = sxAxis;
						option3.series[0].data = xseries;
						option3.series[1].data = yseries;
						var myChart3 = echarts.init(document.getElementById('demo3'));
						myChart3.setOption(option3);
					}
				})
			}
			
			
			//采购金额top10 option
			var option = {
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'shadow'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'value',
				        boundaryGap: [0, 0.01]
				    },
				    yAxis: {
				        type: 'category',
				        data: []
				    },
				    series: [
				        {
				            name: '上月',
				            type: 'bar',
				            itemStyle:{
                                normal:{
                                    color:'#9fdabf'
                                }
                            },
				     	   data:[]
				        }
				    ]
				};
			
			//配送金额所占比例
			var option2 = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:[]
				    },
				    series: [
				        {
				            name:'配送金额',
				            type:'pie',
				            radius: ['50%', '70%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '30',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:[]
				        }
				    ]
				};
			
			//区域采购金额情况分析
			var option3 = {
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        containLabel: true
				    },
				    legend: {
				    	left: 'left',
				        data:['采购金额','采购种类']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: [],
				            axisPointer: {
				                type: 'shadow'
				            }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '金额',
				            axisLabel: {
				                margin: 2,
				                formatter: function (value, index) {
				                    if (value >= 10000 && value < 10000000) {
				                        value = value / 10000 + "万";
				                    } else if (value >= 10000000) {
				                        value = value / 10000000 + "千万";
				                    }
				                    return value;
				                }
				            }
				        },
				        {
				            type: 'value',
				            name: '种类',
				            axisLabel: {
				                formatter: '{value} 种'
				            }
				        }
				    ],
				    series: [
				        {
				            name:'采购金额',
				            type:'bar',
				            data:[]
				        },
				        {
				            name:'采购种类',
				            type:'line',
				            yAxisIndex: 1,
				            data:[]
				        }
				    ]
				};
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