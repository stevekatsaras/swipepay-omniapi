package io.swipepay.omniapi.tx.card.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;

@JsonPropertyOrder({
	"status",
	"merchantProfile",
	"code",
	"reference",
	"amount",
	"currency",
	"txType",
	"environment",
	"event",
	"name",
	"email",
	"bin",
	"pan",
	"expiryMonth",
	"expiryYear",
	"captured",
	"capturedAmount",
	"refunded",
	"refundedAmount",
	"receipt",
	"result",
	"responseCode",
	"responseText",
	"settlementDate",
	"logDate",
	"metadata",
	"errors"
})
public class CardQueryResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private String code;
	private String reference;
	private String amount;
	private String currency;
	private String txType;
	private String environment;
	private String event;
	private String name;
	private String email;
	private String bin;
	private String pan;
	private String expiryMonth;
	private String expiryYear;	
	private Boolean captured;
	private String capturedAmount;
	private Boolean refunded;
	private String refundedAmount;
	private String receipt;
	private String result;
	private String responseCode;
	private String responseText;
	private String settlementDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime logDate;
	private LinkedHashMap<String, String> metadata;
	private String errors;
	
	public CardQueryResponse(Status status) {
		super(status);
	}
	
	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}
	
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public Boolean getCaptured() {
		return captured;
	}

	public void setCaptured(Boolean captured) {
		this.captured = captured;
	}

	public String getCapturedAmount() {
		return capturedAmount;
	}

	public void setCapturedAmount(String capturedAmount) {
		this.capturedAmount = capturedAmount;
	}

	public Boolean getRefunded() {
		return refunded;
	}

	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}

	public String getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(String refundedAmount) {
		this.refundedAmount = refundedAmount;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public LocalDateTime getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDateTime logDate) {
		this.logDate = logDate;
	}

	public LinkedHashMap<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(LinkedHashMap<String, String> metadata) {
		this.metadata = metadata;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}