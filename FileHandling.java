// FileHandling.java // Contians method to read in a file, would have also contained method to write a file, but was not req'd

import java.io.*;

public class FileHandling {

  // Reads a file and creates a list of missions, expected CSV format
  public static void readFile(String filePath, GenericLinkedList<Mission> missionList) {
    int lineNum = 0;
    String line;
    try (BufferedReader bufRdr = new BufferedReader(new FileReader(filePath))) {
      while ((line = bufRdr.readLine()) != null) {
        if (lineNum == 0) {
          lineNum++;
          continue; // Skip the header line
        }
        String[] parts = line.split(","); // Split the line by commas
        String name = parts[0];
        String code = parts[1];
        String destinationPlanet = parts[2];
        int launchYear = Integer.parseInt(parts[3]);
        double successRate = Double.parseDouble(parts[4]);
        boolean mannedStatus = Boolean.parseBoolean(parts[5]);
        Mission mission = null;
        GenericLinkedList<Astronaut> astronautList = new GenericLinkedList<>();
        if (mannedStatus) { // if manned mission, add astronauts
          String astronautLine = parts[6];
          String[] astronauts = astronautLine.split("\\|"); // slpit astronauts by pipe
          for (int ii = 0; ii < astronauts.length; ii++) {
            String[] astronautParts = astronauts[ii].split(":"); // split astronaut data by colon
            String astronautName = astronautParts[0];
            String astronautRole = astronautParts[1];
            int astronautAge = Integer.parseInt(astronautParts[2]);
            String astronautNationality = astronautParts[3];
            astronautList.add(new Astronaut(astronautName, astronautAge, astronautRole, astronautNationality));
            mission = new Mission(name, code, destinationPlanet, successRate, launchYear, mannedStatus, astronautList);
          }
        } else {
          mission = new Mission(name, code, destinationPlanet, successRate, launchYear, mannedStatus, astronautList);
        }
        missionList.add(mission);
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }

  // Writes a list of missions to a file in CSV format
  public static void writeFile(String filePath, GenericLinkedList<Mission> missionList) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      // Write header
      writer.write("Mission Name,Mission Code,Destination Planet,Launch Year,Success Rate,Manned Mission,Astronauts");
      writer.newLine();

      for (int i = 0; i < missionList.getSize(); i++) {
        Mission mission = missionList.get(i);
        StringBuilder sb = new StringBuilder();
        sb.append(mission.getName()).append(",");
        sb.append(mission.getCode()).append(",");
        sb.append(mission.getDestinationPlanet()).append(",");
        sb.append(mission.getLaunchYear()).append(",");
        sb.append(mission.getSuccessRate()).append(",");
        sb.append(mission.getMannedStatus());

        if (mission.getMannedStatus() && mission.getAstronautList() != null && mission.getAstronautList().getSize() > 0) { 
          sb.append(",");
          for (int j = 0; j < mission.getAstronautList().getSize(); j++) {
            Astronaut astro = mission.getAstronautList().get(j);
            sb.append(astro.getName()).append(":")
              .append(astro.getRole()).append(":")
              .append(astro.getAge()).append(":")
              .append(astro.getNationality());
            if (j < mission.getAstronautList().getSize() - 1) {
              sb.append("|");
            }
          }
        }
        writer.write(sb.toString());
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing file: " + e.getMessage());
    }
  }

}
