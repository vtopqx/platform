package cn.innovation.platform.upms.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.constant.RedisConstant;
import cn.innovation.platform.upms.common.model.TbAppInfo;
import cn.innovation.platform.upms.mapper.TbAppInfoMapper;
import cn.innovation.platform.upms.service.ITbAppInfoService;

/**
 * @ClassName: ITbAppInfoServiceImpl 
 * @Description: 应用注册信息接口实现
 * @author mqx 
 * @date 2018年12月11日 下午10:51:33
 */
@Service("appInfoService")
public class ITbAppInfoServiceImpl extends ServiceImpl<TbAppInfoMapper, TbAppInfo> implements ITbAppInfoService {

	private static final Log logger = LogFactory.get();

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String getApplicationAccount(String appKey) {
		long startTime = System.currentTimeMillis();
		// 先查询redis中是否存在
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String key = RedisConstant.APP_USER_PREFIX + appKey;
		String appSecret = ops.get(key);
		if (appSecret == null) {
			// 查询数据库验证
			TbAppInfo info = this.selectById(appKey);
			// 重新放到redis中
			if (!StringUtils.isEmpty(info)) {
				ops.set(key, info.getAppSecret());
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("[应用注册]查询应用创新平台权限，耗时{}", (endTime - startTime));
		return appSecret;
	}

}
