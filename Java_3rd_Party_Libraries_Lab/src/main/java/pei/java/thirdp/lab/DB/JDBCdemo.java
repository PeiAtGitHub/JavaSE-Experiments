package pei.java.thirdp.lab.DB;
import static pei.java.thirdp.lab.DB.DbUtils.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class JDBCdemo {
    
    
    @Test
    public void jdbcDemo() throws Exception    {
        
        try (Connection conn = startEmbeddedDB(JDBC_URL_DERBY, USERNAME, PASSWORD);
              Statement stmt = conn.createStatement()){
            if(isTableExisting(conn, USERNAME, TABLE_CUSTOMERS)) {
                if(isTableEmpty(stmt, TABLE_CUSTOMERS)) {
                    System.out.format("Table %s existing but Empty.%n", TABLE_CUSTOMERS);
                    insertSomeDataToTable(conn, TABLE_CUSTOMERS);    
                }else {
                    System.out.format("Table %s existing and NOT Empty.%n", TABLE_CUSTOMERS);
                }
            }else {
                System.out.format("Table %s NOT existing.%n", TABLE_CUSTOMERS);
                stmt.execute(SQL_CREATE_TABLE_CUSTOMERS);
                System.out.format("Table %s Created!%n", TABLE_CUSTOMERS);
                insertSomeDataToTable(conn, TABLE_CUSTOMERS);
            }
            // 
            displayTableContents(stmt, TABLE_CUSTOMERS);
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            shutDownDerby();
        }
        
    }
    
    
    /*
     * Util methods
     */
    public static Connection startEmbeddedDB(String jdbcUrl, String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Embedded DB started! JDBC URL: " + jdbcUrl);
        return conn;
    }
    
    public static void shutDownDerby() {
        try {
            DriverManager.getConnection(JDBC_URL_SHUTDOWN_DERBY);
        } catch (SQLException ex) {
            if ((ex.getErrorCode() == 50000) && (ex.getSQLState().equals("XJ015"))) {
                System.out.println("Derby shut down normally.");
            } else {
                System.err.format("Derby did not shut down normally:%n%s", ex.getMessage());
            }
        }
    }
    
    
    public static void insertSomeDataToTable(Connection conn, String table) throws Exception {
        
        switch (table) {
        case TABLE_CUSTOMERS:
            try (PreparedStatement pstmt = conn.prepareStatement("insert into " + TABLE_CUSTOMERS
                    + " (CustomerName, ContactName, Address, City, PostalCode, Country)" 
                    + " values(?,?,?,?,?,?)")) {
                pstmt.setString(1, "Tom Corporation");
                pstmt.setString(2, "Tom Cat");
                pstmt.setString(3, "Tom Str. 11");
                pstmt.setString(4, "San Francisco");
                pstmt.setString(5, "1234567890");
                pstmt.setString(6, "USA");
                pstmt.executeUpdate();
            }
            break;
        default:
            throw new Exception("Unsupported table: " + table);
        }
        
        System.out.format("Data insertion to table %s done!%n", table);
        
    }
    
    
    public static void displayTableContents(Statement stmt, String table) throws Exception {
        try (ResultSet rs = stmt.executeQuery(sqlSelectFrom("*", table.toUpperCase()))) {
            System.out.format("Table %s has %d entry(s):%n", table, rs.getFetchSize());
            switch (table) {
            case TABLE_CUSTOMERS:
                while (rs.next()) {
                    System.out.format("%d, %s, %s, %s, %s, %s, %s%n", rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }
                break;
            default:
                throw new Exception("Unsupported table: " + table);
            }

        }

    }
    
    
    public static boolean isTableExisting(Connection conn, String schema, String table) throws SQLException {
        return conn.getMetaData().getTables(null, schema.toUpperCase(), table.toUpperCase(), null).next();
    }
    
    
    public static boolean isTableEmpty (Statement stmt, String table) throws SQLException {
        return !(stmt.executeQuery(sqlSelectFrom("*", table.toUpperCase())).next());
    }
    
}

