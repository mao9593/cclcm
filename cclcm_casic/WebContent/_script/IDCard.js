/*
 *文件名：IDCard.js
 */
/*
 *判别身份证号码是否合法,入口参数为身份证号码
 *返回 true:false,否则赋值
 */
function IDCard(_num) {
	this.num = this.check(_num);

	this.dict = new ActiveXObject("Scripting.Dictionary");
	this.dict.Add("11", "北京");
	this.dict.Add("12", "天津");
	this.dict.Add("13", "河北");
	this.dict.Add("14", "山西");
	this.dict.Add("15", "内蒙古");
	this.dict.Add("21", "辽宁");
	this.dict.Add("22", "吉林");
	this.dict.Add("23", "黑龙江");
	this.dict.Add("31", "上海");
	this.dict.Add("32", "江苏");
	this.dict.Add("33", "浙江");
	this.dict.Add("34", "安徽");
	this.dict.Add("35", "福建");
	this.dict.Add("36", "江西");
	this.dict.Add("37", "山东");
	this.dict.Add("41", "河南");
	this.dict.Add("42", "湖北");
	this.dict.Add("43", "湖南");
	this.dict.Add("44", "广东");
	this.dict.Add("45", "广西");
	this.dict.Add("46", "海南");
	this.dict.Add("50", "重庆");
	this.dict.Add("51", "四川");
	this.dict.Add("52", "贵州");
	this.dict.Add("53", "云南");
	this.dict.Add("54", "西藏");
	this.dict.Add("61", "陕西");
	this.dict.Add("62", "甘肃");
	this.dict.Add("63", "青海");
	this.dict.Add("64", "宁夏");
	this.dict.Add("65", "新疆");
	this.dict.Add("71", "台湾");
	this.dict.Add("81", "香港");
	this.dict.Add("82", "澳门");
	this.dict.Add("91", "国外");
}

IDCard.prototype.check = function(varInput) {
	if (varInput == null || varInput.trim() == "") {
		throw "身份证号不能为空，请重新输入！";
	}
	varInput = varInput.trim();
// var ret = this.convertTo18(varInput);
// if (!ret) {
// throw "身份证号不正确，请重新输入！";
// //return false;
// } else if (varInput.length == 18 && varInput != ret) {
// throw "身份证号不正确，请重新输入！";
// //return false;
// } else {
// //返回值可以自动升级18位身份证号
// //this.num = ret;
// return ret;
// //return true;
// }
	return this.convertTo18(varInput);
}

IDCard.prototype.getYear = function() {
	return this.num.substring(6, 10);
}

IDCard.prototype.getMonth = function() {
	return this.num.substring(10, 12);
}

IDCard.prototype.getDay = function() {
	return this.num.substring(12, 14);
}

IDCard.prototype.getNative = function() {
	return this.dict.Item(this.num.substring(0, 2));
}

IDCard.prototype.getSex = function() {
	return this.num.substring(17, 1) % 2 == 0 ? false : true;
}

/*
 * 15身份证号码升18位,入口参数0为15身份证号码,返回值为18位身份证号码 如果证号错误则返回false
 */
IDCard.prototype.convertTo18 = function(varInput) {
	if (varInput == null || varInput.trim() == "") {
		throw "身份证号不应为空！";
	}
	var year = 0;
	var month = 0;
	var day = 0;
	var strOldID = new String(varInput.trim());
	var strNewID = "";
	if (strOldID.length == 15) {
		// alert(strOldID);
		for (i = 0; i < 15; i++) {
			// 15位的身份证号必须全部由数字组成，否则，视为非法
			if (!checkZInt(strOldID.charAt(i))) {
				throw "1身份证号前17位必须全部由数字组成！";
			}
		}
		// 取得身份证中的年月日
		year = "19" + strOldID.substr(6, 2);
		month = strOldID.substr(8, 2);
		day = strOldID.substr(10, 2);
		// 校验日期是否正确
		if (!checkDate(year, month, day)) {
			throw "出生年月日不正确！";
		}
		strNewID = strOldID.substring(0, 6) + "19" + strOldID.substring(6, 15);
	} else if (strOldID.length == 18) {
		for (i = 0; i < 17; i++) {
			// 15位的身份证号必须全部由数字组成，否则，视为非法
			if (!checkZInt(strOldID.charAt(i))) {
				throw "2身份证号前17位必须全部由数字组成！";
			}
		}
		if (strOldID.substring(17, 18).toUpperCase() != "X" && !checkZInt(strOldID.substring(17, 18))) {
				throw "身份证号第18位必须为数字或x(X)！";
		}

		// 取得身份证中的年月日
		year = "19" + strOldID.substr(6, 4);
		month = strOldID.substr(10, 2);
		day = strOldID.substr(12, 2);
		// 校验日期是否正确
		if (!checkDate(year, month, day)) {
			throw "出生年月日不正确！";
		}
		strNewID = strOldID.substring(0, 17);
	} else if (strOldID.length == 17) {
		for (i = 0; i < 17; i++) {
			// 15位的身份证号必须全部由数字组成，否则，视为非法
			if (!checkZInt(strOldID.charAt(i))) {
				throw "3身份证号前17位必须全部由数字组成！";
			}
		}
		// 取得身份证中的年月日
		year = "19" + strOldID.substr(6, 4);
		month = strOldID.substr(10, 2);
		day = strOldID.substr(12, 2);
		// 校验日期是否正确
		if (!checkDate(year, month, day)) {
			throw "出生年月日不正确！";
		}
		strNewID = strOldID;
	} else {
		throw "身份证号的位数有误，应为15位或18位！";
	}
	return strNewID = strNewID + this.createCK(strNewID);
}

/*
 * 根据17位的身份证号得到最后一位校验码 strID：身份证号前17位 只返回
 */
IDCard.prototype.createCK = function(strID) {
	var s = 0;
	var WI = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var AI = "10X98765432";
	for (i = 0; i < 17; i++) {
		j = strID.substr(i, 1) * WI[i];
		s = s + j;
	}
	s = s % 11;
	return AI.substr(s, 1);
}

/*
 * 主要提供对日期的精确校验，验证日期是否合法 合法返回true,非法返回false 参数说明： year：年 month：月 day：日
 */
function checkDate(year, month, day) {
	var flag = true;
	var time = new Date(year, month - 1, day);
	// alert(time);
	var e_year = time.getFullYear();
	// alert(e_year);
	var e_month = time.getMonth() + 1;
	// alert(e_month);
	var e_day = time.getDate();
	// alert(e_day);
	if (year != e_year || month != e_month || day != e_day) {
		flag = false;
	}
	return flag;
}

/*
 * 检查输入的串是否在0到9之间的字符组成 是则返回true，如果不是则返回false
 */
function checkZInt(str) {
	var reg = /^\d+$/;
	// alert(str + ", " + str.match(reg));
	if (str.match(reg)) {
		// 全部是数字
		return true;
	} else {
		// 含有其他字符
		return false;
	}
}

/*
 * 字符串去掉左右空格的方法
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/*
 * 字符串去掉左空格的方法
 */
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
}

/*
 * 字符串去掉右空格的方法
 */
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
}