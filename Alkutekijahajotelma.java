/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alkutekijahajotelma;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Alkutekijahajotelma {

     public static void alkuluvut(List<Integer> lista, int luku){        //Selvitetään syötetyn luvun alkulukutekijät ja listataan ne.
        int i = 2;
        while(luku > 1 || i <= luku/2){
            if(luku % i == 0){
                luku = luku/i;
                lista.add(i);
            }else{
                i++;
            }
        }
    }
     
    public static void alkulukujenMaara(List<Integer> lista, List<Integer> LKM, List<Integer> alkuTekijat){
        int i;              
        int j = 0;                                                      //Tutkitaan kuinka monta kertaa kukin alkuluku esiintyy syötetyn luvun tekijänä.
        int tekija = 0;                                                 //Listataan määrät samalle indeksille uuteen listaan kuin kyseinen alkulukutekijä
        int tekijaLkm;                                                  //on alkulukulistassa.
        while(j < lista.size()){
            tekijaLkm = 0;
            tekija = lista.get(j);
            for(i = 0; i < lista.size(); i++){
                if(tekija == lista.get(i)){
                    tekijaLkm++;
                }
            }
            alkuTekijat.add(tekija);
            LKM.add(tekijaLkm);
            j = j + tekijaLkm;
        }
    }
    
    public static String tulostusMetodi(List<Integer> alkuTekijat, List<Integer> LKM){
        int i;
        String printti = "";                                            //Tässä metodissa muokataan saatu informaatio haluttuun tulostettavaan muotoon.
        for(i = 0; i < LKM.size(); i++){
            if(LKM.get(i) == 1){
                if(i == 0){
                    printti +=alkuTekijat.get(i);
                }else{
                    printti +="*"+alkuTekijat.get(i);
                }
            }else if(i == 0){
                printti +=alkuTekijat.get(i)+"^"+LKM.get(i);
            }else{
                printti +="*"+alkuTekijat.get(i)+"^"+LKM.get(i);
            }
        }
        return printti;
    }
    
    public static int syotaLuku(){
        Scanner lukija = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Syötä positiivinen kokonaisluku:");
                int luku = Integer.valueOf(lukija.nextLine());
                if(luku==0){                                                        //Ohjelman sulkeminen
                    System.out.println("\nHyvää yötä!");
                    System.exit(0);
                }else if(luku < 0){
                    System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaisluku!");
                    continue;
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
        
         while(true){
            int luku = syotaLuku();
            String tulostus = "";
            List<Integer> lukuMaarat = new ArrayList<>();
            List<Integer> Alkuluvut = new ArrayList<>();
            List<Integer> alkuTekijat = new ArrayList<>();
            alkuluvut(Alkuluvut, luku);
            alkulukujenMaara(Alkuluvut, lukuMaarat, alkuTekijat);
            if(alkuTekijat.size() == 1 && lukuMaarat.get(0) == 1){                  //Tarkistetaan onko syötetty luku alkuluku. Jos alkutekijät listassa on vain yksi
                tulostus +="\n"+luku+" on alkuluku!";                               //alkio ja sen esiintymis määrä lukuMaarat listassa on 1, luku on alkuluku.
            }else{                                                                  //Siis syötetty luku on itse itsensä ainut tekijä!
                tulostus +="\nLuvun "+ luku +" alkulukuhajotelma on: ";
                tulostus+=tulostusMetodi(alkuTekijat, lukuMaarat);
            }
            tulostus+="\n____________________________________________";
            System.out.println(tulostus);
        }
        
    }
    
}
