package com.mlatta.beerorder.service.beer;

import java.util.Optional;
import java.util.UUID;

import com.mlatta.beerorder.web.model.BeerDto;

public interface BeerService {
	
	Optional<BeerDto> getBeerById(UUID id);
	
	Optional<BeerDto> getBeerByUpc(String upc);

}
