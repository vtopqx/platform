package cn.innovation.platform.system.mapper;

import cn.innovation.platform.system.domain.ApiInfo;
import java.util.List;	

/**
 * 上游渠道 数据层
 * 
 * @author mqx
 * @date 2018-12-22
 */
public interface ApiInfoMapper 
{
	/**
     * 查询上游渠道信息
     * 
     * @param id 上游渠道ID
     * @return 上游渠道信息
     */
	public ApiInfo selectApiInfoById(Integer id);
	
	/**
     * 查询上游渠道列表
     * 
     * @param apiInfo 上游渠道信息
     * @return 上游渠道集合
     */
	public List<ApiInfo> selectApiInfoList(ApiInfo apiInfo);
	
	
	/**
     * 新增上游渠道
     * 
     * @param apiInfo 上游渠道信息
     * @return 结果
     */
	public int insertApiInfo(ApiInfo apiInfo);
	
	/**
     * 修改上游渠道
     * 
     * @param apiInfo 上游渠道信息
     * @return 结果
     */
	public int updateApiInfo(ApiInfo apiInfo);
	
	/**
     * 删除上游渠道
     * 
     * @param id 上游渠道ID
     * @return 结果
     */
	public int deleteApiInfoById(Integer id);
	
	/**
     * 批量删除上游渠道
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApiInfoByIds(String[] ids);
	
}