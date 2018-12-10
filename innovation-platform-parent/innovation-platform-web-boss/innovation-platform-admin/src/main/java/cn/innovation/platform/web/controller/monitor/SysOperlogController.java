package cn.innovation.platform.web.controller.monitor;

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
import cn.innovation.platform.framework.web.page.TableDataInfo;
import cn.innovation.platform.system.domain.SysOperLog;
import cn.innovation.platform.system.service.ISysOperLogService;
import cn.innovation.platform.framework.web.base.BaseController;

/**
 * 操作日志记录
 * 
 * @author mqx
 */
@Controller
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{
    private String prefix = "monitor/operlog";

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/operlog";
    }

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:operlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOperLog operLog)
    {
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "operLog");
    }

    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(operLogService.deleteOperLogByIds(ids));
    }

    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap)
    {
        mmap.put("operLog", operLogService.selectOperLogById(operId));
        return prefix + "/detail";
    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return success();
    }
}
