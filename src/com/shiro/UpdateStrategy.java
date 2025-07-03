package com.shiro;

import java.sql.SQLException;

public interface UpdateStrategy {


	 void Update(String id, String newData) throws SQLException, Exception;
	

}
