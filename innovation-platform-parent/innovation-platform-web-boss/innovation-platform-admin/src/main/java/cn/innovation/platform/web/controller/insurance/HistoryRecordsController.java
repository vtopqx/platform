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
import cn.innovation.platform.system.domain.InsuranceHistoryRecords;
import cn.innovation.platform.system.service.IInsuranceHistoryRecordsService;

/**
 * 保险申请记录汇总 信息操作处理
 * @author mqx
 * @date 2018-12-22
 */
@Controller
@RequestMapping("insurance/history")
public class HistoryRecordsController extends BaseController {
	private String prefix = "insurance/history";

	@Autowired
	private IInsuranceHistoryRecordsService insuranceHistoryRecordsService;

	@RequiresPermissions("insurance:history:view")
	@GetMapping()
	public String insuranceHistoryRecords() {
		return prefix + "/history";
	}

	/**
	 * 查询保险申请记录汇总列表
	 */
	@RequiresPermissions("insurance:history:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InsuranceHistoryRecords insuranceHistoryRecords) {
		startPage();
		List<InsuranceHistoryRecords> list = insuranceHistoryRecordsService
				.selectInsuranceHistoryRecordsList(insuranceHistoryRecords);
		return getDataTable(list);
	}

	/**
	 * 导出保险申请记录汇总列表
	 */
	@RequiresPermissions("insurance:history:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(InsuranceHistoryRecords insuranceHistoryRecords) {
		List<InsuranceHistoryRecords> list = insuranceHistoryRecordsService
				.selectInsuranceHistoryRecordsList(insuranceHistoryRecords);
		ExcelUtil<InsuranceHistoryRecords> util = new ExcelUtil<InsuranceHistoryRecords>(InsuranceHistoryRecords.class);
		return util.exportExcel(list, "insuranceHistoryRecords");
	}

	/**
	 * 删除保险申请记录汇总
	 */
	@RequiresPermissions("insurance:history:remove")
	@Log(title = "保险申请记录汇总", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(insuranceHistoryRecordsService.deleteInsuranceHistoryRecordsByIds(ids));
	}

}
