package com.grupogloria.prsdalsrvconproducto.registration.constants;

import java.util.ArrayList;
import java.util.List;

public class GlobalConstants {
	public static final String ExceptionOccured = "ExceptionOccured";
	public static final String ID_TRANSACTION = "idTransaccion";
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int ACCEPTED = 202;
	public static final int RESET_CONTENT = 205;
	public static final int PARTIAL_CONTENT = 206;
	public static final int INTERNAL_ERROR = 503;
	public static final List<Integer> successfulRequests = new ArrayList<Integer>() {
		{
			add(GlobalConstants.OK);
			add(GlobalConstants.CREATED);
			add(GlobalConstants.ACCEPTED);
			add(GlobalConstants.RESET_CONTENT);
			add(GlobalConstants.PARTIAL_CONTENT);
		}
	};
}
