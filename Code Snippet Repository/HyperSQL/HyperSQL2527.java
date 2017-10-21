    public void testAggregatedGroupByHaving2() throws SQLException {

        String sql = "select avg(salary), max(id) from employee "
                     + "group by superior_id "
                     + "having superior_id is not null "
                     + "order by superior_id " + "";
        Object[][] expected = new Object[][] {
            {
                new Double(42500), new Integer(13)
            }, {
                new Double(45000), new Integer(23)
            },
        };

        compareResults(sql, expected, "00000");
    }
