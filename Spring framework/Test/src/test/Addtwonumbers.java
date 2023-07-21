package test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Addtwonumbers {
	public static void main(String[] args) {
		List<Integer> list=Arrays.asList(10,20);
		
		int a=list.stream().collect(Collectors.summingInt(Integer::intValue));
	}

	private static Object Stream() {
		// TODO Auto-generated method stub
		return null;
	}

}
