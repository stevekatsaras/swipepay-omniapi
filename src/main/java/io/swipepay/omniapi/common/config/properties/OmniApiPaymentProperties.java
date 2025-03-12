package io.swipepay.omniapi.common.config.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OmniApiPaymentProperties {
	private Boolean gatewayForceSimulation;
	
	public OmniApiPaymentProperties() {
		
	}

	public Boolean getGatewayForceSimulation() {
		return gatewayForceSimulation;
	}

	public void setGatewayForceSimulation(Boolean gatewayForceSimulation) {
		this.gatewayForceSimulation = gatewayForceSimulation;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}