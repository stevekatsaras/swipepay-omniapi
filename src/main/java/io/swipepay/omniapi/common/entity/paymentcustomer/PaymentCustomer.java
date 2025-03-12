package io.swipepay.omniapi.common.entity.paymentcustomer;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.country.Country;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"code", "merchant_profile_id"})
})
public class PaymentCustomer {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "postcode", nullable = false)
	private String postcode;
	
	@Column(name = "country", nullable = false)
	private String countryCode;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "modified", nullable = false)
	private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "merchant_profile_id", nullable = false)
	private MerchantProfile merchantProfile;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "paymentCustomer")
	@OrderBy("isDefault DESC")
	private List<PaymentCard> paymentCards;
	
	@OneToMany(mappedBy = "paymentCustomer")
	@OrderBy("isDefault DESC")
	private List<PaymentBankAccount> paymentBankAccounts;
	
	public PaymentCustomer() {
		
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	
	public List<PaymentCard> getPaymentCards() {
		return paymentCards;
	}

	public void setPaymentCards(List<PaymentCard> paymentCards) {
		this.paymentCards = paymentCards;
	}

	public List<PaymentBankAccount> getPaymentBankAccounts() {
		return paymentBankAccounts;
	}

	public void setPaymentBankAccounts(List<PaymentBankAccount> paymentBankAccounts) {
		this.paymentBankAccounts = paymentBankAccounts;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}