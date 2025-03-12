package io.swipepay.omniapi.common.entity.paymenttxcc;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.merchantprofilecurrency.MerchantProfileCurrency;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"code", "merchant_profile_id"}),
		@UniqueConstraint(columnNames = {"reference", "merchant_profile_id"})
})
public class PaymentTxCc {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "reference", nullable = false)
	private String reference;
	
	@Column(name = "amount", nullable = false)
	private Long amount;

	@Column(name = "currency", nullable = false)
	private String currencyCode;
	
	@Column(name = "tx_type", nullable = false)
	private String txType;
	
	@Column(name = "environment", nullable = false)
	private String environment;

	@Column(name = "event", nullable = false)
	private String event;
	
	@Column(name = "card_name", nullable = false)
	private String cardName;
	
	@Column(name = "card_email")
	private String cardEmail;
	
	@Column(name = "card_bin", nullable = false)
	private String cardBin;
	
	@Column(name = "card_pan", nullable = false)
	private String cardPan;
	
	@Column(name = "card_number", nullable = false)
	private String cardNumber;
	
	@ToStringExclude
	@Transient
	private String cardNumberPlainText;
	
	@Column(name = "card_expiry_month", nullable = false)
	private String cardExpiryMonth;
	
	@Column(name = "card_expiry_year", nullable = false)
	private String cardExpiryYear;
	
	@ToStringExclude
	@Transient
	private String cardCvv;
	
	@Column(name = "card_brand", nullable = false)
	private String cardBrand;
	
	@Column(name = "client_ip", nullable = false)
	private String clientIp;

	@Column(name = "payment_card_code")
	private String paymentCardCode;
	
	@Column(name = "payment_gateway_code")
	private String paymentGatewayCode;
	
	@Column(name = "receipt_no")
	private String receiptNo;
	
	@Column(name = "tx_success")
	private Boolean txSuccess;
	
	@Column(name = "tx_result")
	private String txResult;
	
	@Column(name = "tx_id")
	private String txId;
	
	@Column(name = "tx_response_code")
	private String txResponseCode;
	
	@Column(name = "tx_response_text")
	private String txResponseText;
	
	@Column(name = "tx_settlement_date")
	private String txSettlementDate;
	
	@Column(name = "tx_log_date")
	private LocalDateTime txLogDate;
	
	@Column(name = "tx_duration_time")
	private Long txDurationTime;
	
	@Lob
	@Column(name = "tx_errors")
	private String txErrors;
	
	@Lob
	@Column(name = "tx_exceptions")
	private String txExceptions;
	
	@Column(name = "captured")
	private Boolean captured;
	
	@Column(name = "captured_amount_total")
	private Long capturedAmountTotal;
	
	@Column(name = "captured_amount_remaining")
	private Long capturedAmountRemaining;
	
	@Column(name = "refunded")
	private Boolean refunded;
	
	@Column(name = "refunded_amount_total")
	private Long refundedAmountTotal;
	
	@Column(name = "refunded_amount_remaining")
	private Long refundedAmountRemaining;
	
	@Column(name = "related_payment_tx_cc_code")
	private String relatedPaymentTxCcCode;
	
	@Column(name = "duration_time")
	private Long durationTime;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;

	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_currency_id", nullable = false)
	private MerchantProfileCurrency merchantProfileCurrency;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_card_id", nullable = false)
	private MerchantProfileCard merchantProfileCard;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_payment_gateway_id")
	private MerchantProfilePaymentGateway merchantProfilePaymentGateway;

	@ManyToOne
	@JoinColumn(name = "payment_card_id")
	private PaymentCard paymentCard;
		
	@ManyToOne
	@JoinColumn(name = "payment_response_cc_id")
	private PaymentResponseCc paymentResponseCc;
	
	@OneToMany(mappedBy = "paymentTxCc")
	private List<PaymentTxCcMetadata> paymentTxCcMetadata;
	
	public PaymentTxCc() {
		
	}
	
	public PaymentTxCc(MerchantProfile merchantProfile) {
		this.merchantProfile = merchantProfile;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardEmail() {
		return cardEmail;
	}

	public void setCardEmail(String cardEmail) {
		this.cardEmail = cardEmail;
	}

	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getCardPan() {
		return cardPan;
	}

	public void setCardPan(String cardPan) {
		this.cardPan = cardPan;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardNumberPlainText() {
		return cardNumberPlainText;
	}

	public void setCardNumberPlainText(String cardNumberPlainText) {
		this.cardNumberPlainText = cardNumberPlainText;
	}

	public String getCardExpiryMonth() {
		return cardExpiryMonth;
	}

	public void setCardExpiryMonth(String cardExpiryMonth) {
		this.cardExpiryMonth = cardExpiryMonth;
	}

	public String getCardExpiryYear() {
		return cardExpiryYear;
	}

	public void setCardExpiryYear(String cardExpiryYear) {
		this.cardExpiryYear = cardExpiryYear;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getPaymentCardCode() {
		return paymentCardCode;
	}

	public void setPaymentCardCode(String paymentCardCode) {
		this.paymentCardCode = paymentCardCode;
	}

	public String getPaymentGatewayCode() {
		return paymentGatewayCode;
	}

	public void setPaymentGatewayCode(String paymentGatewayCode) {
		this.paymentGatewayCode = paymentGatewayCode;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Boolean getTxSuccess() {
		return txSuccess;
	}

	public void setTxSuccess(Boolean txSuccess) {
		this.txSuccess = txSuccess;
	}

	public String getTxResult() {
		return txResult;
	}

	public void setTxResult(String txResult) {
		this.txResult = txResult;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getTxResponseCode() {
		return txResponseCode;
	}

	public void setTxResponseCode(String txResponseCode) {
		this.txResponseCode = txResponseCode;
	}

	public String getTxResponseText() {
		return txResponseText;
	}

	public void setTxResponseText(String txResponseText) {
		this.txResponseText = txResponseText;
	}

	public String getTxSettlementDate() {
		return txSettlementDate;
	}

	public void setTxSettlementDate(String txSettlementDate) {
		this.txSettlementDate = txSettlementDate;
	}

	public LocalDateTime getTxLogDate() {
		return txLogDate;
	}

	public void setTxLogDate(LocalDateTime txLogDate) {
		this.txLogDate = txLogDate;
	}

	public Long getTxDurationTime() {
		return txDurationTime;
	}

	public void setTxDurationTime(Long txDurationTime) {
		this.txDurationTime = txDurationTime;
	}

	public String getTxErrors() {
		return txErrors;
	}

	public void setTxErrors(String txErrors) {
		this.txErrors = txErrors;
	}

	public String getTxExceptions() {
		return txExceptions;
	}

	public void setTxExceptions(String txExceptions) {
		this.txExceptions = txExceptions;
	}

	public Boolean getCaptured() {
		return captured;
	}

	public void setCaptured(Boolean captured) {
		this.captured = captured;
	}

	public Long getCapturedAmountTotal() {
		return capturedAmountTotal;
	}

	public void setCapturedAmountTotal(Long capturedAmountTotal) {
		this.capturedAmountTotal = capturedAmountTotal;
	}

	public Long getCapturedAmountRemaining() {
		return capturedAmountRemaining;
	}

	public void setCapturedAmountRemaining(Long capturedAmountRemaining) {
		this.capturedAmountRemaining = capturedAmountRemaining;
	}

	public Boolean getRefunded() {
		return refunded;
	}

	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}

	public Long getRefundedAmountTotal() {
		return refundedAmountTotal;
	}

	public void setRefundedAmountTotal(Long refundedAmountTotal) {
		this.refundedAmountTotal = refundedAmountTotal;
	}

	public Long getRefundedAmountRemaining() {
		return refundedAmountRemaining;
	}

	public void setRefundedAmountRemaining(Long refundedAmountRemaining) {
		this.refundedAmountRemaining = refundedAmountRemaining;
	}

	public String getRelatedPaymentTxCcCode() {
		return relatedPaymentTxCcCode;
	}

	public void setRelatedPaymentTxCcCode(String relatedPaymentTxCcCode) {
		this.relatedPaymentTxCcCode = relatedPaymentTxCcCode;
	}

	public Long getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Long durationTime) {
		this.durationTime = durationTime;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public MerchantProfile getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(MerchantProfile merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	public MerchantProfileCurrency getMerchantProfileCurrency() {
		return merchantProfileCurrency;
	}

	public void setMerchantProfileCurrency(MerchantProfileCurrency merchantProfileCurrency) {
		this.merchantProfileCurrency = merchantProfileCurrency;
	}

	public MerchantProfileCard getMerchantProfileCard() {
		return merchantProfileCard;
	}

	public void setMerchantProfileCard(MerchantProfileCard merchantProfileCard) {
		this.merchantProfileCard = merchantProfileCard;
	}

	public MerchantProfilePaymentGateway getMerchantProfilePaymentGateway() {
		return merchantProfilePaymentGateway;
	}

	public void setMerchantProfilePaymentGateway(MerchantProfilePaymentGateway merchantProfilePaymentGateway) {
		this.merchantProfilePaymentGateway = merchantProfilePaymentGateway;
	}

	public PaymentCard getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}
	
	public PaymentResponseCc getPaymentResponseCc() {
		return paymentResponseCc;
	}

	public void setPaymentResponseCc(PaymentResponseCc paymentResponseCc) {
		this.paymentResponseCc = paymentResponseCc;
	}

	public List<PaymentTxCcMetadata> getPaymentTxCcMetadata() {
		return paymentTxCcMetadata;
	}

	public void setPaymentTxCcMetadata(List<PaymentTxCcMetadata> paymentTxCcMetadata) {
		this.paymentTxCcMetadata = paymentTxCcMetadata;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}