package ttir2205MV;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import note.controller.NoteController;
import note.errors.ClasaException;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.ClasaRepositoryMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAdaugareNotaECPValid(){
        try {
            NoteController noteController = new NoteController();
            int previousSize = noteController.getNote().size();
            noteController.addNota(new Nota(1, "Matematica", 10));
            int newSize = noteController.getNote().size();
            assertEquals(previousSize + 1, newSize );
        }catch (ClasaException ex){
            ex.printStackTrace();
        }
    }

    @Test(expected=ClasaException.class)
    public void testAdaugareNotaECPInvalid() throws ClasaException{
        NoteController noteController = new NoteController();
        noteController.addNota(new Nota(1, "Matematica", 15));
        noteController.addNota(new Nota(1, "Matematica", 9.5));

    }

    @Test
    public void testAdaugareNotaBVAValid() throws ClasaException{
        NoteController noteController=new NoteController();
        int previousSize = noteController.getNote().size();
        noteController.addNota(new Nota(1, "Matematica", 1));
        noteController.addNota(new Nota(1, "Matematica", 2));
        int newSize = noteController.getNote().size();
        assertEquals(previousSize + 2, newSize );
    }

    @Test(expected=ClasaException.class)
    public void testAdaugareNotaBVAInvalid() throws ClasaException{
        NoteController noteController=new NoteController();
        noteController.addNota(new Nota(1, "Matematica", 0));
    }

    @Test()
    public void testCalculareMedieWBTValid(){
        try {
            ClasaRepositoryMock clasaRepository = new ClasaRepositoryMock();
            List<Elev> elevi = new ArrayList<>();
            elevi.add(new Elev(1, "Titus"));
            elevi.add(new Elev(2, "Victor"));
            List<Nota> note = new ArrayList<>();
            note.add(new Nota(1, "Matematica", 10));
            note.add(new Nota(1, "Matematica", 1));
            note.add(new Nota(1, "Romana", 10));
            note.add(new Nota(1, "Romana", 1));
            note.add(new Nota(2, "Matematica", 3));
            note.add(new Nota(2, "Matematica", 4));
            note.add(new Nota(2, "Romana", 6));
            note.add(new Nota(2, "Romana", 5));
            clasaRepository.creazaClasa(elevi, note);
            List<Medie> medii=clasaRepository.calculeazaMedii();
            //System.out.println(medii.get(0).getMedie());
            double m1=5.5;
            double m2=4.5;
            if(medii.get(0).getElev().getNrmatricol()==1) {
                assertEquals(m1, medii.get(0).getMedie(), 0.0);
                assertEquals(m2, medii.get(1).getMedie(), 0.0);
            }
            else{
                assertEquals(m2, medii.get(0).getMedie(), 0.0);
                assertEquals(m1, medii.get(1).getMedie(), 0.0);
            }
        }catch (ClasaException ex){
            ex.printStackTrace();
        }
    }

    @Test(expected=ClasaException.class)
    public void testCalculareMedieWBTNevalid() throws ClasaException{
        ClasaRepositoryMock clasaRepositoryMock=new ClasaRepositoryMock();
        clasaRepositoryMock.calculeazaMedii();
    }

    @Test
    public void testCalculareMedieWBTValid2(){
        try{
            ClasaRepositoryMock clasaRepositoryMock=new ClasaRepositoryMock();
            List<Elev> elevi = new ArrayList<>();
            elevi.add(new Elev(2, "Victor"));
            elevi.add(new Elev(1,"Titus"));
            List<Nota> note = new ArrayList<>();
            note.add(new Nota(1, "Matematica", 10));
            note.add(new Nota(1, "Matematica", 1));
            clasaRepositoryMock.creazaClasa(elevi,note);
            List<Medie> medii=clasaRepositoryMock.calculeazaMedii();
            double m1=5.5;
            double m2=1.0;
            if(medii.get(0).getElev().getNrmatricol()==1) {
                assertEquals(m1, medii.get(0).getMedie(), 0.0);
                assertEquals(m2, medii.get(1).getMedie(), 0.0);
            }
            else{
                assertEquals(m2, medii.get(0).getMedie(), 0.0);
                assertEquals(m1, medii.get(1).getMedie(), 0.0);
            }
        }
        catch (ClasaException ex){
            ex.printStackTrace();
        }
    }










    }
