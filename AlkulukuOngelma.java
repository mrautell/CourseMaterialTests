//Milloin lauseke n^2 + 1 on alkuluku?
//On pystytty todistamaan, että parittomilla n ei saada lausekkeesta alkulukuja sijoituksen n=1 jälkeen.
//Tässä tapauksessa siis 1^2 + 1 = 2, joka on alkuluku. Koska jokaisella parittomalla n saadaan lausekkeesta
//parillinen tulos, se ei voi olla alkuluku (kaikki parilliset luvut jaollisia luvulla 2).
//Lähdetään tutkimaan alkulukujen muodostumista, kun sijoitetaan vain parillisia lukuja n:n arvoiksi.
package alkulukuongelma;

import static java.lang.Math.pow;


public class AlkulukuOngelma {

    public static int onkoALkuluku(int luku){           //Tutkitaan tässä metodissa, että onko pääohjelman luku p alkuluku.
        int i;                          
        int j = 0;
        for(i=3; i <= luku/2; i=i+2){                   //Voidaan jättää parillisilla luvillla jakaminen pois, sillä lauseke
            if(luku % i== 0){                           //n^2+1 tuottaa tällä hetkellä vain parittomia lukuja, jotka eivät
                j=1;                                    //ole jaollisia millään parillisella luvulla.
                break;                                  //Jos jako menee tasan jollain arvolla i, p ei ole alkuluku.
            }
        }
        return j;
    }
 
    public static void main(String[] args) {
        
        int n;
        int p;
        int c;
        int edellinen = 0;
        for(n=2; n<500; n= n+2){
            p = (int)(pow((double)n,(double)2)+1);
            c = onkoALkuluku(p);
            if(c==0){
                System.out.println(n+"^2 + 1 = "+p);
                int erotus = p - edellinen;                     //Tutkitaan tämän "kierroksen" ja edellisen alkuluvun välinen ero
                System.out.println("Ero edelliseen: "+erotus);  //Tästä nähdään, että p:n ja edellisen välimatka kasvaa ja pienenee kaoottisesti.
                edellinen = p;
            }
        }
    }
    
}
