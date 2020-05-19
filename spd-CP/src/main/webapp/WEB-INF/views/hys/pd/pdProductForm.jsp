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
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
	<style>
		#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
		.showpic{cursor:pointer}
		.areaTxtBox{display: inline-block; border: 1px solid #ccc;padding: 5px; border-radius: 2px;}
		.addProductBox select option{color:#666}
		.addOption{z-index:66;}
		.selectMoreBox{width: 260px;height: 30px;position: relative;display:inline-block; border: 1px solid #ccc;margin:0px;border-radius: 3px;padding-left: 5px;box-sizing: border-box;vertical-align:middle;}
		.selectedBox{width: 100%;height: 100%;position: absolute;left: 0;top:0}
		.selectedItem{ user-select: none;line-height: initial;height: 23px; float: left;margin: 3px 0 0 5px;padding: 2px; border: 1px solid #ccc;border-radius: 3px;font-size: 13px;background: #ccc;color: #666;}
		.selectedItem>.close{padding-left: 5px;font-size: 13px; cursor: pointer;}
		.selectMoreList{width:300px;height:100px;display: none;margin:0;background:#fff; overflow-x:hidden; overflow-y:auto;position: absolute;left:0;top:32px;border:1px solid #ccc;border-radius: 3px;box-sizing: border-box;}
		.selectMoreList li{width: 100%;height: 30px;font-size:13px;user-select: none; line-height: 30px;color:#666;padding-left:10px;}
		.selectMoreList li:hover{background: #87CEFA;}
	</style>
	<title>添加耗材</title>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
			//点击空白处隐藏
			$(document).click(function(e){
				 e = e || window.event;
                var target = e.target || e.srcElement;
                var tables = document.getElementsByClassName('proClassify');
                if(target.className != 'proClassify'){
                    $(".addOption").hide();
                }
			})
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="pdProduct" enctype="multipart/form-data" method="post">
		<input type="hidden" id="flag" name="flag" value="${flag }" />
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="right-main-box">
			<div class="btnBox" style="border-bottom:1px solid #ccc;height:36px;">
				<h4 style="padding-left:0;">添加耗材</h4>
			</div>
			<div class="addProductBox">
				<label for=""><span class="mustIcon">*</span>产品编号</label>
				<input type="text" name="number" required id="number" value="" />
				<a href="###" onclick="generateNumber()" class="hcy-btn hcy-btn-primary">生成</a>
				<a href="###" class="hcy-btn hcy-btn-primary" id="codePrint">条码打印</a><br />
				<label for=""><span class="mustIcon">*</span>产品名称</label>
				<input type="text" name="name" id="proInp" onKeyUp="query()"  required="required"/>
				<label for="">首拼码</label>
				<input type="text" id="proSpm" name="spm" /><br />
				<label for=""><span class="mustIcon"  >*</span>规格</label>
				<input type="text" name="spec"  required/>
				<label for=""><span class="mustIcon">*</span>型号</label>
				<input type="text" name="version" required/><br />
				<label for=""><span class="mustIcon">*</span>单位</label>
				<input type="text" name="unit" required/>
				<div class="addSelect">
					<label for=""><span class="mustIcon">*</span>产品组别</label>
					<input type="hidden" name="groupId" value="${prod.groupId }"/>
					<input type="text" readonly autocomplete="off" name="groupName" value="${spd:getGroupName(prod.groupId) }"  required placeholder="--请选择--" class="proClassify"/>
					<ul id="addGroup" class="addOption">
						<c:forEach var="pdGroup" items="${groupList}">  
	                        <li>${pdGroup.name}</li> <input type="hidden" value="${pdGroup.id }" /> 
	                    </c:forEach> 
						<li><i class="fa fa-plus"></i>新增产品组别</li>
					</ul>
				</div><br />
				<label for=""><span class="mustIcon">*</span>产品类型</label>
				<select style="width:260px;height:30px;" name="type" onchange="typeChange()" id="prodType" value="${prod.type }"  required>
					<option onclick="typeChange()" value="">--请选择--</option>
					<c:forEach var="dict" items="${fns:getDictList('product_type') }">
						<option <c:if test="${prod.type eq (dict.label eq '高耗值型'?'1':'0') }">selected</c:if> value="${dict.label eq '高耗值型'?'1':'0' }">${dict.label }</option>
					</c:forEach>
				</select>
				<div class="addSelect">
					<label for=""><span class="mustIcon">*</span>产品分类</label>
					<input type="hidden" name="categoryId" />
					<input type="text" autocomplete="off" readonly name="categoryName" value="${spd:getCategoryName(prod.categoryId) }"  required class="proClassify" placeholder="--请选择--"/>
					<ul id="addCategory" class="addOption">
						<li><i class="fa fa-plus"></i>新增产品分类</li>
					</ul>
				</div><br />
				<label for=""><span class="mustIcon">*</span>生产厂家</label>
				<select style="width:260px;height:30px;" id="venderId" name="venderId" required>
					<option value="">--请选择--</option>
					<c:forEach var="pdVender" items="${venderList}">  
                        <option value="${pdVender.id}" <c:if test="${pdVender.id eq prod.venderId}">selected</c:if>>${pdVender.name}</option>  
                    </c:forEach> 
				</select>
				<br />
				<label for=""><span class="mustIcon">*</span>产品进价</label>
				<input type="text" name="purPrice" required/>
				<label for=""><span class="mustIcon">*</span>产品出价</label>
				<input type="text" name="sellingPrice" required/></br>
				<label for=""><span class="mustIcon">*</span>注册证</label>
				<input type="text" style="width:632px;" name="registration" required/><label class="autoLab">多个注册证以“；”分开</label><br />
				<label for="" style="vertical-align:top;">备注</label>
				<div class="areaTxtBox">
					<textarea class="spdRemarks" name="description" style='margin: 0;display: block;border: none;box-shadow: none' maxlength="200" >${prod.description }</textarea>
					<label style="text-align: right;display: block;color: #666;" class="autoLab"><span class="keyUpNum">0</span>/200</label>
				</div><br />
				<label style="padding-top:15px;float:left">产品三证扫描件</label>
				<div class="cardBoxWrap" style="padding-left:105px;">
	          		<div class="card-box">
	          			<div class="card-box-top">
				            <h4 class="fl">产品授权书</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgAuthNum" /><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgAuthDate" />
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgAuthSiteUp" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证1</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegister1Num"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegister1Date" />
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite1Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证2</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegister2Num" /><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegister2Date" />
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite2Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">产品注册证3</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegister3Num" /><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegister3Date"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgRegisterSite3Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">供应商营业执照</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicense1Num"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicense1Date"/>
			            </div>
			           <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite1Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">供应商经营许可证</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicense2Num"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicense2Date"/>
			            </div>
			           <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite2Up" style="width: 100%; height: 100%;" multiple/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">生产经营企业执照</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicense3Num" /><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicense3Date" />
			            </div>
			           <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite3Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
	          		<div class="card-box">
	          			<div class="card-box-top"> 
				            <h4 class="fl">生产企业生产许可证</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicense4Num"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicense4Date"/>
			            </div>
			            <div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgLicenseSite4Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
	          		</div>
          		</div>
          		<label  style="padding-top:15px;float:left">产品照片</label>
          		<div class="cardBoxWrap" style="padding-left:105px;">
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct1Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">正面</div>
	          		</div>
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct2Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">背面</div>
	          		</div>
	          		<div class="card-box" style="height:208px;">
	          			<div class="controls"> 
			            	<input type="text" class="required validate_hidden" value=""/> 
				            <div class='pictureUploadDiv'>
								<div class='tR_upPic_icon'  style="width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;">
								    <input type="file" data-code='0' class="upPic" name="imgProduct3Up" style="width: 100%; height: 100%;" multiple="multiple"/>
									<div class="smallImg" style='display:block;width:256px;height:160px;' >
										<img style="display:none;" src="" class="card-box_img"/>
										<div style="display:none;" class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
									</div>
									<span class="addIcon">+</span>
								</div>
							</div>
						</div>
						<div style="text-align:center;line-height: 40px;font-size:13px;color:#666;">侧面</div>
	          		</div>
	          	</div>
          		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
          			<c:if test="${flag ne 'see' }">
          				<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-save" />
          			</c:if>
          			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
          		</div>
			</div>
		</div>
		<div class="addSelsctBox">
			<label class="addSelLab" style="padding-left:30px;"></label>
			<input type="text" style="width:160px;height:30px;margin:0;" class="addSelInp" id="addselInp1" value="" />
		</div>
		<div id="codePrintBox" class="codePrintBox" style="display:none;">
			<div><h5>厂商：</h5><span class="venderNameSpan">美敦力（上海）管理有限公司</span></div>
			<div><h5>产品名称：</h5><span class="proNameSpan">冠状动脉球囊扩张导管</span></div>
			<div class="specLine"><h5>规格：</h5><span class="specSpan">2.00mm*15mm</span></div>
			<div class="versionLine"><h5>型号：</h5><span class="versionSpan">LA20015</span></div>
			<div><img src = "" id="barcodeImg" /></div>
		</div>
		<input id="isUrgent" name="isUrgent" type="hidden" value="${flag == 'urgent' ? 'a1' : 'a0'}"/>
	</form:form>
	<div id="showPic" style="display:none;">
		<img alt="" src=""  style="">
		<div style="display: none;">
		<p></p>
		<img alt="" src=""  height="300" width="200" >
		</div>
	 </div>
	 <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script src="${ctxStatic}/spd-CP/js/pinying.js"></script>
	<script src="${ctxStatic}/spd-CP/js/jquery-migrate-1.2.1.min.js"></script>
	<!-- <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script> -->
	<script src="${ctxStatic}/spd-CP/js/jquery.jqprint-0.3.js"></script>
	<script src="${ctxStatic}/spd-CP/js/barcode.js"></script>
	<script type="text/javascript">
			$(function(){
				
				//定位光标
				$("#number").focus();
				
				//输入框获取焦点时出现下拉
				$(".proClassify").click(function(){
					$(this).next().show();
				});
				
				//判断input是否为可输入
				if($("#flag").val() == "see"){
					$("input").attr("disabled","disabled");
					$(".addProductBox select").attr("disabled","disabled");
				}
				
				//条形码解析
				$("#number").bind('change',function(){
					var no = $("#number").val();
					if(no.substring(0,2)!='93'){
						var coded = no;
						getPrdNumber(no);
						$("#number").val(upn);
						$("#codePrint").hide();
					}
					
				});
				
				//图片删除
				$(".smallImg_cloBtn").click(function(){
					
				})
				
				//下拉多选显示隐藏
				$(".selectedBox").click(function(){
					if($("#flag").val() != "see"){
						$(".selectMoreList").show();
					}
				})
				//点击空白处隐藏
				 window.onload = function(){
		            var body = document.getElementsByTagName('body')[0];
		            body.onclick = function(e){
		                e = e || window.event;
		                var target = e.target || e.srcElement;
		                var tables = document.getElementsByClassName('proClassify');
		                if(target.className != 'proClassify'){
		                    $(".addOption").hide();
		                }
		            }
		        }
				
				//备注限制字数
				$(".spdRemarks").keyup(function(){
					var len=$(this).val().length;
					$(".keyUpNum").text(len);
				})
				//遍历添加
				$(".addOption").each(function(index){
					//单击选中项目隐藏下拉
					$(document).on("click",".addOption:eq("+index+")>li:not(:last)",function(){
						$(this).parent().prev().focus();
						$(this).parent().prev().val($(this).text());
						$(this).parent().prev().prev().val($(this).next().val());
						$(this).parent().hide();
					})
					//单击添加项目
					$(this).find("li:last").click(function(){
						var addText=$(this).text().slice(2);
						var title=$(this).text();
						var ind=$(this).parent();
						$(".addSelsctBox>label").text(addText);
						$(this).parent().hide();
						layer.open({
							type:1,
							title:title,
							content:$(".addSelsctBox").html(),
							area:["400px","300px"],
							shade: [0.8, '#393D49'],
							btn:["确定","取消"],
							yes:function(index,layero){
								var a = layero.find(".addSelInp").val();
								addData(a);
								layer.closeAll();
							}
						})
					})
				});
				 //显示图片
			    $(".showpic").click(function(){
			    	var imgUrl=$(this).parents(".card-box").find(".smallImg img").attr("src");
			    	if(imgUrl == "" ){
			    		layer.alert('请先添加图片', {icon: 0});
			    		return false;
			    	}
				  	if($(this).attr("data")=="idcard"){
					  $("#showPic img:last").attr("src",$(this).parents(".card-box").find(".card-box_img").attr("data"));
					  $("#showPic div").show();
				    }else{
					  $("#showPic div").hide();
				    }
			   		$("#showPic img:first").attr("src",$(this).parents(".card-box").find(".card-box_img").attr("src"));
				   	layer.open({
				   			type:1,
				   			title:"查看图片",
				   			content:$("#showPic"),
				   			area:["650px","460px"],
				   			shade: [0.8, '#393D49'],
				   			btn:["关闭"],
				   			btn2:function(){
				   			  layer.closeAll();
				   			}
				   		});
				   	});
				function addData(a){
					var t = $(".addSelsctBox").text();
					if(t.indexOf("产品分类")>0){
						var type = $("#prodType").val();
						if(type == '1'){
							$.ajax({
								url:"${ctx}/hys/pdCategory/saveAjax",
								type:"post",
								data:{name:a,type:"1"},
								dataType:"json",
								success:function(data){
									addCategoryRow(data);
								}
							})
						}else if(type == '0'){
							$.ajax({
								url:"${ctx}/hys/pdCategory/saveAjax",
								type:"post",
								data:{name:a,type:"0"},
								dataType:"json",
								success:function(data){
									addCategoryRow(data);
								}
							})
						}else{
							alert("要先选择一个产品类型 (´•灬•‘)");
						}
					}else{
						$.ajax({
						url:"${ctx}/hys/pdGroup/saveAjax",
						type:"post",
						data:{name:a},
						dataType:"json",
						success:function(data){
							var json = eval(data);
							if(json.statusCode=="200"){
								var html='<li>'+json.name+'</li><input type="hidden" value="'+json.id+'" />';
								$("#addGroup").prepend(html);	
								
							}
							layer.alert(json.msg);
						}
					})
					}
				}
				
				//ajax的加入一行产品分类
				function addCategoryRow(data){
					var json = eval(data);
					if(json.statusCode=="200"){
						var html='<li>'+json.name+'</li><input type="hidden" value="'+json.id+'" />';
						$("#addCategory").prepend(html);	
					}
					layer.alert(json.msg);
				}
				
				
				//时间插件
				lay('.validTime').each(function(){
				  laydate.render({
				    elem: this
				  });
				}); 
				//条码打印
				$("#codePrint").click(function(){
					//判断对应数据是否填完整
					var number=$("input[name='number']").val();//产品编号
					var venderName=$("select[name='venderId']>option:checked").text();//生产厂家
					var proName=$("input[name='name']").val();//产品名称
					var spec=$("input[name='spec']").val();//规格
					var version=$("input[name='version']").val();//型号
					if(number=="" || number==undefined || number==null){
						layer.alert("请生成产品编号！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if(venderName=="" || venderName==undefined || venderName==null){
						layer.alert("请选择生产厂家！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if(proName=="" || proName==undefined || proName==null){
						layer.alert("请填写产品名称！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if( (spec=="" && version=="")  || (spec==undefined && version==undefined)|| (spec==null && version==null )){
						layer.alert("请填写规格或型号！",{icon:0},function(index){
							layer.close(index);
						});
						return false;
					}
					if( (spec=="" && version!="")  || (spec==undefined && version!=undefined)|| (spec==null && version!=null )){
						$(".specLine").hide();
						$(".versionSpan").text(version);
					}
					if( (spec!="" && version=="")  || (spec!=undefined && version==undefined)|| (spec!=null && version==null )){
						$(".version").hide();
						$(".specSpan").text(spec);
					}
					//将选中、填写的赋值到弹框中
					$(".venderNameSpan").text(venderName);
					$(".proNameSpan").text(proName);
					$(".specSpan").text(spec);
					$(".versionSpan").text(version);
					layer.open({
						type:1,
						title:"条码打印",
						content:$(".codePrintBox").html(),
						area:["500px","400px"],
						shade: [0.8, '#393D49'],
						btn:["打印"],
						yes:function(index,layero){
							printFn();
							layer.closeAll();
						}
					})
					
				})
				//紧急采购量关联
				$("[name=urgentPurCount]").change(function(){
					var val = $("[name=urgentPurCount]").val();
					$("[name=surplusPurCount]").val(val);
				})
				
				//生产厂家改变
				$("[name=venderId]").change(function(){
					var name = $("[name=venderId]>option:checked").text();
					var id = $("[name=venderId]>option:checked").val();
					$("[name=venderName]").val(name);
					$("[name=venderId]").val(id);
				})
				
			})
			//document.ready end
			
			
			//打印
			function printFn(){
				$(".codePrintBox").removeAttr("style");
				$(".codePrintBox").jqprint();	
				$(".codePrintBox").attr("style","display:none;");
	//			var bdhtml = document.body.innerHTML;      
	//	        var sprnstr = "<!--startprint-->";      
	//	        var eprnstr = "<!--endprint-->";      
	//	        var prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
	//	        prnhtml = prnhtml.substring(0,prnhtml.indexOf(eprnstr));      
	//	        document.body.innerHTML = prnhtml;   
	//	        window.print();    
	//			document.body.innerHTML = bdhtml;
			}
			
			//生成编号
			function generateNumber(){
				$.ajax({
					type:"POST",
					url:"${ctx}/hys/pdProduct/generateNumber",
					dataType:"json",
					success:function(data){
						$("#codePrint").show();
						var data = eval(data);
						var site = (data.site).substring(0);
						$("#number").val(data.number);
					//	$("#barcodeImg").attr("src","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523855034892&di=545d64fce923a20295b472d8bc90735c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0eb30f2442a7d933b1feb756a74bd11373f00144.jpg");
					//	$("#barcodeImg").attr("src","");
						$("#barcodeImg").attr("src","${ctxImg}"+site);
						$(".codePrintBox").removeAttr("style");
						$(".codePrintBox").attr("style","display:none;");
					}
				})
			}
			
			//类型改变
			function typeChange(){
				$.ajax({
					type:"POST",
					url:"${ctx}/hys/pdProduct/categoryList",
					dataType:"json",
					success:function(data){
						categoryChange(data);
					}
				})
			}
			
			//改变分类列表
			function categoryChange(data){
				data = eval(data);
				var html = '';
				var type = $("#prodType").val();
				$("#addCategory>li:not(:last)").empty();
				if(type == '1'){
					for(var i = 0 ; i < data.length ; i ++){
						if(data[i].type == '1'){
							html += '<li>'+data[i].name+'</li><input type="hidden" value="'+data[i].id+'" />'
						}
					}
				}else if(type == '0'){
					for(var i = 0 ; i < data.length ; i ++){
						if(data[i].type == '0'){
							html += '<li>'+data[i].name+'</li><input type="hidden" value="'+data[i].id+'" />'
						}
					}
				}
				//html += '<li><i class="fa fa-plus"></i>新增产品分类</li>';
				$("#addCategory").prepend(html);
			}
			
			//扫描开放
			/* function scan(){
				document.getElementById("number").focus();
				$("#number").removeAttr("onkeypress");
			} */
			
			//提交
			function sub(){
				$("#inputForm").attr("action","${ctx}/hys/pdProduct/save?flag="+$("[name=flag]").val());
				return true;
			}
			//首拼码
			function query(){
			    var str = document.getElementById("proInp").value.trim();
			    if(str == "") return;
			    var arrRslt = makePy(str);
			    //循环将值到下拉框
			    var option = null;
			    document.getElementById("proSpm").innerHTML="";//清空下拉框
			    var first = document.getElementById("proSpm");
			    for(var j=0;j<arrRslt.length;j++){
							var obj = document.getElementById("proSpm");
							obj.value=arrRslt[j],arrRslt[j];
			    }
			}
			
			
		</script>
</body>
</html>