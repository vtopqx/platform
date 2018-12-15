package cn.innovation.platform.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.innovation.platform.system.mapper.InsuranceDamdataRecordsMapper;
import cn.innovation.platform.system.domain.InsuranceDamdataRecords;
import cn.innovation.platform.system.service.IInsuranceDamdataRecordsService;
import cn.innovation.platform.common.support.Convert;

/**
 * 大坝保险申请记录 服务层实现
 * 
 * @author mqx
 * @date 2018-12-13
 */
@Service
public class InsuranceDamdataRecordsServiceImpl implements IInsuranceDamdataRecordsService 
{
	@Autowired
	private InsuranceDamdataRecordsMapper insuranceDamdataRecordsMapper;

	/**
     * 查询大坝保险申请记录信息
     * 
     * @param id 大坝保险申请记录ID
     * @return 大坝保险申请记录信息
     */
    @Override
	public InsuranceDamdataRecords selectInsuranceDamdataRecordsById(Integer id)
	{
	    return insuranceDamdataRecordsMapper.selectInsuranceDamdataRecordsById(id);
	}
	
	/**
     * 查询大坝保险申请记录列表
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 大坝保险申请记录集合
     */
	@Override
	public List<InsuranceDamdataRecords> selectInsuranceDamdataRecordsList(InsuranceDamdataRecords insuranceDamdataRecords)
	{
	    return insuranceDamdataRecordsMapper.selectInsuranceDamdataRecordsList(insuranceDamdataRecords);
	}
	
    /**
     * 新增大坝保险申请记录
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 结果
     */
	@Override
	public int insertInsuranceDamdataRecords(InsuranceDamdataRecords insuranceDamdataRecords)
	{
	    return insuranceDamdataRecordsMapper.insertInsuranceDamdataRecords(insuranceDamdataRecords);
	}
	
	/**
     * 修改大坝保险申请记录
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 结果
     */
	@Override
	public int updateInsuranceDamdataRecords(InsuranceDamdataRecords insuranceDamdataRecords)
	{
	    return insuranceDamdataRecordsMapper.updateInsuranceDamdataRecords(insuranceDamdataRecords);
	}

	/**
     * 删除大坝保险申请记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteInsuranceDamdataRecordsByIds(String ids)
	{
		return insuranceDamdataRecordsMapper.deleteInsuranceDamdataRecordsByIds(Convert.toStrArray(ids));
	}
	
}
