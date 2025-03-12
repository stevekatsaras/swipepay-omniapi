package io.swipepay.omniapi.common.entity.paymentbankaccount;

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

import io.swipepay.omniapi.common.entity.country.Country;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"code", "merchant_profile_id"})
})
public class PaymentBankAccount {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "bsb", nullable = false)
	private String bsb;
	
	@Column(name = "account_number", nullable = false)
	private String accountNumber;
	
	@Column(name = "account_name", nullable = false)
	private String accountName;
	
	@Column(name = "account_email")
	private String accountEmail;
	
	@Column(name = "country", nullable = false)
	private String countryCode;
	
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
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "payment_customer_id")
	private PaymentCustomer paymentCustomer;
	
	public PaymentBankAccount() {
		
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

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) {
		this.bsb = bsb;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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