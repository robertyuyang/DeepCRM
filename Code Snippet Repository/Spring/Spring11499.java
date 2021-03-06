	@Test
	public void pathVariables() throws Exception {
		MockServerHttpRequest mockRequest = MockServerHttpRequest.method(HttpMethod.GET, "http://example.com").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(mockRequest);
		Map<String, String> pathVariables = Collections.singletonMap("foo", "bar");
		exchange.getAttributes().put(RouterFunctions.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathVariables);

		DefaultServerRequest request = new DefaultServerRequest(exchange, messageReaders);

		assertEquals(pathVariables, request.pathVariables());
	}
