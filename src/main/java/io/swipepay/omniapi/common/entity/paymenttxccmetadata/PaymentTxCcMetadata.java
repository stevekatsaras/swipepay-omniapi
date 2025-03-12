package io.swipepay.omniapi.common.entity.paymenttxccmetadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;

@Entity
public class PaymentTxCcMetadata {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "md_key", nullable = false)
	private String mdKey;
	
	@Column(name = "md_value", nullable = false)
	private String mdValue;
	
	@ManyToOne
	@JoinColumn(name = "payment_tx_cc_id", nullable = false)
	private PaymentTxCc paymentTxCc;
	
	public PaymentTxCcMetadata() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMdKey() {
		return mdKey;
	}

	public void setMdKey(String mdKey) {
		this.mdKey = mdKey;
	}

	public String getMdValue() {
		return mdValue;
	}

	public void setMdValue(String mdValue) {
		this.mdValue = mdValue;
	}

	public PaymentTxCc getPaymentTxCc() {
		return paymentTxCc;
	}

	public void setPaymentTxCc(PaymentTxCc paymentTxCc) {
		this.paymentTxCc = paymentTxCc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}