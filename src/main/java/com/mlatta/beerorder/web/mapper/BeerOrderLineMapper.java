package com.mlatta.beerorder.web.mapper;

import org.mapstruct.Mapper;

import com.mlatta.beerorder.domain.BeerOrderLine;
import com.mlatta.beerorder.web.model.BeerOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    
	BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);
    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
