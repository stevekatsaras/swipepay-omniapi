package io.swipepay.omniapi.card.get;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	"name",
	"email",
	"bin",
	"pan",
	"expiryMonth",
	"expiryYear",
	"enabled",
	"modified"
})
public class CardGetResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private String code;
	private String name;
	private String email;
	private String bin;
	private String pan;
	private String expiryMonth;
	private String expiryYear;
	private Boolean enabled;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime modified;
	
	public CardGetResponse(Status status) {
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}