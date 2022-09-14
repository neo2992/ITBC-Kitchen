import java.util.ArrayList;
import java.util.Objects;

public class Recipe implements Priceable {

    private String name;
    private ArrayList<WeightedIngredient> ingredients;
    private RecipeDifficulty recipeDifficulty;


    public Recipe (String name, RecipeDifficulty recipeDifficulty) {
        this.name = name;
        ingredients = new ArrayList<>();
        this.recipeDifficulty = recipeDifficulty;
    }

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        double sumOffIngredientPrices = 0;
        for (var ingredient : ingredients) {
            sumOffIngredientPrices += ingredient.getPrice();
        }
        return sumOffIngredientPrices;
    }

    public boolean inRecipe(WeightedIngredient ingredient) {
        for (var item : ingredients) {
            if (ingredient.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addIngredient(WeightedIngredient ingredient) {
        if (!inRecipe(ingredient)) {
            ingredients.add(ingredient);
        }
    }

    public void removeIngredient(WeightedIngredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void getScaledRecipe(double scale) {
        for (var ingredient : ingredients) {
            //ingredient.weight *= (scale / 100);
            ingredient.setWeight(ingredient.getWeight() * (scale / 100));
        }
    }

    public void getRescaledRecipe(double scale) {
        for (var ingredient : ingredients) {
            ingredient.setWeight(ingredient.getWeight() * (100 / scale));
        }
    }

    public ArrayList<WeightedIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<WeightedIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public  void setIngredientsWeight(WeightedIngredient ingredient, double weight) {
            ingredient.setWeight(weight);
    }

    public void setIngredientPricePerUnit(WeightedIngredient ingredient, double pricePerUnit) {
        ingredient.setPricePerUnit(pricePerUnit);
    }

    public RecipeDifficulty getRecipeDifficulty() {
        return recipeDifficulty;
    }

    public int numOfIngredients() {
        int numOfIngredients = 0;
        for (var ingredient : ingredients) {
            numOfIngredients++;
        }
        return numOfIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(name, recipe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }

    public void printRecipe(Recipe recipe) {
        System.out.println("Recipe - " + recipe.getName());
        for (var ingredient : ingredients) {
            System.out.println(ingredient);
        }
        System.out.print("\n");
    }

    @Override
    public String toString() {
        return "Recipe - " + name + "\n" + ingredients + "\n";
    }
}