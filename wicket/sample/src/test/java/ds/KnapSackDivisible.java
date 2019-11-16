package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnapSackDivisible {
  public static float MAX_WT = 15;
  public static int[] profits = new int[]{10,5,15,7,6,18,3};
  public static int[] weights = new int[]{2, 3, 5,7,1, 4,1};
  /*
   * weights devisible and can use fraction like in case of vegetable
   * 1) calculate profit/weight
   * 2) sort by max profit by weight and include as much and recursively add the next on
   * 
   */
  public static void main(String args[]){
	  List<Item> itemList = new ArrayList<>();
	  for(int i =0;i < profits.length;i++){
		  itemList.add(new Item(profits[i],weights[i]));
	  }
	  Collections.sort(itemList);
	  Collections.reverse(itemList);
	  List<Item> optimalList = new ArrayList<Item>();
	  findOptimalList(itemList,optimalList,MAX_WT);
	  float totalProfit = 0;
	  for(int i =0; i< optimalList.size();i++){
		  totalProfit = totalProfit+optimalList.get(i).profitPerWeight*optimalList.get(i).usedWeight;
	  }
	  System.out.println("totalProfit:"+totalProfit);
	  
  }
  public static void findOptimalList(List<Item> itemList, List<Item> optimalList,float maxWeight){
	  for(int i =0;i < itemList.size(); i++){
		  System.out.println("profitPerWeight:"+itemList.get(i).profitPerWeight+":profit:"+itemList.get(i).profit+":weight:"+itemList.get(i).weight+":maxWeight:"+maxWeight);
		  if(maxWeight > 0){
			  if(maxWeight >=  itemList.get(i).weight){
				  optimalList.add(itemList.get(i));
				  itemList.get(i).usedWeight = itemList.get(i).weight;
				  maxWeight = maxWeight-itemList.get(i).weight;
			  }else{
				 float fractionWeightUsed =  maxWeight;
				 maxWeight = 0;
				 itemList.get(i).usedWeight = fractionWeightUsed;
				 optimalList.add(itemList.get(i));
			  }
		  }
		  
	  }
  }
}


class Item implements Comparable<Item>{
	int profit, weight;
	float profitPerWeight;
	float usedWeight;
	

	public Item(int profit, int weight){
		this.profit = profit;
		this.weight = weight;
		profitPerWeight = (float)profit/weight;
	}
	
	
	@Override
	public int compareTo(Item input) {
		if(this.profitPerWeight < input.profitPerWeight){
			return -1;
		}else{
			return 1;
		}
	}
	
}