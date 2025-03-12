package io.swipepay.omniapi.common.entity.country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
	Country findByIso2(String iso2);
}