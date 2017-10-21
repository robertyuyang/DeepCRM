    public void testFirstPeriodOverlapsSecondPeriod() throws SQLException {
    	
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) OVERLAPS PERIOD (?, ?);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-01-01 01:02:03'");

    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-01-12 01:02:03'", 1);

    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-12-31 01:02:03'", 1, 2, 3);

    	executeAndTestQuery(stmt, "TIMESTAMP '2000-04-01 01:02:03'", "TIMESTAMP '2000-05-01 01:02:03'");

    	stmt.close();
    }
