package cn.innovation.platform;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName: SystemServletInitializer 
 * @Description: web容器中进行部署
 * @author mqx 
 * @date 2018年12月2日 下午8:45:36
 */
public class SystemServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SytemRunApplication.class);
	}
}
