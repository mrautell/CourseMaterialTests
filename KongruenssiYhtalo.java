/*
Ohjelmassa selvitetään millä arvolla X (syötetty kerroin * X)%syötetty moduloluku = syötetty jakojäännös
yhtälön tulos saadaan joko kokeilemalla eri arvoja X:n paikalle tai käyttämällä Eukleideen algoritmia siten, että
moduloluku = q_0 * kerroin + r_0
kerroin = q_1 * r_0 + r_1
jne. kunnes r_n saa arvon 0 ja kertoimen ja moduloluvun suurin yhteinen tekijä saadaan selville (arvo r_(n-1)).
Tämän jälkeen rakennetaan lineaarikobinaatio ja muutaman tempun avulla saadaan haluttu X selville.

Tässä ohjelmassa käytetään yksinkertaista silmukka, joka kokeilee millä arvolla X saadaan haluttu jakojäännös.
*/
package kongruenssiyhtalo;

import java.util.Scanner;
import java.util.InputMismatchException;

public class KongruenssiYhtalo {

   Scanner lukija = new Scanner(System.in); 
   
   public static int syt(int jaettava, int jakaja){                 //Selvitetään kahden luvun suurin yhteinen tekijä
        double kokonaisosa;                                         //Tässä käytetään hyödyksi Eukleideen algoritmia
        int syt;
        int jakojaannos;
        while (true){
            kokonaisosa = Math.floor((double)jaettava / jakaja);
            jakojaannos = jaettava - (jakaja * (int)kokonaisosa);
            if(jaettava % jakaja == 0){
                syt = jakaja;
                break;
            }
            jaettava = jakaja;
            jakaja = jakojaannos;
        }
        return syt;
    }
   
    public static int selvitaX(int kerroin, int jakojaannos, int moduloLuku){
        int x = 1;
        int y;                              //Silmukka kokeilee x:n arvoja 1 ->
        while (true){                       //pysähtyy kun y = syötetty jakojäännös
            y = (kerroin * x) % moduloLuku; //ja palauttaa x:n arvon, millä y saatiin.
            if(y == jakojaannos){
                return x;
            }else{
                x++;
            }
        }
    }
   
    public static int syota(int i){
        Scanner lukija = new Scanner(System.in);
        while(true){
            try{
                int luku = Integer.valueOf(lukija.nextLine());
                if(luku == 0 && i != 2){
                    System.out.println("\nHyvää yötä!");
                    System.exit(0);
                }else if(luku < 0){
                    System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaislkuku!");
                }
                return luku;
            }catch(InputMismatchException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!");
            }catch(NumberFormatException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!");
            }
        }    
    }
   
    public static void main(String[] args) {
        
        System.out.println("Kongruenssiyhtälö on muotoa (kerroin)X≡(jakojäännös)(__mod)");
        System.out.println("Ohjelmassa operoidaan positiivisilla kokonaisluvuilla!\n");
        while(true){
            System.out.println("Syötä kongruenssi yhytälön kerroin:");
            int kerroin = syota(1);
            
            System.out.println("Syötä kongruenssi yhytälön jakojäännös:");
            int jakojaannos = syota(2);
            
            System.out.println("Syötä kongruenssi yhytälön moduloluku:");
            int moduloLuku = syota(3);
            
            System.out.println("_________________________________________________");
            System.out.println("\nSyöttämäsi yhtälö on muotoa: "+kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+").\n");
            if(jakojaannos >= moduloLuku){                                  //Muokataan yhtälö käsiteltävään muotoon, sillä jos jakojaannos >= modulolukku tätä jakojäännöstä 
                System.out.println("Nyt "+jakojaannos+" ≥ "+moduloLuku);    //ei voida koskaan saavuttaa perus jako-operaatiolla. Tämä johtuu siitä, että jakojäännös löytyy aina väliltä
                String printti = "Koska "+jakojaannos+"≡";                  //0<= jakojäännös < jakaja. 
                while(jakojaannos >= moduloLuku){                           //Nyt skaalataan jakojäännös välille 0 - (moduloLuku-1).
                    jakojaannos = jakojaannos - moduloLuku;
                }
                printti += jakojaannos+" (mod "+moduloLuku+")";
                System.out.println(printti);
                System.out.println("Voidaan kongruenssiyhtälö kirjoittaa muotoon:");
                System.out.println(+kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+")");
            }
            
            int sytti = syt(kerroin, moduloLuku);                       //lasketaan kertoimen ja moduloluvun suurin yhteinen tekijä
            int syt_kerroin_jakojaannos = syt(jakojaannos, kerroin);    //lasketaan kertoimen ja jakojäännöksen suurin yhteinen tekijä
            
            if(sytti == 1){
                System.out.println("Nyt syt("+kerroin+","+moduloLuku+")= 1, joten kongruenssiyhtälölle löytyy ratkaisu");
                
                if(syt_kerroin_jakojaannos != 1){
                    int syt_kerroinJakojaannos_mod = syt(syt_kerroin_jakojaannos, moduloLuku); //Tutkitaan mikä on kertoimen ja jakojäännöksen suurimman yhteisen tekijän
                                                                                               //ja moduloluvun suurin yhteinen tekijä
                    if(syt_kerroinJakojaannos_mod == 1){
                        System.out.println("Nyt yhtälö on muotoa ac≡bc (mod m) ja syt(c,m)=1");
                        System.out.println("Tämän takia yhtälöä voidaan muokata seuraavasti:");
                        System.out.println(kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+") |:"+syt_kerroin_jakojaannos);
                        kerroin = kerroin/syt_kerroin_jakojaannos;
                        jakojaannos = jakojaannos/syt_kerroin_jakojaannos;
                        
                        if(kerroin < 2){
                            System.out.println("⇒  X≡"+jakojaannos+" (mod "+moduloLuku+")\n");
                        }else{
                            System.out.println("⇒  "+kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+")\n");
                        }
                    }
                }
            }else{
                System.out.println("Nyt syt("+kerroin+","+moduloLuku+")="+sytti+" > 1");
                System.out.println("Tällöin kongruenssiyhtälöllä on ratkaisu täsmälleen silloin, kun syt(kerroin,mod) jakaa jakojäännöksen");
                if(jakojaannos % sytti != 0){
                    System.out.println("Nyt "+sytti+"∤"+jakojaannos+", joten kongruenssiyhtälöllä ei ole ratkaisua.");
                    System.out.println("_________________________________________________");
                    continue;
                }else{
                    System.out.println("Nyt "+sytti+"|"+jakojaannos+", joten");
                    System.out.println(kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+") |:"+sytti);
                    kerroin = kerroin/sytti;
                    jakojaannos = jakojaannos/sytti;
                    moduloLuku = moduloLuku/sytti;
                    if(kerroin < 2){
                        System.out.println("⇒  X≡"+jakojaannos+" (mod "+moduloLuku+")\n");
                    }else{
                        System.out.println("⇒  "+kerroin+"X≡"+jakojaannos+" (mod "+moduloLuku+")");
                    }
                }    
            }
            int x = selvitaX(kerroin, jakojaannos, moduloLuku);
            System.out.println("Kongruenssi yhtälön eräs ratkaisu on X = "+x);
            System.out.println("Tällöin kaikki ratkaisut ovat muotoa X≡"+x+" (mod "+moduloLuku+").");
            System.out.println("_________________________________________________\n");
        }
    }
    
}
