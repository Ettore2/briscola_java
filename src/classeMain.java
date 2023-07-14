import java.util.Scanner;
import java.util.Date;

public class classeMain {
    //funzione per "cancellare lo schermo
    public static void cls(){
        for(int i=0;i<50;i++){
            System.out.print("\n");
        }
    }

    public static void sleep(int millisecondi){
        Date start=new Date();
        Date end;
        do{
            end=new Date();
        }while(start.getTime()+millisecondi>=end.getTime());
    }

    public static void stampoSchermata(Mano manoC,Mazzo mazzo,Campo campo,Mano manoU,boolean freccie,boolean turnoU,int giocatori){
        //stampo schermata di gioco
        classeMain.cls();
        if(giocatori==1){
            manoC.stampaManoC();
        }else{
            manoC.stampaManoU();
        }
        System.out.print("\n");
        mazzo.stampoMeB();//stampo mazzo e briscola (se serve)
        if(freccie){
            campo.stampoFrecciaV(turnoU);
        }else{
            campo.stampoCampo();//stampo campo
        }
        System.out.print("\n\n\n");
        manoU.stampaManoU();
    }


    public static void main (String []args){

        Scanner scanner=new Scanner(System.in);
        Mano manoU=new Mano();
        Mano manoC=new Mano();
        Mazzo mazzo=new Mazzo();
        Campo campo=new Campo();
        String sXInput=new String();
        int puntiU=0,puntiC=0,pausa=600,giocatori;
        int segnoBriscola,iCarta;
        boolean turnoU=true;//1=turno utente  0=turno computer
        int i;

        //♥(3) ♦(4) ♣(5) ♠(6) ┐ └ ─ │ ┘ ┌ █ ▄ ▀
        //System.out.println("\n┌─────┐\n│▀ ▄ ▀│\n│ ███ │\n│▄ ▀ ▄│\n└─────┘");
        //System.out.println("\n ─ ─ ─ \n│     │\n       \n│     │\n ─ ─ ─ ");

        //chiedo il numero di giocatori
        do{
            System.out.print("\ninserisci il numero di giocatori (1 o 0) e premi invio per confermare!\n");
            System.out.flush();
            sXInput=scanner.nextLine()+"0";
            //if(sXInput.length()!=2){
            //    System.out.println("stringa sbagliata");
            //}
            giocatori=sXInput.charAt(0)-(int)'0';
            //System.out.print(iCarta);
        }while(giocatori!=0&&giocatori!=1||sXInput.length()!=2);//ho scelto una carta valida da giocare

        mazzo.creoMazzo();//creo mazzo
        segnoBriscola=mazzo.doBriscola()[1];//prendo i seme della briscola
        //do carte
        for(i=0;i<3;i++){
            manoU.prendoCarta(mazzo.doCarta());//do carte all utente
            manoC.prendoCarta(mazzo.doCarta());//do carte al computer
        }

        //stampo schermata di gioco
        classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);

        //loop per giocare
        do{
            //gioca il computer (se deve giocare la prima carta)
            if(turnoU==false){
                iCarta=manoC.giocaCartaC(campo.carta1Campo(),segnoBriscola);
                campo.piazzaCarta(manoC.infocarta(iCarta));
                manoC.tolgoCarta(iCarta);

                //stampo schermata di gioco
                classeMain.sleep(pausa);
                classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);

            }

            //gioca l'utente
            if(mazzo.doIncideCarte()==38){
                System.out.println("\n!ultima mano!");
            }else{
                System.out.println("\n");
            }

            if(giocatori==1){
                do{
                    System.out.print("\ninserisci il numero della carta da giocare (1,2 o 3) e premi invio per giocarla!\n");
                    System.out.flush();
                    sXInput=scanner.nextLine()+"0";
                    //if(sXInput.length()!=2){
                    //    System.out.println("stringa sbagliata");
                    //}
                    iCarta= sXInput.charAt(0)-(int)'0';
                    //System.out.print(iCarta);

                }while(iCarta>3||iCarta<1||manoU.infocarta(iCarta-1)[0]==0||sXInput.length()!=2);//ho scelto una carta valida da giocare
                iCarta--;
            }else{
                classeMain.sleep(pausa);
                iCarta=manoU.giocaCartaC(campo.carta1Campo(),segnoBriscola);
            }

