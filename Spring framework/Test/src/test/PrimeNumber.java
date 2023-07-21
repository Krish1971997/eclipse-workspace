package test;

public class PrimeNumber {
		   public static void main (String[]args)
		   {

		     int lower = 1, upper = 20;


		     for (int i = lower; i <= upper; i++)
		       if (isPrime (i))
		        System.out.print(i+" ");
		   }

		   static boolean isPrime (int n)
		   {
		     int count = 0;

		     // 0, 1 negative numbers are not prime
		     if (n < 2)
		       return false;

		     // checking the number of divisors b/w 1 and the number n-1
		     for (int in = 2; in < n; in++)
		       {
		     if (n % in == 0)
		        return false;
		       }

		     // if reached here then must be true
		     return true;
		   }
		   
}
