	@Test
	public void fromHttpRequestMultipleForwardedHeader() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Forwarded", "host=84.198.58.199;proto=https");
		request.addHeader("Forwarded", "proto=ftp; host=1.2.3.4");
		request.setScheme("http");
		request.setServerName("example.com");
		request.setRequestURI("/rest/mobile/users/1");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("https", result.getScheme());
		assertEquals("84.198.58.199", result.getHost());
		assertEquals("/rest/mobile/users/1", result.getPath());
	}
