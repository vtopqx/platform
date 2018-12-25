package cn.innovation.platform.upms.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.upms.common.model.TbApiArea;

/**
 * <p>
 * 上游渠道城市区域配置表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-25
 */
public interface IApiAreaService extends IService<TbApiArea> {

	/**
	 * @Description: 查询上游渠道城市区域
	 * @param companyId
	 *            公司ID
	 * @param apiCode
	 *            渠道码
	 * @return
	 */
	public List<TbApiArea> getApiArea(String requestId, Integer companyId, String apiCode);
}
