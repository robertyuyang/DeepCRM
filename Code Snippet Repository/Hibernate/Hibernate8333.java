	@Test
	public void testBatching() throws SQLException {
		doInHibernate( this::sessionFactory, session -> {
			Person father = new Person();
			Person mother = new Person();
			Person son = new Person();
			Person daughter = new Person();

			Address home = new Address();
			Address office = new Address();

			father.address = home;
			son.address = home;
			mother.address = office;
			daughter.address = office;

			session.persist( home );
			session.persist( office );

			session.persist( father );
			session.persist( mother );
			session.persist( son );
			session.persist( daughter );

			connectionProvider.clear();
		} );

		PreparedStatement addressPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Address (ID) values (?)" );
		verify( addressPreparedStatement, times( 2 ) ).addBatch();
		verify( addressPreparedStatement, times( 1 ) ).executeBatch();
		PreparedStatement personPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Person (address_ID, ID) values (?, ?)" );
		verify( personPreparedStatement, times( 4 ) ).addBatch();
		verify( personPreparedStatement, times( 1 ) ).executeBatch();
	}
