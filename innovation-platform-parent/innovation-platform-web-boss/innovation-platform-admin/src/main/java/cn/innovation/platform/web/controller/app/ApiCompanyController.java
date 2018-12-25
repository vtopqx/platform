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
import cn.innovation.platform.system.domain.ApiCompany;
import cn.innovation.platform.system.service.IApiCompanyService;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.common.base.AjaxResult;
import cn.innovation.platform.common.utils.ExcelUtil;

/**
 * 上游渠道公司 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-24
 */
@Controller
@RequestMapping("/app/apiCompany")
public class ApiCompanyController extends BaseController {
	private String prefix = "app/apiCompany";

	@Autowired
	private IApiCompanyService apiCompanyService;

	@RequiresPermissions("app:apiCompany:view")
	@GetMapping()
	public String apiCompany() {
		return prefix + "/apiCompany";
	}

	/**
	 * 查询上游渠道公司列表
	 */
	@RequiresPermissions("app:apiCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ApiCompany apiCompany) {
		startPage();
		List<ApiCompany> list = apiCompanyService.selectApiCompanyList(apiCompany);
		return getDataTable(list);
	}

	/**
	 * 导出上游渠道公司列表
	 */
	@RequiresPermissions("app:apiCompany:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ApiCompany apiCompany) {
		List<ApiCompany> list = apiCompanyService.selectApiCompanyList(apiCompany);
		ExcelUtil<ApiCompany> util = new ExcelUtil<ApiCompany>(ApiCompany.class);
		return util.exportExcel(list, "apiCompany");
	}

	/**
	 * 新增上游渠道公司
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存上游渠道公司
	 */
	@RequiresPermissions("app:apiCompany:add")
	@Log(title = "上游渠道公司", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApiCompany apiCompany) {
		apiCompany.setUpdateTime(new Date());
		return toAjax(apiCompanyService.insertApiCompany(apiCompany));
	}

	/**
	 * 修改上游渠道公司
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		ApiCompany apiCompany = apiCompanyService.selectApiCompanyById(id);
		mmap.put("apiCompany", apiCompany);
		return prefix + "/edit";
	}

	/**
	 * 修改保存上游渠道公司
	 */
	@RequiresPermissions("app:apiCompany:edit")
	@Log(title = "上游渠道公司", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ApiCompany apiCompany) {
		apiCompany.setUpdateTime(new Date());
		return toAjax(apiCompanyService.updateApiCompany(apiCompany));
	}

	/**
	 * 删除上游渠道公司
	 */
	@RequiresPermissions("app:apiCompany:remove")
	@Log(title = "上游渠道公司", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(apiCompanyService.deleteApiCompanyByIds(ids));
	}

}
