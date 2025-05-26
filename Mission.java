// Mission.java // Class to represent a space mission record

public class Mission {
  // Mission Variables
  private String name;
  private String code;
  private String destinationPlanet;
  private double successRate;
  private int launchYear;
  private Boolean mannedStatus;
  GenericLinkedList<Astronaut> astronautList = new GenericLinkedList<>();

  // Constructor
  public Mission(String name, String code, String destinationPlanet, double successRate, int launchYear, Boolean mannedStatus, GenericLinkedList<Astronaut> astronautList) {
    this.name = name;
    this.code = code;
    this.destinationPlanet = destinationPlanet;
    this.successRate = successRate;
    this.launchYear = launchYear;
    this.mannedStatus = mannedStatus;
    this.astronautList = astronautList;
  }

  // Method to forcefully edit mission details
  public void edit(String name, String code, String destinationPlanet, int successRate, int launchYear, Boolean mannedStatus) {
    this.setName(name);
    this.setCode(code);
    this.setDestinationPlanet(destinationPlanet);
    this.setSuccessRate(successRate);
    this.setLaunchYear(launchYear);
    this.setMannedStatus(mannedStatus);
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getDestinationPlanet() {
    return destinationPlanet;
  }

  public double getSuccessRate() {
    return successRate;
  }

  public int getLaunchYear() {
    return launchYear;
  }

  public Boolean getMannedStatus() {
    return mannedStatus;
  }

  public GenericLinkedList<Astronaut> getAstronautList() {
    return astronautList;
  }

  public Astronaut getAstronaut(int index) {
    return astronautList.get(index);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setDestinationPlanet(String destinationPlanet) {
    this.destinationPlanet = destinationPlanet;
  }

  public void setSuccessRate(int successRate) {
    this.successRate = successRate;
  }

  public void setLaunchYear(int launchYear) {
    this.launchYear = launchYear;
  }

  public void setMannedStatus(Boolean mannedStatus) {
    this.mannedStatus = mannedStatus;
  }

  public void addAstronaut(Astronaut astronaut) {
    astronautList.add(astronaut);
  }

  public void removeAstronaut(Astronaut astronaut) {
    astronautList.remove(astronaut);
  }
}
