package cn.innovation.platform.system.mapper;

import cn.innovation.platform.system.domain.ApiCompany;
import java.util.List;	

/**
 * 上游渠道公司 数据层
 * 
 * @author mqx
 * @date 2018-12-24
 */
public interface ApiCompanyMapper 
{
	/**
     * 查询上游渠道公司信息
     * 
     * @param id 上游渠道公司ID
     * @return 上游渠道公司信息
     */
	public ApiCompany selectApiCompanyById(Integer id);
	
	/**
     * 查询上游渠道公司列表
     * 
     * @param apiCompany 上游渠道公司信息
     * @return 上游渠道公司集合
     */
	public List<ApiCompany> selectApiCompanyList(ApiCompany apiCompany);
	
	/**
     * 新增上游渠道公司
     * 
     * @param apiCompany 上游渠道公司信息
     * @return 结果
     */
	public int insertApiCompany(ApiCompany apiCompany);
	
	/**
     * 修改上游渠道公司
     * 
     * @param apiCompany 上游渠道公司信息
     * @return 结果
     */
	public int updateApiCompany(ApiCompany apiCompany);
	
	/**
     * 删除上游渠道公司
     * 
     * @param id 上游渠道公司ID
     * @return 结果
     */
	public int deleteApiCompanyById(Integer id);
	
	/**
     * 批量删除上游渠道公司
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApiCompanyByIds(String[] ids);
	
}