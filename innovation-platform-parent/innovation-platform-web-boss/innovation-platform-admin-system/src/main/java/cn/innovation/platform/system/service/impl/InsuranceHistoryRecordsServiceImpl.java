package cn.innovation.platform.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.innovation.platform.system.mapper.InsuranceHistoryRecordsMapper;
import cn.innovation.platform.system.domain.InsuranceHistoryRecords;
import cn.innovation.platform.system.service.IInsuranceHistoryRecordsService;
import cn.innovation.platform.common.support.Convert;

/**
 * 保险申请记录汇总 服务层实现
 * 
 * @author mqx
 * @date 2018-12-22
 */
@Service
public class InsuranceHistoryRecordsServiceImpl implements IInsuranceHistoryRecordsService 
{
	@Autowired
	private InsuranceHistoryRecordsMapper insuranceHistoryRecordsMapper;

	/**
     * 查询保险申请记录汇总信息
     * 
     * @param id 保险申请记录汇总ID
     * @return 保险申请记录汇总信息
     */
    @Override
	public InsuranceHistoryRecords selectInsuranceHistoryRecordsById(Integer id)
	{
	    return insuranceHistoryRecordsMapper.selectInsuranceHistoryRecordsById(id);
	}
	
	/**
     * 查询保险申请记录汇总列表
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 保险申请记录汇总集合
     */
	@Override
	public List<InsuranceHistoryRecords> selectInsuranceHistoryRecordsList(InsuranceHistoryRecords insuranceHistoryRecords)
	{
	    return insuranceHistoryRecordsMapper.selectInsuranceHistoryRecordsList(insuranceHistoryRecords);
	}
	
    /**
     * 新增保险申请记录汇总
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 结果
     */
	@Override
	public int insertInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords)
	{
	    return insuranceHistoryRecordsMapper.insertInsuranceHistoryRecords(insuranceHistoryRecords);
	}
	
	/**
     * 修改保险申请记录汇总
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 结果
     */
	@Override
	public int updateInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords)
	{
	    return insuranceHistoryRecordsMapper.updateInsuranceHistoryRecords(insuranceHistoryRecords);
	}

	/**
     * 删除保险申请记录汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteInsuranceHistoryRecordsByIds(String ids)
	{
		return insuranceHistoryRecordsMapper.deleteInsuranceHistoryRecordsByIds(Convert.toStrArray(ids));
	}
	
}
