/**
*@author xialonglei
*@since 2016/5/5
*
*/
pubilc class UnicodeToChineseUtil{
	/**unicodeǰ׺\u*/
	private final String PREFIX_UNICODE = "\\u";
	public UnicodeToChineseUtil{}
	/**
	 * ���ַ����е�unicodeתΪ����
	 * @param text Ҫ�������ַ���
	 * @return sb.toString() ��unicodeתΪ���ĵ��ַ���
	 */
	public String ascii2Native(String str) {
		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf(PREFIX_UNICODE);
		while (index != -1) {
			sb.append(str.substring(begin, index));
			sb.append(ascii2Char(str.substring(index, index + 6)));
			begin = index + 6;
			index = str.indexOf(PREFIX_UNICODE, begin);
		}
		sb.append(str.substring(begin));
		return sb.toString();
	}
	/**
	*��\uxxxxתΪchar
	*@param str \uxxxx
	*@return (char)code
	*/
	private char ascii2Char(String str) {
		if (str.length() != 6) {
			throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
		}
		if (!PREFIX_UNICODE.equals(str.substring(0, 2))) {
			throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");
		}
		String tmp = str.substring(2, 4);
		// תΪʮ����
		int code = Integer.parseInt(tmp, 16) << 8; // תΪ��λ�������λ���
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16); //���8Ϊ���
		System.out.println((char) code);
		return (char) code;
	}
}