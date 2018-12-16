package cn.innovation.platform.insurance.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
import cn.innovation.platform.insurance.common.model.DamdataRecords;
import cn.innovation.platform.insurance.mapper.DamdataRecordsMapper;
import cn.innovation.platform.insurance.service.IDamdataRecordsService;

/**
 * <p>
 * 大坝保险申请记录表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-11
 */
@Service("damdataRecordsService")
public class DamdataRecordsServiceImpl extends ServiceImpl<DamdataRecordsMapper, DamdataRecords>
		implements IDamdataRecordsService {

	@Override
	public void apply(DamdataRecordsDto dto) {
		// TODO Auto-generated method stub

	}

}
