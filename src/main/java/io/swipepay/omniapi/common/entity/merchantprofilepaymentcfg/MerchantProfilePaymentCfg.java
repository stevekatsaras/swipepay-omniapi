package io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

@Entity
public class MerchantProfilePaymentCfg {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "receipt_length", nullable = false)
	private Integer receiptLength;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	public MerchantProfilePaymentCfg() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getReceiptLength() {
		return receiptLength;
	}

	public void setReceiptLength(Integer receiptLength) {
		this.receiptLength = receiptLength;
	}

	public MerchantProfile getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(MerchantProfile merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}