package model;

import java.util.List;

public interface Animal {
    String getName();
    String getBirthDate();
    List<String> getCommands();
    String getType();
    void setName(String name);
    void performCommand(String command);
    void learnCommand(String command);
    void listCommands();
}

interface Commandable {
    String getName();
    String getBirthDate();
    List<String> getCommands();
    void performCommand(String command);
    void learnCommand(String command);
    void listCommands();
}
