package com.mlatta.beerorder.web.mapper;

import org.mapstruct.Mapper;

import com.mlatta.beerorder.domain.BeerOrder;
import com.mlatta.beerorder.web.model.BeerOrderDto;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
