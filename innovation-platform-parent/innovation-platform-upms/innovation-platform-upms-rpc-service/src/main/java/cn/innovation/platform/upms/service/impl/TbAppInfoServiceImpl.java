package cn.innovation.platform.upms.service.impl;

import java.util.HashMap;
import java.util.Map;

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

import cn.innovation.platform.common.constant.RedisConstant;
import cn.innovation.platform.common.utils.StringUtils;
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
public class TbAppInfoServiceImpl extends ServiceImpl<TbAppInfoMapper, TbAppInfo> implements ITbAppInfoService {

	private static final Log logger = LogFactory.get();

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public TbAppInfo getAppInfo(String appKey) {
		long startTime = System.currentTimeMillis();
		TbAppInfo appInfo = new TbAppInfo();
		// 先查询redis中是否存在
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String appInfoKey = RedisConstant.REDIS_APPINFO_PREFIX + appKey;
		String appInfoStr = ops.get(appInfoKey);
		if (StringUtils.isNotEmpty(appInfoStr)) {
			JSONObject jsonObject = JSONUtil.parseObj(appInfoStr);
			String apiList = jsonObject.getStr("apiList");
			String channelCode = jsonObject.getStr("channelCode");
			String appSecret = jsonObject.getStr("appSecret");
			appInfo.setId(Integer.valueOf(appKey));
			appInfo.setChannelCode(channelCode);
			appInfo.setAppSecret(appSecret);
			appInfo.setApiList(apiList);
		} else {
			// 查询数据库验证
			EntityWrapper<TbAppInfo> wrapper = new EntityWrapper<TbAppInfo>();
			wrapper.where("id = {0}", appKey);
			wrapper.and(" status=0 ");
			appInfo = this.selectOne(wrapper);
			// 重新放到redis中
			if (StringUtils.isNotEmpty(appInfo)) {
				Map<String, String> appMap = new HashMap<String, String>();
				appMap.put("appKey", appKey);
				appMap.put("apiList", appInfo.getApiList());
				appMap.put("channelCode", appInfo.getChannelCode());
				appMap.put("appSecret", appInfo.getAppSecret());
				//存如redis
				ops.set(appInfoKey, JSONUtil.toJsonStr(appMap));
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("[应用接口]查询应用平台权限，耗时{},返回:{}", (endTime - startTime),appInfo);
		return appInfo;
	}

}
