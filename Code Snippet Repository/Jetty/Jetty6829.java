    @Test
    public void testOutgoingPing() throws IOException
    {
        OutgoingFramesCapture capture = new OutgoingFramesCapture();

        FragmentExtension ext = new FragmentExtension();
        ext.setBufferPool(bufferPool);
        ext.setPolicy(WebSocketPolicy.newServerPolicy());
        ExtensionConfig config = ExtensionConfig.parse("fragment;maxLength=4");
        ext.setConfig(config);

        ext.setNextOutgoingFrames(capture);

        String payload = "Are you there?";
        Frame ping = new PingFrame().setPayload(payload);

        ext.outgoingFrame(ping, null, BatchMode.OFF);

        capture.assertFrameCount(1);
        capture.assertHasFrame(OpCode.PING, 1);

        WebSocketFrame actual = capture.getFrames().getFirst();

        Assert.assertThat("Frame.opcode", actual.getOpCode(), is(OpCode.PING));
        Assert.assertThat("Frame.fin", actual.isFin(), is(true));
        Assert.assertThat("Frame.rsv1", actual.isRsv1(), is(false));
        Assert.assertThat("Frame.rsv2", actual.isRsv2(), is(false));
        Assert.assertThat("Frame.rsv3", actual.isRsv3(), is(false));

        ByteBuffer expected = BufferUtil.toBuffer(payload, StandardCharsets.UTF_8);
        Assert.assertThat("Frame.payloadLength", actual.getPayloadLength(), is(expected.remaining()));
        ByteBufferAssert.assertEquals("Frame.payload", expected, actual.getPayload().slice());
    }
