<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>药品汇总统计</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/print.css" media="print">
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css">
	<style>
		.echart{width:100%;clear:both;overflow:hidden;}
		.echartWrap{width:50%;height:470px;float: left;box-sizing: border-box;padding:10px;}
		.echartBox{width:100%;height:450px;border: 1px solid #ccc;}
		.echartBoxTop{width:100%;height: 50px;clear: both;overflow: hidden;border-bottom: 1px solid #ccc;}
		.echartBoxTop h1{float:left;font-size:14px;color:#000;line-height: 50px;margin-left:15px;}
		.echartBoxTop .moreBtn{float: right;cursor: pointer; font-size: 13px;color: #666; padding: 6px 6px; border: 1px solid #ccc;border-radius:3px;margin: 10px 15px 0 0;}
		.echartBoxTop .selectBtn{float: right;font-size: 13px;color: #666;width:120px;height:30px;margin:10px 15px 0 0;}
		.echartBoxBottom{width:100%;height:400px;}
		#chooseTime{width:200px;height:28px;padding:0 0 0 15px;margin:0 0 0 5px;}
		.selectWrap select{float:right;margin:10px 5px 0 0;width:80px;height:30px;padding:0;}
	</style>
</head>
<body>
	<div class="right-main-box">
		<div class="chooseTime" style="padding:15px 0 0 15px;">
			时间段<input type="text" id="chooseTime" value="<fmt:formatDate value="${beginTime }" pattern="yyyy-MM-dd" /> - <fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd" />"/>
		</div>
		<div class="echart">
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>采购金额排名TOP10（单位：万元）</h1>
						<a href="###" class="moreBtn">查看表格</a>
					</div>
					<div class="echartBoxBottom" id="demo1"></div>
				</div>
			</div>
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>企业配送金额比率</h1>
						<select class="selectBtn">
							<option value="1">1供应商</option>
							<option value="2">2供应商</option>
							<option value="3">3供应商</option>
						</select>
					</div>
					<div class="echartBoxBottom" id="demo2"></div>
				</div>
			</div>
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>基药配送比率</h1>
						<select class="selectBtn">
							<option value="1">1医院</option>
							<option value="2">2医院</option>
							<option value="3">3医院</option>
						</select>
					</div>
					<div class="echartBoxBottom" id="demo3"></div>
				</div>
			</div>
			<div class="echartWrap">
				<div class="echartBox">
					<div class="echartBoxTop">
						<h1>区域采购情况分析</h1>
						<div class="selectWrap">
							<select id="selectArea" data-code="${hospital.areaTown}" class='newP_reg_margin selectArea' name="areaTown">
							</select>
							<select id="selectCity" data-code="${hospital.areaCity}"  class='newP_reg_margin selectCity' name="areaCity" onchange="SetMultiLevelSelect('${ctx}/sys/area/countiesByCityCode',this,'selectArea');">
							</select>
							<select id="selectProvince" data-code="${hospital.areaProvince}" class='newP_reg_margin selectProvince' name="areaProvince" onchange="SetMultiLevelSelect('${ctx}/sys/area/citiesByProvinceCode',this,'selectCity');">
							</select>
						</div>
					</div>
					<div class="echartBoxBottom" id="demo4"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/echarts.min.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script type="text/javascript">
		$(function(){
			//时间插件
			laydate.render({
				elem: '#chooseTime',
				range: true
			});
			//金额排名
			var myChart = echarts.init(document.getElementById('demo1'));
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
				        data: ['a医院','b医院','c医院','d医院','e医院','f医院','j医院','h医院','i医院','j医院']
				    },
				    series: [
				        {
				            name: '2011年',
				            type: 'bar',
				            itemStyle:{
                                normal:{
                                    color:'#9fdabf'
                                }
                            },
				            data: [150, 350, 490, 550, 670, 720,820,860,936,1000]
				        }
				    ]
				};
			myChart.setOption(option);
			//配送比率
			var myChart2 = echarts.init(document.getElementById('demo2'));
			var option2 = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:['A医院','B医院','C医院','D医院','E医院']
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
				            data:[
				                {value:335, name:'A医院'},
				                {value:310, name:'B医院'},
				                {value:234, name:'C医院'},
				                {value:135, name:'D医院'},
				                {value:1548, name:'E医院'}
				            ]
				        }
				    ]
				};
			myChart2.setOption(option2);
			//基药配送
			var myChart3 = echarts.init(document.getElementById('demo3'));
			var option3 = {
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: ['基药','非基药']
				    },
				    series : [
				        {
				            name: '比率',
				            type: 'pie',
				            radius : '75%',
				            center: ['50%', '60%'],
				            data:[
				                {value:735, name:'基药'},
				                {value:310, name:'非基药'}
				            ],
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
			myChart3.setOption(option3);
			//区域采购金额
			var myChart4 = echarts.init(document.getElementById('demo4'));
			var option4 = {
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },
				    grid:{
				    	left: '3%',
		                right: '3%',
		                bottom: '4%',
				    	containLabel: true
	                },
				    legend: {
				    	left: 'left',
				        data:['蒸发量','平均温度']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				            axisPointer: {
				                type: 'shadow'
				            }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '金额',
				            min: 0,
				            interval: 50,
				            axisLabel: {
				                formatter: '{value} 万元'
				            }
				        },
				        {
				            type: 'value',
				            name: '种类',
				            min: 0,
				            interval: 5,
				            axisLabel: {
				                formatter: '{value} 种'
				            }
				        }
				    ],
				    series: [
				        {
				            name:'金额',
				            type:'bar',
				            data:[2.0, 4.9, 7.0, 11.2, 25.6, 36.7, 145.6, 14.2, 32.6, 20.0, 6.4, 3.3]
				        },
				        {
				            name:'种类',
				            type:'line',
				            yAxisIndex: 1,
				            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 33.4, 23.0, 16.5, 12.0, 6.2]
				        }
				    ]
				};
			myChart4.setOption(option4);
		})
	</script>		
	<script type="text/javascript">
		var pJson = JSON.parse('${provincelist}');
		$(function(){
			initFirstSelectOnJson("selectProvince",pJson);//初始化省份
			
			//区域改变事件
			$("#selectArea").change(function(){
				initCgAmountSituation();
			});
		});
		//时间改变事件
		function changeDate(time){
			initPurchaseAmount(time);
			initSupplierList(time);
			$("#selectProvince").val("");
			$("#selectCity").val("");
			$("#selectArea").val("");
			$("#demo3").empty();//先清空避免显示bug
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
			var selector=$(thisObj).closest(".selectWrap").find("."+next)[0];
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