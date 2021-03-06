	@Test
	@TestForIssue( jiraKey = "" )
	public void testUnsetSessionCannotOverwriteConnectedSesssionFlushed() {
		Parent p = new Parent();
		Child c = new Child();
		p.children.add( c );

		Session s1 = openSession();
		s1.getTransaction().begin();
		s1.saveOrUpdate( p );

		// flush the session so that p.children will contain its role
		s1.flush();

		// The collection is "connected" to s1 because it contains the CollectionEntry
		CollectionEntry ce = ( (SessionImplementor) s1 ).getPersistenceContext()
				.getCollectionEntry( (PersistentCollection) p.children );
		assertNotNull( ce );

		// the collection session should be s1
		assertSame( s1, ( (AbstractPersistentCollection) p.children ).getSession() );

		Session s2 = openSession();
		s2.getTransaction().begin();

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000471:" );
		assertFalse( triggerable.wasTriggered() );

		// The following should trigger warning because we're unsetting a different session
		// We should not do this in practice; it is done here only to force the warning.
		// The collection role and key should be included in the message (no way to test that other than inspection).
		assertFalse( ( (PersistentCollection) p.children ).unsetSession( (SessionImplementor) s2 ) );

		assertTrue( triggerable.wasTriggered() );

		// collection's session should still be s1
		assertSame( s1, ( (AbstractPersistentCollection) p.children ).getSession() );

		s2.getTransaction().rollback();
		s2.close();

		s1.getTransaction().rollback();
		s1.close();
	}
