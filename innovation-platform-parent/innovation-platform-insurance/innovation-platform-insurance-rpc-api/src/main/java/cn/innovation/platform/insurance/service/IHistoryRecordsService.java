package cn.innovation.platform.insurance.service;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.insurance.common.dto.HistoryRecordsDto;
import cn.innovation.platform.insurance.common.model.HistoryRecords;

/**
 * @ClassName: IHistoryRecordsService 
 * @Description: 保险申请记录汇总接口
 * @author mqx 
 * @date 2018年12月22日 下午11:18:51
 */
public interface IHistoryRecordsService extends IService<HistoryRecords> {

	/**
	 * @Description: 申请
	 * @param dto
	 *            参数对象
	 */
	public BaseResult addApply(HistoryRecordsDto dto);
}
