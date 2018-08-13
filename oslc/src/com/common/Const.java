package com.common;

public class Const {
	public static final String HOST_NAME="http://weblink01-ts.huawei.com/ifm/";
	
	public static final String CREATE_ASSET_LEASE_URI="oslc/so/OslcAssetLeaseCreate";
	public static final String CREATE_ASSET_LEASE_ALLOCATION_URI="oslc/so/createOslcAssetLeaseDetails";
	
	public static final String USER_INFO="user_info";
	public static final String PWD_INFO="pwd_info";
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static final String DELETE="DELETE";
	
	public static final String LOGIN="oslc/login";
	public static final String USERNAME="tWX591602";
	public static final String PASSWORD="password";
	
	public static final Integer ALLOCATIONS_PERCENTTAGE = 100;
	public static final String ALLOCATIONS="allocations";
	public static final String ASSET_LEASE_TEMPLATE="assetLeaseTemplate";
	
	public static final String FIELD_PRIFIX="spi:"; 
	public static final String CHAR_SET="UTF-8"; 
	public static final String 	CONTRACT_ALLOCATION_NODE_NAME="cstAllocationsTX";
	
	
	
	public static final int UN_RESPONSE = 404;//�������޷���Ӧ
	public static final int AUTH_ERROR = 401;//��֤ʧ��
	public static final int SUCCESS = 200;//����ɹ�
	public static final int CREATE_SUCCESS = 201;//��Դ�����ɹ�
	public static final int ILLEGAL_ARGUMENT = 20;//�������Ϸ�
	public static final int ILLEGAL_JSON = 21;//json���ݲ��Ϸ�
	public static final int NULL_JSON = 22;//JSON����Ϊ��
	public static final int ILLEGAL_PERCENT = 23;//̯���ٷֱ��ܺͲ�����100
	
	public static final String[] INTERFACE_ASSET_LEASE_FIELD={
			"action",
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
		    "cstAllocationsTX"
	};
	
	public static final String[] INTERFACE_ALLOCATION_FIELD={
		"action",
		"cstRegionCodeTX",
		"cstProjectCodeTX",
		"cstProductCodeTX",
		"cstAssetLeaseIdTX",
		"cstDepartmentCodeTX",
		"cstAllocationPercentageNU"	    
	};

}
