package com.wqw.cloudresolution.ymlEntity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix = "aliyun",ignoreUnknownFields = false)
/**
 * 系统参数
 */
public class ParaMeterEntity {

    //获取系统外网IP地址
    private String getipurl;
    //用户登录aliyun帐号
    private String regionId;
    //用户Access Key ID
    private String accessKeyId;
    //用户Access Key Secret
    private String secret;
    //要修改的前缀
    private String RRKeyWord;
    //要修改的记录类型  A,NS,MX,TXT,CNAME 等等
    private String TypeKeyWord;
    //要修改的域名后缀
    private String DomainName;

    public String getGetipurl() {
        return getipurl;
    }

    public void setGetipurl(String getipurl) {
        this.getipurl = getipurl;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {

        this.secret = secret;
    }

    public String getRRKeyWord() {
        return RRKeyWord;
    }

    public void setRRKeyWord(String RRKeyWord) {
        this.RRKeyWord = RRKeyWord;
    }

    public String getTypeKeyWord() {
        return TypeKeyWord;
    }

    public void setTypeKeyWord(String typeKeyWord) {
        TypeKeyWord = typeKeyWord;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }
}

