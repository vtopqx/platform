package cn.innovation.platform.system.service;

import cn.innovation.platform.system.domain.ApiCompany;
import java.util.List;

/**
 * 上游渠道公司 服务层
 * 
 * @author mqx
 * @date 2018-12-24
 */
public interface IApiCompanyService 
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
	 * @Description: 查询所有上游渠道公司
	 * @param apiCompany
	 * @return
	 */
	public List<ApiCompany> selectApiCompanyAll(String companyId);
	
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
     * 删除上游渠道公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApiCompanyByIds(String ids);
	
}
