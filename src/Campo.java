public class Campo {
    private int[][] carte={{0,0},{0,0}};
    private int i;

    //System.out.println("\n┌─────┐\n│▀ ▄ ▀│\n│ ███ │\n│▄ ▀ ▄│\n└─────┘");
    //System.out.println("\n ─ ─ ─ \n│     │\n       \n│     │\n ─ ─ ─ ");
    //------------------------------------------------------------inizio metodi
    //                                                             pazzaCarta
    public void piazzaCarta(int[] infoCarta){
        //se il primo spazio e vuoto piazzo li la carta
        if(carte[0][0]==0){
            carte[0][0]=infoCarta[0];
            carte[0][1]=infoCarta[1];
        }else{
            carte[1][0]=infoCarta[0];
            carte[1][1]=infoCarta[1];
        }//fine piazzoCarta
    }

    //                                                             svuoto
    public void svuoto(){
        carte[0][0]=0;
        carte[1][0]=0;
    }//fineSvuoto

    //                                                             stampoCampo
    public void stampoCampo(){
        if(carte[0][0]==0){                               //nessuna carta
            System.out.println("\n\t\t ─ ─ ─    ─ ─ ─ \n\t\t│     │  │     │\n\t\t                \n\t\t│     │  │     │\n\t\t ─ ─ ─    ─ ─ ─ ");
        }else if(carte[1][0]==0){                            //1 carta
            System.out.print("\n\t\t┌─────┐   ─ ─ ─ \n\t\t│");
            if(carte[0][0]==8){
                System.out.print("J");
            }else if(carte[0][0]==9){
                System.out.print("Q");
            }else if(carte[0][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[0][0]);
            }
            System.out.print("   "+(char)(carte[0][1]+3)+"│  │     │\n\t\t│     │         \n\t\t│"+(char)(carte[0][1]+3)+"   ");
            if(carte[0][0]==8){
                System.out.print("J");
            }else if(carte[0][0]==9){
                System.out.print("Q");
            }else if(carte[0][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[0][0]);
            }
            System.out.print("│  │     │\n\t\t└─────┘   ─ ─ ─ ");
        }else{                                                      //2 carte
            System.out.print("\n\t\t┌─────┐  ┌─────┐\n\t\t│");
            if(carte[0][0]==8){
                System.out.print("J");
            }else if(carte[0][0]==9){
                System.out.print("Q");
            }else if(carte[0][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[0][0]);
            }
            System.out.print("   "+(char)(carte[0][1]+3)+"│  │");
            if(carte[1][0]==8){
                System.out.print("J");
            }else if(carte[1][0]==9){
                System.out.print("Q");
            }else if(carte[1][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[1][0]);
            }
            System.out.print("   "+(char)(carte[1][1]+3)+"│\n\t\t│     │  │     │\n\t\t│"+(char)(carte[0][1]+3)+"   ");
            if(carte[0][0]==8){
                System.out.print("J");
            }else if(carte[0][0]==9){
                System.out.print("Q");
            }else if(carte[0][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[0][0]);
            }
            System.out.print("│  │"+(char)(carte[1][1]+3)+"   ");
            if(carte[1][0]==8){
                System.out.print("J");
            }else if(carte[1][0]==9){
                System.out.print("Q");
            }else if(carte[1][0]==10){
                System.out.print("K");
            }else{
                System.out.print(carte[1][0]);
            }
            System.out.print("│\n\t\t└─────┘  └─────┘");
        }
    }//fine stampoCampo

    //                                                             carta1Campo
    public int[] carta1Campo(){
        return carte[0];
    }

    //                                                             vinceUtente
    public boolean vinceUtente(boolean turnoU,int briscola){
        if(turnoU){//utente gioca prima carta
            if(carte[0][1]==carte[1][1]){//seme uguale
                if(carte[0][0]==1){
                    return true;
                }else if(carte[0][0]==3){
                    if(carte[1][0]==1){
                        return false;
                    }else{
                        return true;
                    }
                }else{//la prima carta è 2,4,5,6,7,8,9 o 10
                    if(carte[1][0]==1||carte[1][0]==3||carte[1][0]>carte[0][0]){
                        return false;
                    }else{
                        return true;
                    }
                }
            }else if(carte[0][1]==briscola){//seme diverso,ho giocato briscola
                return true;
            }else{//seme diverso, non ho giocato briscola
                if(carte[1][1]==briscola){//computer gioca briscola
                    return false;
                }else{//computer gioca carta di seme diverso
                    return true;
                }
            }
        }else{//utente gioca seconda carta
            if(carte[0][1]==carte[1][1]){//seme uguale
                if(carte[0][0]==1){
                    return false;
                }else if(carte[0][0]==3){
                    if(carte[1][0]==1){
                        return true;
                    }else{
                        return false;
                    }
                }else{//la prima carta è 2,4,5,6,7,8,9 o 10
                    if(carte[1][0]==1||carte[1][0]==3||carte[1][0]>carte[0][0]){
                        return true;
                    }else{
                        return false;
                    }
                }
            }else if(carte[0][1]==briscola){//seme diverso,computer ha giocato briscola
                return false;
            }else{//seme diverso, computer non ha giocato briscola
                if(carte[1][1]==briscola){//ho giocato briscola
                    return true;
                }else{//ho giocato carta di seme diverso
                    return false;
                }
            }
        }
    }//fine vinceUtente

    //                                                            calcoloPunti
    public int calcoloPunti(){
        int punti=0;
        for(i=0;i<2;i++){//controllo carte 0 e 1
            if(carte[i][0]==1){
                punti+=11;
            }else if(carte[i][0]==3){
                punti+=10;
            }else if(carte[i][0]==10){
                punti+=4;
            }else if(carte[i][0]==9){
                punti+=3;
            }else if(carte[i][0]==8){
                punti+=2;
            }
        }
        return punti;
    }//fine calcoloPunti

    //                                                              StampoFrecciaV
    public void stampoFrecciaV(boolean turnoU){
        System.out.print("\n\n");
        if(!turnoU){
            System.out.println("\t\t\t  ▄▄");
            System.out.println("\t\t\t▄████▄");
            System.out.println("\t\t\t  ██");
            System.out.println("\t\t\t  ██");
        }else{
            System.out.println("\t\t\t  ██");
            System.out.println("\t\t\t  ██");
            System.out.println("\t\t\t▀████▀");
            System.out.println("\t\t\t  ▀▀");

        }
    }
}
