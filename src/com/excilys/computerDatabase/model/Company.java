package com.excilys.computerDatabase.model ;

/**
 * Class company <br/>
 * Represents a company
 * @author Geoffrey
 */
public class Company {

	/**
	 * the id of the company
	 */
	private final long id ;

	/**
	 * the name of the company
	 */
	private String name ;

	public Company(long id, String name) {
		this.id = id ;
		this.name = name ;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * function equals for Company class <br/>
	 * 2 objects 'Company' are equal if they have the same name and id
	 */
	@Override
	public boolean equals(Object obj) {

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		Company company = (Company) obj ;

		if (this.name != null ? !this.name.equals(company.name) : company.name != null)
			return false ;

		return this.id == company.id ;	
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Company");
		sb.append("(").append(this.id).append(" , ").append(this.name).append(")");

		return sb.toString();
	}	

}
