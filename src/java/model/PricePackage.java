/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
/**
 *
 * @author yentt
 */
public class PricePackage {

    private int priceId;
    private String name;
    private int acessDuration;
    private float price;
    private float salePrice;
    private boolean status;
    private String description;

    public PricePackage() {
    }

    public PricePackage(int priceId, String name, int acessDuration, float price, float salePrice, boolean status, String description) {
        this.priceId = priceId;
        this.name = name;
        this.acessDuration = acessDuration;
        this.price = price;
        this.salePrice = salePrice;
        this.status = status;
        this.description = description;
    }


}
