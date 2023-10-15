public class ZeroSubarrays
{
    public static void main(String[] args)
    {
        System.out.println(new Solution().zeroFilledSubarray(new int[] {0,0,0,0,0}));
    }
}

class Solution
{
    public long zeroFilledSubarray(int[] nums)
    {
        long subarrays = 0;
        int zero_count = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] == 0)
            {
                zero_count++;
            }
            System.out.println("i: " + i + ", zero count: " + zero_count);
            if (zero_count > 0 && (nums[i] != 0 || i == nums.length - 1))
            {
                subarrays += (long) ((zero_count + 1) / 2.0 * zero_count);
                zero_count = 0;
            }
        }

        return subarrays;
    }
}
