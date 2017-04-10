package com.excilys.computerDatabase.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.excilys.computerDatabase.exception.DateException;

public class Computer {

	private final long id ; 
	private final String name ; 
	private LocalDate introduced = null ;
	private LocalDate discontinued = null ;


	public Computer(long id, String name){
		this.id = id ;
		this.name = name ;
	}

	public Computer(long id, String name, LocalDate introduced){
		this.id = id ;
		this.name = name ;
		this.introduced = introduced ;	
	}

	public Computer(long id, String name, LocalDate introduced2, LocalDate discontinued2) throws DateException{
		this.id = id ;
		this.name = name ;
		this.introduced = introduced2 ;	
		this.discontinued = discontinued2 ;

		if(this.introduced == null && this.discontinued != null ) 
			throw new DateException("Computer : introduced is null but not discontinued");

		if(this.introduced.compareTo(this.discontinued) >= 0)
			throw new DateException("Computer : the date it was discontinued must be greater than the one he was introduced");
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) throws DateException {

		if(this.introduced.compareTo(discontinued) >= 0)
			throw new DateException("Computer : the date it was discontinued must be greater than the one he was introduced");

		this.discontinued = discontinued;
	}



	public void setIntroduced(LocalDate introduced) throws DateException {

		if( introduced.compareTo(this.discontinued) >= 0)
			throw new DateException("Computer : the date it was discontinued must be greater than the one he was introduced");

		this.introduced = introduced;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

}
