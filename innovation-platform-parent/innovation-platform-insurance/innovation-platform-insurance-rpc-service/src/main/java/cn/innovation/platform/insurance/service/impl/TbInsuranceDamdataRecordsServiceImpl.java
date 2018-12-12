package cn.innovation.platform.insurance.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.insurance.common.model.TbInsuranceDamdataRecords;
import cn.innovation.platform.insurance.mapper.TbInsuranceDamdataRecordsMapper;
import cn.innovation.platform.insurance.service.ITbInsuranceDamdataRecordsService;

/**
 * <p>
 * 大坝保险申请记录表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-11
 */
@Service("insuranceDamdataRecordsService")
public class TbInsuranceDamdataRecordsServiceImpl
		extends ServiceImpl<TbInsuranceDamdataRecordsMapper, TbInsuranceDamdataRecords>
		implements ITbInsuranceDamdataRecordsService {

}
