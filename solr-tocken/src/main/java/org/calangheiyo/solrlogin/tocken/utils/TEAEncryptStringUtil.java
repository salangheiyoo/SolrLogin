package org.calangheiyo.solrlogin.tocken.utils;


public class TEAEncryptStringUtil{
    private static final StringTeaEncryptInstance STRING_TEA_ENCRYPT_INSTANCE = new StringTeaEncryptInstance();

    public static String encrypt(String input){
        return STRING_TEA_ENCRYPT_INSTANCE.encode(input);
    }

    public static String decrypt(String input){
        return STRING_TEA_ENCRYPT_INSTANCE.decode(input);
    }


    private static class StringTeaEncryptInstance extends TEAEncryptSupport<String>{

        protected byte[] getInputByte(String input) {
            byte[] orginalBytes =  ((String) input).getBytes();
            int offset = 8 - orginalBytes.length % 8;
            byte[] bytes2Encode = new byte[orginalBytes.length+offset];
            bytes2Encode[0] = (byte)offset;
            System.arraycopy(orginalBytes,0,bytes2Encode,offset,orginalBytes.length);
            return bytes2Encode;
        }

        protected String getOutPutObject(byte[] bytes) {
            int offset = bytes[0];
            byte[] byteResult = new byte[bytes.length-offset];
            System.arraycopy(bytes,offset,byteResult,0,byteResult.length);
            return new String(byteResult);
        }

    }



}
