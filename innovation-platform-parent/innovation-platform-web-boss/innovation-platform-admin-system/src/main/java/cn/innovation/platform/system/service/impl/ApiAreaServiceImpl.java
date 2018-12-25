package cn.innovation.platform.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.innovation.platform.system.mapper.ApiAreaMapper;
import cn.innovation.platform.system.domain.ApiArea;
import cn.innovation.platform.system.service.IApiAreaService;
import cn.innovation.platform.common.support.Convert;

/**
 * 上游渠道城市区域配置 服务层实现
 * 
 * @author mqx
 * @date 2018-12-24
 */
@Service
public class ApiAreaServiceImpl implements IApiAreaService 
{
	@Autowired
	private ApiAreaMapper apiAreaMapper;

	/**
     * 查询上游渠道城市区域配置信息
     * 
     * @param id 上游渠道城市区域配置ID
     * @return 上游渠道城市区域配置信息
     */
    @Override
	public ApiArea selectApiAreaById(Integer id)
	{
	    return apiAreaMapper.selectApiAreaById(id);
	}
	
	/**
     * 查询上游渠道城市区域配置列表
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 上游渠道城市区域配置集合
     */
	@Override
	public List<ApiArea> selectApiAreaList(ApiArea apiArea)
	{
	    return apiAreaMapper.selectApiAreaList(apiArea);
	}
	
    /**
     * 新增上游渠道城市区域配置
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 结果
     */
	@Override
	public int insertApiArea(ApiArea apiArea)
	{
	    return apiAreaMapper.insertApiArea(apiArea);
	}
	
	/**
     * 修改上游渠道城市区域配置
     * 
     * @param apiArea 上游渠道城市区域配置信息
     * @return 结果
     */
	@Override
	public int updateApiArea(ApiArea apiArea)
	{
	    return apiAreaMapper.updateApiArea(apiArea);
	}

	/**
     * 删除上游渠道城市区域配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteApiAreaByIds(String ids)
	{
		return apiAreaMapper.deleteApiAreaByIds(Convert.toStrArray(ids));
	}
	
}
