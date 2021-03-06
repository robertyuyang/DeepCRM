	void transformMoveWithDirtySimpleProperty() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Node node3 = (Node) s.load(Node.class, new Integer(3));
		Node node2 = node3.getParentNode();
		Node node1 = node2.getParentNode();

		node2.removeSubNode( node3 );
		node1.addSubNode( node3 );
		node3.setDescription( "node3-updated" );

		tx.commit();
		s.close();
	}
