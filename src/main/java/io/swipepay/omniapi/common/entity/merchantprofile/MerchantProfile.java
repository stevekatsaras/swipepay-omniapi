package io.swipepay.omniapi.common.entity.merchantprofile;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchant.Merchant;

@Entity
public class MerchantProfile {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "access_key", nullable = false, unique = true)
	private String accessKey;
	
	@Column(name = "secret_key", nullable = false)
	private String secretKey;

	@Column(name = "data_key", nullable = false)
	private String dataKey;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "environment", nullable = false)
	private String environment;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;
	
	public MerchantProfile() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}