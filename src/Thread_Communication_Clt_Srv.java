import java.io.*;
import java.net.*;

  class Thread_Communication_Clt_Srv extends Thread
  {
    public  Socket Communication;
    public  String NOM_CLIENT="AUCUN";
    public  boolean Fin = false;
    public  BufferedReader plec;
    public  PrintWriter pred;
    public  int ID = 0;
    
    static public  Thread_Gestion_Option_Jeux GESTION_ET_OPTIONS_JEUX;
    
    Thread_Communication_Clt_Srv (Socket COM,Thread_Gestion_Option_Jeux GOJ,int Id)
    {
     Communication = COM ;
     GESTION_ET_OPTIONS_JEUX = GOJ ;
     ID = Id ;
     try{
     plec = new BufferedReader(new InputStreamReader(Communication.getInputStream()));
     pred = new PrintWriter( new BufferedWriter(new OutputStreamWriter(Communication.getOutputStream())),true);
     NOM_CLIENT = plec.readLine();
     System.out.println("\n     => UN NOUVEAU CLIENT [ NOM = "+NOM_CLIENT+" ]");
     System.out.println("       CREATION D'UN NOUVEAU Thread de FLUX DE COMMUNICATION CLIENT - SERVEUR ");
     }catch(Exception e)
     {
       System.out.println(" ERREUR 15 ");        
     }  
    }
    
    public int Vers_Entier(String S)
    {  
     return Integer.parseInt(S);
    }

    public void ENVOYER (String S)
    {  
     pred.println(S);
    }
    
    public void run ()
    {
     try{     
       
       String MSG_CLIENT="";

       while(!Fin)
       {
           MSG_CLIENT = plec.readLine();
           if ( MSG_CLIENT.equals( Protocole_COM.DECONNEXION_CLIENT ) )
           {
            System.out.println("\n      => LE CLIENT [ "+NOM_CLIENT+" ] : OPERATION = DECONNEXION ... ");
            Fin = true ;
            GESTION_ET_OPTIONS_JEUX.ServeurJeu.Nombre_Client--;
            GESTION_ET_OPTIONS_JEUX.ACTUALISER_AFFICHAGE();
           }
           else
           if ( MSG_CLIENT.equals( Protocole_COM.DEPOSER_BILLET ) )
           {
             GESTION_ET_OPTIONS_JEUX.BILLETS[GESTION_ET_OPTIONS_JEUX.NOMBRE_BILLET]=new Billet();
              
             System.out.println("\n      => LE CLIENT [ "+NOM_CLIENT+" ] : OPERATION = DEPOSER UN BILLET ... ");
             int T[]=new int[6];
             for(int i=0;i<6;i++)
             {
              T[i]=Vers_Entier(plec.readLine());
             }
             GESTION_ET_OPTIONS_JEUX.BILLETS[GESTION_ET_OPTIONS_JEUX.NOMBRE_BILLET].Init(T); 
             GESTION_ET_OPTIONS_JEUX.BILLETS[GESTION_ET_OPTIONS_JEUX.NOMBRE_BILLET].Client = this ;
             GESTION_ET_OPTIONS_JEUX.NOMBRE_BILLET++;
             System.out.print("\n     BILLET = [ ");
             for(int i=0;i<6;i++)
             {
              System.out.print(T[i] );
              if(i<5)
              System.out.print(", "); 
             }
            System.out.println("]"); 
            GESTION_ET_OPTIONS_JEUX.ACTUALISER_AFFICHAGE();
            }
            else 
            if ( MSG_CLIENT.equals( Protocole_COM.JEU_OUVERT ) ) 
            {
             if( GESTION_ET_OPTIONS_JEUX.Etat_DE_Partie == Etat_Partie.PARTIE_FERMER)
             {
              pred.println(Protocole_COM.NON);
              System.out.println("\n     => LE CLIENT [ "+NOM_CLIENT+" ] VEUT DEPOSER UN BILLET");
              System.out.println("     => OPERATION REFUSER CAR LE SERVEUR A DEJA ANNONCER LA FERMETURE DU JEU ... "); 
              GESTION_ET_OPTIONS_JEUX.ACTUALISER_AFFICHAGE();
             }
             else pred.println(Protocole_COM.OUI);
            }
            else
            {
             System.out.println("\n       NOUVEAU MSG : \n          NOM CLIENT = "+NOM_CLIENT+" : Message = [ "+MSG_CLIENT+" ]");
             GESTION_ET_OPTIONS_JEUX.ACTUALISER_AFFICHAGE();
            }    
               
        }   
        plec.close();
        pred.close();
        Communication.close();
      }
      catch(Exception e)
      {
               
      } 
     
     }
}


