	@Test
	public void bindExistingMono() throws Exception {
		Foo foo = new Foo();
		foo.setName("Jim");
		this.bindContext.getModel().addAttribute("fooMono", Mono.just(foo));

		MethodParameter parameter = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Foo.class);
		testBindFoo("foo", parameter, value -> {
			assertEquals(Foo.class, value.getClass());
			return (Foo) value;
		});

		assertSame(foo, this.bindContext.getModel().asMap().get("foo"));
	}
