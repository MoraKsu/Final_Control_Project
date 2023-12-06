package database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDatabase {
    private static final String DATABASE_FILE_PATH = "src/resources/animal_database.txt";

    public List<String> readDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE_PATH))) {
            List<String> animals = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                animals.add(line);
            }
            return animals;
        } catch (IOException e) {
            System.err.println("Error reading from database: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeDatabase(List<String> animals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE_PATH))) {
            for (String animal : animals) {
                writer.write(animal + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnimal(String animalData) {
        List<String> animals = readDatabase();
        animals.add(animalData);
        writeDatabase(animals);
    }
}
