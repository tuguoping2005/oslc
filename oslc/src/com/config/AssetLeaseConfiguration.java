package com.config;
/**
 * 
 * @author tuguoping
 * AssetLeaseConfiguration配置信息类
 */
public class AssetLeaseConfiguration {

	public static final String CREATE_ASSET_LEASE_URI="so/OslcAssetLeaseCreate";
	public static final String CREATE_ASSET_LEASE_ALLOCATION_URI="so/createOslcAssetLeaseDetails";
	
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static final String DELETE="DELETE";
	
	public static final String HUAWEI_WEB_SITE="http://weblink01-ts.huawei.com/ifm";
	public static final String USERNAME="willy";
	public static final String PASSWORD="willy@321";
	
	private static final String SPI="spi:"; 
	public static final String 	CONTRACT_ALLOCATION_NODE_NAME="allocation";
	
	//用于配制tririga合同创建接口的参数
	public static final String [] FIELD_CONFIG_LEASE={
		    "cstContractNameTX",
		    "cstContractNumberTX",
		    "cstContractTypeTX",
		    "cstLeaseNumberTX",
		    "cstCurrencyTX",
		    "cstContractStartDateTX",
		    "cstRecordIdTX",
		    "cstRevisionTX",
		    "triLeaseTypeCL",
		    "triLeaseClassCL",
		    "cstLeaseNameTX",
		    "cstSpecialClausesTX",
		    "cstLikelyRenewalLeaseDateTX",
		    "cstTenantNumberTX",
		    "cstPaymentStartDateTX",
		    "cstContractEndDateTX",
		    "cstFullPaymentStartDateTX",
		    "cstFrequencyTX",
		    "cstExpectedAmountNU",
		    "cstPaymentTypeTX",
		    "cstVendorNumberTX",
		    "cstTenantNumberTX"
	};
	
	//用于配制tririga allocation创建接口的参数
	public static final String [] FIELD_CONFIG_ALLOCATION={
		"cstRegionCodeTX",
		"cstProjectCodeTX",
		"cstProductCodeTX",
		"cstAssetLeaseIdTX",
		"cstDepartmentCodeTX",
		"cstAllocationPercentageNU"
	};
	
	/*public static JSONObject newJson(String [] FieldConfig,JSONObject fromJson){
		JSONObject json = new JSONObject();
		for(String field:FieldConfig){
			String fieldValue = fromJson.getString(field);
			if(fieldValue.equals("")&&fieldValue!=null){
				json.put(SPI+field, fieldValue);
			}
		}
		return json;
	}*/
}
