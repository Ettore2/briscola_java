import java.util.Arrays;

public class Mano {
    //♥(3) ♦(4) ♣(5) ♠(6) ┐ └ ─ │ ┘ ┌ █ ▄ ▀
    private int carta[][]={{0,0},{0,0},{0,0}};//[n carta][descrivo carta]->0=numero 1=segno
    private int i;
    //---------------------------------------------------------------------------------inizio metodi
    //                                                                                   stampoManoU
    public void stampaManoU(){
        System.out.print("\n           ");
        for(i=0;i<3;i++){//stampo bordi alti
            if(carta[i][0]!=0){
                System.out.print("┌─────┐   ");
            }else{
                System.out.print("          ");
            }
        }

        System.out.print("\n           ");
        for(i=0;i<3;i++){//stampo parti medio-alte con informazioni
            if(carta[i][0]!=0){
                System.out.print("│");
                if(carta[i][0]==8){
                    System.out.print("J");
                }else if(carta[i][0]==9){
                    System.out.print("Q");
                }else if(carta[i][0]==10){
                    System.out.print("K");
                }else{
                    System.out.print(carta[i][0]);
                }
                System.out.print("   "+(char)(carta[i][1]+3)+"│   ");
            }else{
                System.out.print("          ");
            }
        }

        System.out.print("\n           ");
        for(i=0;i<3;i++){//stampo parti medie
            if(carta[i][0]!=0){
                System.out.print("│     │   ");
            }else{
                System.out.print("          ");
            }
        }

        System.out.print("\n           ");
        for(i=0;i<3;i++){//stampo parti medio-basse con informazioni
            if(carta[i][0]!=0){
                System.out.print("│"+(char)(carta[i][1]+3)+"   ");
                if(carta[i][0]==8){
                    System.out.print("J");
                }else if(carta[i][0]==9){
                    System.out.print("Q");
                }else if(carta[i][0]==10){
                    System.out.print("K");
                }else{
                    System.out.print(carta[i][0]);
                }
                System.out.print("│   ");
            }else{
                System.out.print("          ");
            }
        }

        System.out.print("\n           ");
        for(i=0;i<3;i++){//stampo bordi bassi
            if(carta[i][0]!=0){
                System.out.print("└─────┘   ");
            }else{
                System.out.print("          ");
            }
        }


    }//fina stampoManoU

    //                                                                                  stampoManoC
    public void stampaManoC(){
        //System.out.println("\n┌─────┐\n│▀ ▄ ▀│\n│ ███ │\n│▄ ▀ ▄│\n└─────┘");
        System.out.print("\n            ");
        for(i=0;i<3;i++){//stampo bordi alti
            if(carta[i][0]!=0){
                System.out.print("┌─────┐ ");
            }else{
                System.out.print("        ");
            }
        }

        System.out.print("\n            ");
        for(i=0;i<3;i++){//stampo parti medio-alte con informazioni
            if(carta[i][0]!=0){
                System.out.print("│▀ ▄ ▀│ ");
            }else{
                System.out.print("        ");
            }
        }

        System.out.print("\n            ");
        for(i=0;i<3;i++){//stampo parti medie
            if(carta[i][0]!=0){
                System.out.print("│ ███ │ ");
            }else{
                System.out.print("        ");
            }
        }

        System.out.print("\n            ");
        for(i=0;i<3;i++){//stampo parti medio-basse con informazioni
            if(carta[i][0]!=0){
                System.out.print("│▄ ▀ ▄│ ");
            }else{
                System.out.print("        ");
            }
        }

        System.out.print("\n            ");
        for(i=0;i<3;i++){//stampo bordi bassi
            if(carta[i][0]!=0){
                System.out.print("└─────┘ ");
            }else{
                System.out.print("        ");
            }
        }


    }//fina stampoManoC

    //                                                                                prendoCarta
    public void prendoCarta(int[]infoCarta){
        i=0;
        while(carta[i][0]!=0){//trovo spot vuoto
            i++;
        }
        carta[i][0]=infoCarta[0];//assegno informazioni carta
        carta[i][1]=infoCarta[1];


    }//fine prendocarta

    //                                                                                 manoVuota
    public boolean manoVuota(){
        int slotVuoti=0;
        //conto qunati degli slot carta sono vuoti
        for(i=0;i<3;i++){
            if(carta[i][0]==0){
                slotVuoti++;
            }
        }
        if(slotVuoti==3){
            return true;
        }else{
            return false;
        }
    }//fine manoVuota

    //                                                                                  infocarta
    public int[] infocarta(int indiceCarta){
        return carta[indiceCarta];
    }

    //                                                                                  tolgoCarta
    public void tolgoCarta(int indiceCarta){
        carta[indiceCarta][0]=0;
    }//fine tolgoCarta

