// Menu.java // Handles the user interface for the Mission Control System

import java.util.Scanner;

public class Menu {

  Scanner scanner = new Scanner(System.in);

  // ANSI escape codes for colors
  private static String scError = "\u001B[31m"; // Red for error messages
  private static String scSystem = "\u001B[33m"; // Yellow for system messages
  private static String scUser = "\u001B[36m"; // Cyan for user input prompt
  private static String scReset = "\u001B[0m"; // Reset color
  private static String scClear = "\033[H\033[2J"; // Clear screen
  /*
   * - ANSI Codes General
   * https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
   * 
   * - ANSI Colour Codes
   * https://gist.github.com/mgumiero9/665ab5f0e5e7e46cb049c1544a00e29f
   * 
   * - ANSI Clear Screen Codes
   * https://stackoverflow.com/questions/37774983/clearing-the-screen-by-printing-
   * a-character
   */

  // Closer method to print a closing message and end scanning, occurs at the end of the program
  public void closer() {
    System.out.print(scClear);
    System.out.println("\u001B[5m" + "Thank you for using the Mission Control System. Goodbye!" + scReset);
    this.scanner.close();
  }

  // Method to print a message and wait for user input to continue
  public void continer() {
    System.out.print(scSystem + "Press Enter to continue..." + scReset);
    this.scanner.nextLine();
  }

  // Method to print and return the user's choice from the main menu - "home page"
  public int CLIMenu() {
    // present the inital options
    System.out.print(scClear);
    System.out.flush();
    System.out.print(scSystem);
    System.out.println("========================================");
    System.out.println("           Mission Control");
    System.out.println("========================================");
    System.out.print(scReset);
    System.out.println("Available system options:");
    System.out.println("1. View all Missions");
    System.out.println("2. View all manned missions");
    System.out.println("3. View all unmanned missions");
    System.out.println("4. View a mission's astronauts");
    System.out.println("5. Add a new mission");
    System.out.println("6. Edit a mission");
    System.out.println("7. Summary of missions' success rates");
    System.out.println("8. List astronauts for a given detail");
    System.out.println("9. Exit");
    System.out.println(scSystem + "========================================" + scUser);
    System.out.print("Please select an option: " + scReset);

    int choice = this.scanner.nextInt();
    this.scanner.nextLine(); // Consume the newline character

    // Clear the screen and print the selected option only, for use clarity
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print(scSystem);
    System.out.println("========================================");
    System.out.println("           Mission Control");
    System.out.println("========================================");
    System.out.print(scReset);
    System.out.println("Selected system options:");
    for (int ii = 1; ii < choice; ii++) {
      System.out.print("\n"); // Print empty line to postiion the selected option in the same relative position
    }
    switch (choice) {
      case 1:
        System.out.println("1. View all Missions");
        break;
      case 2:
        System.out.println("2. View all manned missions");
        break;
      case 3:
        System.out.println("3. View all unmanned missions");
        break;
      case 4:
        System.out.println("4. View a mission's astronauts");
        break;
      case 5:
        System.out.println("5. Add a new mission");
        break;
      case 6:
        System.out.println("6. Edit a mission");
        break;
      case 7:
        System.out.println("7. Summary of missions' success rates");
        break;
      case 8:
        System.out.println("8. List astronauts for a given detail");
        break;
      default:
        break;
    }
    for (int ii = 0; ii < 9 - choice; ii++) {
      System.out.print("\n"); // Print empty lines to end visual element in the same relative position
    }
    System.out.print(scSystem);
    System.out.println("========================================");
    System.out.print(scReset);

    return choice;
  }

  // Method to print a selecred missiong to CLI
  public void viewMission(Mission mission) {
    System.out.print("\u001B[34m"); // Blue ANSI escape code
    System.out.print("Mission Name: " + mission.getName());
    System.out.print("// Mission Code: " + mission.getCode());
    System.out.print("// Destination Planet: " + mission.getDestinationPlanet());
    System.out.print("// Success Rate: " + mission.getSuccessRate());
    System.out.print("// Launch Year: " + mission.getLaunchYear());
    System.out.println("// Manned Status: " + mission.getMannedStatus());
    System.out.print(scReset);
  }

