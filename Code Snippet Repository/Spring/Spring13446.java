	@Test
	public void collectionOfColoursNotSelected() throws Exception {
		this.tag.setPath("otherColours");
		this.tag.setValue("PURPLE");

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element checkboxElement = (Element) document.getRootElement().elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("checkbox", checkboxElement.attribute("type").getValue());
		assertEquals("otherColours", checkboxElement.attribute("name").getValue());
		assertNull(checkboxElement.attribute("checked"));
	}
