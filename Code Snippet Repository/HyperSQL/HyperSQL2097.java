    public void checkDelete(SchemaObject object) {

        if (object instanceof Table) {
            Table table = (Table) object;

            if (isFullyAccessibleByRole(table.getName())) {
                return;
            }

            Right right = (Right) fullRightsMap.get(table.getName());

            if (right != null && right.canDelete()) {
                return;
            }
        }

        throw Error.error(ErrorCode.X_42501, object.getName().name);
    }
