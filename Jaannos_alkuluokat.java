/*Kokonaislukujen joukossa Z määritellyn kongruenssin x≡y (mod m) <=> m|x-y
perusteella määritellään jäännäsluokat modulo m. Samaan jäännösluokkaan kuuluvat
luvut joilla on sama jakojäännös jaettaessa luvulla m. Tämän takia jäännösluokkia
on m kappaletta(0,1,2,...,m-1).
Jäännösluokkaa([a] (mod m)) sanotaan alkuluokaksi (mod m), mikäli jäännösluokkien edustajien ja moduloluvun
m suurin yhteinen tekijä on yksi(syt(a,m)=1). Tämän takia nolla ei kuulu alkuluokkiin. 
*/
package jaannos_alkuluokat;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jaannos_alkuluokat {

    public static int syotto(){
        Scanner lukija = new Scanner(System.in);
        while(true){
            try{
                int luku = Integer.valueOf(lukija.nextLine());
                if(luku == 0){
                    System.out.println("\nHyvää yötä!\n");
                    System.exit(0);
                }else if(luku < 0){
                    System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaisluku!\n");
                    continue;
                }else{
                    return luku;
                }
            }catch(InputMismatchException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!");
            }catch(NumberFormatException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!");
            }
        }    
    }
    
    public static void muodostaJaannosluokka(int luku, List<Integer> jaannosluokanAlkiot){
        
        for(int i=0;i<luku;i++){                //Muodostetaan lista jäännösluokista
            jaannosluokanAlkiot.add(i);         //Näitä on aina {[0],[1],[2],...,[luku-1]}
        }
    }
    public static void muodostaAlkuluokka(int luku, List<Integer> alkuluokanAlkiot){
        alkuluokanAlkiot.add(1);
        int m;
        for(int i=2;i<luku;i++){                //tutkitaan onko jäännösluokan edustajalla ja moduloluvulla
            m=0;                                //muita yhteisiä tekijöitä kuin luku 1.
            if(luku%i==0){                      //Jos näin on jäännösluokan edustajaa ei lisätä alkuluokkien joukkoon.
                continue;
            }else{
                for(int j=2;j<luku/2;j++){
                    if(i%j==0 && luku%j==0){
                        m=1;
                        break;
                    }
                }
                if(m==0){
                    alkuluokanAlkiot.add(i);
                }
            }
        }
    }
    public static void tulostaLuokka(List<Integer> lista){
        String printti ="{";
        for(int i=0;i<lista.size();i++){
            if(i==0){
                printti +="["+lista.get(i)+"]";
            }else{
                printti +=",["+lista.get(i)+"]";
            }
        }
        printti+="}";
        System.out.println(printti);
    }
    
    public static void main(String[] args) {
        
        System.out.println("Ohjelma tulostaa syötetyn luvun jäännös- ja alkuluokan");
        System.out.println("Ohjelmassa operoidaan positiivisilla kokonaisluvuilla");
        while(true){
            System.out.println("Syötä luku, jonka määräämän jäännös- ja alkuluokan haluat tietää:");
            int luku = syotto();
            List<Integer> jaannosluokanAlkiot = new ArrayList<>();
            List<Integer> alkuluokanAlkiot = new ArrayList<>();
            muodostaJaannosluokka(luku, jaannosluokanAlkiot);
            muodostaAlkuluokka(luku, alkuluokanAlkiot);
            System.out.println("Luvun "+luku+" määräämät jäännösluokat:");
            tulostaLuokka(jaannosluokanAlkiot);
            System.out.println("\nLuvun "+luku+" määräämät alkuluokat:");
            tulostaLuokka(alkuluokanAlkiot);
            System.out.println("__________________________________________________________________");
        }
    }
    
}
