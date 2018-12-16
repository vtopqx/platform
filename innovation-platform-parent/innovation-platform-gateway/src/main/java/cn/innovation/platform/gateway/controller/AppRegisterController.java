package cn.innovation.platform.gateway.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;
import cn.innovation.platform.upms.service.IApplicationRegisterInfoService;

/**
 * @ClassName: AppRegisterController
 * @Description: 应用注册服务
 * @author mqx
 * @date 2018年11月19日 下午4:59:51
 */
@Controller
@RequestMapping("/api/app")
public class AppRegisterController {

	@Resource
	private IApplicationRegisterInfoService applicationRegisterInfoService;

	/**
	 * @Description: 应用注册
	 * @param paramJson
	 *            注册信息json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult register(String consumerKey) {
		// 查询该应用是否已经添加
		ApplicationRegisterInfo info = applicationRegisterInfoService.getApplicationAccount(consumerKey);
		BaseResult result = new BaseResult(SystemStatusEnum.CODE_200.value(), SystemStatusEnum.CODE_200.remark(), info);
		return result;
	}

}
