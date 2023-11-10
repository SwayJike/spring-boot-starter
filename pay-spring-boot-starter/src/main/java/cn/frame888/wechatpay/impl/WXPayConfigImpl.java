package cn.frame888.wechatpay.impl;

import cn.frame888.wechatpay.WeChatPayService;
import cn.frame888.wechatpay.WechatPayConfig;
import com.github.wxpay.sdk.WXPayConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;
import java.io.*;
import java.util.Base64;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 14:30
 */
@Slf4j
@EnableConfigurationProperties(WechatPayConfig.class)
public class WXPayConfigImpl implements WXPayConfig {

    public WXPayConfigImpl(){
        log.info("Initializing Pay For 'WXPayConfigImpl'");
    }

    @Resource
    private WechatPayConfig wechatPayConfig;

    @Override
    public String getAppID() {
        return wechatPayConfig.getAppId();
    }

    @Override
    public String getMchID() {
        return wechatPayConfig.getMchId();
    }

    @Override
    public String getKey() {
        return wechatPayConfig.getKey();
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(Base64.getDecoder().decode(wechatPayConfig.getCertStr().split(",")[1].getBytes()));
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }

    /**
     * 将证书解析为Base64字符串
     * @return
     */
    public static String parseCertFile(InputStream in){
        // 加载 P12 证书文件
        String certBase64 = "";
        try {
            // 将输入流转为字节数组
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] certByte = baos.toByteArray();

            // 将字节数组转换成 Base64 字符串
            certBase64 = "data:application/x-pkcs12;base64," + Base64.getEncoder().encodeToString(certByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return certBase64;
    }
}
