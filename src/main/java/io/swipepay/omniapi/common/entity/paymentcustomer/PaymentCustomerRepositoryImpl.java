package io.swipepay.omniapi.common.entity.paymentcustomer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class PaymentCustomerRepositoryImpl implements PaymentCustomerRepositorySupport {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public void refresh(PaymentCustomer paymentCustomer) {
		em.refresh(paymentCustomer);
	}
	
}