package org.calangheiyo.solrlogin.tocken.utils;

public class TestEncrypt {
    public static void main (String[] args){
        testEncrypt("aaa");
        testEncrypt("bbb");
        testEncrypt("bbb");
        testEncrypt("666");
        testEncrypt("b235w3tw3t236tbb");
        testEncrypt("woshi aini de ");
        testEncrypt("解密");
        testEncrypt("我爱你");
        testEncrypt("");
        testEncrypt("bb23423@@#$%^#%^42424b");

    }

    private static void testEncrypt(String input){
        String encryptResult = TEAEncryptStringUtil.encrypt(input);
        String decryptResult = TEAEncryptStringUtil.decrypt(encryptResult);
        System.out.print("Origin input :{"+input+"} ,encryptResult : {");
        System.out.print(encryptResult);
        System.out.println("}, decryptResult : {"+decryptResult+"} .");
    }
}
