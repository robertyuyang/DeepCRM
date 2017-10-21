    public Result execute(Session session) {

        Result result;

        try {
            result = getResult(session);
        } catch (Throwable t) {
            result = Result.newErrorResult(t);
        }

        if (result.isError()) {
            result.getException().setStatementType(group, type);
        }

        return result;
    }
