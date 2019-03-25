package ttir2205MV;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import note.controller.NoteController;
import note.errors.ClasaException;
import note.model.Nota;
import org.junit.Test;

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





}
