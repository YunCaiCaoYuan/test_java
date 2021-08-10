
/**
 * 暴力破解法
 * @param ts 主串
 * @param ps 模式串
 * @return 如果找到，返回在主串中第一个字符出现的下标，否则为-1
 */
public static int bf(String ts, String ps) {
    char[] t = ts.toCharArray();
    char[] p = ps.toCharArray();
    int i = 0; // 主串的位置
    int j = 0; // 模式串的位置

    while (i < t.length && j < p.length) {
       if (t[i] == p[j]) { // 当两个字符相同，就比较下一个
           i++;
           j++;
       } else {
           i = i - j + 1; // 一旦不匹配，i后退
           j = 0; // j归0
       }
    }

    if (j == p.length) {
       return i - j;
    } else {
       return -1;
    }
}
