package com.example.episodicevents.productsExample;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DigitalProduct extends Product {
    private String fileUrl;

    public DigitalProduct(String sku, String name, int priceInCents, String fileUrl) {
        super(sku, name, priceInCents);
        this.fileUrl = fileUrl;
    }

    public String getProductCategory() {
        return "media";
    }

}
