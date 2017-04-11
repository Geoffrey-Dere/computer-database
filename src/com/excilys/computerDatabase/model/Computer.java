package com.excilys.computerDatabase.model;

import java.time.LocalDate;
import com.excilys.computerDatabase.model.exception.DateException;

public class Computer {

	private long id ; 

	/**
	 * the name of the computer
	 */
	private final String name ;

	/**
	 * the date that the computer was introduced, can be null
	 */
	private LocalDate introduced = null ;

	/**
	 * the date that the computer was introduced, can be null only if the date
	 * it was introduced is also null
	 */
	private LocalDate discontinued = null ;

	
	public Computer(long id, String name){
		this.id = id ;
		this.name = name ;
	}
	
	public Computer(String name){
		this.name = name ;
		System.out.println(this.id);
	}

	public Computer(String name, LocalDate introduced) throws DateException{
		this.name = name ;	
		setIntroduced(introduced);	
	}
	
	public Computer(long id, String name, LocalDate introduced) throws DateException{
		this.id = id ;
		this.name = name ;	
		setIntroduced(introduced);	
	}

	public Computer(String name, LocalDate introduced, LocalDate discontinued) throws DateException{
		this.name = name ;
		setIntroduced(introduced);
		setDiscontinued(discontinued);		
	}
	
	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued) throws DateException{
		this.id = id ;
		this.name = name ;
		setIntroduced(introduced);
		setDiscontinued(discontinued);		
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * Set the date that it was discontinued
	 * @param discontinued, the date
	 * @throws DateException if the date it was introduced is null or greater that the date it was discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) throws DateException {

		if(this.introduced == null || this.introduced.compareTo(discontinued) >= 0)
			throw new DateException("Computer : the date it was discontinued must be greater than the one he was introduced");

		this.discontinued = discontinued;
	}


	/**
	 * set the date that it was introduced 
	 * @param introduced, the date
	 * @throws DateException, exception if the date it was discontinued is greater that the date it was introduced
	 */
	public void setIntroduced(LocalDate introduced) throws DateException {

		if( this.discontinued != null && introduced.compareTo(this.discontinued) >= 0)
			throw new DateException("Computer : the date it was discontinued must be greater than the one he was introduced");

		this.introduced = introduced;
	}

	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}
	
	/**
	 * function equals for Computer class <br/>
	 * 2 objects are equal if they have the same name and id
	 */
	@Override
	public boolean equals(Object obj) {

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		Computer company = (Computer) obj ;

		if (this.name != null ? !this.name.equals(company.name) : company.name != null)
			return false ;

		return this.id == company.id ;	
	}

	
	public String toString(){
		StringBuilder sb = new StringBuilder("Computer(");
		sb.append(id).append(",").append(name).append(",").
		append(introduced).append(",").append(discontinued).append(")");
		return sb.toString();
		
	}
}
