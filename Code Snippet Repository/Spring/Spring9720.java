	@Override
	@Nullable
	public Object getValue(ELContext elContext, @Nullable Object base, Object property) throws ELException {
		if (base != null) {
			if (base instanceof WebApplicationContext) {
				WebApplicationContext wac = (WebApplicationContext) base;
				String beanName = property.toString();
				if (logger.isTraceEnabled()) {
					logger.trace("Attempting to resolve property '" + beanName + "' in root WebApplicationContext");
				}
				if (wac.containsBean(beanName)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Successfully resolved property '" + beanName + "' in root WebApplicationContext");
					}
					elContext.setPropertyResolved(true);
					try {
						return wac.getBean(beanName);
					}
					catch (BeansException ex) {
						throw new ELException(ex);
					}
				}
				else {
					// Mimic standard JSF/JSP behavior when base is a Map by returning null.
					return null;
				}
			}
		}
		else {
			if (WEB_APPLICATION_CONTEXT_VARIABLE_NAME.equals(property)) {
				elContext.setPropertyResolved(true);
				return getWebApplicationContext(elContext);
			}
		}

		return null;
	}
