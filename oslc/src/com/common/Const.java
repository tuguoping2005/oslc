package com.common;

public class Const {
	public static final String HOST_NAME="http://120.79.30.159/oslc/";
//	public static final String HOST_NAME="http://weblink01-ts.huawei.com/ifm/";
	
	public static final String CREATE_ASSET_LEASE_URI="so/cstWillyOSLCServiceCreate";
//	public static final String CREATE_ASSET_LEASE_URI="so/OslcAssetLeaseCreate";
	public static final String CREATE_ASSET_LEASE_ALLOCATION_URI="so/OSLCCreatedemi";
//	public static final String CREATE_ASSET_LEASE_ALLOCATION_URI="so/createOslcAssetLeaseDetails";
	
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static final String DELETE="DELETE";
	
	public static final String LOGIN="oslc/login";
//	public static final String CONFIG_OSLC_ROOT="http://weblink01-ts.huawei.com/ifm";
	public static final String USERNAME="willy";
	public static final String PASSWORD="Csjy1214@";
	
	public static final String FIELD_PRIFIX="spi:"; 
	public static final String CHAR_SET="UTF-8"; 
	public static final String 	CONTRACT_ALLOCATION_NODE_NAME="allocations";
	
	public static final int ERROR = 404;
	public static final int AUTH_ERROR = 401;
	public static final int SUCCESS = 200;
	public static final int ASY_SUCCESS = 201;
	
	public static final String[] INTERFACE_ASSET_LEASE_FIELD={
			"action",
			"cstCostNU",
			"cstRequestNameTX",
			"allocations"
		    /*"cstContractNameTX",
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
		    ":cstFrequencyTX",
		    "cstExpectedAmountNU",
		    "cstPaymentTypeTX",
		    "cstVendorNumberTX",
		    "cstTenantNumberTX",
		    "allocations"*/
	};
	
	public static final String[] INTERFACE_ALLOCATION_FIELD={
		"action",
		"cstNameTX",
		"cstDescriptionTX"
		/*"cstRegionCodeTX",
		"cstProjectCodeTX",
		"cstProductCodeTX",
		"cstAssetLeaseIdTX",
		"cstDepartmentCodeTX",
		"cstAllocationPercentageNU"*/	    
	};

}
