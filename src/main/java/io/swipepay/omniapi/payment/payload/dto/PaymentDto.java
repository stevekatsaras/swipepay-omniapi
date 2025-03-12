package io.swipepay.omniapi.payment.payload.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ 
	"method", 
	"type", 
	"code", 
	"successful", 
	"captured", 
	"captured-amount-total", 
	"captured-amount-remaining", 
	"capture-code", 
	"refunded", 
	"refunded-amount-total",
	"refunded-amount-remaining", 
	"refund-code",
	"order", 
	"credit-card", 
	"metadata", 
	"result"})
public class PaymentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String method;
	private String type;
	private String code;
	private Boolean successful;
	private Boolean captured;
	
	@JsonProperty(value = "captured-amount-total")
	private String capturedAmountTotal;
	
	@JsonProperty(value = "captured-amount-remaining")
	private String capturedAmountRemaining;
	
	@JsonProperty(value = "capture-code")
	private String captureCode;
	
	private Boolean refunded;
	
	@JsonProperty(value = "refunded-amount-total")
	private String refundedAmountTotal;
	
	@JsonProperty(value = "refunded-amount-remaining")
	private String refundedAmountRemaining;
	
	@JsonProperty(value = "refund-code")
	private String refundCode;
	
	private LinkedHashMap<String, String> metadata;
	
	public PaymentDto() {
		metadata = new LinkedHashMap<String, String>();
	}
	
	public PaymentDto(
			String method, 
			String type, 
			String code, 
			Boolean successful, 
			Boolean captured,
			String capturedAmountTotal, 
			String capturedAmountRemaining, 
			String captureCode, 
			Boolean refunded,
			String refundedAmountTotal, 
			String refundedAmountRemaining, 
			String refundCode, 
			LinkedHashMap<String, String> metadata) {

		this.method = method;
		this.type = type;
		this.code = code;
		this.successful = successful;
		this.captured = captured;
		this.capturedAmountTotal = capturedAmountTotal;
		this.capturedAmountRemaining = capturedAmountRemaining;
		this.captureCode = captureCode;
		this.refunded = refunded;
		this.refundedAmountTotal = refundedAmountTotal;
		this.refundedAmountRemaining = refundedAmountRemaining;
		this.refundCode = refundCode;
		this.metadata = metadata;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public Boolean getCaptured() {
		return captured;
	}

	public void setCaptured(Boolean captured) {
		this.captured = captured;
	}

	public String getCapturedAmountTotal() {
		return capturedAmountTotal;
	}

	public void setCapturedAmountTotal(String capturedAmountTotal) {
		this.capturedAmountTotal = capturedAmountTotal;
	}

	public String getCapturedAmountRemaining() {
		return capturedAmountRemaining;
	}

	public void setCapturedAmountRemaining(String capturedAmountRemaining) {
		this.capturedAmountRemaining = capturedAmountRemaining;
	}

	public String getCaptureCode() {
		return captureCode;
	}

	public void setCaptureCode(String captureCode) {
		this.captureCode = captureCode;
	}

	public Boolean getRefunded() {
		return refunded;
	}

	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}

	public String getRefundedAmountTotal() {
		return refundedAmountTotal;
	}

	public void setRefundedAmountTotal(String refundedAmountTotal) {
		this.refundedAmountTotal = refundedAmountTotal;
	}

	public String getRefundedAmountRemaining() {
		return refundedAmountRemaining;
	}

	public void setRefundedAmountRemaining(String refundedAmountRemaining) {
		this.refundedAmountRemaining = refundedAmountRemaining;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public LinkedHashMap<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(LinkedHashMap<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}