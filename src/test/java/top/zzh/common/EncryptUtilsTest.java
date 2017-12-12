package top.zzh.common;

import org.junit.Test;


public class EncryptUtilsTest {

    @Test
    public void testEncrypt() {
        System.out.println(EncryptUtils.md5("123456"));
    }
}
