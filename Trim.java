import java.util.Arrays;
import java.util.Comparator;

public class Trim implements Comparator<Object>
{
    public static void main(String[] args)
    {
        String[] n = {"64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"};
        int[][] q = {{9,4},{6,1},{3,8},{12,9},{11,4},{4,9},{2,7},{10,3},{13,1},{13,1},{6,1},{5,10}};
        //String[] nums = {"102","473","251","814"};
        //int[][] queries = {{1,1},{2,3},{4,2},{1,2}};
        //{"9415","5908","1840","5307"};
        //{{3,2},{2,2},{3,3},{1,3}};
        System.out.println(Arrays.toString(smallestTrimmedNumbers(n, q)));
    }

    public static int[] smallestTrimmedNumbers(String[] nums, int[][] queries)
    {
        int[] answer = new int[queries.length];
        StringNumber[] tmp = new StringNumber[nums.length];

        for (int i = 0; i < queries.length; i++)
        {
            for (int j = 0; j < nums.length; j++)
            {
                tmp[j] = new StringNumber(nums[j].substring(nums[j].length() - queries[i][1]), j);;
            }
            Arrays.sort(tmp, new Trim().thenComparingInt(o -> ((StringNumber) o).index));
            System.out.println(Arrays.toString(tmp));
            answer[i] = tmp[queries[i][0] - 1].index;
        }
        return answer;
    }

    /**
     * Comparing 2 very long strings as numbers
     */
    public int compare(Object o1, Object o2)
    {
        String s1 = ((StringNumber) o1).str;
        String s2 = ((StringNumber) o2).str;
        if (s1.equals(s2)) return 0;

        int tmp;
        for (int i = 0; i < s1.length(); i++)
        {
            tmp = s1.charAt(i) - s2.charAt(i);
            if (tmp != 0) return tmp;
        }
        return 0;
    }

    public static int getDigit(int num, int d)
    {
        num = (int) (num / Math.pow(10, d - 1));
        double digit = num / 10.0 - (int) (num / 10.0);
        return (int) Math.round(digit * 10);
    }
}

class StringNumber
{
    public String str;
    public int index;

    public StringNumber(String str, int index)
    {
        this.str = str;
        this.index = index;
    }

    public String toString()
    {
        return ("\"" + str + "\"|" + index);
    }
}
