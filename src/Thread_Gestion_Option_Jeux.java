import java.io.*;
import java.net.*;
  
  class Thread_Gestion_Option_Jeux extends Thread
  {
    static public boolean Fin = false;
    static public int NOMBRE_BILLET = 0 ;
    static public Thread_Serveur_Jeux ServeurJeu ;
    static public boolean Etat_DE_Partie = Etat_Partie.PARTIE_OUVERTE ;
    static public Billet [] BILLETS = new Billet [20];
    
    
   public void MENU()
   {
    System.out.println("\n __________________________________________________________________________\n");
    System.out.println(" *************  UNIVERSITE DE BEJAIA  "+(char)184+"  2007-2008  ************* ");
    System.out.println(" ******************  JEU DE LOTO CILENT/SERVEUR  **************** ");
    System.out.println(" *************************_ LE SERVEUR _************************* ");

    System.out.println("\n       "+(char)26+" INFOS :  "+(char)4+" Nombre Client      = "+ ServeurJeu.Nombre_Client+" Client(s)");
    System.out.println("                  "+(char)4+" Etat du Jeu        = "+((Etat_DE_Partie==true)?"[ OUVERT + ]":"[ FERMER - ]"));
    System.out.println("                  "+(char)4+" NB Billet Deposer  = "+NOMBRE_BILLET+" Billet => "+NOMBRE_BILLET*20+" $");
    System.out.println("                  "+(char)4+" Etat Serveur       = CONNECTER ");
    System.out.println();
    System.out.println("   1 "+(char)26+" OUVERTURE-FERMETURE DU JEU "+((Etat_DE_Partie==true)?"[ OUVERT + ]":"[ FERMER - ]")+" ");/*-----------------");*/
    System.out.println("   2 "+(char)26+" JOUER [ CHOISIR LES NUMEROS GANGIANTS ] ");/*-----------------");*/
    System.out.println("   3 "+(char)26+" STATISTIQUE SUR LES DONNEES DU JEU ");/*----------------------");*/
    System.out.println("   4 "+(char)26+" LA LISTE DES BILLETS CLIENT    ");/*--------------------------");*/
    System.out.println("   5 "+(char)26+" A PROPOS ... ");/*--------------------------------------------");*/
    System.out.println("   6 "+(char)26+" QUITTER \n");/*-------------------------------------------------");*/
    System.out.print  ("     "+(char)26+" CHOIX = ");
   }
   
   public void ACTUALISER_AFFICHAGE ()
   {
    MENU();
   }
   public void LA_LISTE_BILLET()
   {
   if(NOMBRE_BILLET>0)
   {
    System.out.println("\n\n     "+(char)4+" LA LISTE DES BILLETS DES AVEC LEURS NOMs : "); 
    for(int j=0;j<NOMBRE_BILLET;j++)
    { 
     System.out.print("  NOM CLIENT = [ "+BILLETS[j].Client.NOM_CLIENT+" ]  ");
             System.out.print("  BILLET = [ ");
             for(int i=0;i<6;i++)
             {
              System.out.print(BILLETS[j].NOMBRE[i] );
              if(i<5)
              System.out.print(", "); 
             }
            System.out.println("]");  
     }
    }
    else
    {
     System.out.println("    => AUCUN BILLET A AFFICHER "); 
    } 
   }
   public void STATISTIQUE_DONNEES ()
   {
     System.out.println("\n\n   "+(char)4+" STATISTIQUES SERVEUR :\n"); 
     System.out.println("     "+(char)4+" LE NOMBRE DE BILLET TOTAL DEPOSER PAR LES CLIENT EST : "+NOMBRE_BILLET+" Billet(s)"); 
     System.out.println("     "+(char)4+" LE SOLDE TOTAL EST : "+NOMBRE_BILLET*20+" $"); 
     System.out.println("     "+(char)4+" L'ETAT DU JEU EST : "+(Etat_DE_Partie?"OUVERT":"FERMER")); 
     System.out.println("     "+(char)4+" LE PRIX D'UN BILLET EST FIXER A 20 $ ");  
   }

   public int CHOIX()
   {
    int choix = 0;
     MENU();
     choix = Lire.Entier();
     
    while((choix>6)||(choix<1))
    { 
     System.out.println("     "+(char)4+" LE CHOIX EST  0 < CHOIX < 7 "); 
     System.out.print  ("     "+(char)26+" CHOIX = "); 
     choix = Lire.Entier();
    } 
    
    return choix;
   }

    Thread_Gestion_Option_Jeux ()
    {
    
    }
//===================================================================
   public void JOUER()   
   { int i=0;
    if(NOMBRE_BILLET >0)
    {
     int NB[]=new int[6];
     System.out.println("     "+(char)4+" INTRODUIRE LES 6 NOMBRES DU BILLET GANGIANT : ");
     for(i=0;i<6;i++)
     {
          System.out.print("     "+(char)4+" BILLET["+i+"] = ");
          NB[i]=Lire.Entier();
     } 
     for(i=0;i<NOMBRE_BILLET;i++)
     {
       int X=BILLETS[i].NOMBRE_DE_NOMBRE_GAGNIANT(NB);
       
       if(X==0)
       {
        System.out.println("\n\n     LE BILLET DU CLIENT [ "+BILLETS[i].Client.NOM_CLIENT+" ] Possede [ 0 ] NOMBRE GANGIANT"); 
        System.out.println("     IL A RIEN GANGIER : 0 $"); 
        BILLETS[i].Client.ENVOYER(Protocole_COM.BELLET_PERDU);
       }
       else
       { int Solde = NOMBRE_BILLET*20;
        BILLETS[i].Client.ENVOYER(Protocole_COM.BELLET_GAGNIER);
        BILLETS[i].Client.ENVOYER(""+X);
        BILLETS[i].Client.ENVOYER(""+BILLETS[i].BENIFICE(X,Solde));
        System.out.println("\n\n     LE BILLET DU CLIENT [ "+BILLETS[i].Client.NOM_CLIENT+" ] Possede [ "+X+" ] NOMBRE GANGIANT"); 
        System.out.println("     IL A GANGIER : "+BILLETS[i].BENIFICE(X,Solde)+" $"); 
       } 
     } 
  }
  else
  {
     System.out.println("     "+(char)26+" AUCUN BILLET N'A ETE DEPOSER PAR LES CLIENTS ... ");
  }
}
//===================================================================
  
    public void Initialisation (Thread_Serveur_Jeux SerJeux)
    {
     this.ServeurJeu = SerJeux;
    }
    public void A_PROPOS()
    {
     System.out.println  ("\n\n   A Propos ... \n\n");
     System.out.println  ("     "+(char)4+" JEU LOTO - CLlient(s) / Serveur avec JAVA ...");
     System.out.println  ("     "+(char)4+" VERSION : 1.0 ");
     System.out.println  ("     "+(char)4+" LES CONCEPTEURS : MEHDI Kamel ");
     System.out.println  ("                         MEHIDI Lamine ");
     System.out.println  ("     "+(char)4+" Copyright "+(char)184+" UNIVERSITE DE BEJAIA 2007-2008  ");
     System.out.println  ("\n     "+(char)4+" Clicker Sur ENTRER pour Continue ... ");
     String FIN = Lire.Chaine();
    }
    public void run ()
    {
     String Touche; 
     while(!Fin)
     {
      int Choix = CHOIX() ;
      
      switch(Choix){
      case 1 : 
               Etat_DE_Partie = !Etat_DE_Partie;
               if(Etat_DE_Partie == Etat_Partie.PARTIE_OUVERTE)
               {
                System.out.println  ("     "+(char)4+" OUVERTURE DU JEU "+(char)26+" LES CLIENTS PEUVENT ACHETER DES BILLETS "); 
               }
               else
               {
                System.out.println  ("     "+(char)4+" FERMETURE DU JEU "+(char)26+" LES CLIENTS NE PEUVENT PAS ACHETER DES BILLETS "); 
               }break; 

      case 2 : JOUER(); break; 
      case 3 : STATISTIQUE_DONNEES ();break;
      case 4 : LA_LISTE_BILLET();break;
      case 5 : A_PROPOS();break;
      case 6 : 
               System.out.print ("     "+(char)4+" FERMETURE DU SERVEUR");
               try{
               for(int i=3;i>=0;i--)
               {
                Thread.sleep(1000);
                System.out.print (" "+i);
               }
               }catch(Exception e)
               {
               
               } 
               System.exit(0); break;
      
      }
      }
     }
}


