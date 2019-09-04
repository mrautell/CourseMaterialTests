/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eukleideen.algoritmi.ja.lineaarikombinaatio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EukleideenAlgoritmiJaLineaarikombinaatio {

    public static int selvitaSuurempi(int luku1, int luku2){ //selvitetään kumpi luvuista on suurempi, jotta suurimman yhteisin tekijän määrittäminen onnistuu.
                                                             
        if(luku1 > luku2){
            return 1;
        }else if(luku2 > luku1){
            return 2;
        }else{
            return 0;
        }
    }
    
    public static int eukleideenAlgoritmi(int jaettava, int jakaja){ //Tässä selvitetään kahden luvun suurin yhteinen tekijä eukleideen algoritmin avulla, 
        double kokonaisosa;                                          //missä sovelletaan jakoalgoritmia a=qb+r (a=jaettava, q=kokonaisosa, b=jakaja, r=jakojäännös)
        int syt;
        int jakojaannos;
        String printti = "\n";
        while (true){
            kokonaisosa = Math.floor((double)jaettava / jakaja);
            jakojaannos = jaettava - (jakaja * (int)kokonaisosa);
            printti +=(jaettava+" = "+(int)kokonaisosa+" * "+jakaja+" + "+jakojaannos+"\n"); //tulostetaan jokainen laskurivi käyttäjällä, jotta nähdään mistä/miten suurin yhteinen tekijä selviää
            if(jaettava % jakaja == 0){                                                      //tätä tietoa tarvitaan myös lineaarikombinaation määrittämisessä
                syt = jakaja;
                break;                                                                       //Kun päästään tilanteeseen, missä jakojäännös r on nolla, löydetään alkuperäisten lukujen syt(a,b)
            }                                                                                //edellisen laskurivin jakojäännöksenä. 
            jaettava = jakaja;                                                               //Algoritmi jatkuu siis aina siihen asti, että viimeisen rivin jakojäännökseksi tulee nolla.
            jakaja = jakojaannos;
        }
        System.out.println(printti);
        return syt;
    }
     
    public static void main(String[] args) {
        
        Scanner lukija = new Scanner(System.in);
        while(true){
            int num1 = 0;
            int num2 = 0;
            while(true){
                System.out.println("__________________________________________________");
                System.out.println("\nSyötä ensimmäinen positiivinen kokonaisluku: ");
                try{
                    num1 = Integer.valueOf(lukija.nextLine());
                    if(num1==0){                                                        //Ohjelman sulkeminen
                        System.out.println("\nHyvää yötä!");
                        System.exit(0);
                    }else if(num1 < 0){
                        System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaisluku!\n");
                        continue;
                    }
                    System.out.println("Syötä toinen positiivinen kokonaisluku: ");
                    num2 = Integer.valueOf(lukija.nextLine());
                    if(num2 == 0){
                        System.out.println("\nHyvää yötä!");
                        System.exit(0);
                    }else if(num2 < 0){
                        System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaisluku!\n");
                        continue;
                    }
                    break;
                }catch(InputMismatchException ex){
                    System.out.println("Syöttämäsi luku ei ollut kokonaisluku!\n");
                }catch(NumberFormatException ex){
                    System.out.println("Syöttämäsi luku ei ollut kokonaisluku!\n");
                }
            }


            int sytti;
            String tulostus1 = "";
            String tulostus2 = "";
            String tulostus3 = "Lineaarikombinaatio:\n";
            int tarkasta;
            tarkasta = selvitaSuurempi(num1, num2);
            if(tarkasta == 1){
                sytti = eukleideenAlgoritmi(num1, num2);
            }else if(tarkasta == 2){
                sytti = eukleideenAlgoritmi(num2, num1);
            }else{
                sytti = num1;
            }

            tulostus1 +="syt("+num1+","+num2+") = "+sytti+"\n";
            int pyj = (num1 * num2)/sytti;                  //Tässä lasketaan kahden syötetyn luvun pienin yhteinen jaettava.
            tulostus2 += "pyj("+num1+","+num2+") = "+pyj+"\n";
            System.out.println(tulostus1);
            System.out.println(tulostus2);

            int i;
            int x1=0;                                       //lähdetään selvittämään kahden syötetyn luvun välistä lineaarikombinaatiota siten, että ax-by=syt(a,b), missä a ja b ovat
            int x2=0;                                       //aijemmin syötetyt luvut. 
            int x=0;                                        //Haluamme tietää pienimmän mahdollisen lineaarikombinaation, joten on tehtävä tarkistuksia matkan varrella.
            int y1=0;
            int y2=0;
            int y=0;

            if(num1%num2==0 || num2%num1==0){                                   //tarkastetaan ovatko luvut keskenään jaollisisa
                if(num1%num2==0){
                    y=num1/num2 + 1;
                    tulostus3 +=(y+" * "+num2+" - "+1+" * "+num1+" = "+sytti);
                }else if(num2%num1==0){
                    y=num2/num1 + 1;
                    tulostus3 +=(y+" * "+num1+" - "+1+" * "+num2+" = "+sytti);
                }
                System.out.println(tulostus3);
            }else{

                for(i=1;i>=0;i++){                              //Tutkitaan tässä silmukassa, millä positiivisella kokonaisluvulla num1 tulee kertoa, että jaettaessa num2:lla
                    x1 = (i*num1)%num2;                         //jakojäännökseksi saadaan syt(a,b).
                    if(x1==sytti){
                        y1 = (int)(Math.floor((i * num1)/num2));//Kun positiiivinen kokonaisluku i on selvillä, voidaan määrittää num2 positiivinen kokonaisluku kerroin.
                        break;
                    }
                }
                int j;
                for(j=1;j>=0;j++){                              //Edellä kuvattu toistuu, lukujen ollessa nyt toisin päin.
                    x2 = (j*num2)%num1;
                    if(x2==sytti){
                        y2 = (int)(Math.floor((j * num2)/num1));
                        break;
                    }
                }
                int summa1 = i + y1; 
                int summa2 = j + y2; 

                if(summa1 <= summa2){                           //tarkistetaan tässä kumpi edellä käytetty silmukka on tuottanut pienemmät arvot ja käytetään näitä, 
                    x=i;                                        //jotta saadaan haluttu pienin mahdollinen lineaarikombinaatia
                    y=y1;
                    if(x*num1-y*num2==sytti){                                       //Tarkistetaan vielä kummasta tulee vähentää kumpi, jotta tulostus esittyy oikeassa muodossa
                        tulostus3 +=(x+" * "+num1+" - "+y+" * "+num2+" = "+sytti);
                    }else if(y*num2-x*num1==sytti){
                        tulostus3 +=(y+" * "+num2+" - "+x+" * "+num1+" = "+sytti);
                    }
                }else if(summa2 < summa1){
                    x=j;
                    y=y2;
                    if(x*num2-y*num1==sytti){
                        tulostus3 +=(x+" * "+num2+" - "+y+" * "+num1+" = "+sytti);
                    }else if(y*num1-x*num2==sytti){
                        tulostus3 +=(y+" * "+num1+" - "+x+" * "+num2+" = "+sytti);
                    }
                }
                System.out.println(tulostus3);
            }
        }
    }
    
}
