    public static Boolean overlaps(Session session, Object[] a, Type[] ta,
                                   Object[] b, Type[] tb) {

        Type commonType = normalizeInput(session, a, ta, b, tb, false);

        if (commonType == null) {
            return null;
        }

        if (commonType.compare(session, a[0], b[0]) > 0) {
            Object[] temp = a;

            a = b;
            b = temp;
        }

        if (commonType.compare(session, a[1], b[0]) > 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
