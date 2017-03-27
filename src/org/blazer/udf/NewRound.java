package org.blazer.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 四舍五入 保留N位小数 补零
 * 
 * @author hyy
 *
 */
public class NewRound extends UDF {

	public String evaluate(String _float) {
		return evaluate(_float, 2);
	}

	public String evaluate(String _float, Integer _digits) {
		return new java.math.BigDecimal(_float).setScale(_digits, java.math.BigDecimal.ROUND_HALF_UP).toString();
	}

	public static void main(String[] args) {
		NewRound n = new NewRound();
		String str = "121222222222222222222222223.1542";
		System.out.println(n.evaluate(str));
		str = "121222222222222222222222223.1";
		System.out.println(n.evaluate(str));
		str = "0";
		System.out.println(n.evaluate(str));
		str = "0.1";
		System.out.println(n.evaluate(str));
	}

}
