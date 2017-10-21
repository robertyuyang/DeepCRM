	@Test
	void assertFalseWithBooleanTrueAndMessageSupplier() {
		try {
			assertFalse(true, () -> "test");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "test");
		}
	}
