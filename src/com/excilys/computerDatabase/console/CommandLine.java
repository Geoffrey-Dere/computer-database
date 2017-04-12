package com.excilys.computerDatabase.console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.exception.DateException;
import com.excilys.computerDatabase.service.CompanyService;
import com.excilys.computerDatabase.service.ComputerService;

public class CommandLine {


	private CompanyService companyService ;
	private ComputerService computerService ;
	private Scanner scanner ;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

	public CommandLine() {
		this.scanner = new Scanner(System.in);
		this.companyService = new CompanyService();
		this.computerService = new ComputerService();
	}



	public void run() {

		String choix ;
		boolean run = true ;

		printMenu();		
		while(run){

			System.out.print("your choice : " );
			choix = scanner.next();

			switch (choix){
			case "q" :
				run = false ;
				break ;
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
			default :
				printMenu();
			}
		}
	}

	private void displayComputers() {
		for(Computer computer : this.computerService.getAllComputers()){
			System.out.println(computer);
		}
	}
	
	private void displayCompanies() {
		for(Company company : this.companyService.getAllCompanies()){
			System.out.println(company);
		}
	}


	private void displayOneComputer() {

		int id = this.nextInt("Enter the id of the Computer");

		Optional<Computer> computer = this.computerService.getComputer(id);

		if(computer.isPresent()){
			System.out.println(computer.get());
		} else {
			System.out.println("no computer with id " + id );
		}
	}

	private void displayOneCompany() {

		int id = this.nextInt("Enter the id of the company");

		Optional<Company> company = this.companyService.getCompany(id);

		if(company.isPresent()){
			System.out.println(company.get());
		} else {
			System.out.println("no company with id " + id );
		}
	}


	private void deleteOneComputer() {

		int id = this.nextInt("Enter the id of the Computer");

		Optional<Computer> computer = this.computerService.getComputer(id);
		
		if(computer.isPresent() && this.computerService.removeComputer(computer.get()))
			System.out.print("the computer has been deleted");
		else
			System.out.print("the computer hasn't been deleted");

	}

	private void updateOneComputer() {
		int id = this.nextInt("Enter the id of the Computer");

		String name ; 
		LocalDate intro = null ;
		LocalDate discon = null ;

		Optional<Computer> computer = this.computerService.getComputer(id);

		if(computer.isPresent()){

			System.out.println("enter the new name : ");
			name = scanner.nextLine();

			System.out.println(	"New introducing date (yyyy-MMM-dd)");
			intro = LocalDate.parse( this.scanner.next() , formatter);

			System.out.println(	"New discontinuing date (yyyy-MMM-dd)");
			discon = LocalDate.parse( this.scanner.next() , formatter);

			try {
				computer.get().setName(name);
				computer.get().setDate(intro, discon);
				this.computerService.updateComputer(computer.get());
			} catch (DateException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("no computer with id " + id );
		}

	}


	private int nextInt(String msg){

		int id = -1 ;
		while (id != -1){
			try {
				System.out.print(msg);
				id = scanner.nextInt();
			} catch (java.util.InputMismatchException e){
			}
		}	
		return id ;
	}


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
