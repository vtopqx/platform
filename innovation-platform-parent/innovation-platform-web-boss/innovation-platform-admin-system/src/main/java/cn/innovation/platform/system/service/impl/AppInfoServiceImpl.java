package cn.innovation.platform.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.json.JSONUtil;

import cn.innovation.platform.common.constant.Constants;
import cn.innovation.platform.common.support.Convert;
import cn.innovation.platform.system.domain.AppInfo;
import cn.innovation.platform.system.mapper.AppInfoMapper;
import cn.innovation.platform.system.service.IAppInfoService;
import cn.innovation.platform.system.service.RedisService;

/**
 * 应用注册 服务层实现
 * 
 * @author mqx
 * @date 2018-12-13
 */
@Service
public class AppInfoServiceImpl implements IAppInfoService {
	@Autowired
	private AppInfoMapper appInfoMapper;

	@Autowired
	private RedisService redisService;

	/**
	 * 查询应用注册信息
	 * 
	 * @param id
	 *            应用注册ID
	 * @return 应用注册信息
	 */
	@Override
	public AppInfo selectAppInfoById(Integer id) {
		return appInfoMapper.selectAppInfoById(id);
	}

	/**
	 * 查询应用注册列表
	 * 
	 * @param appInfo
	 *            应用注册信息
	 * @return 应用注册集合
	 */
	@Override
	public List<AppInfo> selectAppInfoList(AppInfo appInfo) {
		return appInfoMapper.selectAppInfoList(appInfo);
	}

	/**
	 * 新增应用注册
	 * 
	 * @param appInfo
	 *            应用注册信息
	 * @return 结果
	 */
	@Override
	public int insertAppInfo(AppInfo appInfo) {
		appInfo.setUpdateTime(new Date());
		return appInfoMapper.insertAppInfo(appInfo);
	}

	/**
	 * 修改应用注册
	 * 
	 * @param appInfo
	 *            应用注册信息
	 * @return 结果
	 */
	@Override
	public int updateAppInfo(AppInfo appInfo) {
		int result = appInfoMapper.updateAppInfo(appInfo);
		if (result > 0) {
			// 禁用时删除缓存
			if (appInfo.getStatus().toString().equals(Constants.DISENABLE)) {
				redisService.remove(Constants.REDIS_APPINFO_PREFIX + appInfo.getId());
			} else {
				// 存入redis
				redisService.set(Constants.REDIS_APPINFO_PREFIX + appInfo.getId(), JSONUtil.toJsonStr(appInfo));
			}
		}
		return result;
	}

	/**
	 * 删除应用注册对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAppInfoByIds(String ids) {
		int result = appInfoMapper.deleteAppInfoByIds(Convert.toStrArray(ids));
		if (result > 0) {
			String[] idArray = Convert.toStrArray(ids);
			for (String id : idArray) {
				redisService.remove(Constants.REDIS_APPINFO_PREFIX + id);
			}
		}
		return result;
	}

}
