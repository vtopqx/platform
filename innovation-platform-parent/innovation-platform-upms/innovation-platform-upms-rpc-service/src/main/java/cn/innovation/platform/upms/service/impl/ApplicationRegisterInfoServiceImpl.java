package cn.innovation.platform.upms.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.upms.common.dto.AppRegisterInfoDto;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;
import cn.innovation.platform.upms.mapper.ApplicationRegisterInfoMapper;
import cn.innovation.platform.upms.service.IApplicationRegisterInfoService;

/**
 * @ClassName: ApplicationRegisterInfoServiceImpl
 * @Description: 应用注册服务实现
 * @author mqx
 * @date 2017年12月7日 下午1:48:13
 */
@Service("applicationRegisterInfoService")
public class ApplicationRegisterInfoServiceImpl extends
		ServiceImpl<ApplicationRegisterInfoMapper, ApplicationRegisterInfo> implements IApplicationRegisterInfoService {

	private static final Log logger = LogFactory.get();
	
	@Resource
	private RedisTemplate<String, ApplicationRegisterInfo> redisTemplate;


	/**
	 * 根据Key获取注册信息 验证consumerKey是否正确
	 */
	@Override
	public ApplicationRegisterInfo getApplicationAccount(String consumerKey) {
		logger.info("upms:查询应用注册信息!");
		long startTime = System.currentTimeMillis();
		// 先查询redis中是否存在
		ValueOperations<String, ApplicationRegisterInfo> ops = redisTemplate.opsForValue();
		String key = "USER:" + consumerKey;
		ApplicationRegisterInfo appInfo = ops.get(key);
		if (appInfo == null) {
			// 查询数据库验证
			appInfo = this.selectById(consumerKey);
			// 重新放到redis中
			if (!StringUtils.isEmpty(appInfo)) {
				ops.set(key, appInfo);
			}
		}
		long endTime = System.currentTimeMillis();
		logger.debug("查询应用创新平台权限，耗时{}", (endTime - startTime));
		return appInfo;
	}

	/**
	 * app注册创新平台consumerKey用户
	 */
	@Override
	public boolean registerAppUser(AppRegisterInfoDto registerInfo) {
		boolean isSuccess = false;
		return isSuccess;
	}

}
