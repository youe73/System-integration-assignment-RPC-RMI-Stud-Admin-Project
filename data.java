import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

import static java.lang.Integer.valueOf;

public class data {
    String stud;
    String stud2;
    ArrayList<String> second = new ArrayList<String>();

    public void createfromfile() {
        Scanner scanner;

        PrintWriter pw = null;
        PrintWriter pw2 = null;

        {
            try {
                scanner = new Scanner(new File("students.txt"));
                int id =0;
                try {
                    pw = new PrintWriter(new FileWriter("firstsemesterstudents.txt"));
                    pw2 = new PrintWriter(new FileWriter("secondsemesterstudent.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] spl = line.split("\n");

                    for (String a:spl) {
                        String[] attr = a.split(",");
                        id ++;
                        String name = attr[0];
                        int semester = Integer.parseInt(attr[1]);

                        if(semester==1)
                        {
                            stud = id + ", " + name + ", " + semester +"\n";
                            pw.write(stud);
                            System.out.println("1st semester : " + stud);

                        } else {

                            stud2 = id + ", " + name + ", " + semester + "\n";
                            pw2.write(stud2);
                            /*second.add(stud2);*/
                            System.out.println("2st semester : " + stud2);
                        }

                    }

                }
                pw.close();
                pw2.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
/*
    public void readfromfileToDB()
    {
        Connect c = new Connect();
        c.connection();


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



                c.insertData(name, semesters);

                System.out.println(name + "  " + semesters + " have been inserted in database");
            }

        }
    }
    */



}
