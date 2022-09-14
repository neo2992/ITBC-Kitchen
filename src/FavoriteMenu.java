import java.util.ArrayList;

public abstract class FavoriteMenu extends Menu {
    private static final ArrayList<Recipe> favoriteRecipes = new ArrayList<>();

    public static void favoriteRecipesMenu() {
        System.out.println("-".repeat(40) +
                "\nWhat would you like to do with your favorite recipes:" +
                "\n0 - return to main menu" +
                "\n1 - see list of your favorite recipes" +
                "\n2 - add new favorite recipe" +
                "\n3 - remove recipe from your favorite recipes" +
                "\n4 - pogledaj svoje omiljene recepte do odredjenog iznosa");

        do {
            input = sc.nextLine();
            switch(input) {
                case "0": Menu.menuStart(); break;
                case "1": peekFavoriteRecipesMenu(); break;
                case "2": addFavoriteRecipeMenu(); break;
                case "3": removeFavoriteRecipeMenu(); break;
                case "4": getFavoriteRecipesByPriceMenu(); break;
                default:
                    System.out.println("Wrong input, try again.");
                    break;
            }
        } while (!input.equals("0"));
    }
    public static void peekFavoriteRecipesMenu() {
        if (favoriteRecipes.isEmpty()) {
            System.out.println("No favorite recipe.");
        } else {
            for (var el : favoriteRecipes) {
                el.printRecipe(el);
                //System.out.println(el);
            }
        }
        favoriteRecipesMenu();
    }

    public static void addFavoriteRecipeMenu() {
        System.out.println("Enter recipe you would like to add to favorites or enter 0 to exit to favorites menu: ");
        for (var el : Database.recipes.entrySet()) {
            System.out.println(el.getValue().getName());
        }

        input = sc.nextLine();
        if (input.equals("0")) {
            favoriteRecipesMenu();
        }

        if (isFavoriteRecipe(input)) {
                System.out.println("Recipe already in favorites.");
                favoriteRecipesMenu();
            }

        for (var el : Database.recipes.entrySet()) {
            if (el.getValue().getName().equalsIgnoreCase(input) && Database.recipeInDatabase(input)) {
                favoriteRecipes.add(el.getValue());
                System.out.println("Recipe added to favorites.");
                break;
            } else if (!el.getValue().getName().equalsIgnoreCase(input) && !Database.recipeInDatabase(input)) {
                System.out.println("Recipe doesn't exit in database.");
                break;
            }
        }
        favoriteRecipesMenu();
    }

    public static void removeFavoriteRecipeMenu() {
        if (favoriteRecipes.isEmpty()) {
            System.out.println("No favorite recipes.");
            favoriteRecipesMenu();
        }

        System.out.println("Enter recipe you want to remove or enter 0 to exit to favorites menu: ");

        for (var favoriteRecipe : favoriteRecipes) {
            System.out.println(favoriteRecipe.getName());
        }

        input = sc.nextLine().toLowerCase();
        if (input.equals("0")) {
            favoriteRecipesMenu();
        }

        for (var favoriteRecipe : favoriteRecipes) {
            if (favoriteRecipe.getName().equalsIgnoreCase(input)) {
                favoriteRecipes.remove(favoriteRecipe);
                System.out.println("Recipe removed from favorite recipes.");
                break;
            } else if (!favoriteRecipe.getName().equalsIgnoreCase(input) && !isFavoriteRecipe(input)) {
                System.out.println("No recipe with that name in favorite recipes.");
                break;
            }
        }
        favoriteRecipesMenu();
    }

    public static boolean isFavoriteRecipe(String name) {
        if (!favoriteRecipes.isEmpty()) {
            for (var favoriteRecipe : favoriteRecipes) {
                if (favoriteRecipe.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFavoriteRecipesByPriceMenu () {
        if (favoriteRecipes.isEmpty()) {
            System.out.println("No favorite recipes.");
            favoriteRecipesMenu();
        }

        System.out.println("Enter price for your meal or enter 0 to exit to favorites menu:");
        double price = sc.nextDouble();
        sc.nextLine();
        if (price == 0) {
            favoriteRecipesMenu();
        }

        for (var favoriteRecipe : favoriteRecipes) {
            if (favoriteRecipe.getPrice() <= price) {
                System.out.println(favoriteRecipe.getName() + "   price - " + favoriteRecipe.getPrice());
            }
        }
        favoriteRecipesMenu();
    }
}
