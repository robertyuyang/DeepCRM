    public boolean first() throws SQLException {

        checkClosed();
        checkNotForwardOnly();

        if (isOnInsertRow || isRowUpdated) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_24513);
        }

        return navigator.first();
    }
