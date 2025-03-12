package io.swipepay.omniapi.card.list;

import java.io.Serializable;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;

@JsonPropertyOrder({
	"status",
	"merchantProfile",
	"cards"
})
public class CardListResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private LinkedList<CardListResponseItem> cards;
	
	public CardListResponse(Status status) {
		super(status);
	}
	
	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	public LinkedList<CardListResponseItem> getCards() {
		return cards;
	}

	public void setCards(LinkedList<CardListResponseItem> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}