  // Method to print a selected astronaut to CLI
  public void viewAstronaut(Astronaut astronaut) {
    System.out.print("\u001B[93m"); // Light Yellow ANSI escape code
    System.out.print("Astronaut Name: " + astronaut.getName() + " // ");
    System.out.print("Astronaut Age: " + astronaut.getAge() + " // ");
    System.out.print("Astronaut Role: " + astronaut.getRole() + " // ");
    System.out.println("Astronaut Nationality: " + astronaut.getNationality());
    System.out.print(scReset);
  }

  // Method to print options for astronaut detail filtering
  public String selectAstronautOption() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print(scSystem);
    System.out.println("========================================");
    System.out.println("           Mission Control");
    System.out.println("========================================");
    System.out.print(scReset);
    System.out.println("Available astronaut options:");
    System.out.println("1. Name");
    System.out.println("2. Role");
    System.out.println("3. Age");
    System.out.println("4. Nationality");
    System.out.print(scSystem);
    System.out.println("\n\n\n\n\n=========================================");
    System.out.print(scUser);
    System.out.print("Please select an option: ");
    System.out.print(scReset);

    String choice = "";

    // Loop until a valid choice is made
    do {
      switch (this.scanner.nextInt()) {
        case 1:
          choice = "name";
          break;
        case 2:
          choice = "role";
          break;
        case 3:
          choice = "age";
          break;
        case 4:
          choice = "nationality";
          break;
        default:
          break;
      }
    } while (choice.isEmpty());
    this.scanner.nextLine(); // Consume the newline character
    return choice;
  }

  // Method to print options for mission detail selection
  public int selectMissionOption() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print(scSystem);
    System.out.println("========================================");
    System.out.println("           Mission Control");
    System.out.println("========================================");
    System.out.print(scReset);
    System.out.println("Available mission options:");
    System.out.println("1. Name");
    System.out.println("2. Code");
    System.out.println("3. Destination Planet");
    System.out.println("4. Success Rate");
    System.out.println("5. Launch Year");
    System.out.println("6. Manned Status");
    System.out.println("7. Add Astronaut");
    System.out.println("8. Remove Astronaut");
    System.out.println(scSystem + "\n=========================================");
    System.out.print(scUser + "Please select an option: " + scReset);

    int choice = this.scanner.nextInt();
    this.scanner.nextLine(); // Consume the newline character
    return choice;
  }

  // Generic input methods for string, int, and boolean
  public String stringInput(String prompt) {
    System.out.print(prompt);
    try {
      String choice = this.scanner.nextLine();
      if (choice.isEmpty()) {
        throw new Exception("Input cannot be empty");
      }
      return choice;
    } catch (Exception e) {
      System.out.println(scError + "Invalid input. Please enter a valid string." + scReset);
      return stringInput(prompt);
    }
  }

  public int intInput(String prompt) {
    System.out.print(prompt);
    try {
      int choice = this.scanner.nextInt();
      this.scanner.nextLine(); // Consume the newline character
      return choice;
    } catch (Exception e) {
      System.out.println(scError + "Invalid input. Please enter a valid number." + scReset);
      this.scanner.nextLine(); // Consume the invalid input
      return intInput(prompt);
    }
  }

  public Boolean booleanInput(String prompt) {
    System.out.print(prompt);
    String choice = this.scanner.nextLine();
    if (choice.equalsIgnoreCase("yes")) {
      return true;
    } else if (choice.equalsIgnoreCase("no")) {
      return false;
    } else {
      System.out.println(scError + "Invalid input. Please enter 'yes' or 'no'." + scReset);
      return booleanInput(prompt);
    }
  }

}
