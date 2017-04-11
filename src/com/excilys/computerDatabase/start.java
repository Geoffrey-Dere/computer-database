package com.excilys.computerDatabase ;

import com.excilys.computerDatabase.persistence.DaoFactory;

public class start {

	public start() {

	}

	public static void main(String[] args){
		DaoFactory.getFactory(DaoFactory.MYSQL_FACTORY);
	}

}
