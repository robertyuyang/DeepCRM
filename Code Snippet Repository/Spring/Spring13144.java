	@Test
	public void dataClassBindingWithAdditionalSetter() throws Exception {
		initServletWithControllers(DataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param1", "value1");
		request.addParameter("param2", "true");
		request.addParameter("param3", "3");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("value1-true-3", response.getContentAsString());
	}
