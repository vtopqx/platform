package cn.innovation.platform.gateway.controller.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.enums.GlobalStatusCode;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;
import cn.innovation.platform.upms.service.IApplicationRegisterInfoService;
import cn.innovation.platform.upms.service.ITbAppInfoService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 应用注册信息表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2018-12-11
 */
@Controller
@RequestMapping("/demo/tbAppInfo")
public class AppInfoController {

	@Resource
	private IApplicationRegisterInfoService applicationRegisterInfoService;
	
	@Resource
	private ITbAppInfoService appInfoService;

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
		BaseResult result = new BaseResult(GlobalStatusCode.CODE_200.value(), GlobalStatusCode.CODE_200.remark(), info);
		return result;
	}
	
}
