import controller.AnimalRegistryController;
import database.AnimalDatabase;
import view.AnimalRegistryView;

public class Main {
    public static void main(String[] args) {
        AnimalRegistryView view = new AnimalRegistryView();
        AnimalDatabase animalDatabase = new AnimalDatabase();
        AnimalRegistryController controller = new AnimalRegistryController(view, animalDatabase);
        controller.start();
    }
}
