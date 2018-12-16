package cn.innovation.platform.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.innovation.platform.system.mapper.InsuranceVinsunRecordsMapper;
import cn.innovation.platform.system.domain.InsuranceVinsunRecords;
import cn.innovation.platform.system.service.IInsuranceVinsunRecordsService;
import cn.innovation.platform.common.support.Convert;

/**
 * 畅思保险申请记录 服务层实现
 * 
 * @author mqx
 * @date 2018-12-16
 */
@Service
public class InsuranceVinsunRecordsServiceImpl implements IInsuranceVinsunRecordsService 
{
	@Autowired
	private InsuranceVinsunRecordsMapper insuranceVinsunRecordsMapper;

	/**
     * 查询畅思保险申请记录信息
     * 
     * @param id 畅思保险申请记录ID
     * @return 畅思保险申请记录信息
     */
    @Override
	public InsuranceVinsunRecords selectInsuranceVinsunRecordsById(Integer id)
	{
	    return insuranceVinsunRecordsMapper.selectInsuranceVinsunRecordsById(id);
	}
	
	/**
     * 查询畅思保险申请记录列表
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 畅思保险申请记录集合
     */
	@Override
	public List<InsuranceVinsunRecords> selectInsuranceVinsunRecordsList(InsuranceVinsunRecords insuranceVinsunRecords)
	{
	    return insuranceVinsunRecordsMapper.selectInsuranceVinsunRecordsList(insuranceVinsunRecords);
	}
	
    /**
     * 新增畅思保险申请记录
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 结果
     */
	@Override
	public int insertInsuranceVinsunRecords(InsuranceVinsunRecords insuranceVinsunRecords)
	{
	    return insuranceVinsunRecordsMapper.insertInsuranceVinsunRecords(insuranceVinsunRecords);
	}
	
	/**
     * 修改畅思保险申请记录
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 结果
     */
	@Override
	public int updateInsuranceVinsunRecords(InsuranceVinsunRecords insuranceVinsunRecords)
	{
	    return insuranceVinsunRecordsMapper.updateInsuranceVinsunRecords(insuranceVinsunRecords);
	}

	/**
     * 删除畅思保险申请记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteInsuranceVinsunRecordsByIds(String ids)
	{
		return insuranceVinsunRecordsMapper.deleteInsuranceVinsunRecordsByIds(Convert.toStrArray(ids));
	}
	
}
