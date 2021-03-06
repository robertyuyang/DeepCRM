	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			person.getPhones().add( new Phone( 1L, "landline", "028-234-9876" ) );
			person.getPhones().add( new Phone( 2L, "mobile", "072-122-9876" ) );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			Set<Phone> phones = person.getPhones();
			Assert.assertEquals( 2, phones.size() );
			phones.stream().forEach( phone -> log.infov( "Phone number %s", phone.getNumber() ) );
			phones.remove( phones.iterator().next() );
			Assert.assertEquals( 1, phones.size() );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			Set<Phone> phones = person.getPhones();
			Assert.assertEquals( 1, phones.size() );
		} );
	}
