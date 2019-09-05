/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakojaannos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jakojaannos {

   public static int laskeJakojaannos(int mod, int luku){
       int jakojaannos;
       jakojaannos = luku % mod;
       return jakojaannos;
   }
   public static int syotaLuku(){
        Scanner lukija = new Scanner(System.in);
        while(true){
            System.out.println("Syötä jaettava luku (minkä jakojäännöksen haluat tietää):");
            try{
                int luku = Integer.valueOf(lukija.nextLine());
                if(luku == 0){
                    System.out.println("\nHyvää yötä!\n");
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
   
   public static int syotaMod(){
        Scanner lukija = new Scanner(System.in);
        while(true){
            System.out.println("Syötä jakaja(= mod):");
            try{
                int mod = Integer.valueOf(lukija.nextLine());
                if(mod == 0){
                    System.out.println("\nHyvää yötä!\n");
                    System.exit(0);
                }else if(mod < 0){
                    System.out.println("Syöttämäsi luku ei ollut positiivinen kokonaisluku!");
                    continue;
                }
                return mod;
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
            int mod = syotaMod();
            String tulos = "Jakojäännös jaettaessa luku "+luku+" luvulla "+mod+" on ";
            int jakojaannos = laskeJakojaannos(mod, luku);
            tulos += jakojaannos;
            tulos += "\neli "+luku+" (mod "+mod+") = "+jakojaannos;
            System.out.println(tulos);
            System.out.println("_________________________________________________________");
        }
        
    }
    
}
