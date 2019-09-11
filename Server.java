import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {


    public static void main(String[] args) throws RemoteException {

        try
        {
            System.out.println("RMI server starts");

            Registry registry = LocateRegistry.createRegistry(1999);

            System.out.println("RMI registry created ");

            String engineName = "Compute";

            getStudent useInterface = new getStudentImplementation();

            Naming.rebind("//localhost:1999/" + engineName, useInterface);
            System.out.println("Engine " + engineName + " bound in registry");


        }
        catch (Exception e)
        {
            System.err.println("Exception:" + e);
        }



    }
}
