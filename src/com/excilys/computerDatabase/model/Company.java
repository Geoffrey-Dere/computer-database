package com.excilys.computerDatabase.model ;

/**
 * Class company <br/>
 * Represents a company
 * @author Geoffrey
 */
public class Company {

	private final long id ;
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
