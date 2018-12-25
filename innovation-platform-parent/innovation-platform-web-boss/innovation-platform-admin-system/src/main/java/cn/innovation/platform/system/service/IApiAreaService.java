package cn.innovation.platform.system.service;

import cn.innovation.platform.system.domain.ApiArea;
import java.util.List;

/**
 * 上游渠道城市区域配置 服务层
 * 
 * @author mqx
 * @date 2018-12-24
 */
public interface IApiAreaService 
{
	/**
     * 查询上游渠道城市区域配置信息
     * 
     * @param id 上游渠道城市区域配置ID
     * @return 上游渠道城市区域配置信息
     */
	public ApiArea selectApiAreaById(Integer id);
	
	/**
     * 查询上游渠道城市区域配置列表
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 上游渠道城市区域配置集合
     */
	public List<ApiArea> selectApiAreaList(ApiArea apiArea);
	
	/**
     * 新增上游渠道城市区域配置
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 结果
     */
	public int insertApiArea(ApiArea apiArea);
	
	/**
     * 修改上游渠道城市区域配置
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 结果
     */
	public int updateApiArea(ApiArea apiArea);
		
	/**
     * 删除上游渠道城市区域配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApiAreaByIds(String ids);
	
}
