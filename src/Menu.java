import java.util.*;
import java.util.stream.Collectors;

public abstract class Menu {

    static Scanner sc = new Scanner(System.in);
    static String input;
    static Fridge fridge = new Fridge();
    static WeightedIngredient ingredient;
    static ArrayList <Recipe> recipes;


    static {
        Database.allIngredients();
        Database.allRecipes();
        System.out.println("Welcome to FridgeApp!");
    }

    public static void menuStart() {
        System.out.println("-".repeat(40) + "\nPick an option:" +
                "\n0 - exit application" +
                "\n1 - open fridge" +
                "\n2 - add ingredient to the fridge" +
                "\n3 - remove/decrease ingredient in fridge" +
                "\n4 - check meals you can make" +
                "\n5 - make a meal" +
                "\n6 - scale and make a meal" +
                "\n7 - check meals you can make for a price of your choice" +
                "\n8 - check meals you can make of your chosen difficulty" +
                "\n9 - check meals you can make for a price and difficulty of your choice" +
                "\n10 - sort all meals by price" +
                "\n11 - sort all meals by difficulty" +
                "\n12 - manage your favorite recipes");

    do {
        input = sc.nextLine();
        switch(input) {
            case "0":
                System.out.println("Application terminated"); System.exit(0); break;
            case "1": peekFridgeMenu(); break;
            case "2": addIngredientMenu(); break;
            case "3": removeIngredientMenu(); break;
            case "4": canMakeRecipeMenu(); break;
            case "5": makeRecipe(); break;
            case "6": getScaledRecipesMenu(); break;
            case "7": makeRecipeByPriceMenu(); break;
            case "8": getRecipesByDifficultyMenu(); break;
            case "9": getRecipesByPriceAndDifficultyMenu(); break;
            case "10": sortByPriceMenu(); break;
            case "11": sortByDifficultyMenu(); break;
            case "12": FavoriteMenu.favoriteRecipesMenu(); break;
            default:
                System.out.println("Wrong input, try again");
                break;
        }
    }
        while (!input.equals("0"));
    }

    public static void peekFridgeMenu() {
        if (!fridge.fridgeEmpty()) {
            fridge.printIngredients();
        }
        menuStart();
    }

    public static void addIngredientMenu() {
        System.out.println("Enter ingredient you want to add to your fridge or 0 to go back to main menu: ");
        ingredient = new WeightedIngredient();
        ingredient.setName(sc.nextLine());

        if (ingredient.getName().equals("0")) {
            menuStart();
        }

        if (!ingredient.equals(Database.ingredients.get(ingredient.getName()))) {
            System.out.println("Unknown ingredient. Type ingredient in English singular.");
            menuStart();
        }

        System.out.println("Enter ingredient weight: ");
        try {
            ingredient.setWeight(Double.parseDouble(sc.nextLine()));
            if (ingredient.getWeight() <= 0) {
                System.out.println("Can't add that amount.");
                menuStart();
            }
        } catch (Exception e) {
            System.out.println("Wrong input.");
            menuStart();
        }

        fridge.addIngredient(ingredient);
        ingredient.setPricePerUnit(Database.ingredients.get(ingredient.getName()).getPricePerUnit());
        System.out.println("Ingredient added to your fridge.");
        menuStart();
    }

