package com.shiro;

import java.sql.SQLException;

public interface UpdateStrategy {


	 void Update(int id, String newData) throws SQLException, Exception;
	

}
