package cn.innovation.platform.web.controller.insurance;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.innovation.platform.common.annotation.Log;
import cn.innovation.platform.common.base.AjaxResult;
import cn.innovation.platform.common.enums.BusinessType;
import cn.innovation.platform.common.utils.ExcelUtil;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.system.domain.InsuranceDamdataRecords;
import cn.innovation.platform.system.service.IInsuranceDamdataRecordsService;

/**
 * @ClassName: DamdataRecordsController 
 * @Description: 大坝保险申请记录 信息操作处理
 * @author mqx 
 * @date 2018年12月15日 下午3:43:04
 */
@Controller
@RequestMapping("/insurance/damdata")
public class DamdataRecordsController extends BaseController {
	private String prefix = "insurance/damdata";

	@Autowired
	private IInsuranceDamdataRecordsService insuranceDamdataRecordsService;

	@RequiresPermissions("insurance:damdata:view")
	@GetMapping()
	public String damdata() {
		return prefix + "/damdata";
	}

	/**
	 * 查询大坝保险申请记录列表
	 */
	@RequiresPermissions("insurance:damdata:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InsuranceDamdataRecords damdata) {
		startPage();
		List<InsuranceDamdataRecords> list = insuranceDamdataRecordsService
				.selectInsuranceDamdataRecordsList(damdata);
		return getDataTable(list);
	}

	/**
	 * 导出大坝保险申请记录列表
	 */
	@RequiresPermissions("insurance:damdata:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(InsuranceDamdataRecords damdata) {
		List<InsuranceDamdataRecords> list = insuranceDamdataRecordsService
				.selectInsuranceDamdataRecordsList(damdata);
		ExcelUtil<InsuranceDamdataRecords> util = new ExcelUtil<InsuranceDamdataRecords>(InsuranceDamdataRecords.class);
		return util.exportExcel(list, "damdata");
	}

	/**
	 * 新增大坝保险申请记录
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存大坝保险申请记录
	 */
	@RequiresPermissions("insurance:damdata:add")
	@Log(title = "大坝保险申请记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(InsuranceDamdataRecords damdata) {
		return toAjax(insuranceDamdataRecordsService.insertInsuranceDamdataRecords(damdata));
	}

	/**
	 * 修改大坝保险申请记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		InsuranceDamdataRecords damdata = insuranceDamdataRecordsService
				.selectInsuranceDamdataRecordsById(id);
		mmap.put("damdata", damdata);
		return prefix + "/edit";
	}

	/**
	 * 修改保存大坝保险申请记录
	 */
	@RequiresPermissions("insurance:damdata:edit")
	@Log(title = "大坝保险申请记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(InsuranceDamdataRecords damdata) {
		return toAjax(insuranceDamdataRecordsService.updateInsuranceDamdataRecords(damdata));
	}

	/**
	 * 删除大坝保险申请记录
	 */
	@RequiresPermissions("insurance:damdata:remove")
	@Log(title = "大坝保险申请记录", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(insuranceDamdataRecordsService.deleteInsuranceDamdataRecordsByIds(ids));
	}

}
