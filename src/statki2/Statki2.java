

package statki2;

import java.util.ArrayList;
import java.util.Scanner;

class Statek {

    private ArrayList<String> polaPołożenia = new ArrayList<>();
    private String nazwa;

    public ArrayList<String> getPolaPołożenia() {
        return polaPołożenia;
    }

    public void setPolaPołożenia(ArrayList<String> polaPołożenia) {
        this.polaPołożenia = polaPołożenia;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Statek{" + "polaPo\u0142o\u017cenia=" + polaPołożenia + ", nazwa=" + nazwa + '}';
    }
    
    public ArrayList<String>położenieStatków(){
    
        int liczbaLosowa1 = (int)(Math.random()*10);
        int liczbaLosowa2 = (int)(Math.random()*10);
        String alfabet = "abcdefghij";
        char wiersz = alfabet.charAt(liczbaLosowa1);
        
        ArrayList<String> położenieStatku = new ArrayList<>();
        for(int i=0; i < 3; i++){
        String tmp = "" + wiersz + (liczbaLosowa2 + i);
        położenieStatku.add(tmp);
        }
        return położenieStatku;
    }
}

class Komputer {

    public String StrzałKomputera() {
    
        String strzałKomputera = null;
        
        int liczbaLosowa1 = (int)(Math.random()*10);
        int liczbaLosowa2 = (int)(Math.random()*10);
        String alfabet = "abcdefghij";
        char wiersz = alfabet.charAt(liczbaLosowa1);
        
        String tmp = "" + wiersz + (liczbaLosowa2);
        return strzałKomputera = tmp;
    }
    
    public ArrayList<String> utwórzPoleGry()
    {
        String alfabet = "abcdefghij";
        String tmp;
        ArrayList<String> poleGry = new ArrayList<>();
        
        for (int z = 0; z <10; z++) {
            char litera = alfabet.charAt(z);
                for (int i=1; i < 11; i++)
                    {
                    tmp = "" + litera  + i;
                    poleGry.add(tmp);
                    }
                }
        
        return poleGry;
    }   
}

public class Statki2 {

    public String przeszukiwanie (String strzałGracza, Statek obiektStatku) {       
        
        int tmp = 0;
        for (int i = 0; i < obiektStatku.getPolaPołożenia().size(); i++ )
        {    
            if (obiektStatku.getPolaPołożenia().get(i).equals(strzałGracza)) tmp = 1 ;    
        }
        obiektStatku.getPolaPołożenia().remove(strzałGracza);
        String wynikPrzeszukiwania = Integer.toString(tmp);
        return wynikPrzeszukiwania;
        }
    
    public static void main(String[] args) throws InterruptedException {

        Statek galera = new Statek();
        Statek galeraGracza = new Statek();
        Statki2 poszukiwanie = new Statki2();
        Statki2 poszukiwanieKomputera = new Statki2();
        
        Komputer komputer = new Komputer();

        galera.setPolaPołożenia(galera.położenieStatków());
        System.out.println("metoda get" + galera.getPolaPołożenia());
        
        galera.setNazwa("Czarna Perła");

        System.out.println(galera.toString());
        
        boolean stanGry = true;
        
        int ilośćRuchów = 0;
        int trafienia = 0;
        int licznik = 0;
        
        Scanner sc = new Scanner(System.in);
        
        ArrayList<String> zapamiętaneOddaneStrzały = new ArrayList<>();
        ArrayList<String> statekGracza = new ArrayList<>();
        ArrayList<String> polaGryWygenerowane = komputer.utwórzPoleGry();
        ArrayList<String> zapamiętaneStrzałyKomputera = new ArrayList<>();
        
        while (statekGracza.size()<3)
        {
            System.out.println("Ustal pola swojego statku");  
            String polaStatkówGracza = sc.nextLine();
            statekGracza.add(polaStatkówGracza);
            galeraGracza.setPolaPołożenia(statekGracza);
        }
        
        while(stanGry) { 
        System.out.println("--------------TWÓJ RUCH--------------");
        String strzał = sc.nextLine();
        boolean zmienna = true;

        for (int i=0; i<zapamiętaneOddaneStrzały.size(); i++){
        if (strzał.equals(zapamiętaneOddaneStrzały.get(i))) {
            System.out.println("Nie można dodać dwa razy tej samej warości");
            zmienna = false;
            break;
        } 
        }
        
        
        if (zmienna==true) zapamiętaneOddaneStrzały.add(strzał);
        licznik++;
        
        String wynikStrzału = poszukiwanie.przeszukiwanie(strzał, galera);
        String porównanie = "1";
        
        
        if (porównanie.equals(wynikStrzału)) 
            {
                System.out.println("Trafiłeś");
                trafienia++;
            } else{
               System.out.println("Pudło");;
            } 
        
        ilośćRuchów++;
        
            java.lang.Thread.sleep(1000);
            
            if (trafienia<3) {
            System.out.println("-----------RUCH PRZECIWNIKA----------");
            java.lang.Thread.sleep(1000);
            System.out.println("Komputer:");
            
            java.lang.Thread.sleep(1000);
            
            Integer y = (int) (Math.random() * polaGryWygenerowane.size());
            String pole = polaGryWygenerowane.get(y);
            System.out.println("Strzał: " + pole );
            polaGryWygenerowane.remove(pole);
            zapamiętaneStrzałyKomputera.add(pole);
           // poszukiwanieKomputera.przeszukiwanie(pole, galeraGracza);
            String wynikStrzałuKomputera = poszukiwanieKomputera.przeszukiwanie(pole, galeraGracza);
            
            if (porównanie.equals(wynikStrzałuKomputera)) 
            {
                System.out.println("Komputer trafił");
            } else{
               System.out.println("Pudło!");;
            }
            
            System.out.println("Rozmiar tabicy: " + polaGryWygenerowane.size());
            
            java.lang.Thread.sleep(1000);
            }
            
            
            if (trafienia==3) 
            {
                stanGry=false;
                System.out.println("Uzyskałeś " + trafienia + " trafienia w " + ilośćRuchów + " ruchach! Zatopiłeś statek");
            }
        }
        
        
        
        System.out.println("Strzelałeś do pól: ");
        for (int i = 0; i < zapamiętaneOddaneStrzały.size(); i++)
                {
                    
                    System.out.print (zapamiętaneOddaneStrzały.get(i) + " ");
                }
        
        System.out.println("");
        System.out.println("-----------------KONIEC GRY-----------------");
    }
}
