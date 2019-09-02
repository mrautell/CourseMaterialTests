/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerinphiifunktio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Markus
 */
public class EulerinPhiiFunktio {
    
    public static void alkuluvut(List<Integer> lista, int luku){                //määritetään kaikki alkuluvut, jotka ovat tekijänä syötetyssä luvussa
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
        int j = 0;
        int tekija = 0;
        int tekijaLkm;
        while(j < lista.size()){
            tekijaLkm = 0;
            tekija = lista.get(j);
            for(i = 0; i < lista.size(); i++){                          //tarkistetaan kuinka monta kertaa kukin alkuluku esiintyy tekijänä syötetyssä luvussa
                if(tekija == lista.get(i)){
                    tekijaLkm++;
                }
            }
            alkuTekijat.add(tekija);                                    //lisätään alkuluku tekijälistaan
            LKM.add(tekijaLkm);                                         //lisätään saman indeksin kohdalle sen esiintyvyyden määrä toiseen listaan
            j = j + tekijaLkm;
        }
    }
     
    public static String tulostus(List<Integer> alkuTekijat, List<Integer> LKM, String printti){
        int i;
        for(i = 0; i < LKM.size(); i++){
            if(LKM.get(i) == 1){
                if(i == 0){
                    printti += (alkuTekijat.get(i)).toString();
                }else{
                    printti += ("*"+alkuTekijat.get(i)).toString();
                }
            }else if(i == 0){
                printti += (alkuTekijat.get(i)+"^"+LKM.get(i)).toString();
            }else{
                printti += ("*"+alkuTekijat.get(i)+"^"+LKM.get(i)).toString();
            }
        }
        return printti;
    }
    
    public static String euleriTulostus(List<Integer> alkuTekijat, List<Integer> LKM, String printti){
        int i;
        for(i = 0; i < LKM.size(); i++){
            if(LKM.get(i) == 1){
                if(i == 0){
                    printti += ("("+alkuTekijat.get(i)+"-1)").toString();
                }else{
                    printti += (" * ("+alkuTekijat.get(i)+"-1)").toString();
                }
            }else if(i == 0){
                printti += (alkuTekijat.get(i)+"^("+LKM.get(i)+"-1) * ("+alkuTekijat.get(i)+"-1)").toString();
            }else{
                printti += (" * "+alkuTekijat.get(i)+"^("+LKM.get(i)+"-1) * ("+alkuTekijat.get(i)+"-1)").toString();
            }
        }
        return printti;
    }
    
    public static int eulerinLasku(List<Integer> alkuTekijat, List<Integer> LKM){
        int i;
        double a;
        double b;
        double c;
        double tulos = 1.00;
        for(i = 0; i < LKM.size(); i++){                        //Tarkastetaan taas alkulukujen esiintyvyys laskennallisista syistä.
            if(LKM.get(i) == 1){                                //Jos toistuvuus 1, ei tarvitsi murehtia potenssiin korottelusta
                tulos = tulos * (alkuTekijat.get(i) - 1);
            }else{                                              //Jos toistuvuus on >1 -> luku^(toistuvuus -1) * (luku-1)
                a = alkuTekijat.get(i);                         //                              =c                   =(alkuTekijat.get(i)-1)
                b = (LKM.get(i)-1);
                c = Math.pow(a, b);
                tulos = tulos * c * (alkuTekijat.get(i)-1);
            }
        }
        return (int)tulos;
    }
    public static int annaLuku(){
        Scanner lukija = new Scanner(System.in);
        while (true){
            try{
                System.out.println("Anna positiivinen kokonaisluku:");
                int luku = Integer.valueOf(lukija.nextLine());
                if(luku==0){                                            //Ohjelman sulkeminen
                    System.out.println("\nHyvää yötä!\n");
                    System.exit(0);
                }else if(luku < 0){
                    System.out.println("Syöttämäsi luku ei ollut kokonaisluku!\n");
                    continue;
                }
                return luku;
            }catch(InputMismatchException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!\n");
            }catch(NumberFormatException ex){
                System.out.println("Syötteesi ei ollut kokonaisluku!\n");
            }
        }
    }
    
    public static void main(String[] args) {                            //ohjelman tehtävä on selvittää, kuinka monta alkiota syötetyn luvun määräämässä alkuluokassa on.
        while(true){
            int luku = annaLuku();
            int tulo;
            String printti = "";
            String euler = "φ("+luku+") = ";
            List<Integer> lukuMaarat = new ArrayList<>();
            List<Integer> Alkuluvut = new ArrayList<>();
            List<Integer> alkuTekijat = new ArrayList<>();
            alkuluvut(Alkuluvut, luku);
            alkulukujenMaara(Alkuluvut, lukuMaarat, alkuTekijat);
            if(alkuTekijat.size() == 1 && lukuMaarat.get(0) == 1){      //Tarkistetaan onko syötetty luku itsessään alkuluku
                printti +=(luku+" on alkuluku!");                       //tällöin listaan kertynyt vain 1 tekijä, jonka toistuvuus 1.
                tulo = luku -1;
                euler += "("+luku+"-1) = "+tulo;
                System.out.println(printti);
                System.out.println(euler);
            }else{
                printti +="Luvun "+ luku +" alkulukuhajotelma on: ";
                printti = tulostus(alkuTekijat, lukuMaarat, printti);   //muokataan tulostusta haluttuun muotoon
                System.out.println(printti);
                euler = euleriTulostus(alkuTekijat, lukuMaarat, euler); //tulostetaan eulerin phi funktio lasku näkyviin, jotta käyttäjä näkee mistä arvot syntyvät.
                tulo = eulerinLasku(alkuTekijat, lukuMaarat);           //Lasketaan eulerin phi funktion tulos
                euler += " = "+tulo;
                System.out.println(euler);
            }
            System.out.println("___________________________\n");
        }
    }
    
}
