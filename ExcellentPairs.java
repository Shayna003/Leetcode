import java.io.File;
import java.util.*;

class Solution2
{
    long bitOperations;
    public long countExcellentPairs(int[] nums, int k)
    {
        long s1 = System.currentTimeMillis();
        Set<Integer> numbers = new HashSet<>(nums.length);
        long pairs = 0;

        for (int i = 0; i < nums.length; i++)
        {
            numbers.add(nums[i]);
        }

        //System.out.println("nums: " + Arrays.toString(nums));
        Iterator<Integer> iter = numbers.iterator();
        int[] bits = new int[numbers.size()];
        long num_count = 0;
        int tmp;
        long ok_nums = 0;

        while (iter.hasNext())
        {
            tmp = bits(iter.next());
            if (tmp < k)
            {
                bits[(int)num_count] = tmp;
                num_count++;
            }
            else
            {
                ok_nums++;
            }
        }

        pairs += ok_nums + ok_nums * num_count * 2;
        pairs += ok_nums * (ok_nums - 1);
        System.out.println("skipped pairs: " + pairs);
        System.out.println("    ok_nums + ok_nums * num_count * 2: " + (ok_nums + ok_nums * num_count * 2));
        System.out.println("    (int) (ok_nums * (ok_nums - 1)): " + ok_nums * (ok_nums - 1));

        bits = Arrays.copyOf(bits, (int) num_count);
        long so1 = System.currentTimeMillis();
        Arrays.sort(bits);
        long so2 = System.currentTimeMillis();
        System.out.println("(int) Math.ceil(k / 2): " + (int) Math.ceil(k / 2.0));
        int divider = bits.length;
        for (int i = 0; i < bits.length; i++)
        {
            if (bits[i] >= (int) Math.ceil(k / 2.0))
            {
                divider = i;
                break;
            }
        }
                //Arrays.binarySearch(bits, (int) Math.ceil(k / 2.0));

        ////System.out.println("binarySearch: " + divider);
        //if (divider < 0) divider = -divider - 1;
        long so3 = System.currentTimeMillis();
        //System.out.println("divider: " + divider);
        //System.out.println("bits: " + Arrays.toString(bits));


        long s2 = System.currentTimeMillis();
        System.out.println("Array Operations: " + (s2 - s1) + "ms.");
        System.out.println("    nums.length: " + nums.length);
        System.out.println("    numbers.size: " + numbers.size());
        System.out.println("    repeated nums excluded: " + (nums.length - numbers.size()));
        System.out.println("    num_count: " + num_count);
        System.out.println("    skipped nums: " + (numbers.size() - num_count));
        System.out.println();
        System.out.println("    sort time: " + (so2 - so1));
        System.out.println("    divider compute time: " + (so3 - so2));

        for (int i = 0; i < divider; i++)
        {
            //System.out.println("i: " + i);
            loop:
            for (int j = divider; j < bits.length; j++)
            {
                //System.out.println("j: " + j);
                if (bits[i] + bits[j] >= k)
                {
                    pairs += (bits.length - j) * 2;
                    //System.out.println("break loop");
                    break loop;
                }
            }
            //2568515816
            //1259249384
            //System.out.println("end of loop");
        }
        System.out.println("pairs: " + pairs);
        System.out.println("(bits.length - divider) * (bits.length - divider): " + ((long)(bits.length - divider) * (long)(bits.length - divider)));
        pairs += ((long)(bits.length - divider) * (long)(bits.length - divider));

        long s3 = System.currentTimeMillis();
        System.out.println("Pairing Operations: " + (s3 - s2) + "ms.");
        System.out.println("Bit Operations: " + bitOperations + "ms.");
        System.out.println("Total time: " + (s3 - s1) + "ms.");
        return pairs;
    }

    public int bits(int x)
    {
        long s1 = System.currentTimeMillis();
        int count = 0;
        while (x > 0)
        {
            count += x & 1;
            x = x >> 1;
        }
        bitOperations += (System.currentTimeMillis() - s1);
        return count;
        //double d = Math.log(x) / Math.log(2);
        //return x % 2 == 0 ? (int) (d + 1) : (int) Math.ceil(d);
    }
}

public class ExcellentPairs
{
    public static void main(String[] args)
    {
        int[] nums = null;
        try (Scanner in = new Scanner(new File("src/data5.dat")))
        {
            //in.next();
            String[] numsTokens = in.nextLine().split(",");

            nums = new int[numsTokens.length];

            for (int i = 0; i < numsTokens.length; i++)
            {
                nums[i] = Integer.parseInt(numsTokens[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //sort(new int[] {3,1,2,9,8,7,8,3,1,2,5,6,1,4,3,5,0});
        System.out.println(new Solution2().countExcellentPairs(nums, 17));
    }

    /**
     * Works like a sorted set but much slower
     */
    static void sort(int[] nums)
    {
        ArrayList<Integer> numbers = new ArrayList<>(nums.length);
        numbers.add(nums[0]);
        for (int i = 1; i < nums.length; i++)
        {
            loop:
            for (int j = 0; j < numbers.size(); j++)
            {
                if (numbers.get(j) == nums[i])
                {
                    break loop;
                }
                else if (numbers.get(j) > nums[i])
                {
                    numbers.add(j, nums[i]);
                    break loop;
                }
                else if (j == numbers.size() - 1) numbers.add(nums[i]);
            }
        }
        //System.out.println(numbers);
    }
}