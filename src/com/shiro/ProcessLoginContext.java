package com.shiro;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// open close rule
// open for extensions but closed for modification
// stategy design pattern
public class ProcessLoginContext {
	
    private ProcessLoginBy processLoginStrategy;

    public ProcessLoginContext(ProcessLoginBy processLoginStrategy) {
        this.processLoginStrategy = processLoginStrategy;
    }

    public void setProcessStrategy(ProcessLoginBy processLoginStrategy) {
        this.processLoginStrategy = processLoginStrategy;
    }

    public boolean performProcess(String input,String password, Admin an) throws ClassNotFoundException, SQLException {
    	boolean valid = processLoginStrategy.process(input,password,an);
    	return valid;
    }
}
 	
 



