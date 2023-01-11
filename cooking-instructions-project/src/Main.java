import java.util.LinkedList;
import java.util.Scanner;

record Recipe(String step, int timeToCompleteInSeconds){

    @Override
    public String toString() {
        return String.format("%s (%d seconds to complete)", step, timeToCompleteInSeconds);
    }
}

public class Main {

    public static void main(String[] args) {

        LinkedList<Recipe> recipeSteps = new LinkedList<>();  // second <> not necessary

        Recipe step1 = new Recipe("Preheat oven to 350", 10);  // create new Recipe object
        addStep(recipeSteps, step1);
        addStep(recipeSteps, new Recipe("Add in chocolate", 50));
        addStep(recipeSteps, new Recipe("Add in chocolate", 50));  // testing out duplicate if check
        addStep(recipeSteps, new Recipe("Add in butter", 50));
        addStep(recipeSteps, new Recipe("Add in syrup", 50));
        addStep(recipeSteps, new Recipe("Mix all wet ingredients together", 50));
        addStep(recipeSteps, new Recipe("Put ingredients into oven", 1800));
        addStep(recipeSteps, new Recipe("Cool", 1000));
        addStep(recipeSteps, new Recipe("Eat this very healthy meal", 500));

        var iterator = recipeSteps.listIterator();  // setup iterator variable and calling listIterator on our LinkedList
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while (!quitLoop){
            if (!iterator.hasPrevious()){  // we check if this returns false, which means we are at start of list
                System.out.println("First step in recipe : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()){  // check if this returns false, which means we are at end of list
                System.out.println("Last step in recipe : " + iterator.previous());
                forward = false;
            }

            System.out.println("Enter menu option (First, Next, Previous, List, Quit): ");
            String menuItem = scanner.next().toUpperCase().substring(0, 1);  // forces input to uppercase and grabs first letter only

            switch (menuItem){
                case "F":
                    System.out.println("\nFirst step in recipe is: ");
                    System.out.println(recipeSteps.getFirst());
                    break;

                case "N" :
                    System.out.println("\nNext step in recipe:");
                    if (!forward) {  // We know forward flag is true when we start at top of cursor, false at end.
                        // If false, then we are moving backward.
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();  // we need to move cursor 1 extra spot
                        }
                    }

                    if (iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;

                case "P" :
                    System.out.println("\nPrevious step in recipe:");
                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();  // we need to move cursor 1 extra spot
                        }
                    }

                    if (iterator.hasPrevious()){
                        System.out.println(iterator.previous());
                    }
                    break;

                case "L" :
                    System.out.println();
                    for (Recipe recipe : recipeSteps) {
                        System.out.println(recipe);
                    }
                    System.out.println("""
                    \nPlease select option (select word or letter):
                    (F)irst Step in Recipe
                    (N)ext step
                    (P)revious step
                    (L)ist Entire Recipe
                    (M)ain menu
                    (Q)uit""");

                    break;

                case "M" :
                    printMenu();
                    break;

                default :
                    quitLoop = true;
                    break;
            }
        }

    }

    private static void addStep(LinkedList<Recipe> list, Recipe recipe) {

        if (list.contains(recipe)) {  // checks for matching element so we do not add duplicate
            System.out.println("Found duplicate step in recipe: " + recipe);
            return;
        }

        for (Recipe p : list) {
            if (p.step().equalsIgnoreCase(recipe.step())) {
                System.out.println("Found duplicate step in recipe: " + recipe);
                return;
            }
        }

        int matchedIndex = 0;  // keeps track of what index we are at
        for (var listRecipeStep: list) {
            if (recipe.timeToCompleteInSeconds() < listRecipeStep.timeToCompleteInSeconds()) {
                list.add(matchedIndex, recipe);
                return;
            }
            matchedIndex++;
        }

        list.add(recipe);
    }

    private static void printMenu(){
        System.out.println();
        System.out.println("""
                Available menu options (select word or letter):
                (F)irst Step in Recipe
                (N)ext step
                (P)revious step
                (L)ist Entire Recipe
                (M)ain menu
                (Q)uit""");  // by putting the triple quote at the end, it avoids a new line at end
    }

}


