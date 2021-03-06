    public void testRenderFieldError() {
        tag.setCssClass("class");
        tag.setCssStyle("style");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<ul style='style' class='class'><li><span>not good</span></li><li><span>bad</span></li><li><span>bad to the bone</span></li></ul>");
        assertEquals(expected, output);
    }
