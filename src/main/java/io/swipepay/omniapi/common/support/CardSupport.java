package io.swipepay.omniapi.common.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;

@Component
public class CardSupport {
	public final Integer CARD_NUMBER_PREFIX_LENGTH = 6; // the first 6 digits of the card (bin)
	public final Integer CARD_NUMBER_SUFFIX_LENGTH = 3; // the last 3 digits of the card
	
	public String bin(String pan) {
		return StringUtils.substring(pan, 0, CARD_NUMBER_PREFIX_LENGTH);
	}
	
	public boolean expired(String expMth, String expYr) throws ParseException {
		Calendar yrCal = Calendar.getInstance();
		yrCal.setTime(new SimpleDateFormat("yyyy").parse(expYr));
		
		// default date to first day of the month
		// then alter the date for the last day of the month
		
		LocalDate expiryDate = LocalDate.of(yrCal.get(Calendar.YEAR), Integer.parseInt(expMth), 1);
		expiryDate = expiryDate.withDayOfMonth(expiryDate.lengthOfMonth()); 
		return LocalDate.now().isAfter(expiryDate);
	}
	
	// attempts to find a merchant profile card from a PAN
	
	public MerchantProfileCard find(String pan, LinkedList<MerchantProfileCard> merchantProfileCards) {
		MerchantProfileCard merchantProfileCardFound = null;
		for (MerchantProfileCard merchantProfileCard : merchantProfileCards) {
			Pattern pattern = Pattern.compile(merchantProfileCard.getCard().getPattern());
			if (pattern.matcher(pan).matches()) {
				merchantProfileCardFound = merchantProfileCard;
				break;
			}
		}
		return merchantProfileCardFound;
	}
	
	public String mask(String pan, String pattern) {
		Integer lengthDiff = pan.length() - CARD_NUMBER_PREFIX_LENGTH - CARD_NUMBER_SUFFIX_LENGTH;
		
		return StringUtils.overlay(
				pan, 
				StringUtils.repeat(pattern.toString(), lengthDiff), 
				CARD_NUMBER_PREFIX_LENGTH, 
				pan.length() - CARD_NUMBER_SUFFIX_LENGTH);
	}
}