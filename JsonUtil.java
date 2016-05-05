package com.datayes.www.util;

/**
 * 对不正当的json字符串进行转换的工具类
 * 
 * @author longlei.xia
 * @since 2016/5/4
 */
public class JsonUtil {

	public JsonUtil() {
	}

	/**
	 * 为json格式的字符串key、value加上引号 支持转换的字符串类型key:value or key:"value"
	 * 
	 * @param jsonString
	 */
	public String addQuote(String jsonString) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < jsonString.length(); i++) {
			char curChar = jsonString.charAt(i);
			if (curChar == '{' && jsonString.charAt(i + 1) != '\"') {
				sb.append(curChar);
				sb.append("\"");
			} else if (curChar == ':' && !jsonString.substring(i - 5, i).contains("http")
					&& jsonString.charAt(i + 1) != '\"') {
				sb.append("\"");
				sb.append(curChar);
				if (jsonString.charAt(i + 1) != '{' && jsonString.charAt(i + 1) != '[') {
					sb.append("\"");
				}
			} else if (curChar == ',' && jsonString.charAt(i - 1) != '\"') {
				if (jsonString.charAt(i - 1) != '}' && jsonString.charAt(i - 1) != ']') {
					sb.append("\"");
				}
				sb.append(curChar);
				if (jsonString.charAt(i + 1) != '{' && jsonString.charAt(i + 1) != '[') {
					sb.append("\"");
				}
			} else if (curChar == '}' && jsonString.charAt(i - 1) != '\"') {
				if (jsonString.charAt(i - 1) != '}' && jsonString.charAt(i - 1) != ']') {
					sb.append("\"");
				}
				sb.append(curChar);
			} else {
				sb.append(curChar);
			}
		}
		return sb.toString();
	}
}
