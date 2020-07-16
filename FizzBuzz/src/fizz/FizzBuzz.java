package fizz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
	
	public static String change(int i) {
		Map<Integer, String> map = new HashMap<>();
		
		map.put(3, "Fizz");
		map.put(5,"Buzz");
		map.put(7,"Tazz");
		
		StringBuilder res = new StringBuilder("");
		map.forEach((num,l)->{if(i % num == 0) res.append(l);});
		if (res.toString().equals("")){
			return ""+i;
		}
		else {
			return res.toString();
		}
	}
	
	public static void main(String[] args) {
		String[] array = new String[100];
		Arrays.fill(array, "0");

		List<String> list =	IntStream.range(1, 106).mapToObj(i -> change(i)).collect(Collectors.toList());
		System.out.println(list);
		
	}
	
}
