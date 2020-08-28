package com.mlatta.beerorder.web.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mlatta.beerorder.domain.BeerOrderLine;
import com.mlatta.beerorder.service.beer.BeerService;
import com.mlatta.beerorder.web.model.BeerDto;
import com.mlatta.beerorder.web.model.BeerOrderLineDto;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

	private BeerService beerService;
	private BeerOrderLineMapper beerOrderLineMapper;
	
	@Autowired
	public void setBeerService(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@Autowired
	@Qualifier("delegate")
	public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
		this.beerOrderLineMapper = beerOrderLineMapper;
	}
	
	public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
		
		BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
		Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(line.getUpc());
		
		beerDtoOptional.ifPresent(beerDto -> {
			orderLineDto.setBeerId(beerDto.getId());
			orderLineDto.setBeerName(beerDto.getBeerName());
			orderLineDto.setBeerStyle(beerDto.getBeerStyle().toString());
			orderLineDto.setPrice(beerDto.getPrice());
		});
		
		return orderLineDto;
	}
	
}
