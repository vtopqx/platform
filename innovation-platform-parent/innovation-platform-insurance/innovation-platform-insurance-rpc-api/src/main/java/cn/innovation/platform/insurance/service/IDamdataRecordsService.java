package cn.innovation.platform.insurance.service;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
import cn.innovation.platform.insurance.common.model.DamdataRecords;

/**
 * <p>
 * 大坝保险申请记录表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-11
 */
public interface IDamdataRecordsService extends IService<DamdataRecords> {

	/**
	 * @Description: 申请
	 * @param dto
	 *            参数对象
	 */
	public String addApply(DamdataRecordsDto dto);
}
