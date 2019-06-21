package com.rom.app.order.management;


public class SequenceGenerator {
  private int nodeId =1;
  private static final int TOTAL_BITS = 63;
  private static final int EPOCH_BITS = 41;
  private static final int NODE_ID_BITS = 5;
  private static final int SEQUENCE_BITS = 17;
  int currentcount = 1;
  long id = 0;
  private static final int maxSequence = (int)(Math.pow(2, SEQUENCE_BITS) - 1);
  public SequenceGenerator(int nodeId){
	  this.nodeId = nodeId;
	  init();
	 
  }
  
  private void init(){
	  long currentTimestamp = System.currentTimeMillis();
	  id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
	  id |= (nodeId << (TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS));
  }
  
  public long getNextId(){
	  long currentId = id;
	  if(currentcount <= maxSequence){
		  currentcount++;
	  }else{
		  init();
		  currentcount = 1;
	  }
	  currentId |= currentcount;
	  return currentId;
  }
  
}
