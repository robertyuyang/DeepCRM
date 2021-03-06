		@Advice.OnMethodExit
		static void exit(@Advice.This Object self, @Advice.Argument(0) Map<?, ?> argument, @MappedBy String mappedBy) {
			if ( argument != null && Hibernate.isPropertyInitialized( argument, mappedBy ) ) {
				Object[] array = argument.values().toArray();
				for ( int i = 0; i < array.length; i++ ) {
					if ( Hibernate.isPropertyInitialized( array[i], mappedBy ) && getter( array[i] ) != self ) {
						setterSelf( array[i], self );
					}
				}
			}
		}
