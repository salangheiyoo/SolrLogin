package org.calangheiyo.solrlogin.tocken.utils;

import com.sun.deploy.util.ArrayUtil;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * TEA(Tiny Encryption Algorithm) implement
 * @author sukaixiang
 */
abstract class TEAEncryptSupport<T> {
    //加密解密所用的KEY
    private final static int[] KEY = new int[]{0x789f5645, 0xf68bd5a4,0x81963ffa, 0x458fac58};
    //加密轮次，32次
    private static final int DEFAULT_ENCRYPT_TIMES = 32;
    private static final int DEFAULT_DECRYPT_KEY = 0xC6EF3720;//relative to times
    //这是算法标准给的值
    private static final int DELTA = 0x9e3779b9;

    protected  abstract byte[] getInputByte(T input);
    protected  abstract   T getOutPutObject(byte[] bytes );

    public String encode(final T input){
        Assert.notNull(input);
        byte[] byte2Encode = getInputByte(input);
        byte[] byteResult = new byte[byte2Encode.length];
        for(int index=0; index<byte2Encode.length; index+=8){
            byte[] bytes2Encrypt1Time = new byte[8];
            System.arraycopy(byte2Encode,index,bytes2Encrypt1Time,0,8);
            byte[] bytes = doEncrypt(bytes2Encrypt1Time);
            System.arraycopy(bytes,0,byteResult,index,8);
        }
        return byte2String(byteResult);
    }

    public final T decode(String input){
        Assert.notNull(input);
        byte[] byte2Decode = string2Byte(input);
        Assert.isTrue(byte2Decode.length%8==0,"Error input to decode");
        byte[] byteResult = new byte[byte2Decode.length];
        for(int index=0; index<byte2Decode.length; index+=8){
            byte[] bytes2Encrypt1Time = new byte[8];
            System.arraycopy(byte2Decode,index,bytes2Encrypt1Time,0,8);
            byte[] bytes = doDecrypt(bytes2Encrypt1Time);
            System.arraycopy(bytes,0,byteResult,index,8);
        }
        return getOutPutObject(byteResult);
    }

    private String byte2String(byte[] byteResult){
        char[] charset = new char[byteResult.length];
        for(int index=0; index!=byteResult.length; index++){
            charset[index] = (char)(byteResult[index] & 0xFF);
        }
        return String.valueOf(charset);
    }

    private byte[] string2Byte(String input){
        int inputLength = input.length();
        char[] charset =  new char[inputLength];
        input.getChars(0,inputLength,charset,0);
        byte[] bytes = new byte[charset.length];
        for(int index=0; index!=charset.length; index++){
            bytes[index] = (byte)(charset[index]);
        }
        return bytes;
    }

    /**
     * @param content 一次加密64位 byte[8]
     * @return byte[8]
     */
    private static byte[] doEncrypt(final byte[] content){
        int[] v = byte2Int64(content);
        int sum = 0;

        for(int i=0; i!=DEFAULT_ENCRYPT_TIMES; i++){
            sum += DELTA;
            v[0] += ((v[1]<<4) + KEY[0]) ^ (v[1] + sum) ^ ((v[1]>>5) + KEY[1]);
            v[1] += ((v[0]<<4) + KEY[2]) ^ (v[0] + sum) ^ ((v[0]>>5) + KEY[3]);
        }
        return int2Byte64(v);
    }

    /**
     * @param content 一次解密64位 byte[8]
     * @return byte[8]
     */
    private static byte[] doDecrypt(final byte[] content){
        int[] v = byte2Int64(content);
        int sum = DEFAULT_DECRYPT_KEY;

        for(int i=0; i!=DEFAULT_ENCRYPT_TIMES; i++){
            v[1] -= ((v[0]<<4) + KEY[2]) ^ (v[0] + sum) ^ ((v[0]>>5) + KEY[3]);
            v[0] -= ((v[1]<<4) + KEY[0]) ^ (v[1] + sum) ^ ((v[1]>>5) + KEY[1]);
            sum -= DELTA;
        }
        return int2Byte64(v);
    }

    private static int[] byte2Int64(final byte[] content){
        Assert.notNull(content,"TEA once operate 64 bits");
        Assert.isTrue(content.length == 8,"TEA once operate 64 bits");
        return new int[]{
        ((content[0]& 0xFF)<<24) | ((content[1]& 0xFF)<<16) | ((content[2]& 0xFF)<<8) | (content[3]& 0xFF),//unsigned int
        ((content[4]& 0xFF)<<24) | ((content[5]& 0xFF)<<16) | ((content[6]& 0xFF)<<8) | (content[7]& 0xFF)//unsigned int
        };
    }

    private static byte[] int2Byte64(final int[] value){
        Assert.notNull(value,"TEA once operate 64 bits");
        Assert.isTrue(value.length == 2,"TEA once operate 64 bits");
        return new byte[]{
                (byte) ((value[0] >> 24) & 0xFF),  (byte) ((value[0] >> 16) & 0xFF), (byte) ((value[0] >> 8) & 0xFF), (byte) (value[0] & 0xFF),
                (byte) ((value[1] >> 24) & 0xFF),  (byte) ((value[1] >> 16) & 0xFF), (byte) ((value[1] >> 8) & 0xFF), (byte) (value[1] & 0xFF)
        };
    }
}
