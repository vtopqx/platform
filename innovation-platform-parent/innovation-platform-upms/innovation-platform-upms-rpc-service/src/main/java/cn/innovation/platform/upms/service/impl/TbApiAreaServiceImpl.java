package cn.innovation.platform.upms.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.upms.common.model.TbApiArea;
import cn.innovation.platform.upms.mapper.TbApiAreaMapper;
import cn.innovation.platform.upms.service.IApiAreaService;

/**
 * @ClassName: TbApiAreaServiceImpl
 * @Description: 上游渠道城市区域配置表 服务实现类
 * @author mqx
 * @date 2018年12月25日 下午5:55:26
 */
@Service("apiAreaService")
public class TbApiAreaServiceImpl extends ServiceImpl<TbApiAreaMapper, TbApiArea> implements IApiAreaService {

	private static final Log logger = LogFactory.get();

	@Override
	public List<TbApiArea> getApiArea(String requestId, Integer companyId, String apiCode) {
		long startTime = System.currentTimeMillis();
		List<TbApiArea> list = null;
		try {
			EntityWrapper<TbApiArea> wrapper = new EntityWrapper<TbApiArea>();
			wrapper.where("company_id = {0}", companyId);
			list = this.selectList(wrapper);
			logger.info("[平台接口]查询渠道城市地域信息完成!流水号:{},耗时{}", requestId, (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.error("[平台接口]查询渠道城市地域信息异常!流水号:{},原因:{}", requestId, stackTraceString);
		}
		return list;
	}

}
