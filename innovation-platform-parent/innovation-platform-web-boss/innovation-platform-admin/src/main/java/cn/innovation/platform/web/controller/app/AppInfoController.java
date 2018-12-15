package cn.innovation.platform.web.controller.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import cn.innovation.platform.common.utils.TokenProcessor;
import cn.innovation.platform.framework.web.base.BaseController;
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.system.domain.AppInfo;
import cn.innovation.platform.system.service.IAppInfoService;

/**
 * 应用注册 信息操作处理
 * 
 * @author mqx
 * @date 2018-12-13
 */
@Controller
@RequestMapping("/app/appInfo")
public class AppInfoController extends BaseController {
	private String prefix = "app/appInfo";

	@Autowired
	private IAppInfoService appInfoService;

	@RequiresPermissions("app:appInfo:view")
	@GetMapping()
	public String appInfo() {
		return prefix + "/appInfo";
	}

	/**
	 * 查询应用注册列表
	 */
	@RequiresPermissions("app:appInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AppInfo appInfo) {
		startPage();
		List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
		return getDataTable(list);
	}

	/**
	 * 导出应用注册列表
	 */
	@RequiresPermissions("app:appInfo:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AppInfo appInfo) {
		List<AppInfo> list = appInfoService.selectAppInfoList(appInfo);
		ExcelUtil<AppInfo> util = new ExcelUtil<AppInfo>(AppInfo.class);
		return util.exportExcel(list, "appInfo");
	}

	/**
	 * 新增应用注册
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存应用注册
	 */
	@RequiresPermissions("app:appInfo:add")
	@Log(title = "应用注册", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request,AppInfo appInfo) {
		appInfo.setAppSecret(TokenProcessor.getInstance().generateToken(request));
		return toAjax(appInfoService.insertAppInfo(appInfo));
	}

	/**
	 * 修改应用注册
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		AppInfo appInfo = appInfoService.selectAppInfoById(id);
		mmap.put("appInfo", appInfo);
		return prefix + "/edit";
	}

	/**
	 * 修改保存应用注册
	 */
	@RequiresPermissions("app:appInfo:edit")
	@Log(title = "应用注册", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AppInfo appInfo) {
		return toAjax(appInfoService.updateAppInfo(appInfo));
	}

	/**
	 * 删除应用注册
	 */
	@RequiresPermissions("app:appInfo:remove")
	@Log(title = "应用注册", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(appInfoService.deleteAppInfoByIds(ids));
	}

}
