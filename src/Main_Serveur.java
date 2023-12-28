import java.io.*;
import java.net.*;


class Main_Serveur extends Object
{
   public static Thread_Serveur_Jeux          Serveur_Du_Jeu;
   public static Thread_Gestion_Option_Jeux   Affichage_ET_Option;
   
   public static void main(String[] args) throws Exception 
   {  
        Serveur_Du_Jeu       = new Thread_Serveur_Jeux();
        Affichage_ET_Option  = new Thread_Gestion_Option_Jeux();

        Serveur_Du_Jeu.Initialisation       (Affichage_ET_Option);
        Affichage_ET_Option.Initialisation  (Serveur_Du_Jeu); 
              
        Affichage_ET_Option.start();
        Serveur_Du_Jeu.start();
                
    }
}

//=================================================================================================
//=================================================================================================
//=================================================================================================




