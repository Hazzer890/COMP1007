// MissionController.java // Main class to control the mission management system, main file

public class MissionController {

  public static void main(String[] args) {

    GenericLinkedList<Mission> missionList = new GenericLinkedList<>(); // Create a linked list of missions, using GenericLinkedList
    Menu menu = new Menu(); // Menu object to handle user input and display
    FileHandling.readFile(args[0], missionList); // use the 0th (first) argument as the file path to read in the mission data
    int exitCondition = 0;
    try { // try catch to handle any exceptions not already handled in the menu
      do {
        switch (menu.CLIMenu()) { // direct user input form the menu to explict actions
          case 1:
            viewAllMissions(missionList, menu);
            menu.continer();
            break;
          case 2:
            viewAllMannedMissions(missionList, true, menu);
            menu.continer();
            break;
          case 3:
            viewAllMannedMissions(missionList, false, menu);
            menu.continer();
            break;
          case 4:
            String gCode = menu.stringInput("Please enter the mission code: ");
            getMissionByCode(gCode, missionList, menu);
            menu.continer();
            break;
          case 5:
            addMission(missionList, menu);
            menu.continer();
            break;
          case 6:
            String eCode = menu.stringInput("Please enter the mission code: ");
            editMissionByCode(eCode, missionList, menu);
            menu.continer();
            break;
          case 7:
            viewSuccessProbabilities(missionList);
            menu.continer();
            break;
          case 8:
            String inputField = menu.selectAstronautOption();
            String inputDetail = menu.stringInput("Please enter the astronaut detail: ");
            for (int jj = 0; jj < missionList.getSize(); jj++) { // for all manned missions, fiter astronauts by attribute
              if (missionList.get(jj).getMannedStatus()) {
                listAstronautsFilterByAttribute(missionList.get(jj).getAstronautList(), inputField, inputDetail, menu);
              }
            }
            menu.continer();
            break;
          case 9:
            menu.closer();
            FileHandling.writeFile(args[0], missionList); // use the 1st argument as the file path to write out mission data
            exitCondition = 1;
            break;
          default:
            System.out.println("Invalid option. Please try again.");
            break;
        }

      } while (exitCondition == 0);
    } catch (Exception e) { // in the event of an unexpected error, close the scanner resource
      menu.closer();
      System.out.println("\u001B[31m" + "An error occurred: " + e.getMessage() + "\u001B[0m");
    }

  }

  // Mission methods -*-*-*-*-*-*-*-*-*-*-*-*-*-

  // Method to add a mission to the list, with explicit parameters, used for debugging and testing only
  static void addMission(String name, String code, String destinationPlanet, double successRate, int launchYear, Boolean mannedStatus, GenericLinkedList<Mission> missionList, GenericLinkedList<Astronaut> astronautList) {
    Mission mission = new Mission(name, code, destinationPlanet, successRate, launchYear, mannedStatus, astronautList);
    missionList.add(mission);
  }

