package pei.java.thirdp.lab.DB;


/**
 * 
 * @author pei
 *
 */
public class DbUtils {
    
    // 
    public static final String USERNAME = "username";
    public static final String PASSWORD= "password";
    public static final String TABLE_CUSTOMERS = "CUSTOMERS";
    
    // SQLs
    public static final String SQL_CREATE_TABLE_CUSTOMERS =  "create table " + TABLE_CUSTOMERS 
              + "(id integer not null generated always as identity (start with 1, increment by 1), "
              + "CustomerName varchar(50) not null, "
              + "ContactName varchar(50) not null, "
              + "Address varchar(100), "
              + "City varchar(30), "
              + "PostalCode varchar(10), "
              + "Country varchar(30), "
              + "constraint primary_key primary key (id))";    

    
    public static final String sqlSelectFrom(String column, String table) {
        return String.format("select %s from %s", column, table);
    }

    public static final String sqlDropTable(String table) {
        return String.format("drop table %s", table);
    }

    public static final String sqlClearTable(String table) {
    	return String.format("delete from %s", table);
    }
}
