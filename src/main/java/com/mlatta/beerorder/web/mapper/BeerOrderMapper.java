package com.mlatta.beerorder.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mlatta.beerorder.domain.BeerOrder;
import com.mlatta.beerorder.web.model.BeerOrderDto;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

	@Mapping(target="customerId", source="customer.id")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
