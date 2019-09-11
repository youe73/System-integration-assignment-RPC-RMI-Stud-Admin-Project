import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    public static int ans;

    public static void main(String[] args)
    {
        try
        {
            String remoteEngine = "rmi://localhost:1999/Compute";
            /*String remoteEngine = "rmi://192.168.1.15:1999/Compute";*/


            getStudent obj = (getStudent) Naming.lookup(remoteEngine);


            System.out.println("Enter 1 for first semester or 2 for second semester ");
            Scanner input = new Scanner(System.in);

            ans = input.nextInt();


                if(ans == 1)
                {
                    String a = obj.queryfirstsemester();

                    System.out.println("Server respons: First semester read from textfile " + a);


                } else if (ans == 2)
                {
                    String b = obj.querysecondsemester();
                    System.out.println("Server respons: Second semester read from DB:"  + b);
                }
                else {

                    System.out.println("Invalid action....");
                    return;
                }


        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }
}
