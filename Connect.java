import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Connect {


    public Connection connection() {

        String url = "jdbc:sqlite:C:/sqlite/cphbusiness.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void createTable()
    {
        String url = "jdbc:sqlite:C:/sqlite/cphbusiness.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " semester integer\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table students created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String name, int semester) {
        String sql = "INSERT INTO students(name, semester) VALUES(?,?)";

        try{
            Connection conn = this.connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectAll(){
        String sql = "SELECT * FROM students";

        try {
            Connection conn = this.connection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("semester"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readfromfileToDB()
    {
        Connection conn = this.connection();

        Scanner scanner=null;
        try {
            scanner = new Scanner(new File("secondsemesterstudent.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String lines = scanner.nextLine();
            String[] splitline = lines.split("\n");

            for (String a:splitline) {
                String[] attr = a.split(",");

                String name = attr[1];
                String semester = attr[2];
                int semesters = Integer.parseInt(semester.trim());

                insert(name, semesters);

                System.out.println(name + "  " + semesters + " have been inserted in database");
            }

        }
    }
}