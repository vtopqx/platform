package cn.innovation.platform.gateway.controller.insurance;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.gateway.common.advice.HttpHandler;
import cn.innovation.platform.insurance.common.dto.VinsunRecordsDto;
import cn.innovation.platform.insurance.common.enums.VinsunCodeEnum;
import cn.innovation.platform.insurance.service.IVinsunRecordsService;

/**
 * @ClassName: VinsunController
 * @Description: 畅思保险对外接口
 * @author mqx
 * @date 2018年12月18日 下午10:47:54
 */
@Controller
@RequestMapping("/api/insurance")
public class VinsunController {

	@Resource
	private IVinsunRecordsService vinsunRecordsService;

	/**
	 * @Description: 保险申请
	 * @param dto
	 *            参数对象
	 * @return
	 */
	@RequestMapping(value = "/axhao", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult thdpy(@Valid VinsunRecordsDto dto, HttpServletRequest request) {
		// 获取浏览器类型
		// String userAgent = request.getHeader("User-Agent");
		// 获取IP
		String ip = HttpHandler.getClientIp(request);
		dto.setClientIp(ip);
		dto.setUa("Mozilla");

		Map<String, String> resultMap = vinsunRecordsService.addApply(dto);
		if (StringUtils.isNotEmpty(resultMap)) {
			String code = resultMap.get("code");
			if (code.equals(VinsunCodeEnum.CODE_1001.value())) {
				return new BaseResult(SystemStatusEnum.CODE_200.value(), SystemStatusEnum.CODE_200.remark(), resultMap);
			} else if (code.equals(VinsunCodeEnum.CODE_1010.value())) {
				return new BaseResult(SystemStatusEnum.CODE_202.value(), SystemStatusEnum.CODE_202.remark());
			} else if (code.equals(VinsunCodeEnum.CODE_1005.value())) {
				return new BaseResult(SystemStatusEnum.CODE_400.value(), SystemStatusEnum.CODE_400.remark());
			} else {
				return new BaseResult(SystemStatusEnum.CODE_417.value(), SystemStatusEnum.CODE_417.remark());
			}
		} else {
			return new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
		}
	}

}
