package io.swipepay.omniapi.common.entity.merchantprofilecurrency;

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

import io.swipepay.omniapi.common.entity.currency.Currency;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"merchant_profile_id", "currency_id"})
})
public class MerchantProfileCurrency {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	@ManyToOne
	@JoinColumn(name = "currency_id", nullable = false)
	private Currency currency;
	
	public MerchantProfileCurrency() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}