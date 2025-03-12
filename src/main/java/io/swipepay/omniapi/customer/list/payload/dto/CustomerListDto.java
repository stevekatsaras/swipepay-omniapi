package io.swipepay.omniapi.customer.list.payload.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swipepay.omniapi.bankaccount.list.payload.dto.BankAccountListDto;
import io.swipepay.omniapi.card.list.payload.dto.CardListDto;

@JsonPropertyOrder({
	"code",
	"firstName",
	"lastName",
	"address",
	"city",
	"state",
	"postcode",
	"country",
	"enabled", 
	"modified", 
	"cards", 
	"bankAccounts"})
public class CustomerListDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String postcode;
	
	@JsonProperty(value = "country")
	private String countryCode;
	
	private Boolean enabled;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime modified;
	
	@JsonProperty(value = "cards")
	private LinkedList<CardListDto> cardListDtos;
	
	@JsonProperty(value = "bankAccounts")
	private LinkedList<BankAccountListDto> bankAccountListDtos;
	
	public CustomerListDto() {
		
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

	public LinkedList<CardListDto> getCardListDtos() {
		return cardListDtos;
	}

	public void setCardListDtos(LinkedList<CardListDto> cardListDtos) {
		this.cardListDtos = cardListDtos;
	}

	public LinkedList<BankAccountListDto> getBankAccountListDtos() {
		return bankAccountListDtos;
	}

	public void setBankAccountListDtos(LinkedList<BankAccountListDto> bankAccountListDtos) {
		this.bankAccountListDtos = bankAccountListDtos;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}