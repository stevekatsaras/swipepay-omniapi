package io.swipepay.omniapi.common.entity.paymentgateway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class PaymentGateway {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "vendor", nullable = false)
	private String vendor;
	
	@Column(name = "endpoint")
	private String endpoint;
	
	@Column(name = "connect_timeout_ms")
	private String connectTimeoutMs;
	
	@Column(name = "read_timeout_ms")
	private String readTimeoutMs;
	
	@Column(name = "does_cc", nullable = false)
	private Boolean doesCc;
	
	@Column(name = "does_de", nullable = false)
	private Boolean doesDe;
	
	@Column(name = "does_coin", nullable = false)
	private Boolean doesCoin;
	
	public PaymentGateway() {
		
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
	
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getConnectTimeoutMs() {
		return connectTimeoutMs;
	}

	public void setConnectTimeoutMs(String connectTimeoutMs) {
		this.connectTimeoutMs = connectTimeoutMs;
	}

	public String getReadTimeoutMs() {
		return readTimeoutMs;
	}

	public void setReadTimeoutMs(String readTimeoutMs) {
		this.readTimeoutMs = readTimeoutMs;
	}

	public Boolean getDoesCc() {
		return doesCc;
	}

	public void setDoesCc(Boolean doesCc) {
		this.doesCc = doesCc;
	}

	public Boolean getDoesDe() {
		return doesDe;
	}

	public void setDoesDe(Boolean doesDe) {
		this.doesDe = doesDe;
	}

	public Boolean getDoesCoin() {
		return doesCoin;
	}

	public void setDoesCoin(Boolean doesCoin) {
		this.doesCoin = doesCoin;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}