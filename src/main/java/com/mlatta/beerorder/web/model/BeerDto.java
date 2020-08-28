package com.mlatta.beerorder.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mlatta.beerorder.service.beer.enums.BeerStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto implements Serializable {

	private static final long serialVersionUID = 791805610700868422L;

	@Null
	private UUID id;
	
	@Null
	private Integer version;
	
	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;
	
	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime lastModified;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;
	
	private BeerStyle beerStyle;
	
	@NotBlank
	private String upc;
	
	@NotNull
	@Positive
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;
	
	@Positive
	private Integer quantityOnHand;
}
