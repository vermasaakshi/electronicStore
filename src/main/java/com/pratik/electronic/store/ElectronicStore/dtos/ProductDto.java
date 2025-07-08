package com.pratik.electronic.store.ElectronicStore.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

  private String productId;
  private String title;
  private String description;
  private int price;
  private int discount;
  private Date addedDate;
  private int stock;
  private String productImageName;
  private String categoryId;


}
