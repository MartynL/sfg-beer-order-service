package com.mlatta.beerorder.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mlatta.beerorder.domain.Customer;
import com.mlatta.beerorder.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerOrderBootStrap implements CommandLineRunner {

	public static final String TASTING_ROOM = "Tasting Room";
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";

	private final CustomerRepository customerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		loadCustomerData();
	}

	private void loadCustomerData() {
		if(customerRepository.count() == 0) {
			Customer savedCustomer = customerRepository.save(
				Customer.builder()
					.customerName(TASTING_ROOM)
					.apiKey(UUID.randomUUID())
					.build());
			
			log.warn("Tasting room customer is: "+ savedCustomer.getId().toString());
		}
	}

}
