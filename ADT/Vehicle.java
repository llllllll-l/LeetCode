package ADT;

import java.util.Random;

public class Vehicle {
  private String licensePlateNumber;

  public Vehicle(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }

  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    for (char character : licensePlateNumber.toCharArray()) {
      hash += character;
    }
    return hash;
  }

  private static String generateRandomLicensePlate() {
    StringBuilder licensePlate = new StringBuilder();
    Random rd = new Random();

    for (int i = 0; i < 3; i++) {
      char randomChar = (char) (rd.nextInt(26) + 'A');
      licensePlate.append(randomChar);
    }

    licensePlate.append("-");

    for (int i = 0; i < 3; i++) {
      int randomDigit = rd.nextInt(10);
      licensePlate.append(randomDigit);
    }
    return licensePlate.toString();
  }

  public static void main(String[] args) {
    int numberOfVehicles = 650;
    QuadraticProbingHashTable hashTable = new QuadraticProbingHashTable(997);

    // Just to test the remove functionallity easier we need a constant license plate
    Vehicle baseVehicle = new Vehicle("ABC-123");
    hashTable.insert(baseVehicle.getLicensePlateNumber(), baseVehicle);

    for (int i = 0; i < numberOfVehicles; i++) {
      String licensePlate = generateRandomLicensePlate();
      Vehicle vehicle = new Vehicle(licensePlate);
      hashTable.insert(licensePlate, vehicle);
    }

    String searchPlateNumber = "ABC-123";
    Vehicle foundVehicle = hashTable.find(searchPlateNumber);
    System.out.println("Try to find vehicle: " + searchPlateNumber);
    if (foundVehicle != null) {
      System.out.println(
        "Vehicle found: " + foundVehicle.getLicensePlateNumber()
      );
    } else {
      System.out.println("Vehicle " + searchPlateNumber + " not found.");
    }
    hashTable.displayHashTable();
    System.out.println("number of collisions: " + hashTable.getCollisions());
    System.out.println("Longest offset: " + hashTable.getOffset());

    // delete an entry
    hashTable.delete("ABC-123");
    searchPlateNumber = "ABC-123";
    foundVehicle = hashTable.find(searchPlateNumber);
    if (foundVehicle != null) {
      System.out.println(
        "Vehicle found: " + foundVehicle.getLicensePlateNumber()
      );
    } else {
      System.out.println("Vehicle " + searchPlateNumber + " not found.");
    }
    //hashTable.displayHashTable();
  }
}