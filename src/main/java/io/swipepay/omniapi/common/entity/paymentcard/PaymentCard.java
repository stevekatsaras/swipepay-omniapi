package io.swipepay.omniapi.common.entity.paymentcard;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"code", "merchant_profile_id"})
})
public class PaymentCard {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
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
	
	@Column(name = "card_expiry_month", nullable = false)
	private String cardExpiryMonth;
	
	@Column(name = "card_expiry_year", nullable = false)
	private String cardExpiryYear;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "is_default")
	private Boolean isDefault;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_card_id", nullable = false)
	private MerchantProfileCard merchantProfileCard;
	
	@ManyToOne
	@JoinColumn(name = "payment_customer_id")
	private PaymentCustomer paymentCustomer;
	
	public PaymentCard() {
		
	}
	
	public PaymentCard(MerchantProfile merchantProfile) {
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
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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
	
	public MerchantProfileCard getMerchantProfileCard() {
		return merchantProfileCard;
	}

	public void setMerchantProfileCard(MerchantProfileCard merchantProfileCard) {
		this.merchantProfileCard = merchantProfileCard;
	}

	public PaymentCustomer getPaymentCustomer() {
		return paymentCustomer;
	}

	public void setPaymentCustomer(PaymentCustomer paymentCustomer) {
		this.paymentCustomer = paymentCustomer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}