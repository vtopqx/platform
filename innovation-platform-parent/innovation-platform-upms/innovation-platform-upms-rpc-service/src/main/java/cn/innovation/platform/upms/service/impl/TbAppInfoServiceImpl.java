package cn.innovation.platform.upms.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.constant.GlobalConstant;
import cn.innovation.platform.common.constant.RedisConstant;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.upms.common.model.TbAppInfo;
import cn.innovation.platform.upms.mapper.TbAppInfoMapper;
import cn.innovation.platform.upms.service.IAppInfoService;

/**
 * @ClassName: ITbAppInfoServiceImpl
 * @Description: 应用注册信息接口实现
 * @author mqx
 * @date 2018年12月11日 下午10:51:33
 */
@Service("appInfoService")
public class TbAppInfoServiceImpl extends ServiceImpl<TbAppInfoMapper, TbAppInfo> implements IAppInfoService {

	private static final Log logger = LogFactory.get();

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public TbAppInfo getAppInfo(String appKey) {
		long startTime = System.currentTimeMillis();
		TbAppInfo appInfo = new TbAppInfo();
		try {
			// 先查询redis中是否存在
			ValueOperations<String, String> ops = redisTemplate.opsForValue();
			String appInfoKey = RedisConstant.REDIS_APPINFO_PREFIX + appKey;
			String jsonStr = ops.get(appInfoKey);
			if (!StringUtils.isNotEmpty(jsonStr)) {
				// 查询数据库验证
				EntityWrapper<TbAppInfo> wrapper = new EntityWrapper<TbAppInfo>();
				wrapper.where("id = {0}", appKey);
				wrapper.and(" status={0}", GlobalConstant.ENABLE);
				appInfo = this.selectOne(wrapper);
				// 重新放到redis中
				if (StringUtils.isNotEmpty(appInfo)) {
					// 存入redis
					ops.set(appInfoKey, JSONUtil.toJsonStr(appInfo));
				}
			}else{
				JSONObject obj = JSONUtil.parseObj(jsonStr);
				if (StringUtils.isNotEmpty(obj)) {
					appInfo = obj.toBean(TbAppInfo.class);
				}
			}
			logger.info("[平台接口]查询平台接口权限完成!耗时{},返回:{}", (System.currentTimeMillis() - startTime), appInfo);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.error("[平台接口]查询平台接口权限异常!appKey:{},原因:{}", appKey, stackTraceString);
		}
		return appInfo;
	}

}
