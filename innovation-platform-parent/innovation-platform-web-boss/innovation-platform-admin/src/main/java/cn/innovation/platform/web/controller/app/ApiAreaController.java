package cn.innovation.platform.web.controller.app;

import java.util.Date;
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
import cn.innovation.platform.common.enums.BusinessType;
import cn.innovation.platform.system.domain.ApiArea;
import cn.innovation.platform.system.service.IApiAreaService;
import cn.innovation.platform.system.service.IApiCompanyService;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.common.base.AjaxResult;
import cn.innovation.platform.common.utils.ExcelUtil;
import cn.innovation.platform.common.utils.StringUtils;

/**
 * 上游渠道城市区域配置 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-24
 */
@Controller
@RequestMapping("/app/apiArea")
public class ApiAreaController extends BaseController {
	private String prefix = "app/apiArea";

	@Autowired
	private IApiAreaService apiAreaService;

	@Autowired
	private IApiCompanyService apiCompanyService;

	@RequiresPermissions("app:apiArea:view")
	@GetMapping()
	public String apiArea() {
		return prefix + "/apiArea";
	}

	/**
	 * 查询上游渠道城市区域配置列表
	 */
	@RequiresPermissions("app:apiArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ApiArea apiArea) {
		startPage();
		List<ApiArea> list = apiAreaService.selectApiAreaList(apiArea);
		return getDataTable(list);
	}

	/**
	 * 导出上游渠道城市区域配置列表
	 */
	@RequiresPermissions("app:apiArea:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ApiArea apiArea) {
		List<ApiArea> list = apiAreaService.selectApiAreaList(apiArea);
		ExcelUtil<ApiArea> util = new ExcelUtil<ApiArea>(ApiArea.class);
		return util.exportExcel(list, "apiArea");
	}

	/**
	 * 新增上游渠道城市区域配置
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put("apiCompany", apiCompanyService.selectApiCompanyAll(null));
		return prefix + "/add";
	}

	/**
	 * 新增保存上游渠道城市区域配置
	 */
	@RequiresPermissions("app:apiArea:add")
	@Log(title = "上游渠道城市区域配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApiArea apiArea) {
		apiArea.setUpdateTime(new Date());
		String selectVal = apiArea.getCompanyName();
		if (StringUtils.isNotEmpty(selectVal)) {
			String[] array = selectVal.split(",");
			apiArea.setCompanyId(Integer.valueOf(array[0]));
			apiArea.setCompanyName(array[1]);
		}
		return toAjax(apiAreaService.insertApiArea(apiArea));
	}

	/**
	 * 修改上游渠道城市区域配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		ApiArea apiArea = apiAreaService.selectApiAreaById(id);
		mmap.put("apiArea", apiArea);
		mmap.put("apiCompany", apiCompanyService.selectApiCompanyAll(apiArea.getCompanyId().toString()));
		return prefix + "/edit";
	}

	/**
	 * 修改保存上游渠道城市区域配置
	 */
	@RequiresPermissions("app:apiArea:edit")
	@Log(title = "上游渠道城市区域配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ApiArea apiArea) {
		apiArea.setUpdateTime(new Date());
		String selectVal = apiArea.getCompanyName();
		if (StringUtils.isNotEmpty(selectVal)) {
			String[] array = selectVal.split(",");
			apiArea.setCompanyId(Integer.valueOf(array[0]));
			apiArea.setCompanyName(array[1]);
		}
		return toAjax(apiAreaService.updateApiArea(apiArea));
	}

	/**
	 * 删除上游渠道城市区域配置
	 */
	@RequiresPermissions("app:apiArea:remove")
	@Log(title = "上游渠道城市区域配置", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(apiAreaService.deleteApiAreaByIds(ids));
	}

}
