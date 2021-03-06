	@Test
	@TestForIssue( jiraKey = "")
	public void testEmbeddableWithEntityKey() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Currency currency1= new Currency();
		Currency currency2= new Currency();
		s.persist( currency1 );
		s.persist( currency2 );
		Integer id1 = currency1.getId();
		Integer id2 = currency2.getId();
		ExchangeRateKey cq = new ExchangeRateKey(20140101, currency1, currency2);

		ExchangeOffice wm = new ExchangeOffice();
		s.persist( wm );

		final BigDecimal fee = BigDecimal.valueOf( 12, 2 );

		wm.getExchangeRateFees().put( cq, fee );
		Integer id = wm.getId();
		s.flush();
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		wm = (ExchangeOffice) s.byId(ExchangeOffice.class).load(id);
		assertNotNull(wm);
		wm.getExchangeRateFees().size();
		currency1 = (Currency) s.byId(Currency.class).load(id1);
		assertNotNull(currency1);
		currency2 = (Currency) s.byId(Currency.class).load(id2);
		assertNotNull(currency2);
		cq = new ExchangeRateKey(20140101, currency1, currency2);

		assertEquals( fee, wm.getExchangeRateFees().get( cq ) );

		tx.commit();
		s.close();
	}
