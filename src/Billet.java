import java.io.*;

public class Billet 
{
//======================================================================================
   public static int Prix = 20 ;
   public Thread_Communication_Clt_Srv  Client;
   public int NOMBRE[];
//======================================================================================
   Billet(){
   NOMBRE=new int[6];
      for(int i=0;i<6;i++)
      NOMBRE[i]=0;
   }
//======================================================================================
   public void Init(int T[]){
      for(int i=0;i<6;i++)
      NOMBRE[i]=T[i];
   }
//======================================================================================
   public boolean Existe(int T[],int Element){
      int i ;
      boolean Ex = false;
      for(i=0;i<6;i++)
      if(Element == T[i]) Ex = true ;
      
      return Ex;
   }
//======================================================================================
   public int NOMBRE_DE_NOMBRE_GAGNIANT(int T[]) {
      int i , N = 0;
      for(i=0;i<6;i++)
      if(Existe(T,NOMBRE[i])) N++;
      
      return N; 
   }
//======================================================================================
   public double BENIFICE(int NB,double Solde) {
      double B=0.0;
      switch(NB)
      {
       case 6 : B=Solde*60/100;
       case 5 : B=Solde*50/100;
       case 4 : B=Solde*40/100;
       case 3 : B=Solde*30/100;
       case 2 : B=Solde*20/100;
       case 1 : B=Solde*10/100;
       default : B=0.0;
      }
      return B; 
   }
//======================================================================================
}
