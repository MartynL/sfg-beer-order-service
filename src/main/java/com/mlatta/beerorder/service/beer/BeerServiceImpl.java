package com.mlatta.beerorder.service.beer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mlatta.beerorder.web.model.BeerDto;

import lombok.Setter;

@Service("beerService")
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BeerServiceImpl implements BeerService {
	
	public static final String BEER_PATH_V1 = "/api/v1/beer/";
	public static final String BEER_UPC_PATH_V1 = "/api/v1/beerUpc/";
	
	private final RestTemplate restTemplate;
	
	@Setter
	private String beerServiceHost;
	
	public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Optional<BeerDto> getBeerById(UUID id) {
		return Optional.of(
			restTemplate.getForObject(beerServiceHost + BEER_PATH_V1 + id.toString(), BeerDto.class)
		);
	}

	@Override
	public Optional<BeerDto> getBeerByUpc(String upc) {
		return Optional.of(
			restTemplate.getForObject(beerServiceHost + BEER_UPC_PATH_V1 + upc, BeerDto.class)
		);
	}

}
