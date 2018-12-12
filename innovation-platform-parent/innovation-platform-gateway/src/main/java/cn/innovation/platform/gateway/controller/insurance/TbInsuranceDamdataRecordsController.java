package cn.innovation.platform.gateway.controller.insurance;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.insurance.service.ITbInsuranceDamdataRecordsService;

/**
 * <p>
 * 大坝保险申请记录表 前端控制器
 * </p>
 * @author Administrator
 * @since 2018-12-11
 */
@Controller
@RequestMapping("/insurance")
public class TbInsuranceDamdataRecordsController {
	
	@Resource
	private ITbInsuranceDamdataRecordsService insuranceDamdataRecordsService;
	

	/**
	 * @Description: 应用注册
	 * @param paramJson 注册信息json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult register(String consumerKey) {
		return null;
	}
}
