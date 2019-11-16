package test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/*
 * (parameters) -> expression
	or (note the curly braces for statements)
	(parameters) -> { statements; }
	
1. ()->{}
2. () -> "Raoul"
3. () -> {return "Mario";}
4. (Integer i) -> return "Alan" + i; -> invalid lambda - return is a control-flow statement. 
     To make this lambda valid, curly braces are required as follows: (Integer i) -> {return "Alan" + i;}.
5. (String s) -> {"Iron Man";} - “Iron Man” is an expression, not a statement. To make this lambda valid, 
    you can remove the curly braces and semicolon as follows: (String s) -> "Iron Man". Or if you prefer, 
    you can use an explicit return statement as follows: (String s) -> {return "Iron Man";}
    
    
  functional interface is an interface that specifies exactly one abstract method. The signature of the abstract 
  method of the functional interface essentially describes the signature of the lambda expression.
  We call this abstract method a function descriptor.
  
  A recurrent pattern in resource processing (for example, dealing with files or databases) is to open a resource, 
  do some processing on it, and then close the resource. The setup and cleanup phases are always similar and 
  surround the important code doing the processing. This is called the execute around pattern
 */
public class LambdaTest {
	public static List<Apple> appleInventory = new ArrayList<Apple>();
	static{
		Apple green1 = new Apple("green", 100);
		appleInventory.add(green1);
		Apple red = new Apple("red", 90);
		appleInventory.add(red);
		Apple redNheavey = new Apple("red", 160);
		appleInventory.add(redNheavey);
		
		Apple yellow = new Apple("yellow", 165);
		appleInventory.add(yellow);
		
		//sort method directly on list
		appleInventory.sort((Apple a1, Apple a2) -> a1.weight-a2.weight);
		System.out.println("sorted inventory:"+appleInventory);
		
	}
	
	public static void main(String args[]){
		System.out.println("--------Predicate tests----------------");
		predicateTests();
		System.out.println("--------Consumer tests----------------");
		consumerTests();
		System.out.println("--------Function tests----------------");
		functionTests();
		
	}
	
	public static void functionTests(){
		
		Function<List<Apple>, Map<String,List<Apple>>> appleCarts  = (List<Apple> appleList) ->{
			return appleList.stream().collect(Collectors.groupingBy(Apple::getColor));
		};
			
		Function<Map<String,List<Apple>>,List<String>> generatCartHeader = (Map<String,List<Apple>> colour2Apples) ->{
			List<String> hdrs = new ArrayList<>();
			for(Map.Entry<String, List<Apple>> entry:colour2Apples.entrySet()){
				String key =entry.getKey();
				String value = entry.getValue().toString();
				hdrs.add("key:"+key+":value:"+value);
			}
			return hdrs;
		};
		Function<List<Apple>,List<String>> headerFunction =  appleCarts.andThen(generatCartHeader);
		List<String> hdrLst= headerFunction.apply(appleInventory);
		hdrLst.forEach((String s)-> System.out.println("cartheader:"+s));
		
	}
	
	public static void consumerTests(){
		
		Consumer<Apple> sendToArches = (Apple a) -> System.out.println("Sending to arches indexing:"+a);
		Consumer<Apple> sendTomds = (Apple a) -> System.out.println("Sending to mds indexing:"+a);
		Consumer<Apple> indexingConsumers = sendToArches.andThen(sendTomds);
		appleInventory.forEach(indexingConsumers);
		
	}
	
	//Predicate 
	public static void predicateTests(){
		Predicate<Apple> isGreen = (Apple apple)->"green".equals(apple.color);
		List<Apple> greenApples = filterApple(appleInventory,isGreen);
		System.out.println("greenApples:"+greenApples);
		
		Predicate<Apple> isRed = (Apple apple)->"red".equals(apple.color);
		List<Apple> redApples = filterApple(appleInventory,isRed);
		System.out.println("redApples:"+redApples);
		
		Predicate<Apple> heavy = (Apple apple)->apple.heavy;
		Predicate<Apple> redNHeavy = isRed.and(heavy);
		List<Apple> redApplesNHeavy = filterApple(appleInventory,redNHeavy);
		System.out.println("redApplesNHeavy:"+redApplesNHeavy);
		
		Predicate<Apple> redNHeavyOrGreen = redNHeavy.or(isGreen);
		List<Apple> redApplesNHeavyOrGreen = filterApple(appleInventory,redNHeavyOrGreen);
		System.out.println("redApplesNHeavyOrGreen:"+redApplesNHeavyOrGreen);
	}
	
	public static <T> List<T> filterApple(List<T> valueList, Predicate<T> filterCondition){
		return valueList.stream().filter(filterCondition).collect(Collectors.toList());
	}

}


class Apple{
	public Apple(String color, int weight) {
		this.color = color;
		this.weight = weight;
		if(weight > 150){
			heavy = true;
		}
	}
	String color;
	int weight;
	boolean heavy = false;
	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + ", heavy=" + heavy + "]";
	}
	
	public String getColor(){
		return this.color;
	}
	
}