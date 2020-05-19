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
				<label for=""><span class="mustIcon">*</span>药品编号</label>
				<input type="text" name="" required id="" value="" />
				<label style="padding-top:15px;float:left">药品资质</label>
				<div class="cardBoxWrap" style="padding-left:105px;">
	          		<div class="card-box">
	          			<div class="card-box-top">
				            <h4 class="fl">生活批件</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgAuthNumber"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgAuthDate" />
			            </div>
			            <div class="controls"> 
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
				            <h4 class="fl">药品质量标准</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber1"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate1"/>
			            </div>
			            <div class="controls"> 
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
				            <h4 class="fl">药品说明书</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber2"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate2"/>
			            </div>
			            <div class="controls"> 
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
				            <h4 class="fl">药品包装盒及包装备案</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgRegisterNumber3"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgRegisterDate3"/>
			            </div>
			            <div class="controls"> 
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
				            <h4 class="fl">药品质检报告</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber1"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate1"/>
			            </div>
			            <div class="controls"> 
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
				            <h4 class="fl">商标注册证</h4> 
				            <a href="javascript:void(0);" class="fr  showpic">查看大图</a> 
			            </div>
			            <div class="card-box-code">
			            	<label for="">证照号码</label>
			            	<input type="text" name="imgLicenseNumber2"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate2"/>
			            </div>
			            <div class="controls"> 
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
			            	<input type="text" name="imgLicenseNumber3"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate3"/>
			            </div>
			            <div class="controls"> 
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
			            	<input type="text" name="imgLicenseNumber4"/><br />
			            	<label for="">有效期至</label>
			            	<input type="text" class="validTime" name="imgLicenseDate4"/>
			            </div>
			            <div class="controls"> 
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
          		<div class="bottomBtn" style="text-align: center;margin:30px 0;">
          			<input type="submit" onclick="" value="保存" class="hcy-btn hcy-save" />
          			<a href="javascript:history.go(-1)" class="hcy-btn hcy-back" >返回</a>
          		</div>
			</div>
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
	<script src="${ctxStatic}/spd-CP/js/layer.js"></script>
	<script src="${ctxStatic}/pic-upload/pic-upload.js"></script>
	<script type="text/javascript">
			
	</script>
</body>
</html>