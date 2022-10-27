package org.mca;

import org.apache.commons.lang3.StringUtils;

public class Product {
    String name;
    Boolean domestic;
    Double price;
    Integer weight;
    String description;

    @Override
    public String toString() {
//        return "Product{" +
//                "name='" + name + '\'' +
//                ", domestic=" + domestic +
//                ", price=" + price +
//                ", weight=" + weight +
//                ", description='" + description + '\'' +
//                '}';
        int maxWidth = 10;
        return "... " + name + '\n'
                + "    " + "Price: $"
                + "    " + price + '\n'
                + "    " + StringUtils.truncate(description, maxWidth) + "... \n"
                + "    " + "Weight: " + (weight == null ? "N/A" : weight + "g");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDomestic() {
        return domestic;
    }

    public void setDomestic(Boolean domestic) {
        this.domestic = domestic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}