	@Test
	public void testHistoryOfId1() {
		ComponentTestEntity ver1 = new ComponentTestEntity( id1, new Component1( "a" ) );
		ComponentTestEntity ver2 = new ComponentTestEntity( id1, new Component1( "b" ) );
		ComponentTestEntity ver3 = new ComponentTestEntity( id1, new Component1( "c" ) );

		assert getAuditReader().find( ComponentTestEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id1, 2 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id1, 3 ).equals( ver3 );
	}
