package com.example.citizenclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.citizenentities.model.Citizen;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CitizenClientApplication implements CommandLineRunner {

    private final WebClient webClient;

    public CitizenClientApplication() {
        this.webClient = WebClient.create("http://localhost:8080/api/citizens");
    }

    public static void main(String[] args) {
        SpringApplication.run(CitizenClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ΜΗΤΡΩΟ ΠΟΛΙΤΩΝ =====");
            System.out.println("1. Προσθήκη πολίτη");
            System.out.println("2. Αναζήτηση πολίτη");
            System.out.println("3. Διαγραφή πολίτη");
            System.out.println("4. Προβολή όλων των πολιτών");
            System.out.println("5. Έξοδος");
            System.out.print("Επιλογή: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addCitizen(scanner);
                case "2" -> searchCitizen(scanner);
                case "3" -> deleteCitizen(scanner);
                case "4" -> getAllCitizens();
                case "5" -> {
                    System.out.println("Έξοδος από το πρόγραμμα.");
                    scanner.close();
                    System.exit(0);
                }
                default -> {
                    System.out.println("Μη έγκυρη επιλογή. Τερματισμός...");
                    System.exit(1);
                }
            }
        }
    }

    private void addCitizen(Scanner scanner) {
        System.out.print("ΑΤ: ");
        String id = scanner.nextLine();
        System.out.print("Όνομα: ");
        String firstName = scanner.nextLine();
        System.out.print("Επίθετο: ");
        String lastName = scanner.nextLine();
        System.out.print("Φύλο: ");
        String gender = scanner.nextLine();
        System.out.print("Ημ/νια γέννησης (DD-MM-YYYY): ");
        String birthDate = scanner.nextLine();
        System.out.print("ΑΦΜ: ");
        String afm = scanner.nextLine();
        System.out.print("Διεύθυνση: ");
        String address = scanner.nextLine();

        Citizen citizen = new Citizen(id, firstName, lastName, gender, birthDate, afm, address);

        System.out.println("Στέλνω αίτημα POST με δεδομένα: " + citizen);

        webClient.post()
                .uri("") 
                .header("Content-Type", "application/json")
                .bodyValue(citizen)
                .retrieve()
                .bodyToMono(Citizen.class)
                .doOnSuccess(response -> System.out.println("Πολίτης προστέθηκε: " + response))
                .doOnError(error -> System.out.println("Σφάλμα: " + error.getMessage()))
                .block(); 
        System.out.println("🟢 Δοκιμάζουμε GET στο /api/citizens...");
        try {
            List<Citizen> citizens = webClient.get()
                    .uri("")
                    .retrieve()
                    .bodyToFlux(Citizen.class)
                    .collectList()
                    .block();

            System.out.println("✅ Πολίτες που βρέθηκαν: " + citizens);
        } catch (Exception e) {
            System.out.println("❌ Σφάλμα: " + e.getMessage());
        }

    }

    private void searchCitizen(Scanner scanner) {
        System.out.print("Δώσε ΑΤ: ");
        String id = scanner.nextLine();

        webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Citizen.class)
                .doOnSuccess(c -> System.out.println("Βρέθηκε: " + c))
                .doOnError(e -> System.out.println("Σφάλμα: " + e.getMessage()))
                .subscribe();
    }

    private void deleteCitizen(Scanner scanner) {
        System.out.print("Δώσε ΑΤ για διαγραφή: ");
        String id = scanner.nextLine();

        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(c -> System.out.println("Πολίτης διαγράφηκε."))
                .doOnError(e -> System.out.println("Σφάλμα: " + e.getMessage()))
                .subscribe();
    }

    private void getAllCitizens() {
        System.out.println("Αίτημα GET για όλους τους πολίτες...");

        webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(Citizen.class)
                .collectList()
                .doOnSuccess(citizens -> {
                    if (citizens == null || citizens.isEmpty()) {
                        System.out.println("Δεν βρέθηκαν πολίτες.");
                    } else {
                        citizens.forEach(System.out::println);
                    }
                })
                .doOnError(e -> System.out.println("Σφάλμα: " + e.getMessage()))
                .block();
    }


    }
