package cn.innovation.platform.insurance.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.insurance.common.dto.VinsunRecordsDto;
import cn.innovation.platform.insurance.common.model.VinsunRecords;

/**
 * @ClassName: IVinsunRecordsService 
 * @Description: 畅思保险申请记录接口
 * @author mqx 
 * @date 2018年12月18日 下午10:58:14
 */
public interface IVinsunRecordsService extends IService<VinsunRecords> {

	

	/**
	 * @Description: 申请
	 * @param dto
	 *            参数对象
	 */
	public Map<String, String> addApply(VinsunRecordsDto dto);
}
