    public Object[] toValuesArray(Object[] a) {

        int size = size();

        if (a == null || a.length < size) {
            a = new Object[size];
        }

        for (int i = 0; i < size; i++) {
            a[i] = super.objectValueTable[i];
        }

        return a;
    }
