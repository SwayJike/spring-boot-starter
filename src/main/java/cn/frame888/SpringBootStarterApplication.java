package cn.frame888;

import cn.frame888.annotations.EnableStrategy;
import cn.frame888.wechatpay.annotations.EnableWeChatPay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableResponseBody
@EnableWeChatPay
@EnableStrategy(baseScanPackage = "cn.frame888")
@SpringBootApplication
public class SpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterApplication.class, args);
    }

}
