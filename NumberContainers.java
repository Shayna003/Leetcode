import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

class NumberContainers
{
    public static final int MAX = (int) (Math.pow(10, 9) + 1);
    //ArrayList<Number> numbers;
    ArrayList<Index> indexes;
    HashMap<Integer, TreeSet<Index>> numbers;
    long binsearchTime;
    long findTime;
    long changeTime;
    long finds;
    long changes;

    interface ValueObject
    {
        int value();
    }

    static class Index implements Comparable
    {
        int value;
        int number;

        public Index(int value, int number)
        {
            this.value = value;
            this.number = number;
        }
        //@Override
        //public int value() { return value; }

        @Override
        public String toString() { return String.valueOf(value); }

        @Override
        public int compareTo(Object o)
        {
            return value - ((Index) o).value;
        }
        /*public boolean equals(Object obj)
        {
            return value == (Integer) obj;
        }*/
    }

    public int binSearch(ArrayList<Index> list, int value)
    {
        if (list.isEmpty()) return -1;

        long start = System.currentTimeMillis();

        //System.out.println("bin search " + value + " in " + list);
        int first = 0;
        int last = list.size() - 1;
        int middle = (last - first) / 2;
        int middleValue;

        while (first <= last)
        {
            middleValue = list.get(middle).value;

            if (middleValue == value)
            {
                //System.out.println("    return " + middle);
                return middle;
            }
            else if (middleValue < value)
            {
                first = middle + 1;
                if (first > last)
                {
                    //System.out.println("    2expected " + (last + 1));
                    return -last -2;
                }
            }
            else if (middleValue > value)
            {
                last = middle - 1;
                if (first > last)
                {
                    //System.out.println("    4expected " + (first));
                    return -first - 1;
                }
            }
            middle = (first + last) / 2;
        }
        binsearchTime += System.currentTimeMillis() - start;
        //System.out.println("binsearch time for list size " + list.size() + ": " + (System.currentTimeMillis() - start) + "ms.");
        //if (middle < 0) middle = 0;
        //if (middle > list.size()) middle = list.size();
        System.out.println("error");
        return -middle - 1;
    }

    public NumberContainers()
    {
        //numbers = new ArrayList<>(MAX / 1000);
        indexes = new ArrayList<>((int) Math.pow(10, 5) + 1);
        numbers = new HashMap<>((int) Math.pow(10, 5) + 1);
    }

    public void change(int index, int number)
    {
        changes++;
        long start = System.currentTimeMillis();
        int i = binSearch(indexes, index);
        Index idx;
        if (i >= 0)
        {
            idx = indexes.get(i);
            if (number != idx.number)
            {
                numbers.get(idx.number).remove(idx);
                if (numbers.get(idx.number).isEmpty())
                {
                    numbers.remove(number);
                }

                idx.number = number;
                numbers.putIfAbsent(number, new TreeSet<>());
                numbers.get(number).add(idx);
            }
        }
        else
        {
            idx = new Index(index, number);
            numbers.putIfAbsent(number, new TreeSet<>());
            numbers.get(number).add(idx);
            indexes.add(-i - 1, idx);
        }

        changeTime += System.currentTimeMillis() - start;
    }

    int findPosition(ArrayList<? extends ValueObject> list, int value)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (value < list.get(i).value())
            {
                return i;
            }
        }
        return list.size();
    }

    public int find(int number)
    {
        finds++;
        long start = System.currentTimeMillis();

        TreeSet<Index> ts = numbers.get(number);
        if (ts == null || ts.isEmpty())
        {
            findTime += System.currentTimeMillis() - start;
            return -1;
        }
        /*for (int i = 0; i < indexes.size(); i++)
        {
            if (indexes.get(i).number == number)
            {
                findTime += System.currentTimeMillis() - start;
                return i;
            }
        }*/
        findTime += System.currentTimeMillis() - start;
        return ts.first().value;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
class Mains
{
    static boolean contains(int index, ArrayList<NumberContainers.Index> indexes)
    {
        for (NumberContainers.Index i : indexes)
        {
            if (i.value == index) return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        NumberContainers n = new NumberContainers();
        ArrayList<NumberContainers.Index> l = new ArrayList<>();
        //l.add(new NumberContainers.Index(1, -1));
        //l.add(new NumberContainers.Index(3, -1));
        for (int i = 0; i < 200000; i++)
        {
            int tmp = (int)(Math.random() * 20000);
            int index = n.binSearch(l, tmp);

            if (index < 0)
            {
                if (contains(tmp, l))
                {
                    throw new RuntimeException(tmp + " is already present in list, but binSearch returned " + index + "!");
                }
                l.add(-index - 1, new NumberContainers.Index(tmp, -1));
            }
        }
        System.out.println(l);
        boolean ok = true;
        for (int i = 1; i < l.size(); i++)
        {
            if (l.get(i).value < l.get(i - 1).value)
            {
                ok = false;
                System.out.println("ERROR!");
            }
        }
        System.out.println("ok? " + ok);
    }

    public static void main2(String[] args)
    {
        long start = System.currentTimeMillis();
        NumberContainers n = new NumberContainers();
        System.out.println("init time: " + (System.currentTimeMillis() - start));
        int max = (int) Math.pow(10, 5) + 1;

        for (int i = 0; i < max; i++)
        {
            n.change((int) (Math.random() * max), (int) (Math.random() * max));
            n.find((int) (Math.random() * max));
        }

        //System.out.println("numbers.size: " + n.numbers.size());
        System.out.println("indexes.size: " + n.indexes.size());
        //System.out.println("numbers: ");
        //System.out.println(n.numbers);
        //System.out.println("indexes: ");
        //System.out.println(n.indexes);

        System.out.println("total run time: " + (System.currentTimeMillis() - start) + "ms.");
        System.out.println("binsearch time: " + n.binsearchTime);
        System.out.println("change time: " + n.changeTime + " for " + n.changes + " changes, avg: " + (n.changeTime / (double) n.changes));
        System.out.println("find time: " + n.findTime + " for " + n.finds + " changes, avg: " + (n.findTime / (double) n.finds));
    }
}