	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof BasicTestEntity2) ) {
			return false;
		}

		BasicTestEntity2 that = (BasicTestEntity2) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( str1 != null ? !str1.equals( that.str1 ) : that.str1 != null ) {
			return false;
		}
		if ( str2 != null ? !str2.equals( that.str2 ) : that.str2 != null ) {
			return false;
		}

		return true;
	}
