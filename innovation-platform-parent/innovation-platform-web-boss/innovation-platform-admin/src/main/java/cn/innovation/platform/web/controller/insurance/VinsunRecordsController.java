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
import cn.innovation.platform.system.domain.InsuranceVinsunRecords;
import cn.innovation.platform.system.service.IInsuranceVinsunRecordsService;

/**
 * 畅思保险申请记录 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-16
 */
@Controller
@RequestMapping("/insurance/vinsun")
public class VinsunRecordsController extends BaseController {
	private String prefix = "insurance/vinsun";

	@Autowired
	private IInsuranceVinsunRecordsService insuranceVinsunRecordsService;

	@RequiresPermissions("insurance:vinsun:view")
	@GetMapping()
	public String vinsun() {
		return prefix + "/vinsun";
	}

	/**
	 * 查询畅思保险申请记录列表
	 */
	@RequiresPermissions("insurance:vinsun:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InsuranceVinsunRecords vinsun) {
		startPage();
		List<InsuranceVinsunRecords> list = insuranceVinsunRecordsService
				.selectInsuranceVinsunRecordsList(vinsun);
		return getDataTable(list);
	}

	/**
	 * 导出畅思保险申请记录列表
	 */
	@RequiresPermissions("insurance:vinsun:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(InsuranceVinsunRecords vinsun) {
		List<InsuranceVinsunRecords> list = insuranceVinsunRecordsService
				.selectInsuranceVinsunRecordsList(vinsun);
		ExcelUtil<InsuranceVinsunRecords> util = new ExcelUtil<InsuranceVinsunRecords>(InsuranceVinsunRecords.class);
		return util.exportExcel(list, "vinsun");
	}

	/**
	 * 删除畅思保险申请记录
	 */
	@RequiresPermissions("insurance:vinsun:remove")
	@Log(title = "畅思保险申请记录", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(insuranceVinsunRecordsService.deleteInsuranceVinsunRecordsByIds(ids));
	}

}
