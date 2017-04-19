package com.excilys.computerDatabase.console;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyServiceImpl;
import com.excilys.computerDatabase.service.ComputerServiceImpl;
import com.excilys.computerDatabase.util.DateFormatter;

public class CommandLine {

    private CompanyServiceImpl companyService;
    private ComputerServiceImpl computerService;
    private Scanner scanner;

    /**
     */
    public CommandLine() {
        this.scanner = new Scanner(System.in);
        this.companyService = new CompanyServiceImpl();
        this.computerService = new ComputerServiceImpl();
    }

    /**
     */
    public void run() {

        String choix;
        boolean run = true;

        printMenu();
        while (run) {

            System.out.print("your choice : ");
            choix = scanner.next();

            switch (choix) {
            case "q":
                run = false;
                break;
            case "1":
                displayCompanies();
                break;
            case "2":
                displayComputers();
                break;
            case "3":
                displayOneCompany();
                break;
            case "4":
                displayOneComputer();
                break;
            case "5":
                deleteOneComputer();
                break;
            case "6":
                updateOneComputer();
                break;
            default:
                printMenu();
            }
        }
    }

    /**
     */
    private void displayComputers() {
//        for (Computer computer : this.computerService.getAllComputers()) {
//            System.out.println(computer);
//        }
    }

    /**
     */
    private void displayCompanies() {
        for (Company company : this.companyService.getAllCompanies().getListEntity()) {
            System.out.println(company);
        }
    }

    /**
     */
    private void displayOneComputer() {

//        int id = nextInt("Enter the id of the Computer");
//
//        Optional<Computer> computer = this.computerService.getComputer(id);
//
//        if (computer.isPresent()) {
//            System.out.println(computer.get());
//        } else {
//            System.out.println("no computer with id " + id);
//        }
    }

    /**
     */
    private void displayOneCompany() {

        int id = this.nextInt("Enter the id of the company");

        Optional<Company> company = this.companyService.getCompany(id);

        if (company.isPresent()) {
            System.out.println(company.get());
        } else {
            System.out.println("no company with id " + id);
        }
    }

    /**
     */
    private void deleteOneComputer() {

//        int id = this.nextInt("Enter the id of the Computer");
//
//        Optional<Computer> computer = this.computerService.getComputer(id);
//
//        if (computer.isPresent() && this.computerService.removeComputer(computer.get())) {
//            System.out.print("the computer has been deleted");
//        } else {
//            System.out.print("the computer hasn't been deleted");
//        }

    }

    /**
     */
    private void updateOneComputer() {
//        int id = this.nextInt("Enter the id of the Computer");
//
//        String name;
//        LocalDate intro = null;
//        LocalDate discon = null;
//
//        Optional<Computer> computer = this.computerService.getComputer(id);
//
//        if (computer.isPresent()) {
//
//            System.out.println("enter the new name : ");
//            name = scanner.nextLine();
//
//            System.out.println("New introducing date (yyyy-MMM-dd)");
//            intro = DateFormatter.stringtoLocalDate(this.scanner.next());
//
//            System.out.println("New discontinuing date (yyyy-MMM-dd)");
//            discon = DateFormatter.stringtoLocalDate(this.scanner.next());
//
//            computer.get().setName(name);
//            computer.get().setDate(intro, discon);
//            this.computerService.updateComputer(computer.get());
//
//        } else {
//            System.out.println("no computer with id " + id);
//        }

    }

    /**
     * @param msg the message to display
     * @return the number
     */
    private int nextInt(String msg) {

        int id = 0;

        try {
            System.out.print(msg + " ");
            id = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
        }
        return id;
    }

    /**
     */
    private void printMenu() {
        System.out.println("------- MENU -----------");
        System.out.println("| 'q' : stop application");
        System.out.println("| '1' : display all companies");
        System.out.println("| '2' : display all computer");
        System.out.println("| '3' : display one company");
        System.out.println("| '4' : display one computer");
        System.out.println("| '5' : delete one computer");
        System.out.println("| '6' : update one computer");
        System.out.println("------------------------");
    }
}
