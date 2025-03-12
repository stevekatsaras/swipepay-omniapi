package io.swipepay.omniapi.common.enums;

public enum Status {
	
	// general statuses (0000 - 0999)
	
	RS_0000("0000", "Ok"),	
	RS_0001("0001", "Bad credentials"),
	RS_0002("0002", "Invalid merchant"), 
	RS_0003("0003", "Unable to authorise"),
	RS_0004("0004", "Unable to build request"),
	RS_0005("0005", "Unable to validate"),
	
	
	RS_0009("0009", "Action is invalid"),
	
	
	RS_0023("0023", "Internal crypto error"),	
	RS_0024("0024", "Internal system error"), 
	RS_0025("0025" ,"Client IP address cannot be determined"),
	RS_0026("0026" ,"Client IP address is invalid"),	
//	RS_0027("0027", "Merchant profile missing"),
	RS_0028("0028", "Merchant profile code not supplied"),
	RS_0029("0029", "Merchant profile code is invalid"),
	
	
//	RS_0030("0030", "Type not supplied"),
//	RS_0031("0031", "Type is invalid"),	


	

	


	

	
//	RS_0063("0063", "Payment method not supplied"),
//	RS_0064("0064", "Address is missing"),
//	RS_0065("0065", "Credit cards are missing"),
//	
	RS_0500("0500", "Internal server error"),
	
	// crypto statuses (1000 - 1999)
	
	RS_1000("1000", "Crypto is missing"),
	RS_1001("1001", "Crypto cipher data key not supplied"),
	RS_1002("1002", "Crypto plain text data not supplied"),
	RS_1003("1003", "Crypto cipher text data not supplied"),
	RS_1004("1004", "Crypto cipher data key failed to generate"), 
	RS_1005("1005", "Crypto cipher data key not decrypted"),	
	RS_1006("1006", "Crypto plain text data not encrypted"), 
	RS_1007("1007", "Crypto cipher text data not decrypted"),
	
	// card statuses (2000 - 2999)
	
	RS_2000("2000", "Card is missing"),
	RS_2001("2001", "Card name not supplied"),
	RS_2002("2002", "Card email is invalid"),	
	RS_2003("2003", "Card PAN not supplied"),
	RS_2004("2004", "Card PAN too long"),
	RS_2005("2005", "Card PAN is invalid"),
	RS_2006("2006", "Card expiry month not supplied"),
	RS_2007("2007", "Card expiry year not supplied"),
	RS_2008("2008", "Card has expired"),
	RS_2009("2009", "Card expiry date is invalid"),
	RS_2010("2010", "Card cvv is invalid"),
	RS_2011("2011", "Card code not supplied"),
	RS_2012("2012", "Card is invalid"),
	RS_2013("2013", "Card not found"),
	RS_2014("2014", "Card associated with another customer"),
	RS_2015("2015", "Card not associated with a customer"),
	RS_2016("2016", "Internal card error"),
	
	// tx statuses (3000 - 3999)
	
	RS_3000("3000", "Tx is missing"),
	RS_3001("3001", "Reference not supplied"),
	RS_3002("3002", "Reference has been used"),
	RS_3003("3003", "Amount not supplied"),
	RS_3004("3004", "Amount is invalid"),	
	RS_3005("3005", "Currency not supplied"),
	RS_3006("3006", "Currency is invalid"),
	RS_3007("3007", "Tx code not supplied"),
	RS_3008("3008", "Metadata key/value pairs cannot be empty"),
	RS_3009("3009", "Payment configuration not set"),
	RS_3010("3010", "Payment configuration receipt length is invalid"),
	RS_3011("3011", "Payment gateway(s) not configured"),	
	RS_3012("3012", "Gateway error"),
	RS_3013("3013", "Gateway communications error"),
	RS_3014("3014", "Payment transaction not found"),
	
//	,
//	,
//	RS_3003("3003", "Payment transaction not successfully processed"),
//	RS_3004("3004", "Payment transaction fully captured"),
//	RS_3005("3005", "Capture amount exceeded"),
//	RS_3007("3007", "Payment transaction not captured"),
//	RS_3008("3008", "Payment transaction fully refunded"),
//	RS_3009("3009", "Refund amount exceeded"),


//	RS_3013("3013", "Payment transaction status does not match merchant profile status"),
	
	// customer statuses (4000 - 4999)
	
	RS_4000("4000", "Customer is missing"),
	RS_4001("4001", "Customer first name not supplied"),	
	RS_4002("4002", "Customer last name not supplied"),	
	RS_4003("4003", "Customer address not supplied"),	
	RS_4004("4004", "Customer city not supplied"),
	RS_4005("4005", "Customer state not supplied"),
	RS_4006("4006", "Customer postcode not supplied"),
	RS_4007("4007", "Customer country not supplied"),
	RS_4008("4008", "Customer country is invalid"),
	RS_4009("4009", "Customer code not supplied"),
	RS_4010("4010", "Customer is invalid"),	
	RS_4011("4011", "Customer payment method(s) not supplied"),
	
	// bank account statuses (5000 - 5999)
	
	RS_5000("5000", "Bank account is missing"),
	RS_5001("5001", "Bank account bsb not supplied"),
	RS_5002("5002", "Bank account bsb is invalid"),	
	RS_5003("5003", "Bank account number not supplied"),
	RS_5004("5004", "Bank account number is too short"),
	RS_5005("5005", "Bank account number is too long"),
	RS_5006("5006", "Bank account number is invalid"),			
	RS_5007("5007", "Bank account name not supplied"),
	RS_5008("5008", "Bank account email is invalid"),
	RS_5009("5009", "Bank account country is not supplied"),
	RS_5010("5010", "Bank account country is invalid"),
	RS_5011("5011", "Bank account code not supplied"),
	RS_5012("5012", "Bank account is invalid"),
	RS_5013("5013", "Bank account not found"),
	RS_5014("5014", "Bank account associated with another customer"),
	RS_5015("5015", "Bank account not associated with a customer"),
	RS_5016("5016", "Internal bank account error")
	
	
	
	
	// batch statuses
	
	
	/*
	

	
	
	
	
	
	
	
	
	
	
	
	RS_1091("1091", "Customer credit-card is missing"),	
	RS_1092("1092", "Customer credit-card[name] not supplied"),	
	RS_1093("1093", "Customer credit-card[name] too long"),
	RS_1094("1094", "Customer credit-card[email] too long"),
	RS_1095("1095", "Customer credit-card[email] is invalid"),
	RS_1096("1096", "Customer credit-card[pan] not supplied"),
	RS_1097("1097", "Customer credit-card[pan] too long"),
	RS_1098("1098", "Customer credit-card[pan] is invalid"),
	RS_1099("1099", "Customer credit-card[expiry-month] not supplied"),
	RS_1100("1100", "Customer credit-card[expiry-year] not supplied"),
	RS_1101("1101", "Customer credit-card has expired"),
	RS_1102("1102", "Customer credit-card expiry date is invalid"),
	RS_1103("1103", "Customer credit-card[cvv] is invalid"),	
	RS_1104("1104", "Customer action is invalid"),
	RS_1105("1105", "Customer credit-card[token-code] is invalid"),
	,*/
	
	
	
	;

	
	private final String code;
	private final String text;
	
	private Status(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
}