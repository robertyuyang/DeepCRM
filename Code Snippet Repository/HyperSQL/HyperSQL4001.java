    public ResultMetaData getResultMetaData() {

        if (resultMetaData != null) {
            return resultMetaData;
        }

        switch (type) {

            case StatementTypes.CALL : {
                if (expression == null) {
                    return ResultMetaData.emptyResultMetaData;
                }

                // TODO:
                //
                // 1.) standard to register metadata for columns of
                // the primary result set, if any, generated by call
                //
                // 2.) Represent the return value, if any (which is
                // not, in truth, a result set), as an OUT parameter
                //
                // For now, I've reverted a bunch of code I had in place
                // and instead simply reflect things as the are, describing
                // a single column result set that communicates
                // the return value.  If the expression generating the
                // return value has a void return type, a result set
                // is described whose single column is of type NULL
                ResultMetaData md = ResultMetaData.newResultMetaData(1);
                ColumnBase column =
                    new ColumnBase(null, null, null,
                                   StatementDMQL.RETURN_COLUMN_NAME);

                column.setType(expression.getDataType());

                md.columns[0] = column;

                md.prepareData();

                resultMetaData = md;

                return md;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "StatementProcedure");
        }
    }
