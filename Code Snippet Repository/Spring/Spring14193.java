	@After
	public void teardown() throws Exception {
		try {
			this.sockJsClient.stop();
		}
		catch (Throwable ex) {
			logger.error("Failed to stop SockJsClient", ex);
		}
		try {
			this.server.undeployConfig();
		}
		catch (Throwable t) {
			logger.error("Failed to undeploy application config", t);
		}
		try {
			this.server.stop();
		}
		catch (Throwable t) {
			logger.error("Failed to stop server", t);
		}
		try {
			this.wac.close();
		}
		catch (Throwable t) {
			logger.error("Failed to close WebApplicationContext", t);
		}
	}
