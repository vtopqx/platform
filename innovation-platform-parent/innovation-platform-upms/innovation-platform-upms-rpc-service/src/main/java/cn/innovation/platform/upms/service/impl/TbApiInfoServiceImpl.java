package cn.innovation.platform.upms.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.constant.GlobalConstant;
import cn.innovation.platform.common.constant.RedisConstant;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.upms.common.model.TbApiInfo;
import cn.innovation.platform.upms.mapper.TbApiInfoMapper;
import cn.innovation.platform.upms.service.IApiInfoService;

/**
 * <p>
 * 上游渠道信息表 服务实现类
 * 
 * @author Administrator
 * @since 2018-12-23
 */
@Service("apiInfoService")
public class TbApiInfoServiceImpl extends ServiceImpl<TbApiInfoMapper, TbApiInfo> implements IApiInfoService {

	private static final Log logger = LogFactory.get();

	@Resource
	private RedisTemplate<String, TbApiInfo> redisTemplate;

	@Override
	public TbApiInfo getApiInfo(String requestId, String apiCode) {
		long startTime = System.currentTimeMillis();
		TbApiInfo apiInfo = null;
		try {
			// 先查询redis中是否存在
			ValueOperations<String, TbApiInfo> ops = redisTemplate.opsForValue();
			String apiInfoKey = RedisConstant.REDIS_APPINFO_PREFIX + apiCode;
			apiInfo = ops.get(apiInfoKey);
			if (!StringUtils.isNotEmpty(apiInfo)) {
				EntityWrapper<TbApiInfo> wrapper = new EntityWrapper<TbApiInfo>();
				wrapper.where("api_code = {0}", apiCode);
				wrapper.and(" status={0}", GlobalConstant.ENABLE);
				apiInfo = this.selectOne(wrapper);
				if (StringUtils.isNotEmpty(apiInfo)) {
					// 存入redis
					ops.set(apiInfoKey, apiInfo);
				}
			}
			logger.info("[平台接口]查询渠道信息完成!流水号{},耗时{},返回:{}", requestId, (System.currentTimeMillis() - startTime),
					apiInfo);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.error("[平台接口]查询渠道信息异常!流水号{},原因:{}", requestId, stackTraceString);
		}
		return apiInfo;
	}

}
