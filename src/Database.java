import javax.xml.crypto.Data;
import java.util.*;

public abstract class Database {
     static HashMap<String, WeightedIngredient> ingredients = new HashMap<>();
     static HashMap<String, Recipe> recipes = new HashMap<>();

    public static void allIngredients() {
        var bacon = new WeightedIngredient("bacon", 1, 800);
        ingredients.put("bacon", bacon);

        var egg = new WeightedIngredient("egg", 1, 15);
        ingredients.put("egg", egg);

        var milk = new WeightedIngredient("milk", 1, 80);
        ingredients.put("milk", milk);

        var potato = new WeightedIngredient("potato", 1, 80);
        ingredients.put("potato", potato);

        var sausage = new WeightedIngredient("sausage", 1, 300);
        ingredients.put("sausage", sausage);

        var macaroni = new WeightedIngredient("macaroni", 1, 120);
        ingredients.put("macaroni", macaroni);

        var cheese = new WeightedIngredient("cheese", 1, 400);
        ingredients.put("cheese", cheese);

        var butter = new WeightedIngredient("butter", 1, 250);
        ingredients.put("butter", butter);

        var apple = new WeightedIngredient("apple", 1, 100);
        ingredients.put("apple", apple);

        var mushroom = new WeightedIngredient("mushroom", 1, 180);
        ingredients.put("mushroom", mushroom);

        var olive = new WeightedIngredient("olive", 1, 500);
        ingredients.put("olive", olive);

        var onion = new WeightedIngredient("onion", 1, 40);
        ingredients.put("onion", onion);

        var spinach = new WeightedIngredient("spinach", 1, 15);
        ingredients.put("spinach", spinach);

        var chicken = new WeightedIngredient("chicken", 1, 400);
        ingredients.put("chicken", chicken);

        var carrot = new WeightedIngredient("carrot", 1, 50);
        ingredients.put("carrot", carrot);

        var beans = new WeightedIngredient("beans", 1, 250);
        ingredients.put("beans", beans);

        var strawberry = new WeightedIngredient("strawberry", 1, 600);
        ingredients.put("strawberry", strawberry);

        var chocolate = new WeightedIngredient("chocolate", 1, 1000);
        ingredients.put("chocolate", chocolate);
    }

