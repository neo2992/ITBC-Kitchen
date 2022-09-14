import java.util.ArrayList;

public class Fridge {

    private static ArrayList<WeightedIngredient> ingredients;

    public Fridge() {
        ingredients = new ArrayList<>();
    }

    public ArrayList<WeightedIngredient> getIngredients() {
        return ingredients;
    }

    public WeightedIngredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public WeightedIngredient getIngredient(WeightedIngredient ingredient) {
        for (var item : ingredients) {
            if (item.equals(ingredient)) {
                return item;
            }
        }
        return null;
    }

    public boolean inFridge(WeightedIngredient ingredient) {
        for (var item : ingredients) {
            if (ingredient.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void addIngredient(WeightedIngredient ingredient) {
        if (!inFridge(ingredient)) {
            ingredients.add(ingredient);
        } else {
            for (var item : ingredients) {
                if (ingredient.equals(item)) {
                    item.setWeight(ingredient.getWeight() + item.getWeight());
                }
            }
        }
    }

    public void removeIngredient(WeightedIngredient ingredient) {
        ingredients.removeIf(item -> item.equals(ingredient));
        System.out.println("Ingredient removed.");
    }

    public void decreaseWeight(WeightedIngredient ingredient) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).equals(ingredient) && ingredients.get(i).getWeight() > ingredient.getWeight()) {
                ingredients.get(i).setWeight(ingredients.get(i).getWeight() - ingredient.getWeight());
                System.out.println(ingredient.getName() + " weight decreased.");
            } else if (ingredients.get(i).equals(ingredient) && ingredients.get(i).getWeight() < ingredient.getWeight()) {
                System.out.println("Cant decrease by that amount.");
            } else if (ingredients.get(i).equals(ingredient) && ingredients.get(i).getWeight() == ingredient.getWeight()) {
                removeIngredient(ingredient);
                i--;
            }
        }
        /*for (var item : ingredients) {
            if (item.equals(ingredient) && item.getWeight() > ingredient.getWeight()) {
                item.setWeight(item.getWeight() - ingredient.getWeight());
                System.out.println(ingredient.getName() + " weight decreased.");
            } else if (item.equals(ingredient) && item.getWeight() < ingredient.getWeight()) {
                System.out.println("Cant decrease weight by that amount.");
            } else if (item.equals(ingredient) && item.getWeight() == ingredient.getWeight()) {
                ingredients.remove(item);
                System.out.println("Ingredient removed.");
            }
        }*/
    }

    public boolean fridgeEmpty() {
        if (getIngredients().isEmpty()) {
            System.out.println("Fridge is empty.");
            return true;
        }
        return false;
    }

    public boolean canMakeRecipe(Recipe recipe) {
        int count = recipe.numOfIngredients();
        for (var item : ingredients) {
            for (var recipeItem : recipe.getIngredients()) {
                if (item.equals(recipeItem) && item.getWeight() >= recipeItem.getWeight()) {
                    count--;
                    break;
                }
            }
        }
        return count == 0;
    }

    public void makeRecipe(Recipe recipe) {
        if (canMakeRecipe(recipe)) {
            /*for (var item : ingredients) {
                if (recipe.inRecipe(item)) {
                    item.weight = item.weight - recipe.getIngredientWeight(item.getId());
                }
            }*/
            for (var item : ingredients) {
                for (var recipeItem : recipe.getIngredients()) {
                    if (item.equals(recipeItem)) {
                        item.setWeight(item.getWeight() - recipeItem.getWeight());
                        break;
                    }
                }
            }
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).getWeight() == 0) {
                    ingredients.remove(i);
                    i--;
                }
            }
        } else {
            System.out.println("Can't make this meal, not enough ingredients.");
        }
    }

    public static void setIngredients(ArrayList<WeightedIngredient> ingredients) {
        Fridge.ingredients = ingredients;
    }

    public void printIngredients() {
        for (var ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

    @Override
    public String toString() {
        return "Fridge: " + ingredients;
    }
}
