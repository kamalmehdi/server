import java.io.*;
import java.net.*;

  class Thread_Serveur_Jeux extends Thread
  {
    
    static public  ServerSocket SerSocket;
    static public  Socket Clients;
    static public  boolean Fin = false;
    static public  Thread_Gestion_Option_Jeux GESTION_ET_OPTIONS_JEUX;
    static public  int Nombre_Client=0;
    static public  int ID_Client=0;
    static public  int Port = 8080;
    
    Thread_Serveur_Jeux () throws Exception 
    {
     SerSocket = new ServerSocket(Port);
    }
    
    public void Initialisation (Thread_Gestion_Option_Jeux TGOJ) 
    {
     this.GESTION_ET_OPTIONS_JEUX = TGOJ;
     Nombre_Client=0;
    }
    
    public void run ()
    {
     while(!Fin)
     {
       try{
       Clients = SerSocket.accept();
       Nombre_Client++;
       ID_Client++;
       Thread_Communication_Clt_Srv COM_CL_SR = 
              new Thread_Communication_Clt_Srv(Clients,GESTION_ET_OPTIONS_JEUX,ID_Client);
       COM_CL_SR.start();
       GESTION_ET_OPTIONS_JEUX.ACTUALISER_AFFICHAGE();
       }
       catch(Exception e)
       {
        System.out.println("     => ERREUR DE RECEPTION DES SOCKETS ");
       }
     }
    }
}


