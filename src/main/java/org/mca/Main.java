package org.mca;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String output = getUrlContents("https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1");
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList;
        try {
            productList = List.of(mapper.readValue(output, Product[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Product> domesticProducts = new ArrayList<>();
        List<Product> importedProducts = new ArrayList<>();
        Double domesticProductsPrice = 0.0;
        Double importedProductsPrice = 0.0;
        for (Product p : productList) {
            if (p.domestic) {
                domesticProducts.add(p);
                domesticProductsPrice += p.price;
            } else {
                importedProducts.add(p);
                importedProductsPrice += p.price;
            }
        }
        domesticProducts.sort(Comparator.comparing(Product::getName));
        importedProducts.sort(Comparator.comparing(Product::getName));
        System.out.println(". Domestic");
        printProducts(domesticProducts);
        System.out.println(". Imported");
        printProducts(importedProducts);
        System.out.println("Domestic cost: $" + domesticProductsPrice);
        System.out.println("Imported cost: $" + importedProductsPrice);
        System.out.println("Domestic count: " + domesticProducts.size());
        System.out.println("Imported count: " + importedProducts.size());
    }

    private static void printProducts(List<Product> products) {
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}