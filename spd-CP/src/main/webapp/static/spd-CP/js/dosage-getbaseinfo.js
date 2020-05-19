//页面初始化
$(function(){
	getBaseInfo('1', '1000', '');
	getBaseInfo('2', '10', '');
	getBaseInfo('3', '90', '');
	
	//保存下拉名称
	$(document).on('change','#exeDeptId,#oprDeptId,#surgeonId,#sqrtDoctorId',function(){
		var myvalue = $(this).find("option:selected").text();
		$(this).next().val(myvalue);
	});
	//保存住院号
	$(document).on('change','#patientInfo',function(){
		var myvalue = $(this).find("option:selected");
		$('#inHospitalNo').val(myvalue.data('inhospno'));
		$('#patientDetailInfo').val(myvalue.data('info'));
	});
});

//获取数据-
function getBaseInfo(type, code, remark) {
	if (type && code)
		$.ajax({
			url:'getBaseInfo',
			type:'post',
			data:{'queryType':type, 'queryCode':code, 'remark':remark},
			dataType:'json',
			success:function(data){
				initHtml(type, data);
			}
		});
}

//初始化
function initHtml(type, data) {
	if (type === '1') {//病人信息
		initPatientHtml(data);
	} else if (type === '2') {//医生信息
		initDoctorHtml(data);
	} else if (type === '3') {//科室信息
		initDeptHtml(data);
	}
}

//科室组装html
function initDeptHtml(data) {
	var deptHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//执行科室、手术科室
			var obj = data.data[i];
			deptHtml += '<option value="'+obj.dydm+'">'+obj.cxxx+'</option>';
		}
	}
	$('#exeDeptId, #oprDeptId').empty().append(deptHtml);
}

//医生组装html
function initDoctorHtml(data) {
	var	doctorHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//手术医生、开方医生
			var obj = data.data[i];
			doctorHtml += '<option value="'+obj.dydm+'">'+obj.cxxx+'</option>';
		}
	}
	$('#surgeonId, #sqrtDoctorId').empty().append(doctorHtml);
}

//病人组装html
function initPatientHtml(data) {
	var	patientHtml = '<option value="">请选择</option>';
	if (data && data.code == '0') {
		var len = data.data.length;
		for (var i = 0; i < len; i++) {
			//病人信息
			var obj = data.data[i].cxxx;
			var nameIndex = obj.indexOf('姓名'),
				sexsIndex = obj.indexOf('性别'),
				inNoIndex = obj.indexOf('住院号'),
				telpIndex = obj.indexOf('手机号码'),
				inHospitalNo = $.trim(obj.substring(inNoIndex + 4, telpIndex)),//获取住院号
				patientInfo = $.trim(obj.substring(nameIndex + 3, sexsIndex));//获取病人姓名
			inHospitalNo = inHospitalNo.substr(0, inHospitalNo.length - 1);
			patientInfo = patientInfo.substr(0,patientInfo.length-1) + "  "+inHospitalNo;
			patientHtml += '<option value="'+patientInfo+'"'
						+  'data-info="'+obj+'" data-inhospno="'+inHospitalNo+'">'+patientInfo+'</option>';
		}
	}
	$('#patientInfo').empty().append(patientHtml);
}