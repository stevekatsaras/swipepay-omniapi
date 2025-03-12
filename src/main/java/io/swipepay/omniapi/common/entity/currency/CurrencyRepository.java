package io.swipepay.omniapi.common.entity.currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	
	Currency findByIso3(String iso3);

}