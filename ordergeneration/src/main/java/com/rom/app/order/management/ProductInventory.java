package com.rom.app.order.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rom.app.order.management.model.Product;

public class ProductInventory {
    private static List<Product> productList = new ArrayList<Product>();
    static{
    	productList.add(new Product("product1",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product2",2,"mumbai","hyderbad","UOM","Q"));
    	productList.add(new Product("product3",2,"delhi","hyderbad","UOM","Q"));
    	productList.add(new Product("product4",2,"patna","hyderbad","UOM","Q"));
    	productList.add(new Product("product5",2,"gujarath","hyderbad","UOM","Q"));
    	productList.add(new Product("product6",2,"kolkatha","hyderbad","UOM","Q"));
    	productList.add(new Product("product7",2,"mangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product8",2,"udupi","hyderbad","UOM","Q"));
    	productList.add(new Product("product9",2,"hubli","hyderbad","UOM","Q"));
    	productList.add(new Product("product10",2,"hassan","hyderbad","UOM","Q"));
    	productList.add(new Product("product11",2,"gulbarga","hyderbad","UOM","Q"));
    	productList.add(new Product("product12",2,"karavara","hyderbad","UOM","Q"));
    	productList.add(new Product("product13",2,"panaji","hyderbad","UOM","Q"));
    	productList.add(new Product("product14",2,"chennai","hyderbad","UOM","Q"));
    	productList.add(new Product("product15",2,"mysore","hyderbad","UOM","Q"));
    	productList.add(new Product("product16",2,"mandya","hyderbad","UOM","Q"));
    	productList.add(new Product("product17",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product18",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product19",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product20",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product21",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product22",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product23",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product24",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product25",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product26",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product27",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product28",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product29",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product30",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product31",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product32",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product33",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product34",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product35",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product36",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product37",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product38",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product39",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product40",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product41",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product42",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product43",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product44",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product45",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product46",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product47",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product48",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product49",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product50",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product51",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product52",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product53",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product54",2,"bangalore","hyderbad","UOM","Q"));
    	productList.add(new Product("product55",2,"bangalore","hyderbad","UOM","Q"));
    }
    
    public static Product getRandomPProduct(){
    	Random random = new Random();
    	int randomNum = random.nextInt((54 - 0) + 1) + 0;
    	return productList.get(randomNum);
    }
}
