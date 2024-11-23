import java.util.List;

public class Menu {
    private List<FoodItem> foodItems;

    public Menu(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        this.foodItems.add(foodItem);
    }

    public void removeFoodItem(FoodItem foodItem) {
        this.foodItems.remove(foodItem);
    }

    public void displayMenuItems() {
        for (FoodItem item : foodItems) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
    }
}