    //                                                                                    giocaCartaC
    public int giocaCartaC(int[] carta1Campo,int briscola){
        int indiceCarta=0,min,scelta;//l'inizializzazione di scelsa non serve
        int[] peso= {0,0,0};
        //2=2,4=4......10=10,3=20,1=21  seme briscola-> +9

        //calcolo peso delle 3 carte che ho in mano
        for(i=0;i<3;i++){
            //peso numero
            if(carta[i][0]==1){
                peso[i]+=21;
            }else if(carta[i][0]==3){
                peso[i]+=20;
            }else if(carta[i][0]==0) {
                peso[i]+=100;
            }else{
                peso[i]+=carta[i][0];
            }
            //peso briscola
            if(carta[i][1]==briscola){
                peso[i]+=9;
            }
        }
        //trovo l'indice del peso minore
        min=0;
        for(i=0;i<3;i++){
            if(peso[i]<peso[min]){
                min=i;
            }
        }


        if(carta1Campo[0]==0){//devo giocare la prima carta->butto la carta col peso minore
            //System.out.print("gioco la prima carta");
            return min;
        }else{//devo giocare la seconda carta
            //System.out.print("gioco la seconda carta");
            if(carta1Campo[1]!=briscola&&carta1Campo[0]!=1&&carta1Campo[0]!=3&&carta1Campo[0]!=8&&carta1Campo[0]!=9&&carta1Campo[0]!=10){
                //utente gioca liscio-> strozzo o perdo o peso minore

                //provo strozzi
                for(i=0;i<3;i++){
                    if(carta[i][1]==carta1Campo[1]&&carta[i][0]==1){
                        return i;
                    }
                }//ho visto se posso strozzare con 1
                for(i=0;i<3;i++){
                    if(carta[i][1]==carta1Campo[1]&&carta[i][0]==3){
                        return i;
                    }
                }//ho visto se posso strozzare con 3
                for(i=0;i<3;i++){
                    if(carta[i][1]==carta1Campo[1]&&carta[i][0]==10){
                        return i;
                    }
                }//ho visto se posso strozzare con 10
                for(i=0;i<3;i++){
                    if(carta[i][1]==carta1Campo[1]&&carta[i][0]==9){
                        return i;
                    }
                }//ho visto se posso strozzare con 9
                for(i=0;i<3;i++){
                    if(carta[i][1]==carta1Campo[1]&&carta[i][0]==8){
                        return i;
                    }
                }//ho visto se posso strozzare con 8

                //provo seme diverso
                for(i=0;i<3;i++){
                    if(carta[i][1]!=carta1Campo[1]&&carta[i][1]!=briscola&&carta[1][0]!=1&&carta[1][0]!=3&&carta[1][0]!=8&&carta[1][0]!=9&&carta[1][0]!=10&&carta[1][0]!=0){
                        return i;
                    }
                }//ho visto se ho carta con seme diverso che non da punti

                //gioco la carta col peso minore
                return min;
            }else if(carta1Campo[1]!=briscola){
                //utente gioca punti(o carichi)-> strozzo o briscola o perdo o peso minore

                //provo strozzi
                if(carta1Campo[0]!=1) {
                    for (i=0;i<3;i++) {
                        if (carta[i][1] == carta1Campo[1] && carta[i][0] == 1) {
                            return i;
                        }
                    }//ho visto se posso strozzare con 1
                    if (carta1Campo[0] != 3) {
                        for (i=0;i<3;i++) {
                            if (carta[i][1] == carta1Campo[1] && carta[i][0] == 3) {
                                return i;
                            }
                        }//ho visto se posso strozzare con 3
                        if (carta1Campo[0] != 10) {
                            for (i=0;i<3;i++) {
                                if (carta[i][1] == carta1Campo[1] && carta[i][0] == 10) {
                                    return i;
                                }
                            }//ho visto se posso strozzare con 10
                            if (carta1Campo[0] != 9) {
                                for (i=0;i<3;i++) {
                                    if (carta[i][1] == carta1Campo[1] && carta[i][0] == 9) {
                                        return i;
                                    }
                                }//ho visto se posso strozzare con 9
                                //non posso strozzare con lo stesso 8
                            }
                        }
                    }
                }

                //provo briscole
                if(carta[0][1]==briscola&&carta[0][0]!=0||carta[1][1]==briscola&&carta[1][0]!=0||carta[2][1]==briscola&&carta[2][0]!=0){
                    //so di avere almeno 1 briscola
                    scelta=0;//forse c'e la possibilita di non inizializzare scelta
                    //scelgo una briscola da confrontare con le altre
                    for(i=0;i<3;i++){
                        if(carta[i][1]==briscola&&carta[i][0]!=0){
                            scelta=i;
                        }
                    }
                    //trovo la briscola con peso minore
                    for(i=0;i<3;i++){
                        if(carta[i][1]==briscola&&carta[i][0]!=0&&peso[i]<peso[scelta]){
                            scelta=i;
                        }
                    }
                    return scelta;
                }

                //provo seme diverso
                for(i=0;i<3;i++){
                    if(carta[i][1]!=carta1Campo[1]&&carta[1][0]!=1&&carta[1][0]!=3&&carta[1][0]!=8&&carta[1][0]!=9&&carta[1][0]!=10&&carta[1][0]!=0){
                        return i;
                    }
                }//ho visto se ho carta con seme diverso che non da punti

                //gioco la carta col peso minore
                return min;
            }else{
                //utente gioca briscola-> peso minore
                return min;
            }


        }
    }
}








