package com.excilys.computerDatabase.model;

import java.time.LocalDate;
import com.excilys.computerDatabase.model.exception.DateException;

public class Computer {

	private long id ; 

	/**
	 * the name of the computer
	 */
	private String name ;

	/**
	 * the date that the computer was introduced, can be null
	 */
	private LocalDate introduced = null ;

	/**
	 * the date that the computer was introduced, can be null only if the date
	 * it was introduced is also null
	 */
	private LocalDate discontinued = null ;


	private Computer(BuilderComputer builder){
		this.id = builder.id ;
		this.name = builder.name;
		this.introduced = builder.introduced ;
		this.discontinued = builder.discontinued ;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * Set the date that it was discontinued
	 * @param discontinued, the date
	 */
	public void setDiscontinued(LocalDate discontinued) throws DateException {
		this.discontinued = discontinued;
	}

	public void setDate(LocalDate intro, LocalDate discon) throws DateException {
		this.introduced = intro ;
		this.discontinued = discon ;
	}


	/**
	 * set the date that it was introduced 
	 * @param introduced, the date
	 */
	public void setIntroduced(LocalDate introduced) {
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


	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Computer(");
		sb.append(id).append(" , ").append(name).append(" , ").
		append(introduced).append(" , ").append(discontinued).append(")");
		return sb.toString();

	}

	@Override
	public int hashCode() {
		return introduced.hashCode() + this.discontinued.hashCode() + name.hashCode();
	}


	public static class BuilderComputer{

		private long id ;
		private String name ; 
		private LocalDate introduced ;
		private LocalDate discontinued ;

		public BuilderComputer(String name){
			this.name = name ;
		}

		public BuilderComputer id(long id){
			this.id = id ;
			return this ;
		}
		
		public BuilderComputer introduced(LocalDate introduced){
			this.introduced = introduced ;
			return this ;
		}

		public BuilderComputer discontinued(LocalDate discontinued){
			this.discontinued = discontinued ;
			return this ;
		}

		public Computer build(){
			return new Computer(this);
		}

	}
}
