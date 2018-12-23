package cn.innovation.platform.system.mapper;

import cn.innovation.platform.system.domain.InsuranceHistoryRecords;
import java.util.List;	

/**
 * 保险申请记录汇总 数据层
 * 
 * @author mqx
 * @date 2018-12-22
 */
public interface InsuranceHistoryRecordsMapper 
{
	/**
     * 查询保险申请记录汇总信息
     * 
     * @param id 保险申请记录汇总ID
     * @return 保险申请记录汇总信息
     */
	public InsuranceHistoryRecords selectInsuranceHistoryRecordsById(Integer id);
	
	/**
     * 查询保险申请记录汇总列表
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 保险申请记录汇总集合
     */
	public List<InsuranceHistoryRecords> selectInsuranceHistoryRecordsList(InsuranceHistoryRecords insuranceHistoryRecords);
	
	/**
     * 新增保险申请记录汇总
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 结果
     */
	public int insertInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords);
	
	/**
     * 修改保险申请记录汇总
     * 
     * @param insuranceHistoryRecords 保险申请记录汇总信息
     * @return 结果
     */
	public int updateInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords);
	
	/**
     * 删除保险申请记录汇总
     * 
     * @param id 保险申请记录汇总ID
     * @return 结果
     */
	public int deleteInsuranceHistoryRecordsById(Integer id);
	
	/**
     * 批量删除保险申请记录汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInsuranceHistoryRecordsByIds(String[] ids);
	
}