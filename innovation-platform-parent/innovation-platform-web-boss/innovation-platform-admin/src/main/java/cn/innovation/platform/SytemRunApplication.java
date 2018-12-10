package cn.innovation.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName: SytemRunApplication 
 * @Description: 启动程序
 * @author mqx 
 * @date 2018年12月2日 下午8:45:48
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("cn.innovation.platform.*.mapper")
public class SytemRunApplication {
	public static void main(String[] args) {
		SpringApplication.run(SytemRunApplication.class, args);
		System.out.println("innovation-platform-admin >>>  启动成功! \n");
	}
}