package com.rom.app.order.management.model;

public class GeneratorStatistics {
   public int getNumberOfOrder() {
		return numberOfOrder;
	}
	public long getTimeTaken() {
		return timeTaken;
	}
	public void setNumberOfOrder(int numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}
	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}
private int numberOfOrder;
   private long timeTaken;
}
