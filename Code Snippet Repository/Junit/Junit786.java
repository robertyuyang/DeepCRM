	@Test
	void resolvesMethodSelectorForTwoMethodsOfSameClass() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectMethod(testClass, testClass.getMethod("failingTest")),
			selectMethod(testClass, testClass.getMethod("successfulTest"))).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		List<TestDescriptor> testMethodDescriptors = new ArrayList<>(runnerDescriptor.getChildren());
		assertThat(testMethodDescriptors).hasSize(2);

		TestDescriptor failingTest = testMethodDescriptors.get(0);
		assertTestMethodDescriptor(failingTest, testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));

		TestDescriptor successfulTest = testMethodDescriptors.get(1);
		assertTestMethodDescriptor(successfulTest, testClass, "successfulTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}