    public static void allRecipes() {

        recipes.put("Mac & Cheese", new Recipe("Mac & Cheese", RecipeDifficulty.BEGINNER));
        recipes.get("Mac & Cheese").addIngredient(new WeightedIngredient(ingredients.get("macaroni").getName(), 1, ingredients.get("macaroni").getPricePerUnit()));
        recipes.get("Mac & Cheese").addIngredient(new WeightedIngredient(ingredients.get("cheese").getName(), 0.4, ingredients.get("cheese").getPricePerUnit()));

        recipes.put("Scrambled Eggs", new Recipe("Scrambled Eggs", RecipeDifficulty.EASY));
        recipes.get("Scrambled Eggs").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 4, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Scrambled Eggs").addIngredient(new WeightedIngredient(ingredients.get("milk").getName(), 0.2, ingredients.get("milk").getPricePerUnit()));

        recipes.put("Bacon & Eggs", new Recipe("Bacon & Eggs", RecipeDifficulty.EASY));
        recipes.get("Bacon & Eggs").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 6, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Bacon & Eggs").addIngredient(new WeightedIngredient(ingredients.get("bacon").getName(), 0.4, ingredients.get("bacon").getPricePerUnit()));

        recipes.put("Lasagna", new Recipe("Lasagna", RecipeDifficulty.HARD));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 3, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("onion").getName(), 0.3, ingredients.get("onion").getPricePerUnit()));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("cheese").getName(), 0.4, ingredients.get("cheese").getPricePerUnit()));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("spinach").getName(), 0.5, ingredients.get("spinach").getPricePerUnit()));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("milk").getName(), 0.5, ingredients.get("milk").getPricePerUnit()));
        recipes.get("Lasagna").addIngredient(new WeightedIngredient(ingredients.get("bacon").getName(), 0.6, ingredients.get("bacon").getPricePerUnit()));

        recipes.put("Pizza", new Recipe("Pizza", RecipeDifficulty.PRO));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 4, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("onion").getName(), 0.2, ingredients.get("onion").getPricePerUnit()));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("cheese").getName(), 0.3, ingredients.get("cheese").getPricePerUnit()));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("mushroom").getName(), 0.5, ingredients.get("mushroom").getPricePerUnit()));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("butter").getName(), 0.5, ingredients.get("butter").getPricePerUnit()));
        recipes.get("Pizza").addIngredient(new WeightedIngredient(ingredients.get("bacon").getName(), 0.4, ingredients.get("bacon").getPricePerUnit()));

        recipes.put("Mashed Potatoes", new Recipe("Mashed Potatoes", RecipeDifficulty.MEDIUM));
        recipes.get("Mashed Potatoes").addIngredient(new WeightedIngredient(ingredients.get("milk").getName(), 0.3, ingredients.get("milk").getPricePerUnit()));
        recipes.get("Mashed Potatoes").addIngredient(new WeightedIngredient(ingredients.get("potato").getName(), 2, ingredients.get("potato").getPricePerUnit()));
        recipes.get("Mashed Potatoes").addIngredient(new WeightedIngredient(ingredients.get("butter").getName(), 0.2, ingredients.get("butter").getPricePerUnit()));
        recipes.get("Mashed Potatoes").addIngredient(new WeightedIngredient(ingredients.get("sausage").getName(), 0.8, ingredients.get("sausage").getPricePerUnit()));

        recipes.put("Chicken Nuggets", new Recipe("Chicken Nuggets", RecipeDifficulty.BEGINNER));
        recipes.get("Chicken Nuggets").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 1, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Chicken Nuggets").addIngredient(new WeightedIngredient(ingredients.get("chicken").getName(), 1.2, ingredients.get("chicken").getPricePerUnit()));

        recipes.put("Bean Stew", new Recipe("Bean Stew", RecipeDifficulty.HARD));
        recipes.get("Bean Stew").addIngredient(new WeightedIngredient(ingredients.get("beans").getName(), 1, ingredients.get("beans").getPricePerUnit()));
        recipes.get("Bean Stew").addIngredient(new WeightedIngredient(ingredients.get("carrot").getName(), 0.5, ingredients.get("carrot").getPricePerUnit()));
        recipes.get("Bean Stew").addIngredient(new WeightedIngredient(ingredients.get("onion").getName(), 0.4, ingredients.get("onion").getPricePerUnit()));
        recipes.get("Bean Stew").addIngredient(new WeightedIngredient(ingredients.get("chicken").getName(), 0.7, ingredients.get("chicken").getPricePerUnit()));

        recipes.put("Apple Pie", new Recipe("Apple Pie", RecipeDifficulty.MEDIUM));
        recipes.get("Apple Pie").addIngredient(new WeightedIngredient(ingredients.get("apple").getName(), 8, ingredients.get("apple").getPricePerUnit()));
        recipes.get("Apple Pie").addIngredient(new WeightedIngredient(ingredients.get("butter").getName(), 0.2, ingredients.get("butter").getPricePerUnit()));
        recipes.get("Apple Pie").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 1, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Apple Pie").addIngredient(new WeightedIngredient(ingredients.get("milk").getName(), 0.6, ingredients.get("milk").getPricePerUnit()));

        recipes.put("Strawberry Cake", new Recipe("Strawberry Cake", RecipeDifficulty.PRO));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("egg").getName(), 5, ingredients.get("egg").getPricePerUnit()));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("strawberry").getName(), 1.2, ingredients.get("strawberry").getPricePerUnit()));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("cheese").getName(), 0.3, ingredients.get("cheese").getPricePerUnit()));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("milk").getName(), 1.4, ingredients.get("milk").getPricePerUnit()));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("butter").getName(), 0.3, ingredients.get("butter").getPricePerUnit()));
        recipes.get("Strawberry Cake").addIngredient(new WeightedIngredient(ingredients.get("chocolate").getName(), 0.2, ingredients.get("chocolate").getPricePerUnit()));
    }

    public static ArrayList<Recipe> printAllRecipes(ArrayList <Recipe> allRecipes) {
        allRecipes = new ArrayList<>();
        for (var el : recipes.entrySet()) {
            allRecipes.add(el.getValue());
        }
        return allRecipes;
    }

    public static boolean recipeInDatabase(String name) {
        for (var el : recipes.entrySet()) {
            if (el.getValue().getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static void printRecipesInFridge(Fridge fridge) {
        for (var el: recipes.entrySet()) {
            if (fridge.canMakeRecipe(el.getValue())) {
                el.getValue().printRecipe(el.getValue());
            }
        }
    }
}

