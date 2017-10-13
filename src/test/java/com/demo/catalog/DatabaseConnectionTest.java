package com.demo.catalog;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.demo.catalog.config.MvcConfiguration;

public class DatabaseConnectionTest extends MvcConfiguration{

	@Test
	public void test() {
		assertTrue("Unable to Connect to the Database", isDbConnected());
	}

	boolean isDbConnected() {
	    final String CHECK_SQL_QUERY = "SELECT 1";
	    boolean isConnected = false;
	    try {
			getDataSource().getConnection().prepareStatement(CHECK_SQL_QUERY);
	        isConnected = true;
	    } catch (SQLException sqle) {
	    	sqle.printStackTrace();
	    } catch (NullPointerException npe){
	    	npe.printStackTrace();
	    }
	    return isConnected;
	}
}
