	protected ParentWithCollection createParentWithOneChild(String parentName, String ChildName) {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ParentWithCollection parent = createParent( parentName );
		parent.newChildren( createCollection() );
		parent.addChild( ChildName );
		s.save( parent );
		tx.commit();
		s.close();
		return parent;
	}
