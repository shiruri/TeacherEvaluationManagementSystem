package com.shiro;

import java.sql.SQLException;

interface ProcessLoginBy {
		boolean process(String input,String password,Admin an) throws SQLException, ClassNotFoundException;
	}