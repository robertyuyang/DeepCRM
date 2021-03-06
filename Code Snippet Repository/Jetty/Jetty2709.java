    @Test
    public void testIntegral() throws Exception
    {
        Constraint constraint0 = new Constraint();
        constraint0.setAuthenticate(false);
        constraint0.setName("integral");
        constraint0.setDataConstraint(Constraint.DC_INTEGRAL);
        ConstraintMapping mapping0 = new ConstraintMapping();
        mapping0.setPathSpec("/integral/*");
        mapping0.setConstraint(constraint0);

        _security.setConstraintMappings(Arrays.asList(new ConstraintMapping[]
                {
                        mapping0
                }));

        _server.start();

        String response;
        response = _connector.getResponse("GET /ctx/some/thing HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 404 Not Found"));

        response = _connector.getResponse("GET /ctx/integral/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 302 Found"));
        Assert.assertThat(response, Matchers.containsString("Location: BWTP://"));
        Assert.assertThat(response, Matchers.containsString(":9999"));

        response = _connectorS.getResponse("GET /ctx/integral/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 404 Not Found"));

    }
