    public void putAll(HashMap t) {

        Iterator it = t.keySet.iterator();

        while (it.hasNext()) {
            Object key = it.next();

            put(key, t.get(key));
        }
    }
