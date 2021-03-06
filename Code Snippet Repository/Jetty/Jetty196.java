    public static <T> ScopedInstance<T> newInstance(Class<T> clazz)
    {
        BeanManager bm = CDI.current().getBeanManager();

        ScopedInstance sbean = new ScopedInstance();
        Set<Bean<?>> beans = bm.getBeans(clazz,AnyLiteral.INSTANCE);
        if (beans.size() > 0)
        {
            sbean.bean = beans.iterator().next();
            sbean.creationalContext = bm.createCreationalContext(sbean.bean);
            sbean.instance = bm.getReference(sbean.bean,clazz,sbean.creationalContext);
            return sbean;
        }
        else
        {
            throw new RuntimeException(String.format("Can't find class %s",clazz));
        }
    }
