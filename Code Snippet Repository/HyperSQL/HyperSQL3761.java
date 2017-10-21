    public void recompileDependentObjects(OrderedHashSet tableSet) {

        writeLock.lock();

        try {
            OrderedHashSet set = new OrderedHashSet();

            for (int i = 0; i < tableSet.size(); i++) {
                Table table = (Table) tableSet.get(i);

                set.addAll(getReferencesTo(table.getName()));
            }

            Session session = database.sessionManager.getSysSession();

            for (int i = 0; i < set.size(); i++) {
                HsqlName name = (HsqlName) set.get(i);

                switch (name.type) {

                    case SchemaObject.VIEW :
                    case SchemaObject.CONSTRAINT :
                    case SchemaObject.ASSERTION :
                    case SchemaObject.ROUTINE :
                    case SchemaObject.PROCEDURE :
                    case SchemaObject.FUNCTION :
                    case SchemaObject.SPECIFIC_ROUTINE :
                    case SchemaObject.TRIGGER :
                        SchemaObject object = getSchemaObject(name);

                        object.compile(session, null);
                        break;

                    default :
                }
            }

            if (Error.TRACE) {
                HsqlArrayList list = getAllTables(false);

                for (int i = 0; i < list.size(); i++) {
                    Table t = (Table) list.get(i);

                    t.verifyConstraintsIntegrity();
                }
            }
        } finally {
            writeLock.unlock();
        }
    }
