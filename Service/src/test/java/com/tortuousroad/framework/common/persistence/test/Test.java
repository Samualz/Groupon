package com.tortuousroad.framework.common.persistence.test;

/**
 *
 */
public class Test {

    @org.junit.Test
    public void toHex32() {
        Long number = 32L;
        String result = "";
        if (number <= 0) {
            result = "0";
        } else {
            while (number != 0) {
                result = "0123456789ABCDEFGHIJKLMNOPQRSTUV".substring((int)(number % 32), (int)(number % 32 + 1)) + result;
                number = number / 32;
            }
        }
        System.out.println(result);
    }

}
