package org.calangheiyo.solrlogin.tocken.domain;

/**
 * JWT(java web token) header
 */
public final class JWTHeader {
    private final String typ = "JWT";// java web token
    private final String alg = "CLHY";//自定义加密方法 取自calangheiyo

    public String getTyp() {
        return typ;
    }

    public String getAlg() {
        return alg;
    }


}
