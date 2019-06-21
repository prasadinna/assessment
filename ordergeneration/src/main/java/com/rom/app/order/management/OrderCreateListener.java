package com.rom.app.order.management;

import java.util.List;

import com.rom.app.order.management.model.OrderDetails;

public interface OrderCreateListener {
   public void recieveOrder(List<OrderDetails> order);
}
