// Astronaut.java // Class to represent an astronaut

public class Astronaut {
  // Astronaut Variables
  private String name;
  private int age;
  private String role;
  private String nationality;

  // Constructor
  public Astronaut(String name, int age, String role, String nationality) {
    this.name = name;
    this.age = age;
    this.role = role;
    this.nationality = nationality;
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getRole() {
    return role;
  }

  public String getNationality() {
    return nationality;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
}
