package cn.innovation.platform.common.utils;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * 工具类
 * @author L.cm
 */
public class RuntimeUtils {

    private static final Log logger = LogFactory.get();
    
    /**
     * 运行shell
     * @param script
     */
    public static void runShell(String script) {
        Process process = null;
        try {
            String[] cmd = { "sh", script };
            //执行liunx命令
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != process) {
                process.destroy();
            }
        }
    }

}
