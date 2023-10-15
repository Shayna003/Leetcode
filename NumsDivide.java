import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class NumsDivide
{
    public static void main(String[] args)
    {
        //MAX_NUM = 200_0000;
        //calculatePrimes();
        /*int count = 0;
        for (int i = 0; i < MAX_NUM + 1; i++)
        {
            if (primeNumbers.get(i)) count++;
        }
        //System.out.println(count + " prime numbers between 0 and " + MAX_NUM + ".");


        //primeFactors(1);
        primeFactors(98);
        primeFactors(100);
        primeFactors(6);
        primeFactors(49);
        primeFactors(7);
        primeFactors(2);*/
        /*int[] nums = { 2,3,2,4,3 };
        int[] numsDivide = { 9,6,9,3,15 };

        int[] nums2 = {2,3,3,18,3,2,3,16};
        int[] numsDivide2 = {573595257,616368999,782586708,555836748,128826519,10729950,660848235,459842193,986538021,509885907};

        int[] nums3 = {16,6,6,9,12,12,7,12};
        int[] numsDivide3 = {174740464,864178736};*/

        int[] nums4 = null;
        int[] numsDivide4 = null;

        try (Scanner in = new Scanner(new File("src/data.dat")))
        {
            //in.next();
            String[] numsTokens = in.nextLine().split(",");
            String[] numsDivideTokens = in.nextLine().split(",");

            nums4 = new int[numsTokens.length];
            numsDivide4 = new int[numsDivideTokens.length];

            for (int i = 0; i < numsTokens.length; i++)
            {
                nums4[i] = Integer.parseInt(numsTokens[i]);
            }

            for (int i = 0; i < numsDivideTokens.length; i++)
            {
                numsDivide4[i] = Integer.parseInt(numsDivideTokens[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(minOperations(nums4, numsDivide4));
    }

    //public static int MAX_NUM;
    public static int MAX_FACTOR;
    //public static final int MAX_NUM = 200_0000;//(int) Math.pow(10, 9);
    public static BitSet primeNumbers;// = new BitSet(MAX_NUM + 1); // 0 inclusive

    public static void calculatePrimes()
    {
        long start = System.currentTimeMillis();
        primeNumbers = new BitSet(MAX_FACTOR + 1);
        for (int i = 2; i <= MAX_FACTOR; i++) // turn all bits on first
        {
            primeNumbers.set(i);
        }

        int prime_num = 2;
        int index;

        int count = 0;
        //int sqrt = (int) (Math.sqrt(MAX_NUM));
        while (prime_num * prime_num <= MAX_FACTOR)
        {
            if (primeNumbers.get(prime_num))
            {
                index = prime_num * 2;
                while (index <= MAX_FACTOR)
                {
                    primeNumbers.clear(index);
                    index += prime_num;
                }
            }
            prime_num++;
        }
        System.out.println("Time spent calculating primes: " + (System.currentTimeMillis() - start));


        for (int i = 0; i < primeNumbers.length(); i++)
        {
            if (primeNumbers.get(i)) count++;
            //if (primeNumbers.get(i)) //System.out.println(i);
        }
        //System.out.println("Prime numbers between 0 to " + MAX_FACTOR + ": " + count);
    }

    public static ArrayList<Integer> primeFactors(int num)
    {
        long start = System.currentTimeMillis();
        ArrayList<Integer> primeFactors = new ArrayList<>((int) Math.sqrt(num));

        int number = num;
        while (!primeNumbers.get(number))
        {
            for (int i = 2; i <= number; i++)
            {
                if (i > MAX_FACTOR) return primeFactors;
                if (primeNumbers.get(i) && number % i == 0)
                {
                    ////System.out.println("number: " + number + ", prime factor: " + i);
                    if (i <= MAX_FACTOR) primeFactors.add(i);
                    number /= i;
                    ////System.out.println("number: " + number);
                    break;
                }
            }
        }
        primeFactors.add(number);
        long end = System.currentTimeMillis();
        //System.out.println("Prime factors of " + num + ": " + primeFactors);
        //System.out.println("Time Spent for prime factorization: " + (end - start));

        return primeFactors;
    }

    public static ArrayList<Integer> commonPrimeFactors;
    public static ArrayList<Integer> prime_factors;

    public static int minOperations(int[] nums, int[] numsDivide)
    {
        Arrays.sort(nums);
        Arrays.sort(numsDivide);
        MAX_FACTOR = nums[nums.length - 1];

        calculatePrimes();
        //System.out.println("nums: " + Arrays.toString(nums));
        //System.out.println("numsDivide: " + Arrays.toString(numsDivide));
        if (nums[0] == 1) return 0;
        if (numsDivide[0] == 1) return -1;
        //if (nums[nums.length - 1] > numsDivide[numsDivide.length - 1])
        /*{
            //System.out.println("nums[nums.length - 1]: " + nums[nums.length - 1]);
            //System.out.println("numsDivide[numsDivide.length - 1]:  " + numsDivide[numsDivide.length - 1]);
            return -1;
        }*/

        //MAX_NUM = numsDivide[numsDivide.length - 1];


        long start = System.currentTimeMillis();
        int operations = 0;

        commonPrimeFactors = primeFactors(numsDivide[0]);
        for (int i = 1; i < numsDivide.length; i++)
        {
            prime_factors = primeFactors(numsDivide[i]);
            //System.out.println("delete uncommon for " + numsDivide[i] + ":");
            deleteUncommon(prime_factors, commonPrimeFactors);
            if (commonPrimeFactors.size() == 0) return -1;
        }

        //System.out.println("Common prime factors: " + commonPrimeFactors);
        //System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < nums.length;)
        {
            //System.out.println("Testing if " + nums[i] + " is divisible to all:");
            if (divisible(commonPrimeFactors, primeFactors(nums[i]))) // divisible for all numbers in numsDivide
            {
                System.out.println("time spent in algorithm: " + (System.currentTimeMillis() - start));
                return operations;
            }
            else
            {
                do
                {
                    i++;
                    if (i > nums.length - 1) return -1;
                    operations++;
                }
                while (nums[i] == nums[i - 1]);
            }
        }
        return -1;
    }

    static boolean divisible(ArrayList<Integer> primes, ArrayList<Integer> divisor_primes)
    {
        //System.out.println("    Primes: " + primes);
        //System.out.println("    divisor_primes: " + divisor_primes);

        ArrayList<Integer> primesClone = (ArrayList<Integer>) primes.clone();
        if (divisor_primes.size() > primesClone.size())
        {
            //System.out.println("indivisible");
            return false;
        }
        for (int i = 0; i < divisor_primes.size(); i++)
        {
            if (!primesClone.remove(divisor_primes.get(i)))
            {
                //System.out.println("indivisible");
                return false;
            }
        }
        //System.out.println("divisible");
        return true;
    }

    /**
     * Deletes prime factors in b that are not present in a
     */
    public static void deleteUncommon(ArrayList<Integer> primes, ArrayList<Integer> commonPrimes)
    {
        //System.out.println("    primes: " + primes);
        //System.out.println("    commonPrimes: " + primes);

        commonPrimes.removeIf(n -> !primes.contains(n));
        for (int i = 0; i < commonPrimes.size(); i++)
        {
            if (!primes.remove(commonPrimes.get(i))) commonPrimes.remove((int) i);
        }
        //System.out.println("    result: " + commonPrimes);
    }
}
