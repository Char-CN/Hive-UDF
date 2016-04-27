package org.blazer.udf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class Phone_Base extends UDF {

	protected boolean isNumber(String number) {
		// 连号
		if (StringUtils.isBlank(number) || Pattern.matches(compareNumber, number)) {
			return false;
		}
		// 手机号
		if (compare(number, ifMobileNumberRegCompare)) {
			return true;
		}
		// 电话号
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_1)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_1)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_2)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_2)) {
			return true;
		}
		return false;
	}

	protected boolean isMobileNumber(String number) {
		// 连号
		if (StringUtils.isBlank(number) || number.length() != 11 || Pattern.matches(compareNumber, number)) {
			return false;
		}
		// 手机号
		if (compare(number, ifMobileNumberRegCompare)) {
			return true;
		}
		return false;
	}

	protected boolean isTelNumber(String number) {
		// 连号
		if (StringUtils.isBlank(number) || Pattern.matches(compareNumber, number)) {
			return false;
		}
		// 电话号
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_1)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_1)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_2)) {
			return true;
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_2)) {
			return true;
		}
		return false;
	}

	protected String getNumber(String number) {
		if (compare(number, ifMobileNumberRegCompare)) {
			return getCompare(number, getMobileNumberRegGet);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_1)) {
			return getCompare(number, getAreaCodebitMinNumberRegGet1_1);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_1)) {
			return getCompare(number, getAreaCodebitMinNumberRegGet2_1);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_2)) {
			return "0" + getCompare(number, getAreaCodebitMinNumberRegGet1_2);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_2)) {
			return "0" + getCompare(number, getAreaCodebitMinNumberRegGet2_2);
		}
		return number;
	}

	protected String getMobileNumber(String number) {
		if (compare(number, ifMobileNumberRegCompare)) {
			return getCompare(number, getMobileNumberRegGet);
		}
		return number;
	}

	protected String getTelNumber(String number) {
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_1)) {
			return getCompare(number, getAreaCodebitMinNumberRegGet1_1);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_1)) {
			return getCompare(number, getAreaCodebitMinNumberRegGet2_1);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare1_2)) {
			return "0" + getCompare(number, getAreaCodebitMinNumberRegGet1_2);
		}
		if (compare(number, ifAreaCodebitMinNumberRegCompare2_2)) {
			return "0" + getCompare(number, getAreaCodebitMinNumberRegGet2_2);
		}
		return number;
	}

	private boolean compare(String number, String regCompare) {
		if (Pattern.matches(regCompare, number)) {
			return true;
		}
		return false;
	}

	private String getCompare(String number, String regGet) {
		Matcher matcher = Pattern.compile(regGet).matcher(number);
		matcher.find();
		return matcher.group();
	}

	// TODO: 连号
	private final String compareNumber = "\\d*(1{7}|2{7}|3{7}|4{7}|5{7}|6{7}|7{7}|8{7}|9{7}|0{7})\\d*";

	// TODO: 类似010三位区号
	private final String ifAreaCodebitMinNumberRegCompare1_1 = "\\d*(010|020|021|022|023|024|025|027|028|029)[1-9]\\d{6}\\d*";
	private final String getAreaCodebitMinNumberRegGet1_1 = "((010|020|021|022|023|024|025|027|028|029)[1-9]\\d{6,7})";

	// TODO: 类似010三位区号转成两位
	private final String ifAreaCodebitMinNumberRegCompare1_2 = "\\d*(10|20|21|22|23|24|25|27|28|29)[1-9]\\d{6}\\d*";
	private final String getAreaCodebitMinNumberRegGet1_2 = "((10|20|21|22|23|24|25|27|28|29)[1-9]\\d{6,7})";

	// TODO: 类似0731四位区号转成三位
	private final String ifAreaCodebitMinNumberRegCompare2_1 = "\\d*(079|077|035|033|034|039|037|043|042|041|082|083|081|087|085|066|069|031|070|071|072|073|074|075|076|059|058|057|056|055|063|099|097|048|095|094|045|093|047|091|046|090|051|052|053|054|088|089)\\d{1}[1-9]\\d{6}\\d*";
	private final String getAreaCodebitMinNumberRegGet2_1 = "((079|077|035|033|034|039|037|043|042|041|082|083|081|087|085|066|069|031|070|071|072|073|074|075|076|059|058|057|056|055|063|099|097|048|095|094|045|093|047|091|046|090|051|052|053|054|088|089)\\d{1}[1-9]\\d{6,7})";

	// TODO: 类似0731四位区号转成三位
	private final String ifAreaCodebitMinNumberRegCompare2_2 = "\\d*(79|77|35|33|34|39|37|43|42|41|82|83|81|87|85|66|69|31|70|71|72|73|74|75|76|59|58|57|56|55|63|99|97|48|95|94|45|93|47|91|46|90|51|52|53|54|88|89)\\d{1}[1-9]\\d{6}\\d*";
	private final String getAreaCodebitMinNumberRegGet2_2 = "((79|77|35|33|34|39|37|43|42|41|82|83|81|87|85|66|69|31|70|71|72|73|74|75|76|59|58|57|56|55|63|99|97|48|95|94|45|93|47|91|46|90|51|52|53|54|88|89)\\d{1}[1-9]\\d{6,7})";

	// TODO: 类似139手机号码
	// 2014-03-03 新增 181,185
	// 2016-04-27 新增 149,170,171,173,178
	private final String ifMobileNumberRegCompare = "\\d*(130|131|132|133|134|135|136|137|138|139|145|147|149|150|151|152|153|155|156|157|158|159|170|171|173|175|176|177|178|180|181|182|183|184|185|186|187|188|189)\\d{8}\\d*";
	private final String getMobileNumberRegGet = "((130|131|132|133|134|135|136|137|138|139|145|147|149|150|151|152|153|155|156|157|158|159|170|171|173|175|176|177|178|180|181|182|183|184|185|186|187|188|189)\\d{8})";

}