            campo.piazzaCarta(manoU.infocarta(iCarta));//piazzo la carta sul campo
            manoU.tolgoCarta(iCarta);//tolgo la carta dalla mano
            //stampo schermata di gioco
            classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);

            //gioca il computer (se deve giocare la seconda carta)
            if(turnoU==true){
                iCarta=manoC.giocaCartaC(campo.carta1Campo(),segnoBriscola);
                campo.piazzaCarta(manoC.infocarta(iCarta));
                manoC.tolgoCarta(iCarta);
                //System.out.println(iCarta);

                //stampo schermata di gioco
                classeMain.sleep(pausa);
                classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);

            }

            //decido chi vince
            turnoU=campo.vinceUtente(turnoU,segnoBriscola);//aggiorno turnoU (mi indica chi ha vinto e chi gioca la prima carta)
            //(true->ha vinto utente)
            if(turnoU){
                puntiU+=campo.calcoloPunti();
            }else{
                puntiC+=campo.calcoloPunti();
            }//ho assegnato i punti
            campo.svuoto();                          //svuoto il campo
            //stampo schermata di gioco (con freccia vittoria)
            classeMain.sleep(1500);
            classeMain.stampoSchermata(manoC,mazzo,campo,manoU,true,turnoU, giocatori);
            classeMain.sleep(pausa);
            classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,turnoU, giocatori);

            //pesco le carte (in base a chi ha vinto)
            if(mazzo.doIncideCarte()<39){
                if(turnoU){
                    manoU.prendoCarta(mazzo.doCarta());
                    classeMain.sleep(pausa);
                    classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);
                }
                manoC.prendoCarta(mazzo.doCarta());
                classeMain.sleep(pausa);
                classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);
                if(!turnoU){
                    manoU.prendoCarta(mazzo.doCarta());
                    classeMain.sleep(pausa);
                    classeMain.stampoSchermata(manoC,mazzo,campo,manoU,false,false, giocatori);
                }
            }


        }while(!manoU.manoVuota()||!manoC.manoVuota());//fine loop di gioco
        //♥(3) ♦(4) ♣(5) ♠(6) ┐ └ ─ │ ┘ ┌ █ ▄ ▀
        //schermata finale-------------------------------------------------------------------------------
        classeMain.cls();
        System.out.println("punti\nUtente: "+puntiU+"    computer: "+puntiC+"\n\n\n");
        //scritta vittoria/sconfitta
        if(puntiU>puntiC){
            System.out.println("██  ██  ▄████▄  ██      ██  ██  ██  ███   ██  ██████  ▄████▄");
            System.out.println("██  ██  ██  ██  ▄▄      ██  ██  ▄▄  ████  ██    ██    ██  ██");
            System.out.println("██████  ██████  ██      ██▄▄██  ██  ██ ██ ██    ██    ██  ██");
            System.out.println("██  ██  ██  ██  ██       ████   ██  ██  ████    ██    ██  ██");
            System.out.println("██  ██  ██  ██  ██        ██    ██  ██   ███    ██    ▀████▀");

        }else if(puntiU<puntiC){
            System.out.println("██  ██  ▄████▄  ██      ████▄   ██████  ████▄   ▄█████  ▄████▄");
            System.out.println("██  ██  ██  ██  ▄▄      ██  ██  ██      ██  ██  ██▄     ██  ██");
            System.out.println("██████  ██████  ██      ████▀   ████    ████▀    ▀██▄   ██  ██");
            System.out.println("██  ██  ██  ██  ██      ██      ██      ██ ██      ▀██  ██  ██");
            System.out.println("██  ██  ██  ██  ██      ██      ██████  ██  ██  █████▀  ▀████▀");

        }else{
            System.out.println("████▄   ▄████▄  ████▄   ██████   ▄█████    ▄█████   ██  ▄████▄");
            System.out.println("██  ██  ██  ██  ██  ██  ██      ██        ██        ▄▄  ██  ██");
            System.out.println("████▀   ██████  ████▀   ████    ██  ▄▄▄   ██  ▄▄▄   ██  ██  ██");
            System.out.println("██      ██  ██  ██ ██   ██      ██   ▀██  ██   ▀██  ██  ██  ██");
            System.out.println("██      ██  ██  ██  ██  ██████   ▀████▀    ▀████▀   ██  ▀████▀");

        }
    }

}
