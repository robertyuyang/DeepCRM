    public void testRedirectActionPrefixWithEmptyExtension() throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("redirectAction:" + "myAction", "");

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();
        request.setupGetServletPath("/someServletPath");
        request.setParameterMap(parameterMap);

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setContainer(container);
        defaultActionMapper.setExtensions(",,");
        ActionMapping actionMapping = defaultActionMapper.getMapping(request, configManager);


        StrutsResultSupport result = (StrutsResultSupport) actionMapping.getResult();
        assertNull(result);
    }
