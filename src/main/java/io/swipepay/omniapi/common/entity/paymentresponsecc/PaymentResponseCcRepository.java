package io.swipepay.omniapi.common.entity.paymentresponsecc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentResponseCcRepository extends JpaRepository<PaymentResponseCc, Long> {
	
	PaymentResponseCc findByCode(String code);
}