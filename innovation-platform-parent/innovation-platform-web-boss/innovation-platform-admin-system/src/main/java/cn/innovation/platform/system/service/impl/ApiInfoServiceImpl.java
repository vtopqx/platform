package cn.innovation.platform.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.innovation.platform.common.constant.Constants;
import cn.innovation.platform.common.support.Convert;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.system.domain.ApiInfo;
import cn.innovation.platform.system.mapper.ApiInfoMapper;
import cn.innovation.platform.system.service.IApiInfoService;

/**
 * 上游渠道 服务层实现
 * 
 * @author mqx
 * @date 2018-12-22
 */
@Service
public class ApiInfoServiceImpl implements IApiInfoService {
	@Autowired
	private ApiInfoMapper apiInfoMapper;

	/**
	 * 查询上游渠道信息
	 * 
	 * @param id
	 *            上游渠道ID
	 * @return 上游渠道信息
	 */
	@Override
	public ApiInfo selectApiInfoById(Integer id) {
		return apiInfoMapper.selectApiInfoById(id);
	}

	/**
	 * 查询上游渠道列表
	 * 
	 * @param apiInfo
	 *            上游渠道信息
	 * @return 上游渠道集合
	 */
	@Override
	public List<ApiInfo> selectApiInfoList(ApiInfo apiInfo) {
		return apiInfoMapper.selectApiInfoList(apiInfo);
	}

	/**
	 * 新增上游渠道
	 * 
	 * @param apiInfo
	 *            上游渠道信息
	 * @return 结果
	 */
	@Override
	public int insertApiInfo(ApiInfo apiInfo) {
		apiInfo.setCreateTime(new Date());
		apiInfo.setUpdateTime(new Date());
		return apiInfoMapper.insertApiInfo(apiInfo);
	}

	/**
	 * 修改上游渠道
	 * 
	 * @param apiInfo
	 *            上游渠道信息
	 * @return 结果
	 */
	@Override
	public int updateApiInfo(ApiInfo apiInfo) {
		apiInfo.setUpdateTime(new Date());
		return apiInfoMapper.updateApiInfo(apiInfo);
	}

	/**
	 * 删除上游渠道对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteApiInfoByIds(String ids) {
		return apiInfoMapper.deleteApiInfoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询所有上游渠道API接口
	 * 
	 * @return API列表
	 */
	@Override
	public List<ApiInfo> selectApiAll() {
		ApiInfo apiInfo = new ApiInfo();
		apiInfo.setStatus(Constants.ENABLE);
		return selectApiInfoList(apiInfo);
	}

	/**
	 * 查询所有上游渠道API根据code
	 * 
	 * @return API列表
	 */
	@Override
	public List<ApiInfo> selectApiInfoByCodes(String apiCodes) {
		List<ApiInfo> apis = selectApiAll();
		if (StringUtils.isNotEmpty(apis) && StringUtils.isNotEmpty(apiCodes)) {
			String[] codeArray = Convert.toStrArray(apiCodes);
			for (ApiInfo api : apis) {
				for (String apiCode : codeArray) {
					if (apiCode.equals(api.getApiCode())) {
						api.setFlag(true);
						break;
					}
				}
			}
		}
		return apis;
	}

}
