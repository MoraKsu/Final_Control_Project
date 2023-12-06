package model;

import java.util.ArrayList;
import java.util.List;

public class Camel implements PackAnimals {
    private String name;
    private String birthDate;
    private List<String> commands;

    public Camel(String name, String birthDate, List<String> commands) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>(commands);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }

    @Override
    public String getType() {
        return "Camel";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void performCommand(String command) {
        if (commands.contains(command)) {
            System.out.println(name + " выполняет команду: " + command);
        } else {
            System.out.println(name + " не знает такой команды.");
        }
    }

    @Override
    public void learnCommand(String command) {
        commands.add(command);
        System.out.println(name + " выучил новую команду: " + command);
    }

    @Override
    public void listCommands() {
        System.out.println("Список команд для верблюда: " + String.join(", ", commands));
    }
}
