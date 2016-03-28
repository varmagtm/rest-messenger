package com.techcrux.rest.messenger.util;

public class MessengerAppConstants {

	public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";

	public static final String HIBERNATE_DIALECT = "${" + HIBERNATE_DIALECT_PROPERTY + "}";

	public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";

	public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS_PROPERTY = "current_session_context_class";

	public static final String HIBERNATE_CONNECTION_DATASOURCE = "hibernate.connection.datasource";

	public static final String ENTITIES_PACKAGE = "com.techcrux.rest.messenger.entities";

}
