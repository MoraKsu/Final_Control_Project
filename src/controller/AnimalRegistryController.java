package controller;

import model.*;
import view.AnimalRegistryView;
import database.AnimalDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalRegistryController {
    private AnimalRegistryView view;
    private List<Animal> animals;
    private Counter counter;

    private AnimalDatabase animalDatabase;

    public AnimalRegistryController(AnimalRegistryView view, AnimalDatabase animalDatabase) {
        this.view = view;
        this.animals = new ArrayList<>();
        this.counter = new Counter();
        this.animalDatabase = animalDatabase;
    }

    public void start() {
        boolean exit = false;
        try (Counter counter = new Counter()) {
            while (!exit) {
                view.displayMenu();
                String choice = view.getInput();
                switch (choice) {
                    case "1":
                        counter.add();
                        view.displayMessage("Введите тип животного (1 - Собака, 2 - Кошка, 3 - Хомяк, 4 - Лошадь, 5 - Верблюд, 6 - Осел.): ");
                        String animalType = view.getInput();
                        createAnimal(animalType);
                        break;
                    case "2":
                        listAnimalCommands();
                        break;
                    case "3":
                        teachAnimal();
                        break;
                    case "4":
                        showAllAnimals();
                        break;
                    case "0":
                        exit = true;
                        break;
                    default:
                        view.displayMessage("Неверный выбор. Пожалуйста, выберите снова.");
                }
            }
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        } finally {
            view.closeScanner();
        }
    }

    private void createAnimal(String animalType) {
        AnimalDatabase animalDatabase = new AnimalDatabase();
        view.displayMessage("Введите имя животного:");
        String name = view.getInput();
        view.displayMessage("Введите дату рождения животного (в формате dd-MM-yyyy):");
        String birthDate = view.getInput();

        Animal newAnimal = createAnimalFromType(animalType);
        addAnimalToRegistry(newAnimal);

        // Запись в файл
        String animalData = animalType + "," + newAnimal.getName() + "," + newAnimal.getBirthDate() + "," +
                String.join(" ", newAnimal.getCommands());
        animalDatabase.addAnimal(animalData);

        // Добавление в список animals
        animals.add(newAnimal);

        view.displayMessage("Животное добавлено в реестр.");
    }

    private Animal createAnimalFromType(String animalType) {
        view.displayMessage("Введите имя животного:");
        String name = view.getInput();
        view.displayMessage("Введите дату рождения животного (в формате дд-мм-гггг):");
        String birthDate = view.getInput();
        view.displayMessage("Введите команды, которые умеет выполнять животное (команды можно вводить через пробел):");
        String commands = view.getInput();

        switch (animalType) {
            case "1":
                return new Dog(name, birthDate, Arrays.asList(commands.split(" ")));
            case "2":
                return new Cat(name, birthDate, Arrays.asList(commands.split(" ")));
            case "3":
                return new Hamster(name, birthDate, Arrays.asList(commands.split(" ")));
            case "4":
                return new Horse(name, birthDate, Arrays.asList(commands.split(" ")));
            case "5":
                return new Camel(name, birthDate, Arrays.asList(commands.split(" ")));
            case "6":
                return new Donkey(name, birthDate, Arrays.asList(commands.split(" ")));
            default:
                return null;
        }
    }

    private void showAllAnimals() {
        AnimalDatabase animalDatabase = new AnimalDatabase();
        List<String> animalData = animalDatabase.readDatabase();

        if (!animalData.isEmpty()) {
            view.displayMessage("Список всех животных:");
            for (String data : animalData) {
                String[] parts = data.split(",");
                String type = getAnimalTypeByNumber(parts[0]);
                String name = parts[1];
                String birthDate = parts[2];
                view.displayMessage(type + ", " + name + ", Дата рождения: " + birthDate);
            }
        } else {
            view.displayMessage("Реестр животных пуст.");
        }
    }

    private void listAnimalCommands() {
        List<String> animalData = animalDatabase.readDatabase();  // Используем данные из базы данных

        if (!animalData.isEmpty()) {
            view.displayMessage("Введите тип животного для просмотра списка команд. Доступные типы: Dog, Cat, Hamster, Horse, Camel, Donkey");
            String animalType = view.getInput();

            if (Arrays.asList("Dog", "Cat", "Hamster", "Horse", "Camel", "Donkey").contains(animalType)) {
                view.displayMessage("Введите имя животного:");
                String animalName = view.getInput();

                Animal animal = findAnimal(animalType, animalName, animalData);
                if (animal != null) {
                    view.displayMessage("Список команд для животного " + animal.getName() + ":");
                    animal.listCommands();
                } else {
                    view.displayMessage("Животное с таким типом и именем не найдено.");
                }
            } else {
                view.displayMessage("Неверный тип животного. Пожалуйста, выберите из доступных типов.");
            }
        } else {
            view.displayMessage("Реестр животных пуст. Заведите новое животное.");
        }
    }

    private void teachAnimal() {
        List<String> animalData = animalDatabase.readDatabase();  // Используем данные из базы данных

        if (!animalData.isEmpty()) {
            view.displayMessage("Введите номер типа животного: Dog, Cat, Hamster, Horse, Camel, Donkey:");
            String animalType = view.getInput();

            if (Arrays.asList("Dog", "Cat", "Hamster", "Horse", "Camel", "Donkey").contains(animalType)) {
                view.displayMessage("Введите имя животного:");
                String animalName = view.getInput();

                Animal animal = findAnimal(animalType, animalName, animalData);
                if (animal != null) {
                    view.displayMessage("Введите новую команду для животного:");
                    String newCommand = view.getInput();
                    animal.learnCommand(newCommand);
                } else {
                    view.displayMessage("Животное с таким типом и именем не найдено.");
                }
            } else {
                view.displayMessage("Неверный номер типа животного. Пожалуйста, выберите из доступных номеров.");
            }
        } else {
            view.displayMessage("Реестр животных пуст. Заведите новое животное.");
        }
    }

    private Animal findAnimal(String animalType, String animalName, List<String> animalData) {
        for (String data : animalData) {
            String[] parts = data.split(",");
            String currentType = getAnimalTypeByNumber(parts[0]);
            String currentName = parts[1];
            if (currentName.equalsIgnoreCase(animalName) && currentType.equals(animalType)) {
                return createAnimalFromDatabase(parts);
            }
        }
        return null;
    }

    private Animal createAnimalFromDatabase(String[] parts) {
        String animalType = getAnimalTypeByNumber(parts[0]);
        String name = parts[1];
        String birthDate = parts[2];
        List<String> commands = Arrays.asList(Arrays.copyOfRange(parts, 3, parts.length));

        switch (animalType) {
            case "Dog":
                return new Dog(name, birthDate, commands);
            case "Cat":
                return new Cat(name, birthDate, commands);
            case "Hamster":
                return new Hamster(name, birthDate, commands);
            case "Horse":
                return new Horse(name, birthDate, commands);
            case "Camel":
                return new Camel(name, birthDate, commands);
            case "Donkey":
                return new Donkey(name, birthDate, commands);
            default:
                return null;
        }
    }

    private String getAnimalTypeByNumber(String number) {
        switch (number) {
            case "1": return "Dog";
            case "2": return "Cat";
            case "3": return "Hamster";
            case "4": return "Horse";
            case "5": return "Camel";
            case "6": return "Donkey";
            default: return "";
        }
    }

    private void addAnimalToRegistry(Animal animal) {
        animals.add(animal);
    }
}
