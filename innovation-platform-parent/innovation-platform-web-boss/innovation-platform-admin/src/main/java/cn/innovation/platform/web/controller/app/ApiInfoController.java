package cn.innovation.platform.web.controller.app;

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
import cn.innovation.platform.system.domain.ApiInfo;
import cn.innovation.platform.system.service.IApiInfoService;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.common.base.AjaxResult;
import cn.innovation.platform.common.utils.ExcelUtil;

/**
 * 上游渠道 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-22
 */
@Controller
@RequestMapping("/app/apiInfo")
public class ApiInfoController extends BaseController {
	private String prefix = "app/apiInfo";

	@Autowired
	private IApiInfoService apiInfoService;

	@RequiresPermissions("app:apiInfo:view")
	@GetMapping()
	public String apiInfo() {
		return prefix + "/apiInfo";
	}

	/**
	 * 查询上游渠道列表
	 */
	@RequiresPermissions("app:apiInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ApiInfo apiInfo) {
		startPage();
		List<ApiInfo> list = apiInfoService.selectApiInfoList(apiInfo);
		return getDataTable(list);
	}

	/**
	 * 导出上游渠道列表
	 */
	@RequiresPermissions("app:apiInfo:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ApiInfo apiInfo) {
		List<ApiInfo> list = apiInfoService.selectApiInfoList(apiInfo);
		ExcelUtil<ApiInfo> util = new ExcelUtil<ApiInfo>(ApiInfo.class);
		return util.exportExcel(list, "apiInfo");
	}

	/**
	 * 新增上游渠道
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存上游渠道
	 */
	@RequiresPermissions("app:apiInfo:add")
	@Log(title = "上游渠道", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApiInfo apiInfo) {
		return toAjax(apiInfoService.insertApiInfo(apiInfo));
	}

	/**
	 * 修改上游渠道
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		ApiInfo apiInfo = apiInfoService.selectApiInfoById(id);
		mmap.put("apiInfo", apiInfo);
		return prefix + "/edit";
	}

	/**
	 * 修改保存上游渠道
	 */
	@RequiresPermissions("app:apiInfo:edit")
	@Log(title = "上游渠道", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ApiInfo apiInfo) {
		return toAjax(apiInfoService.updateApiInfo(apiInfo));
	}

	/**
	 * 删除上游渠道
	 */
	@RequiresPermissions("app:apiInfo:remove")
	@Log(title = "上游渠道", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(apiInfoService.deleteApiInfoByIds(ids));
	}

}