  // Overloaded method to add a mission to the list, with user input
  static void addMission(GenericLinkedList<Mission> missionList, Menu menu) {
    String name = menu.stringInput("Please enter the mission name: ");
    String code = menu.stringInput("Please enter the mission code: ");
    String destinationPlanet = menu.stringInput("Please enter the destination planet: ");
    int successRate = -1;
    do { // only allow vaild success rates, throw an exception and try again if not
      try {
        successRate = menu.intInput("Please enter the success rate: ");
        if (successRate < 0 || successRate > 100) {
          throw new Exception("Success rate must be between 0 and 100.");
        }
      } catch (Exception e) {
        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
      }
    } while (successRate < 0 || successRate > 100);
    int launchYear = 0;
    do { // only allow valid launch years, throw an exception and try again if not
      try {
        launchYear = menu.intInput("Please enter the launch year: ");
        if (launchYear < 1900 || launchYear > 2100) {
          throw new Exception("Launch year must be between 1900 and 2100.");
        }
      } catch (Exception e) {
        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
      }
    } while (launchYear < 1900 || launchYear > 2100);
    Boolean mannedStatus = menu.booleanInput("Please enter the manned status (yes/no): ");
    GenericLinkedList<Astronaut> astronautList = new GenericLinkedList<>();
    if (mannedStatus) { // if manned mission, add astronaut/s
      int numAstronauts = menu.intInput("Please enter the number of astronauts: ");
      for (int i = 0; i < numAstronauts; i++) {
        String astronautName = menu.stringInput("Please enter the astronaut name: ");
        String astronautRole = menu.stringInput("Please enter the astronaut role: ");
        int astronautAge = -1;
        do { // throw and catch invalid ages
          try {
            astronautAge = menu.intInput("Please enter the astronaut age: ");
            if (astronautAge < 1) {
              throw new Exception("Astronaut age must be a positive integer.");
            }
          } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
          }
        } while (astronautAge < 1);
        String astronautNationality = menu.stringInput("Please enter the astronaut nationality: ");
        Astronaut astronaut = new Astronaut(astronautName, astronautAge, astronautRole, astronautNationality);
        astronautList.add(astronaut);
      }
    }
    missionList.add(new Mission(name, code, destinationPlanet, successRate, launchYear, mannedStatus, astronautList));
    return;
  }

  // Edit a mission detail, selected missions by code, and detail via user input
  static void editMissionByCode(String code, GenericLinkedList<Mission> missionList, Menu menu) {
    int codeFound = 0;
    for (int i = 0; i < missionList.getSize(); i++) { // iterate thru the mission list until a mission with the same code is found
      Mission mission = missionList.get(i);
      if (mission.getCode().equalsIgnoreCase(code)) {
        codeFound = 1;
        switch (menu.selectMissionOption()) {
          case 1:
            mission.setName(menu.stringInput("Please enter the new mission name: "));
            break;
          case 2:
            mission.setCode(menu.stringInput("Please enter the new mission code: "));
            break;
          case 3:
            mission.setDestinationPlanet(menu.stringInput("Please enter the new destination planet: "));
            break;
          case 4:
            mission.setSuccessRate(menu.intInput("Please enter the new success rate: "));
            break;
          case 5:
            mission.setLaunchYear(menu.intInput("Please enter the new launch year: "));
            break;
          case 6:
            mission.setMannedStatus(menu.booleanInput("Please enter the new manned status (yes/no): "));
            if (mission.getMannedStatus()) {
              int numAstronauts = menu.intInput("Please enter the number of astronauts: ");
              for (int jj = 0; jj < numAstronauts; jj++) {
                String astronautName = menu.stringInput("Please enter the astronaut name: ");
                String astronautRole = menu.stringInput("Please enter the astronaut role: ");
                int astronautAge = menu.intInput("Please enter the astronaut age: ");
                String astronautNationality = menu.stringInput("Please enter the astronaut nationality: ");
                Astronaut astronaut = new Astronaut(astronautName, astronautAge, astronautRole, astronautNationality);
                mission.addAstronaut(astronaut);
              }

            }
            break;
          case 7:
            if (mission.getMannedStatus()) {
              String astronautName = menu.stringInput("Please enter the astronaut name: ");
              String astronautRole = menu.stringInput("Please enter the astronaut role: ");
              int astronautAge = menu.intInput("Please enter the astronaut age: ");
              String astronautNationality = menu.stringInput("Please enter the astronaut nationality: ");
              Astronaut astronaut = new Astronaut(astronautName, astronautAge, astronautRole, astronautNationality);
              mission.addAstronaut(astronaut);
            } else {
              System.out.println("\u001B[31m" + "Mission is not manned, cannot add astronauts." + "\u001B[0m");
            }
            break;
          case 8:
            String astronautName = menu.stringInput("Please enter the astronaut name: ");
            for (int ii = 0; ii < mission.getAstronautList().getSize(); ii++) {
              if (mission.getAstronaut(ii).getName().equalsIgnoreCase(astronautName)) {
                mission.getAstronautList().remove(mission.getAstronaut(ii));
                if (mission.getAstronautList().getSize() == 0) {
                  mission.setMannedStatus(false);
                }
                break;
              } else {
                System.out.println("\u001B[31m" + "Astronaut with name " + astronautName + " not found." + "\u001B[0m");
              }
            }
            break;
        }
      }
    }
    if (codeFound == 0) { // inform user of invalid code :(
     System.out.println("\u001B[31m" + "Mission with code " + code + " not found.");
    }
  }
  
  // Method to delete a mission from the list, selected by index, used for debugging and testing
  void deleteMission(Mission mission, GenericLinkedList<Mission> missionList) {
    missionList.remove(mission);
  }

  // Method to view a mission's astronauts, selected by code
  static void getMissionByCode(String code, GenericLinkedList<Mission> missionList, Menu menu) {
    int codeFound = 0;
    for (int i = 0; i < missionList.getSize(); i++) {
      Mission mission = missionList.get(i);
      if (mission.getCode().equalsIgnoreCase(code)) {
        codeFound = 1;
        menu.viewMission(mission);
        if (mission.getMannedStatus()) {
          for (int ii = 0; ii < mission.getAstronautList().getSize(); ii++) {
            menu.viewAstronaut(mission.getAstronaut(ii));
          }
        }
        System.out.println("");
      }
    }
    if (codeFound == 0) { // inform user of invalid code :(
      System.out.println("\u001B[31m" + "Mission with code " + code + " not found.");
    }
  }

  // Method to view all missions in the list
  static void viewAllMissions(GenericLinkedList<Mission> missionList, Menu menu) {
    for (int i = 0; i < missionList.getSize(); i++) {
      Mission mission = missionList.get(i);
      menu.viewMission(mission);
      for (int ii = 0; ii < mission.getAstronautList().getSize(); ii++) {
        menu.viewAstronaut(mission.getAstronaut(ii));
      }
      System.out.println("");
    }
  }

  // Method to view all manned missions in the list
  static void viewAllMannedMissions(GenericLinkedList<Mission> missionList, Boolean mannedQuery, Menu menu) {
    for (int i = 0; i < missionList.getSize(); i++) {
      Mission mission = missionList.get(i);
      if (mission.getMannedStatus() == mannedQuery) {
        menu.viewMission(mission);
        for (int ii = 0; ii < mission.getAstronautList().getSize(); ii++) {
          menu.viewAstronaut(mission.getAstronaut(ii));
        }
        System.out.println("");
      }
    }
  }

  // Method to view the success probabilities of all missions in the list
  static void viewSuccessProbabilities(GenericLinkedList<Mission> missionList) {
    double cumulativeSuccessRate = 0;
    double highestSuccessRate = 0;
    double lowestSuccessRate = 100;
    double successAverage = 0;
    for (int i = 0; i < missionList.getSize(); i++) {
      cumulativeSuccessRate += missionList.get(i).getSuccessRate(); // sum success rates, to be averaged later
      highestSuccessRate = Math.max(highestSuccessRate, missionList.get(i).getSuccessRate()); // compare previous highest to current, assign larger
      lowestSuccessRate = Math.min(lowestSuccessRate, missionList.get(i).getSuccessRate()); // compare previous lowest to current, assign smaller
    }
    successAverage = cumulativeSuccessRate / missionList.getSize();
    System.out.println("Average success rate: " + successAverage);
    System.out.println("highest success rate: " + highestSuccessRate);
    System.out.println("lowest success rate: " + lowestSuccessRate);
  }

  // Method to list astronauts for a given detail, selected by attribute
  static void listAstronautsFilterByAttribute(GenericLinkedList<Astronaut> astronautList, String attribute, String value, Menu menu) {
    for (int i = 0; i < astronautList.getSize(); i++) {
      Astronaut astronaut = astronautList.get(i);
      switch (attribute) {
        case "name":
          if (astronaut.getName().equalsIgnoreCase(value)) {
            menu.viewAstronaut(astronaut);
          }
          break;
        case "age":
          if (String.valueOf(astronaut.getAge()).equals(value)) {
            menu.viewAstronaut(astronaut);
          }
          break;
        case "role":
          if (astronaut.getRole().equalsIgnoreCase(value)) {
            menu.viewAstronaut(astronaut);
          }
          break;
        case "nationality":
          if (astronaut.getNationality().equalsIgnoreCase(value)) {
            menu.viewAstronaut(astronaut);
          }
          break;
      }
    }
  }
}
