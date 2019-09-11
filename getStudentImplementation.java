import java.rmi.*;
import java.rmi.server.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class getStudentImplementation extends UnicastRemoteObject
        implements getStudent
{

    getStudentImplementation() throws RemoteException
    {
        super();
    }

    public String queryfirstsemester() {
        Scanner scanner = null;
        String outputname="";
        try {
            scanner = new Scanner(new File("firstsemesterstudents.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String lines = scanner.nextLine();
            String[] splitline = lines.split("\n");

            for (String a : splitline) {
                String[] attr = a.split(",");

                String name = attr[1];
                String semester = attr[2];
                int semesters = Integer.parseInt(semester.trim());
                outputname+=name+semester+"\n";
                System.out.println(name + "  " + semesters);

            }

        }

        return outputname;
    }

    public String querysecondsemester()
    {
        String url = "jdbc:sqlite:C:/sqlite/cphbusiness.db";
        Connection conn = null;
        String outputname = "";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = "SELECT * FROM students";

        try {

            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("semester"));
                outputname += rs.getString("name")+rs.getInt("semester") + " \n";

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return outputname;
    }

}
