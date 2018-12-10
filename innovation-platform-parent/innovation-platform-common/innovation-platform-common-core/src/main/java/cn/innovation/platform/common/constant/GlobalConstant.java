package cn.innovation.platform.common.constant;

/**
 * @ClassName: GlobalConstant
 * @Description: 常量tool
 * @author mqx
 * @date 2017年12月6日 下午2:00:42
 */
public class GlobalConstant {

	// 签名默认密钥
	public static final String DEFAULT_SIGNATURE_SECRET = "12345678";

	// 消息重发次数设置
	public static final int MSG_SEND_REPAIR_COUNT = 3;
	public static final int MSG_RECORD_REPAIR_COUNT = 6;
	
	//日志打印标记
	public static final String LOG_START_LABEL = "start";
	public static final String LOG_END_LABEL = "end";
	
	//微信/钉钉应用服务错误字符串
	public static final String SERVICE_ERROR_RESULT = "{\"errcode\": -1,\"errmsg\": \"system busy\"}";

	// Redis缓存key前缀
	// 企业通讯录信息
	public static final String REDIS_ADDRESS_USER_PREFIX = "ADDRESSUSER:";
	// 创新平台用户信息
	public static final String REDIS_RCSOAPLUS_USER_PREFIX = "RCSOAPLUSUSER:";
	// MOA cookie信息
	public static final String REDIS_RCSOAPLUS_MOA_PREFIX = "MOA:";

	// 企业通讯录接口地址定义
	// 通讯录接口版本
	public static final String ADDRESS_INTERFACE_VERSION = "2.0";
	// 3.11接口
	public static final String ADDRESS_ITEM_BATCH_URL = "/enterprise/itemBatchGet.json";
	// 3.10接口
	public static final String ADDRESS_ITEM_URL = "/enterprise/getItem.json";
	// 3.14接口
	public static final String ADDRESS_ENTERPRISE_EUSERID_URL = "/enterprise/getListWithExtraByEuserId.json";
	// 2.2 新增企业部门接口
	public static final String ADDRESS_ADDDEPARTMENT_URL = "/enterprise/addDepartment.json";
	// 2.3 更新企业部门接口
	public static final String ADDRESS_UPDATEDEPARTMENT_URL = "/enterprise/updateDepartment.json";
	// 2.4 删除企业部门接口
	public static final String ADDRESS_DELDEPARTMENT_URL = "/enterprise/delDepartment.json";
	// 2.5 添加联系人接口
	public static final String ADDRESS_ADDITEM_URL = "/enterprise/addItem.json";
	// 2.6 修改联系人接口
	public static final String ADDRESS_UPDATEITEM_URL = "/enterprise/updateItem.json";
	// 2.7 删除联系人接口
	public static final String ADDRESS_DELITEM_URL = "/enterprise/delItem.json";
	// 2.11 企业信息更新
	public static final String ADDRESS_UPDATEENTERPRISEINFO_URL = "/enterprise/updateEnterpriseInfo.json";
	
	//3.1获取用户的一级列表及其所在的一级部门
	public static final String ADDRESS_GETLIST_URL = "/enterprise/getList.json";
	//3.2获取部门的子部门及其直属联系人（返回数据未加密输出）
	public static final String ADDRESS_GETDETAIL_URL = "/enterprise/getDetail.json";
	//3.4查询企业联系人
	public static final String ADDRESS_GETSEARCH_URL = "/enterprise/getSearch.json";
	//3.5查询企业部门联系人数量
	public static final String ADDRESS_GETDEPTCONTACTNUMS_URL = "/enterprise/getDeptContactNums.json";
	//3.7部门列表查询接口
	public static final String ADDRESS_LISTDEPARTMENT_URL = "/enterprise/listDepartment.json";
	//3.8获取企业部门详情接口
	public static final String ADDRESS_GETDEPARTMENT_URL = "/enterprise/getDepartment.json";
	//3.9批量获取部门接口
	public static final String ADDRESS_DEPARTMENTBATCHGET_URL = "/enterprise/departmentBatchGet.json";

}
