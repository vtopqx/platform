package cn.innovation.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.innovation.platform.common.constant.Constants;
import cn.innovation.platform.common.support.Convert;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.system.domain.ApiCompany;
import cn.innovation.platform.system.mapper.ApiCompanyMapper;
import cn.innovation.platform.system.service.IApiCompanyService;

/**
 * 上游渠道公司 服务层实现
 * 
 * @author mqx
 * @date 2018-12-24
 */
@Service
public class ApiCompanyServiceImpl implements IApiCompanyService {
	@Autowired
	private ApiCompanyMapper apiCompanyMapper;

	/**
	 * 查询上游渠道公司信息
	 * 
	 * @param id
	 *            上游渠道公司ID
	 * @return 上游渠道公司信息
	 */
	@Override
	public ApiCompany selectApiCompanyById(Integer id) {
		return apiCompanyMapper.selectApiCompanyById(id);
	}

	/**
	 * 查询上游渠道公司列表
	 * 
	 * @param apiCompany
	 *            上游渠道公司信息
	 * @return 上游渠道公司集合
	 */
	@Override
	public List<ApiCompany> selectApiCompanyList(ApiCompany apiCompany) {
		return apiCompanyMapper.selectApiCompanyList(apiCompany);
	}

	/**
	 * 新增上游渠道公司
	 * 
	 * @param apiCompany
	 *            上游渠道公司信息
	 * @return 结果
	 */
	@Override
	public int insertApiCompany(ApiCompany apiCompany) {
		return apiCompanyMapper.insertApiCompany(apiCompany);
	}

	/**
	 * 修改上游渠道公司
	 * 
	 * @param apiCompany
	 *            上游渠道公司信息
	 * @return 结果
	 */
	@Override
	public int updateApiCompany(ApiCompany apiCompany) {
		return apiCompanyMapper.updateApiCompany(apiCompany);
	}

	/**
	 * 删除上游渠道公司对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteApiCompanyByIds(String ids) {
		return apiCompanyMapper.deleteApiCompanyByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ApiCompany> selectApiCompanyAll(String companyId) {
		ApiCompany company = new ApiCompany();
		company.setStatus(Constants.ENABLE);
		List<ApiCompany> list = selectApiCompanyList(company);
		if (StringUtils.isNotEmpty(list)) {
			for (ApiCompany apiCompany : list) {
				apiCompany.setSelectValue(apiCompany.getId() + "," + apiCompany.getName());
				// 判断是否选中
				if (StringUtils.isNotEmpty(companyId)) {
					if (companyId.equals(apiCompany.getId().toString())) {
						apiCompany.setFlag(true);
					}
				}
			}
		}
		return list;
	}

}
