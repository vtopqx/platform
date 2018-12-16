package cn.innovation.platform.insurance.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.insurance.common.model.VinsunRecords;
import cn.innovation.platform.insurance.mapper.VinsunRecordsMapper;
import cn.innovation.platform.insurance.service.IVinsunRecordsService;

/**
 * <p>
 * 畅思保险申请记录表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-16
 */
@Service("vinsunRecordsService")
public class VinsunRecordsServiceImpl extends ServiceImpl<VinsunRecordsMapper, VinsunRecords>
		implements IVinsunRecordsService {

}
