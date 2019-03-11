package note.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import note.errors.ClasaException;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.controller.NoteController;
import note.model.Nota;

//functionalitati
//F01.	 adaugarea unei note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat şi alfabetic dupa nume.


public class StartApp {

    /**
     * @param args
     * @throws ClasaException
     */
    public static void main(String[] args) throws ClasaException {
        // TODO Auto-generated method stub
        NoteController ctrl = new NoteController();
        List<Medie> medii = new LinkedList<Medie>();
        List<Corigent> corigenti = new ArrayList<Corigent>();
        ctrl.readElevi(args[0]);
        ctrl.readNote(args[1]);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        boolean gasit = false;
        while(!gasit) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Adaugare elev");
            System.out.println("5. Afiseaza elevii clasei impreuna cu notele fiecarui elev");
            System.out.println("0. Iesire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
            try {
                int option = Integer.parseInt(br.readLine());
                int nrMatricol;
                switch(option) {
                    case 5:
                        ctrl.afiseazaClasa();
                        break;
                    case 4:
                        System.out.println("Introduceti numarul matricol al elevului: ");
                        nrMatricol = Integer.parseInt(br.readLine());
                        System.out.println("Introduceti numele elevului: ");
                        String nume = br.readLine();
                        Elev elev = new Elev(nrMatricol, nume);
                        ctrl.addElev(elev);
                        ctrl.creeazaClasa(ctrl.getElevi(),ctrl.getNote());
                        break;
                    case 1:
                        System.out.println("Introduceti numarul matricol al elevului caruia doriti sa ii adaugati nota: ");
                        nrMatricol=Integer.parseInt(br.readLine());
                        System.out.println("Introduceti materia la care doriti sa adaugati nota: ");
                        String materie=br.readLine();
                        System.out.println("Introduceti valoarea notei: ");
                        double valoareNota=Integer.parseInt(br.readLine());
                        Nota nota=new Nota(nrMatricol,materie,valoareNota);
                        ctrl.addNota(nota);
                        ctrl.creeazaClasa(ctrl.getElevi(),ctrl.getNote());
                        break;
                    case 2: medii = ctrl.calculeazaMedii();
                        for(Medie medie:medii)
                            System.out.println(medie);
                        break;
                    case 3: corigenti = ctrl.getCorigenti();
                        for(Corigent corigent:corigenti)
                            System.out.println(corigent);
                        break;
                    case 0: gasit = true;
                        break;
                    default: System.out.println("Introduceti o optiune valida!");
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch(ClasaException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
