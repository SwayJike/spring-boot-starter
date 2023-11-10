package cn.frame888.wechatpay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 14:44
 */
@ConfigurationProperties(prefix = "wechat")
public class WechatPayConfig {

    private String appId;

    private String mchId;

    private String key;

    private String certStr;

    public WechatPayConfig(){}

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertStr() {
        return certStr;
    }

    public void setCertStr(String certStr) {
        this.certStr = certStr;
    }
}
