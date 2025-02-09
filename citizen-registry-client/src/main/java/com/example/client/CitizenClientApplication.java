package com.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.domain.Citizen;

import java.util.Scanner;

@SpringBootApplication
public class CitizenClientApplication implements CommandLineRunner {

    private static final String BASE_URL = "http://localhost:8080/api/citizens";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(CitizenClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("\n--- Citizen Registry Menu ---");
            System.out.println("1. Add Citizen (POST)");
            System.out.println("2. Get All Citizens (GET)");
            System.out.println("3. Get Citizen by AT (GET)");
            System.out.println("4. Update Citizen (PUT)");
            System.out.println("5. Delete Citizen (DELETE)");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCitizen();
                    break;
                case 2:
                    getAllCitizens();
                    break;
                case 3:
                    getCitizenByAt();
                    break;
                case 4:
                    updateCitizen();
                    break;
                case 5:
                    deleteCitizen();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Exiting...");
                    return;
            }
        }
    }

    private void addCitizen() {
        System.out.print("Enter AT: ");
        String at = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter AFM: ");
        String afm = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Citizen citizen = new Citizen(at, firstName, lastName, gender, dateOfBirth, afm, address);
        restTemplate.postForObject(BASE_URL, citizen, Citizen.class);
        System.out.println("Citizen added successfully.");
    }

    private void getAllCitizens() {
        Citizen[] citizens = restTemplate.getForObject(BASE_URL, Citizen[].class);
        if (citizens != null) {
            for (Citizen c : citizens) {
                System.out.println(c);
            }
        } else {
            System.out.println("No citizens found.");
        }
    }

    private void getCitizenByAt() {
        System.out.print("Enter AT: ");
        String at = scanner.nextLine();
        Citizen citizen = restTemplate.getForObject(BASE_URL + "/" + at, Citizen.class);
        System.out.println(citizen);
    }

    private void updateCitizen() {
        System.out.print("Enter AT: ");
        String at = scanner.nextLine();
        System.out.print("Enter new AFM: ");
        String afm = scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();

        Citizen citizen = new Citizen();
        citizen.setAfm(afm);
        citizen.setAddress(address);

        restTemplate.put(BASE_URL + "/" + at, citizen);
        System.out.println("Citizen updated successfully.");
    }

    private void deleteCitizen() {
        System.out.print("Enter AT: ");
        String at = scanner.nextLine();
        restTemplate.delete(BASE_URL + "/" + at);
        System.out.println("Citizen deleted successfully.");
    }
}
