package cn.innovation.platform.upms.rpc.service;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: UpmsRpcServiceApplication 
 * @Description: 应用渠道管理服务
 * @author mqx 
 * @date 2018年12月11日 上午9:15:30
 */
public class UpmsRpcServiceApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/spring/spring-context.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
