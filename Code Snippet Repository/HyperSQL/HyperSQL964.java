    public static boolean containsAny(Object[] arra, Object[] arrb) {

        mainLoop:
        for (int i = 0; i < arrb.length; i++) {
            for (int j = 0; j < arra.length; j++) {
                if (arrb[i] == arra[j] || arrb[i].equals(arra[j])) {
                    return true;
                }
            }
        }

        return false;
    }
