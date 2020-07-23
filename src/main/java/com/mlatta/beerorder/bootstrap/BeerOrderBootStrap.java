package com.mlatta.beerorder.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mlatta.beerorder.domain.Customer;
import com.mlatta.beerorder.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeerOrderBootStrap implements CommandLineRunner {

	public static final String TASTING_ROOM = "Tasting Room";
	public static final String BEER_1_UPC = "062928889569212";
	public static final String BEER_2_UPC = "062977355469234";
	public static final String BEER_3_UPC = "052924582469256";

	private final CustomerRepository customerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		loadCustomerData();
	}

	private void loadCustomerData() {
		if(customerRepository.count() == 0) {
			customerRepository.save(
				Customer.builder()
					.customerName(TASTING_ROOM)
					.apiKey(UUID.randomUUID())
					.build());
		}
	}

}
