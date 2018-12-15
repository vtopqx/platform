package cn.innovation.platform.system.mapper;

import cn.innovation.platform.system.domain.AppInfo;
import java.util.List;	

/**
 * 应用注册 数据层
 * 
 * @author mqx
 * @date 2018-12-13
 */
public interface AppInfoMapper 
{
	/**
     * 查询应用注册信息
     * 
     * @param id 应用注册ID
     * @return 应用注册信息
     */
	public AppInfo selectAppInfoById(Integer id);
	
	/**
     * 查询应用注册列表
     * 
     * @param appInfo 应用注册信息
     * @return 应用注册集合
     */
	public List<AppInfo> selectAppInfoList(AppInfo appInfo);
	
	/**
     * 新增应用注册
     * 
     * @param appInfo 应用注册信息
     * @return 结果
     */
	public int insertAppInfo(AppInfo appInfo);
	
	/**
     * 修改应用注册
     * 
     * @param appInfo 应用注册信息
     * @return 结果
     */
	public int updateAppInfo(AppInfo appInfo);
	
	/**
     * 删除应用注册
     * 
     * @param id 应用注册ID
     * @return 结果
     */
	public int deleteAppInfoById(Integer id);
	
	/**
     * 批量删除应用注册
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppInfoByIds(String[] ids);
	
}