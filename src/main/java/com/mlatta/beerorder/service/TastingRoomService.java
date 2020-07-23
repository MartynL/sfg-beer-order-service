package com.mlatta.beerorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlatta.beerorder.bootstrap.BeerOrderBootStrap;
import com.mlatta.beerorder.domain.Customer;
import com.mlatta.beerorder.repositories.BeerOrderRepository;
import com.mlatta.beerorder.repositories.CustomerRepository;
import com.mlatta.beerorder.web.model.BeerOrderDto;
import com.mlatta.beerorder.web.model.BeerOrderLineDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;
    private final List<String> beerUpcs = new ArrayList<>();

    public TastingRoomService(CustomerRepository customerRepository, BeerOrderService beerOrderService, BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderRepository = beerOrderRepository;
        
        beerUpcs.add(BeerOrderBootStrap.BEER_1_UPC);
        beerUpcs.add(BeerOrderBootStrap.BEER_2_UPC);
        beerUpcs.add(BeerOrderBootStrap.BEER_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 2000) //run every 2 seconds
    public void placeTastingRoomOrder(){

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderBootStrap.TASTING_ROOM);

        if (customerList.size() == 1){ //should be just one
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer) {
    	String beerToOrder = getRandomBeerUpc();
    	
        BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();

        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLine);

        BeerOrderDto beerOrder = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
               // .orderStatusCallbackUrl("http://localhost:8080/beerorder") //todo update
                .beerOrderLines(beerOrderLineSet)
                .build();

        BeerOrderDto savedOrder = beerOrderService.placeOrder(customer.getId(), beerOrder);

        log.debug("Saved Tasting Room Order: " + savedOrder.getId());
    }

    private String getRandomBeerUpc() {
    	return beerUpcs.get(new Random().nextInt(beerUpcs.size() -0));
    }
}
