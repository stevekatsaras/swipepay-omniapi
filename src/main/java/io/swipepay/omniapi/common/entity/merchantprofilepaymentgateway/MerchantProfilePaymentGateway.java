package io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway;

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
import io.swipepay.omniapi.common.entity.paymentgateway.PaymentGateway;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"merchant_profile_id", "payment_gateway_id"})
})
public class MerchantProfilePaymentGateway {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "merchant_account")
	private String merchantAccount;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "priority", nullable = false)
	private Integer priority;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	@ManyToOne
	@JoinColumn(name = "payment_gateway_id", nullable = false)
	private PaymentGateway paymentGateway;
	
	public MerchantProfilePaymentGateway() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public PaymentGateway getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}