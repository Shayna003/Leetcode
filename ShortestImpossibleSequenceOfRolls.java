import java.util.BitSet;

public class ShortestImpossibleSequenceOfRolls
{
    public static void main(String[] args)
    {
        int[] rolls = {1,1,2,2};
        int k = 2;
        System.out.println(shortestSequence(rolls, k));
    }

    public static int shortestSequence(int[] rolls, int k)
    {
        int sequence = 1;
        int counter = 0;
        int[] occurrences = new int[k];

        for (int i = 0; i < rolls.length; i++)
        {
            if (occurrences[i] == sequence - 1)
            {
                occurrences[i]++;
                counter++;
                if (counter % k == 0)
                {
                    sequence++;
                }
            }
        }
        return sequence;
    }
}
