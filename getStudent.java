import java.rmi.Remote;
import java.rmi.RemoteException;

public interface getStudent extends Remote
{
    public String queryfirstsemester() throws RemoteException;
    public String querysecondsemester() throws RemoteException;
}