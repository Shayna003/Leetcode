import java.util.*;

/**
 * 7-17-2022
 * Leetcode weekly contest solutions
 */
class MaxDigitPairSum
{
    public static void main(String[] args)
    {
        int[] nums = {18,43,36,13,7};
        int[] nums2 = {10,12,19,14};
        //System.out.println(maximumSum(nums2));
        /*System.out.println(sumOfDigits(89));
        System.out.println(sumOfDigits(798));
        System.out.println(sumOfDigits(1));
        System.out.println(sumOfDigits(9));
        System.out.println(sumOfDigits(24675));*/
    }

    public static int maximumSum(int[] nums)
    {
        maxValue = -1;
        Map<Integer, Integer[]> numbers = new HashMap<>(nums.length / 10);

        int key;
        for (int i = 0; i < nums.length; i++)
        {
            key = sumOfDigits(nums[i]);
            //System.out.println("num: " + nums[i] + ", sum of digits: " + key);
            numbers.putIfAbsent(key, new Integer[] {0, 0});
            Integer[] sameDigitSum = numbers.get(key);

            if (nums[i] > sameDigitSum[0])
            {
                sameDigitSum[0] = nums[i];
                Arrays.sort(sameDigitSum);
            }
        }

        //System.out.println(numbers);
        numbers.forEach((k, v) ->
        {
            if (v[0] > 0 && v[0] + v[1] > maxValue)
            {
                maxValue = v[0] + v[1];
            }
        });

        return maxValue;
    }

    static int maxValue = -1;

    public static int sumOfDigits(int num)
    {
        if (num < 10) return num;
        int places = (int) Math.ceil(Math.log10(num));

        if (num - Math.pow(10, places) == 0)
        {
            places++;
        }
        //System.out.println("places" + places);

        int tmp;
        int sum = 0;

        for (int i = places - 1; i >= 0 ; i--)
        {
            //System.out.println("i: " + i);
            //System.out.println("math.pow: " + Math.pow(10, i));
            tmp = (int) (num / Math.pow(10, i));
            //System.out.println("tmp: " + tmp);
            sum += tmp;
            num -= tmp * Math.pow(10, i);
            /*tmp = tmp / 10.0;
            System.out.println(tmp);
            System.out.println(tmp - Math.floor(tmp));
            sum += (tmp - Math.floor(tmp)) * 10;
            tmp = Math.floor(tmp);*/
        }
        return (int) sum;
    }
}