package cn.innovation.platform.web.controller.insurance;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.annotation.Log;
import cn.innovation.platform.common.base.AjaxResult;
import cn.innovation.platform.common.enums.BusinessType;
import cn.innovation.platform.common.utils.ExcelUtil;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.system.domain.ApiCompany;
import cn.innovation.platform.system.domain.AppInfo;
import cn.innovation.platform.system.domain.InsuranceHistoryRecords;
import cn.innovation.platform.system.dto.InsuranceStatisDto;
import cn.innovation.platform.system.service.IApiCompanyService;
import cn.innovation.platform.system.service.IAppInfoService;
import cn.innovation.platform.system.service.IInsuranceHistoryRecordsService;

/**
 * 保险申请记录统计 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-22
 */
@Controller
@RequestMapping("insurance/statis")
public class StatisticsController extends BaseController {
	private String prefix = "insurance/statis";

	@Autowired
	private IInsuranceHistoryRecordsService insuranceHistoryRecordsService;

	@Autowired
	private IApiCompanyService apiCompanyService;
	
	@Autowired
	private IAppInfoService appInfoService;

	@RequiresPermissions("insurance:statis:view")
	@GetMapping()
	public String insuranceHistoryRecords() {
		return prefix + "/statis";
	}

	/**
	 * 查询保险申请记录统计
	 */
	@RequiresPermissions("insurance:statis:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InsuranceHistoryRecords insuranceHistoryRecords) {
		startPage();
		List<InsuranceStatisDto> list = insuranceHistoryRecordsService.selectInsuranceStatis(insuranceHistoryRecords);
		return getDataTable(list);
	}

	@Log(title = "赠险申请统计", businessType = BusinessType.EXPORT)
	@RequiresPermissions("insurance:statis:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(InsuranceHistoryRecords insuranceHistoryRecords) {
		List<InsuranceStatisDto> list = insuranceHistoryRecordsService.selectInsuranceStatis(insuranceHistoryRecords);
		ExcelUtil<InsuranceStatisDto> util = new ExcelUtil<InsuranceStatisDto>(InsuranceStatisDto.class);
		return util.exportExcel(list, "statis");
	}

	/**
	 * 查询供应商列表
	 */
	@PostMapping("/getApiList")
	@ResponseBody
	public List<ApiCompany> getApiList() {
		return apiCompanyService.selectApiCompanyAll(null);
	}
	
	/**
	 * 渠道商列表	
	 */
	@PostMapping("/getAppList")
	@ResponseBody
	public List<AppInfo> getAppList() {
		return appInfoService.selectAppInfoList(new AppInfo());
	}

}
