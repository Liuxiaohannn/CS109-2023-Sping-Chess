package controller;

import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class User implements Serializable{
  private String name;
  private String password;


  public User() {
    this.name = "Jungle";
    this.password = "Jungle";

  }

  public User(String name, String password) {
    this.name = name;
    this.password = password;

  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }



  public void saveToFile() {
    try (FileWriter writer = new FileWriter("resources/users.txt", true)) {
      String data = name + "," + password + "," +  "\n";
      writer.write(data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static User loadFromFile(String name) {
    try (Scanner scanner = new Scanner(new File("resources/users.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        if (parts[0].equals(name)) {
          String password = parts[1];

          User user = new User(name, password);

          return user;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
