import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class FoodRatings
{
    HashMap<String, Food> allFoods;
    HashMap<String, TreeSet<Food>> allCuisines;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings)
    {
        allFoods = new HashMap<>(foods.length);
        allCuisines = new HashMap<>(foods.length);

        for (int i = 0; i < foods.length; i++)
        {
            Food f = new Food(foods[i], cuisines[i], ratings[i]);
            allFoods.put(foods[i], f);
            allCuisines.putIfAbsent(cuisines[i], new TreeSet<>(Comparator.comparing(a -> ((Food)a).rating).reversed().thenComparing(a -> ((Food)a).name)));
            allCuisines.get(cuisines[i]).add(f);
        }
    }

    public void changeRating(String food, int newRating)
    {
        Food f = allFoods.get(food);
        TreeSet s = allCuisines.get(f.cuisine);
        s.remove(f);
        f.rating = newRating;
        s.add(f);
    }

    public String highestRated(String cuisine)
    {
        return allCuisines.get(cuisine).first().name;
    }
}


class Food
{
    String name;
    String cuisine;
    int rating;
    public Food(String name, String cuisine, int rating)
    {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}
/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */