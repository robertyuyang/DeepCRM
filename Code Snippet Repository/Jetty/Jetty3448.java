    @Test
    public void testWriteAndSetContentLengthTooSmallAndThrow() throws Exception
    {
        server.setHandler(new WriteAndSetContentLengthTooSmallHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        // Setting a content-length too small throws an IllegalStateException
        assertThat("response code", response.getStatus(), is(500));
        assertThat("response body", response.getContent(), not(is("foo")));
    }
