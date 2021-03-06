	@Test
	@SuppressWarnings( {""})
	public void testIterateWithEvictBottomOfLoop() {
		Session s = openSession();
		s.beginTransaction();
		Set parents = new HashSet();
		for (int i=0; i<5; i++) {
			Parent p = new Parent( String.valueOf( i + 100 ) );
			Child child = new Child( "child" + i );
			child.setParent( p );
			p.getChildren().add( child );
			s.save( p );
			parents.add(p);
		}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		for (Iterator it = s.createQuery( "from Parent" ).iterate(); it.hasNext(); ) {
			Parent p = (Parent) it.next();
			assertEquals( 1, p.getChildren().size() );
			s.evict(p);
		}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		for ( Object parent : parents ) {
			s.delete( parent );
		}
		s.getTransaction().commit();
		s.close();
	}
