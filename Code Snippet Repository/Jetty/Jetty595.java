    @Test
    public void testPathWithQuery() throws Exception
    {
        String name = "a";
        String value = "1";
        final String query = name + "=" + value;
        final String path = "/path";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(path, request.getRequestURI());
                Assert.assertEquals(query, request.getQueryString());
            }
        });

        String pathQuery = path + "?" + query;
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(5, TimeUnit.SECONDS)
                .path(pathQuery);

        Assert.assertEquals(path, request.getPath());
        Assert.assertEquals(query, request.getQuery());
        Assert.assertTrue(request.getURI().toString().endsWith(pathQuery));
        Fields params = request.getParams();
        Assert.assertEquals(1, params.getSize());
        Assert.assertEquals(value, params.get(name).getValue());

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
