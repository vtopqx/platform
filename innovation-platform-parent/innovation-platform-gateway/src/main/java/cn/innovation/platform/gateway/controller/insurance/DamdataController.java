package cn.innovation.platform.gateway.controller.insurance;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.gateway.common.advice.HttpHandler;
import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
import cn.innovation.platform.insurance.common.enums.DamdataCodeEnum;
import cn.innovation.platform.insurance.service.IDamdataRecordsService;

/**
 * @ClassName: DamdataController
 * @Description: 大坝保险申请对外接口
 * @author mqx
 * @date 2018年12月16日 下午8:38:24
 */
@Controller
@RequestMapping("/api/insurance")
public class DamdataController {

	@Resource
	private IDamdataRecordsService insuranceDamdataRecordsService;

	/**
	 * @Description: 保险申请
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/thdpy")
	@ResponseBody
	public BaseResult thdpy(@Valid DamdataRecordsDto dto, HttpServletRequest request) {
		// 获取浏览器类型
		String userAgent = request.getHeader("User-Agent");
		dto.setUserAgent(userAgent);
		// 获取IP
		String ip = HttpHandler.getClientIp(request);
		dto.setIp(ip);
		String code = insuranceDamdataRecordsService.addApply(dto);
		if (StringUtils.isNotEmpty(code)) {
			if (code.equals(DamdataCodeEnum.SUCCESS.value())) {
				return new BaseResult(SystemStatusEnum.CODE_200.value(), SystemStatusEnum.CODE_200.remark());
			} else if(code.equals(DamdataCodeEnum.REPEAT.value())){
				return new BaseResult(SystemStatusEnum.CODE_202.value(), SystemStatusEnum.CODE_202.remark());
			} else {
				return new BaseResult(SystemStatusEnum.CODE_417.value(), SystemStatusEnum.CODE_417.remark());
			}
		} else {
			return new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
		}
	}
}
