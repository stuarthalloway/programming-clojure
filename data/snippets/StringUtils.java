// From Apache Commons Lang, http://commons.apache.org/lang/
public static int indexOfAny(String str, char[] searchChars) {
    if (isEmpty(str) || ArrayUtils.isEmpty(searchChars)) {
	return -1;
    }
    for (int i = 0; i < str.length(); i++) {
	char ch = str.charAt(i);
	for (int j = 0; j < searchChars.length; j++) {
	    if (searchChars[j] == ch) {
		return i;
	    }
	}
    }
    return -1;
}