    public static void removeIngredientMenu() {
        ingredient = new WeightedIngredient();
        if (fridge.fridgeEmpty()) {
            menuStart();
        }

        System.out.println("Enter ingredient name to remove/decrease or type 0 to exit to main menu");
        fridge.printIngredients();
        ingredient.setName(sc.nextLine());
        if (ingredient.getName().equals("0")) {
            menuStart();
        }

        if (fridge.inFridge(ingredient)) {
        System.out.println("Enter 1 - to completely remove ingredient, 2 - to decrease ingredient weight or 0 - to return to main menu");
        input = sc.nextLine();
            switch (input) {
                case "0": menuStart(); break;
                case "1":
                    fridge.removeIngredient(ingredient);
                    break;
                case "2":
                    System.out.println("Ingredient weight is: " + fridge.getIngredient(ingredient).getWeight() + ". Enter weight you would like to remove.");
                    try {
                        ingredient.setWeight(Double.parseDouble(sc.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Wrong input.");
                        menuStart();
                    }
                    if (ingredient.getWeight() >= 0) {
                            fridge.decreaseWeight(ingredient);
                        } else {
                            System.out.println("Cant decrease weight by that amount.");
                        }
                    break;
                default:
                    System.out.println("Wrong input.");
                    menuStart();
                    break;
            }
        } else {
            System.out.println("No ingredient with that name in fridge.");
        }
        menuStart();
    }

    public static void canMakeRecipeMenu() {
        recipes = new ArrayList<>();
        for (var el : Database.recipes.entrySet()) {
            if (fridge.canMakeRecipe(el.getValue())) {
                recipes.add(el.getValue());
            }
        }

        if (!recipes.isEmpty()) {
            for (var recipe : recipes) {
                recipe.printRecipe(recipe);
            }
        } else {
            System.out.println("You cant make any meal, you don't have enough ingredients.");
        }
        menuStart();
    }

    public static ArrayList<Recipe> canMakeRecipe() {
        recipes = new ArrayList<>();
        for (var el : Database.recipes.entrySet()) {
            if (fridge.canMakeRecipe(el.getValue())) {
                recipes.add(el.getValue());
            }
        }
        return recipes;
    }

    public static void makeRecipe() {
        recipes = new ArrayList<>();
        canMakeRecipe();
        boolean mealMade = false;


        if (canMakeRecipe().isEmpty()) {
            System.out.println("You cant make any meal, you don't have enough ingredients.");
            menuStart();
        }

        System.out.println("Enter meal you would like to make or enter 0 to exit to main menu: \n");
        Database.printRecipesInFridge(fridge);
        Recipe recipe = new Recipe(sc.nextLine());
        if (recipe.getName().equals("0")) {
            menuStart();
        }

        for (var el : Database.recipes.entrySet()) {
            if (recipe.getName().equalsIgnoreCase(el.getValue().getName())) {
                recipe.setIngredients(el.getValue().getIngredients());
                fridge.makeRecipe(recipe);
                System.out.println("You made " + el.getValue().getName() + ". Bon Appetit!");
                mealMade = true;
                break;
            }
        }

        if (!mealMade) {
            System.out.println("Cant make that recipe.");
        }
        menuStart();
    }

    public static void getScaledRecipesMenu() {
        recipes = new ArrayList<>();
        System.out.println("Enter scale for you meal (for example, enter 50 for half-sized meal) or enter 0 to exit to main menu: ");
        String scale = sc.nextLine();

        if (scale.equals("0")) {
            menuStart();
        }

        try {
            for (var el : Database.recipes.entrySet()) {
                el.getValue().getScaledRecipe(Double.parseDouble(scale));
                if (fridge.canMakeRecipe(el.getValue())) {
                    recipes.add(el.getValue());
                }
            }
        }  catch (Exception e) {
            System.out.println("Wrong input.");
            menuStart();
        }

        if (recipes.isEmpty()) {
            System.out.println("You cant make any meal, you don't have enough ingredients.");
            for (var el : Database.recipes.entrySet()) {
                el.getValue().getRescaledRecipe(Double.parseDouble(scale));
            }
            menuStart();
        }

        Database.printRecipesInFridge(fridge);
        System.out.println("Would you like to make some of scaled meals? \nEnter 1 to make a meal or 0 to exit to main menu: ");
        input = sc.nextLine();

        if (input.equals("0")) {
            for (var el : Database.recipes.entrySet()) {
                el.getValue().getRescaledRecipe(Double.parseDouble(scale));
            }
            menuStart();
        } else {
            if (input.equals("1")) {
                System.out.println("What meal would you like to make: ");
                Recipe scaledRecipe = new Recipe(sc.nextLine());

                for (var recipe : recipes) {
                    if (scaledRecipe.getName().equalsIgnoreCase(recipe.getName())) {
                        System.out.println("You made " + recipe.getName() + ". Bon Appetit");
                        fridge.makeRecipe(recipe);
                    }
                }

                for (var el : Database.recipes.entrySet()) {
                    el.getValue().getRescaledRecipe(Double.parseDouble(scale));
                }
            } else {
                System.out.println("Wrong input. Try again: ");
                for (var el : Database.recipes.entrySet()) {
                    el.getValue().getRescaledRecipe(Double.parseDouble(scale));
                }
            }
            menuStart();
        }
    }

    public static void makeRecipeByPriceMenu() {
        recipes = new ArrayList<>();
        if (fridge.fridgeEmpty()) {
            menuStart();
        }

        System.out.println("Enter price for your meal or enter 0 to exit to main menu: ");
        String price = sc.nextLine();
        if (price.equals("0")) {
            menuStart();
        }
        try {
            for (var el : Database.recipes.entrySet()) {
                if (el.getValue().getPrice() <= Double.parseDouble(price) && fridge.canMakeRecipe(el.getValue())) {
                    recipes.add(el.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong input.");
            menuStart();
        }

        if (!recipes.isEmpty()) {
            System.out.println("Meals for " + price + " rsd are:");
            for (var recipe : recipes) {
                System.out.println("Recipe - " + recipe.getName() + ", recipe price - " + recipe.getPrice());
            }
        } else {
            System.out.println("You cant make any meal, you don't have enough ingredients.");
        }
        menuStart();
    }

    public static void getRecipesByDifficultyMenu() {
        recipes = new ArrayList<>();
        if (fridge.fridgeEmpty()) {
            menuStart();
        }

        System.out.println("Enter recipe difficulty or enter 0 to exit to main menu: ");
        String difficulty = sc.nextLine();

        if (difficulty.equals("0")) {
            menuStart();
        }

        for (var el : Database.recipes.entrySet()) {
                recipes.add(el.getValue());
            }

        canMakeRecipe();

        List<Recipe> recipesByDifficulty = recipes.stream().
                filter(recipe -> recipe.getRecipeDifficulty().
                        equals(RecipeDifficulty.valueOf(difficulty.toUpperCase()))).toList();

        if (!recipes.isEmpty()) {
            for (var recipe : recipesByDifficulty) {
                recipe.printRecipe(recipe);
            }
        } else {
            System.out.println("You cant make any meal of that difficulty.");
        }
        menuStart();
    }

    public static void getRecipesByPriceAndDifficultyMenu() {
        recipes = new ArrayList<>();

        if (fridge.fridgeEmpty()) {
            menuStart();
        }

        System.out.println("Enter price for your meal or enter 0 to exit to main menu: ");
        try {
            double price = sc.nextDouble();
            sc.nextLine();

            if (price == 0) {
                menuStart();
            }

            System.out.println("Enter recipe difficulty or enter 0 to exit to main menu: ");
            String difficulty = sc.nextLine();

            if (difficulty.equals("0")) {
                menuStart();
            }

            for (var el : Database.recipes.entrySet()) {
                if (el.getValue().getPrice() <= price && fridge.canMakeRecipe(el.getValue())) {
                    recipes.add(el.getValue());
                }
            }

            List<Recipe> recipesByDifficultyAndPrice = recipes.stream().
                    filter(recipe -> recipe.getRecipeDifficulty().
                            equals(RecipeDifficulty.valueOf(difficulty.toUpperCase()))).toList();

            if (recipesByDifficultyAndPrice.isEmpty()) {
                System.out.println("You cant make any meal, not enough ingredients.");
            } else {
                System.out.println("Meals for " + price + " rsd and of " + difficulty.toUpperCase() + " difficulty are:");
                for (var recipe : recipesByDifficultyAndPrice) {
                    System.out.println("Recipe - " + recipe.getName() + ", recipe price - " + recipe.getPrice());
                }
            }
            menuStart();
        } catch (Exception e) {
            System.out.println("Wrong input.");
            menuStart();
        }
    }

    public static void sortByPriceMenu() {
        recipes = new ArrayList<>();

        for (var el : Database.recipes.entrySet()) {
            recipes.add(el.getValue());
        }

        List<Recipe> sorted = recipes.stream().sorted(Comparator.comparing(Recipe::getPrice)).toList();

        for (var recipe : sorted) {
            recipe.printRecipe(recipe);
            System.out.println("recipe price - " + recipe.getPrice() + "\n");
        }
        menuStart();
    }

    public static void sortByDifficultyMenu() {
        recipes = new ArrayList<>();

        for (var el : Database.recipes.entrySet()) {
            recipes.add(el.getValue());
        }

        List<Recipe> sorted = recipes.stream().sorted(Comparator.comparing(Recipe::getRecipeDifficulty)).collect(Collectors.toList());

        for (var recipe : sorted) {
            recipe.printRecipe(recipe);
            System.out.println("recipe difficulty - " + recipe.getRecipeDifficulty() + "\n");
        }
        menuStart();
    }
}
