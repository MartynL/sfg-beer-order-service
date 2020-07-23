package com.mlatta.beerorder.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mlatta.beerorder.domain.BeerOrderLine;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
