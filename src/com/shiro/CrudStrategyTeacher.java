package com.shiro;

import java.sql.SQLException;

public interface CrudStrategyTeacher {
	void process(Teacher teacher) throws SQLException, ClassNotFoundException, Exception;
}
