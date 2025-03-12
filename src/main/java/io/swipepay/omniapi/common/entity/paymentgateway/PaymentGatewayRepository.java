package io.swipepay.omniapi.common.entity.paymentgateway;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Long> {

}