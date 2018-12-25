package cn.innovation.platform.upms.service;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.upms.common.model.TbApiInfo;

/**
 * @ClassName: ITbApiInfoService
 * @Description: 上游渠道信息服务类
 * @author mqx
 * @date 2018年12月23日 下午1:12:07
 */
public interface IApiInfoService extends IService<TbApiInfo> {

	/**
	 * @Description: 根据ApiCode查询上游渠道信息
	 * @param apiCode 渠道码
	 * @return
	 */
	public TbApiInfo getApiInfo(String requestId, String apiCode);
}
