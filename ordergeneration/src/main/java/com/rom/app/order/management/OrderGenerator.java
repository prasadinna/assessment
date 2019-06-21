package com.rom.app.order.management;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rom.app.order.management.model.GeneratorStatistics;
import com.rom.app.order.management.model.OrderDetails;
import com.rom.app.order.management.model.Product;

@Component
public class OrderGenerator {
	
	List<OrderCreateListener> listenerList = new ArrayList<OrderCreateListener>();
	
    private final BlockingQueue<List<OrderDetails>> queue = new LinkedBlockingQueue<>();
    private Runnable task = new Runnable(){
		@Override
		public void run() {
			while(true){
				List<OrderDetails> batchList;
				try {
					batchList = queue.take();
					if(batchList != null ){
						for(OrderCreateListener curretlistener:listenerList){
							curretlistener.recieveOrder(batchList);
						}
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	};
    public OrderGenerator(){
    	Thread publisher = new Thread(task);
    	publisher.start();
    	
    }
	public GeneratorStatistics startOrderGeneration(int numberOfOrder){
		SequenceGenerator seqGener = new SequenceGenerator(5);
		if(numberOfOrder < 0){
			numberOfOrder = 100000;
		}
		long startTime = System.currentTimeMillis();
		List<OrderDetails> batchList = new ArrayList<OrderDetails>(100);
		for(int i=1;i<100000;i++){
			OrderDetails order = new OrderDetails();
			order.setId(seqGener.getNextId());
			
			Product prd1 = ProductInventory.getRandomPProduct();
			Product prd2 = ProductInventory.getRandomPProduct();
			List<Product> productList = new LinkedList<>();
			productList.add(prd1);
			productList.add(prd2);
			order.setProductList(productList);
			if(batchList.size() > 100){
				try {
					queue.put(batchList);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				batchList = new ArrayList<OrderDetails>(100);
			}
			batchList.add(order);
		}
		long timeTaken = System.currentTimeMillis()-startTime;
		GeneratorStatistics statistics = new GeneratorStatistics();
		statistics.setNumberOfOrder(numberOfOrder);
		statistics.setTimeTaken(timeTaken);
		return statistics;
	}
	
	
	/*private void notifyListener(List<OrderDetails> batchLis)
	{
		
		Thread publishThread = new Thread(task);
		publishThread.start();
	}*/
	
	public void registerListener(OrderCreateListener listener){
		listenerList.add(listener);
	}

}
