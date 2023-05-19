package controller;

import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class User implements Serializable, Comparable<User> {
  private String name;
  private String password;
  private int score;

  public User() {
    this.name = "Jungle";
    this.password = "Jungle";
    this.score = 0;
  }

  public User(String name, String password) {
    this.name = name;
    this.password = password;
    this.score = 0;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }


  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public int compareTo(User o) {
    return this.score - o.score;
  }
  public void saveToFile() {
    try (FileWriter writer = new FileWriter("resources/users.txt", true)) {
      String data = name + "," + password + "," + score + "\n";
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
          int score = Integer.parseInt(parts[2]);
          User user = new User(name, password);
          user.setScore(score);
          return user;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
