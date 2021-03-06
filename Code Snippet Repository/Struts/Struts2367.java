    public void testWithoutFormOverriding() throws Exception {

        // we test it on textfield component, but since the tooltip are common to
        // all components, it will be the same for other components as well.
        FormTag formTag = new FormTag();
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");
        formTag.setName("myForm");


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");


        tag.setTooltip("myTooltip");
        tag.setTooltipConfig(
                "#{" +
                        "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                        "'tooltipDelay':'500', " +
                        "'jsTooltipEnabled':'true' "+
                        "}"
        );

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-1.txt"));
    }
