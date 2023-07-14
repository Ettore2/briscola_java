import java.util.Random;
public class Mazzo {
    private int indiceCarte=0;
    Random random=new Random();
    private int i,j;
    private int[][] carte=new int[40][2];
//------------------------------------------------------------------------------inizio metodi
//                                                                                       creoMazzo
    public void creoMazzo(){
        boolean ripetuto;

        //creo carte con numeri da 0 a 39
        for(i=0;i<40;i++){//ciclo per fare tute le carte
            do{//ciclo per estrarre numero casuale valido
                ripetuto=false;
                carte[i][0]=random.nextInt(40);
                //System.out.print(carte[i][0]+" ");
                for(j=0;j<i;j++){//ciclo per controllare di non starmi ripetendo
                    if(carte[i][0]==carte[j][0]){//metto ripetuto a true
                        ripetuto=true;
                    }
                }

            }while(ripetuto);
            //System.out.print(carte[i][0]+" ");
        }

        //traformo i numeri in carte effettive carte[numero][seme]
        for(i=0;i<40;i++){
            carte[i][1]=carte[i][0]/10;
            carte[i][0]=carte[i][0]%10+1;
            //System.out.print(carte[i][0]+""+carte[i][1]+"  ");
        }

    }//fine creoMazzo

    //                                                                                    doCarta
    public int[] doCarta(){
        indiceCarte++;
        //System.out.println(carte[indiceCarte-1][0]+""+carte[indiceCarte-1][1]);
        return carte[indiceCarte-1];
    }//fine doCarta

//                                                                                      doIndiceCarte
    public int doIncideCarte(){
        return indiceCarte;
    }//fine doIndiceCarte

    //                                                                                   doBriscola
    public int[] doBriscola(){
        return carte[39];
    }//fine doBriscola

    //                                                                                    stampoMeB
    public void stampoMeB(){

        if(indiceCarte<39){//stampo mazzo e briscola
            System.out.print("┌─────┐ ┌─────┐\n");
            System.out.print("│▀ ▄ ▀│ │");
            if(carte[39][0]==8){
                System.out.print("J");
            }else if(carte[39][0]==9){
                System.out.print("Q");
            }else if(carte[39][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[39][0]);
            }
            System.out.print("   "+(char)(carte[39][1]+3)+"│\n");
            System.out.print("│ ███ │ │     │\n");
            System.out.print("│▄ ▀ ▄│ │");
            System.out.print((char)(carte[39][1]+3)+"   ");
            if(carte[39][0]==8){
                System.out.print("J");
            }else if(carte[39][0]==9){
                System.out.print("Q");
            }else if(carte[39][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[39][0]);
            }
            System.out.print("│\n");
            System.out.print("└─────┘ └─────┘\n");
        }else if(indiceCarte<40){//stampo briscola
            System.out.print("        ┌─────┐\n");
            System.out.print("        │");
            if(carte[39][0]==8){
                System.out.print("J");
            }else if(carte[39][0]==9){
                System.out.print("Q");
            }else if(carte[39][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[39][0]);
            }
            System.out.print("   "+(char)(carte[39][1]+3)+"│\n");
            System.out.print("        │     │\n");
            System.out.print("        │");
            System.out.print((char)(carte[39][1]+3)+"   ");
            if(carte[39][0]==8){
                System.out.print("J");
            }else if(carte[39][0]==9){
                System.out.print("Q");
            }else if(carte[39][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[39][0]);
            }
            System.out.print("│\n");
            System.out.print("        └─────┘\n");
        }else{//stampo solo acapi
            System.out.print("\n\n\n\n\n");
        }




    }


}



















