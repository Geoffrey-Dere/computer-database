package com.excilys.computerDatabase.persistence;

public abstract class DaoFactory {

	public static final int MYSQL_FACTORY = 0 ;

	public abstract DAO getCompanyDAO();

	public static DaoFactory getFactory(int choice){
		switch(choice){
		case MYSQL_FACTORY :
			return MysqlDAOFactory.getInstance() ;

		default:
			return null ;
		}
	}
}
