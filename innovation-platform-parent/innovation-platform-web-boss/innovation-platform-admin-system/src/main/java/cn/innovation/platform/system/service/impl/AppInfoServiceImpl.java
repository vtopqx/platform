package cn.innovation.platform.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.innovation.platform.common.support.Convert;
import cn.innovation.platform.system.domain.AppInfo;
import cn.innovation.platform.system.mapper.AppInfoMapper;
import cn.innovation.platform.system.service.IAppInfoService;

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
		return appInfoMapper.updateAppInfo(appInfo);
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
		return appInfoMapper.deleteAppInfoByIds(Convert.toStrArray(ids));
	}

}
