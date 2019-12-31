package com.baizhi.util;

import java.util.Arrays;

/**
 * @ClassName SecurityCode
 * @Discription
 * @Acthor
 * @Date 2019/12/17  17:01
 */
public class SecurityCode {
    /**
     * ��֤���Ѷȼ���
     * Simple-����
     * Medium-���ֺ�Сд��ĸ
     * Hard-���ֺʹ�Сд��ĸ
     */
    public enum SecurityCodeLevel {
        Simple, Medium, Hard
    }

    ;

    /**
     * ����Ĭ����֤�룬4λ�е��Ѷ�
     *
     * @return
     */
    public static String getSecurityCode() {
        return getSecurityCode(4, SecurityCodeLevel.Medium, false);
    }

    /**
     * ����Ⱥ��Ѷ��������֤��
     *
     * @param length
     * @param level
     * @param isCanRepeat
     * @return
     */
    private static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
        int len = length;
        char[] codes = {
                '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        if (level == SecurityCodeLevel.Simple) {
            codes = Arrays.copyOfRange(codes, 0, 10);
        } else if (level == SecurityCodeLevel.Medium) {
            codes = Arrays.copyOfRange(codes, 0, 36);
        }

        int n = codes.length;

        if (len > n && isCanRepeat == false) {
            throw new RuntimeException(String.format("咱也不知道这是啥(验证码工具类)", len, level, isCanRepeat, n));
        }
        char[] result = new char[len];
        if (isCanRepeat) {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codes[r];
            }
        } else {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codes[r];
                codes[r] = codes[n - 1];
                n--;
            }
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(SecurityCode.getSecurityCode());
    }
}
