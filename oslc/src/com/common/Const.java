package com.common;

public class Const {
	public static final String CREATE_ASSET_LEASE_URI="so/OslcAssetLeaseCreate";
	public static final String CREATE_ASSET_LEASE_ALLOCATION_URI="so/createOslcAssetLeaseDetails";
	
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static final String DELETE="DELETE";
	
	public static final String HUAWEI_WEB_SITE="http://weblink01-ts.huawei.com/ifm";
	public static final String USERNAME="willy";
	public static final String PASSWORD="willy@321";
	
	public static final String FIELD_PRIFIX="spi:"; 
	public static final String 	CONTRACT_ALLOCATION_NODE_NAME="allocations";
	
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
		    ":cstFrequencyTX",
		    "cstExpectedAmountNU",
		    "cstPaymentTypeTX",
		    "cstVendorNumberTX",
		    "cstTenantNumberTX",
		    "allocations"
	};
	
	public static final String[] INTERFACE_ALLOCATION_FIELD={
		"action",
		"cstRegionCodeTX",
	    "cstProjectCodeTX"	    
	};

}
