package cn.innovation.platform.gateway.controller.insurance;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.gateway.common.advice.HttpHandler;
import cn.innovation.platform.insurance.common.dto.HistoryRecordsDto;
import cn.innovation.platform.insurance.service.IHistoryRecordsService;

/**
 * <p>
 * 保险申请记录汇总表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2018-12-23
 */
@Controller
@RequestMapping("/api/insurance")
public class HistoryRecordsController {

	@Resource
	private IHistoryRecordsService historyRecordsService;

	/**
	 * @Description: 保险申请
	 * @param dto 参数对象
	 * @return
	 */
	@RequestMapping(value = "/apply")
	@ResponseBody
	public BaseResult thdpy(@Valid HistoryRecordsDto dto, HttpServletRequest request) {
		// 获取浏览器类型
		String userAgent = request.getHeader("User-Agent");
		dto.setUserAgent(userAgent);
		// 获取IP
		String ip = HttpHandler.getClientIp(request);
		dto.setClientIp(ip);
		BaseResult result = historyRecordsService.addApply(dto);
		return result;
	}
}
