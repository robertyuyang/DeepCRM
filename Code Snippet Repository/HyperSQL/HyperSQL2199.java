    protected IntervalSecondData readDaySecondInterval(Type type) {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        return (IntervalSecondData) scanner.newInterval(s,
                (IntervalType) type);
    }
