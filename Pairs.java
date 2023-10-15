import java.util.Arrays;

public class Pairs
{
    public static void main(String[] args)
    {
        int[] nums = {1,3,2,1,3,2,2};
        System.out.println(Arrays.toString(numberOfPairs(nums)));
    }

    public static int[] numberOfPairs(int[] nums)
    {
        int[] result = new int[2];
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < nums.length - 1;)
        {
            if (nums[i] == nums[i+1])
            {
                result[0]++;
                i += 2;
            }
            else
            {
                i++;
            }
        }

        result[1] = nums.length - result[0] * 2;
        return result;
    }
}
