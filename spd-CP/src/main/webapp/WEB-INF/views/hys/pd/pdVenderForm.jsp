<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/base.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctxStatic}/pic-upload/pic-upload.css" />
	<link rel="stylesheet" href="${ctxStatic}/spd-CP/css/addProduct.css" />
	<style type="text/css">
		.addProductBox .card-box{height:330px;!important}
		.addProductBox .card-box>.card-box-code{margin:0;}
		.addProductBox .card-box>.showpic{line-height: 30px; padding-left: 12px;}
		 .showPic{text-align:center;cursor:pointer}
    	#showPic  img{display:inline-block; width:512px;height:320px;margin-top:30px;}
    	.tR_upPic_icon{width:auto;height: auto;background:none;border:1px solid #ccc;margin:10px 0 0 10px;!important}
    	#inputForm .controls{margin-left:0;!important}
    	.showpic{cursor:pointer}
    	.smallImg img {width: 100%;height: 100%;position: relative; z-index: 6;!important}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/hys/pdVender/checkVenderName?id=" + encodeURIComponent('${pdVender.id}')},
				},
				messages: {
					name: {remote: "生产厂家已存在"},
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
	<input id="flag" type="hidden" value="${flag }" />
	<form:form id="inputForm" modelAttribute="pdVender" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="right-main-box">
			<div class="addProductBox">
			<input type="hidden" name="id" value="${pdVender.id }" />
				<label for="">生产厂家名称</label>
				<input type="text" class="createInp" style="width:300px;height:30px;border:1px solid #ccc;" name="name" value="${pdVender.name }"  required/><br />
				<label for="" style="float:left;padding-top:15px;">证照扫描件</label>
				<div class="all-card-box" style="padding-left:105px;">
				<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense1Name" value="${pdVender.imgLicense1Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense1Num" value="${pdVender.imgLicense1Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense1Date" value="<fmt:formatDate value="${pdVender.imgLicense1Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite1Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense1Site == '' || pdVender.imgLicense1Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense1Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense1Site == '' || pdVender.imgLicense1Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense1Site" value="${pdVender.imgLicense1Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense2Name" value="${pdVender.imgLicense2Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense2Num" value="${pdVender.imgLicense2Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense2Date" value="<fmt:formatDate value="${pdVender.imgLicense2Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite2Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense2Site == '' || pdVender.imgLicense2Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense2Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense2Site == '' || pdVender.imgLicense2Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense2Site" value="${pdVender.imgLicense2Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense3Name" value="${pdVender.imgLicense3Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense3Num" value="${pdVender.imgLicense3Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense3Date" value="<fmt:formatDate value="${pdVender.imgLicense3Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite3Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense3Site == '' || pdVender.imgLicense3Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense3Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense3Site == '' || pdVender.imgLicense3Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense3Site" value="${pdVender.imgLicense3Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense4Name" value="${pdVender.imgLicense4Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense4Num" value="${pdVender.imgLicense4Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense4Date" value="<fmt:formatDate value="${pdVender.imgLicense4Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite4Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense4Site == '' || pdVender.imgLicense4Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense4Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense4Site == '' || pdVender.imgLicense4Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense4Site" value="${pdVender.imgLicense4Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense5Name" value="${pdVender.imgLicense5Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense5Num" value="${pdVender.imgLicense5Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense5Date" value="<fmt:formatDate value="${pdVender.imgLicense5Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite5Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense5Site == '' || pdVender.imgLicense5Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense5Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense5Site == '' || pdVender.imgLicense5Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense5Site" value="${pdVender.imgLicense5Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense6Name" value="${pdVender.imgLicense6Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense6Num" value="${pdVender.imgLicense6Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense6Date" value="<fmt:formatDate value="${pdVender.imgLicense6Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite6Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense6Site == '' || pdVender.imgLicense6Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense6Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense6Site == '' || pdVender.imgLicense6Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense6Site" value="${pdVender.imgLicense6Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense7Name" value="${pdVender.imgLicense7Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense7Num" value="${pdVender.imgLicense7Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense7Date" value="<fmt:formatDate value="${pdVender.imgLicense7Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite7Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense7Site == '' || pdVender.imgLicense7Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense7Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense7Site == '' || pdVender.imgLicense7Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense7Site" value="${pdVender.imgLicense7Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense8Name" value="${pdVender.imgLicense8Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense8Num" value="${pdVender.imgLicense8Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense8Date" value="<fmt:formatDate value="${pdVender.imgLicense8Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite8Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense8Site == '' || pdVender.imgLicense8Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense8Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense8Site == '' || pdVender.imgLicense8Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense8Site" value="${pdVender.imgLicense8Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense9Name" value="${pdVender.imgLicense9Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense9Num" value="${pdVender.imgLicense9Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense9Date" value="<fmt:formatDate value="${pdVender.imgLicense9Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite9Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense9Site == '' || pdVender.imgLicense9Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense9Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense9Site == '' || pdVender.imgLicense9Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense9Site" value="${pdVender.imgLicense9Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense10Name" value="${pdVender.imgLicense10Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense10Num" value="${pdVender.imgLicense10Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense10Date" value="<fmt:formatDate value="${pdVender.imgLicense10Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite10Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense10Site == '' || pdVender.imgLicense10Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense10Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense10Site == '' || pdVender.imgLicense10Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense10Site" value="${pdVender.imgLicense10Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense11Name" value="${pdVender.imgLicense11Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense11Num" value="${pdVender.imgLicense11Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense11Date" value="<fmt:formatDate value="${pdVender.imgLicense11Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite11Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense11Site == '' || pdVender.imgLicense11Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense11Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense11Site == '' || pdVender.imgLicense11Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense11Site" value="${pdVender.imgLicense11Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		<div class="card-box">
		            <div class="card-box-code">
		            	<label for="">证照名称</label>
		            	<input type="text" name="imgLicense12Name" value="${pdVender.imgLicense12Name }"/><br />
		            	<label for="">证照号码</label>
		            	<input type="text" name="imgLicense12Num" value="${pdVender.imgLicense12Num }"/><br />
		            	<label for="">有效期至</label>
		            	<input type="text" class="validTime" name="imgLicense12Date" value="<fmt:formatDate value="${pdVender.imgLicense12Date }" pattern="yyyy-MM-dd" />"/>
		            </div>
		             <div class="showpic">查看大图</div>
		            <div class="controls"> 
		            	 
			            <div class='pictureUploadDiv'>
							<div class='tR_upPic_icon' >
							    <input type="file" data-code='0' class="upPic" name="licenceSite12Up" style="width: 100%; height: 100%;"/>
								<div class="smallImg" style='display:block;width:256px;height:160px;' >
									<img <c:if test="${pdVender.imgLicense12Site == '' || pdVender.imgLicense12Site == null}">style="display:none;"</c:if> src="${ctxImg }${pdVender.imgLicense12Site }" class="card-box_img"/>
									<div <c:if test="${pdVender.imgLicense12Site == '' || pdVender.imgLicense12Site == null || flag == 'see'}">style="display:none;"</c:if> class="smallImg_cloBtn" data-hiddenid='imgUrl'></div>
								</div>
								<input type="hidden" name="imgLicense12Site" value="${pdVender.imgLicense12Site }" />
								<span class="addIcon">+</span>
							</div>
						</div>
					</div>
          		</div>
          		</div>
			</div>
			<div class="bottomBtn" style="text-align: center;margin:30px 0;">
				<c:if test="${flag != 'see' }">
					<input type="submit" onclick="sub()" value="保存" class="hcy-btn hcy-btn-primary" />
				</c:if>
				
      			<a href="javascript:history.go(-1)" class="hcy-btn hcy-btn-o" style="line-height: normal;padding:6px 12px;background: #fff;color: #666;border-color: #ccc;">返回</a>
      		</div>
		</div>
	</form:form>
	<div id="showPic" style="display:none;">
		<img alt="" src=""  style="">
		<div style="display: none;">
		<p></p>
		<img alt="" src=""  height="300" width="200" >
		</div>
	 </div>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js"></script>
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script src="${ctxStatic}/spd-CP/laydate/laydate.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script>
		$(function(){
			
			//定位光标
			$("[name=name]").focus();
			
			//图片删除
			$(".smallImg_cloBtn").click(function(){
				$(this).parent().next().val("");
			})
			
			 //显示图片
		    $(".showpic").click(function(){
		    	var showFlag=$("#flag").val();
		    	if(showFlag != 'see'){
		    		if($(this).parents(".card-box").find(".smallImg .smallImg_cloBtn").is(":hidden")){
			    		layer.alert('请先添加图片', {icon:0});
			    		return false
			    	}
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
			 if($("#flag").val() == 'see'){
				$("input").attr("disabled","true");	 
			 };
			 
			//时间插件
			lay('.validTime').each(function(){
			  laydate.render({
			    elem: this
			  });
			}); 
		})
		
		
		
		function sub(){
			var flag = $("#flag").val();
			$("#inputForm").attr("action","${ctx}/hys/pdVender/save?flag="+flag);
			return true;
		}
	</script>
</body>
</html>