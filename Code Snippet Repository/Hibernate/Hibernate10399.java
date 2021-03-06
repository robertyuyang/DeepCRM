	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		IntTestEntity ite = new IntTestEntity( 10 );
		em.persist( ite );
		id1 = ite.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		ite = em.find( IntTestEntity.class, id1 );
		ite.setNumber( 20 );
		em.getTransaction().commit();
	}
