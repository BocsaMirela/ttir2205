package ttir2205MV;

import note.controller.NoteController;
import note.errors.ClasaException;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.repository.ClasaRepositoryMock;
import note.utils.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestareIntegrareTopDown {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
	public void testFunctionalitate1() throws ClasaException {
		//P->A
        NoteController ctrl=new NoteController();
		Nota nota = new Nota(1, "Matematica", 10);

		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
	}

    @Test
    public void testFunctionalitate2() throws ClasaException{
        //P->B
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
    }

    @Test
    public void testFunctionalitate3(){
        //P->C
        ClasaRepositoryMock clasaRepositoryMock=new ClasaRepositoryMock();
        List<Elev> elevi = new ArrayList<>();
        elevi.add(new Elev(1, "Victor"));
        elevi.add(new Elev(2,"Titus"));
        List<Nota> note = new ArrayList<>();
        note.add(new Nota(1, "Matematica", 1));
        note.add(new Nota(1, "Matematica", 1));
        note.add(new Nota(2,"Matematica",3));
        note.add(new Nota(2,"Romana",4));
        clasaRepositoryMock.creazaClasa(elevi,note);
        List<Corigent> corigenti=clasaRepositoryMock.getCorigenti();
        assertEquals(corigenti.get(0).getNumeElev(),"Titus");
        assertEquals(corigenti.get(1).getNumeElev(),"Victor");
    }

    @Test
	public void test1() throws ClasaException {
		//P->B->A A-valid B-valid
        NoteController ctrl=new NoteController();
		Elev e1 = new Elev(1, "Titus");
		ctrl.addElev(e1);
		Nota nota = new Nota(1, "Matematica", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Medie> rezultate = ctrl.calculeazaMedii();
		assertEquals(10, rezultate.get(0).getMedie(),0);
	}

    @Test
	public void test5() throws ClasaException {
		//P->B->A A-invalid B-valid
        NoteController ctrl=new NoteController();
		Elev e1 = new Elev(1, "Titus");
		ctrl.addElev(e1);
		Nota nota = new Nota(1, "Matematica", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Medie> rezultate = ctrl.calculeazaMedii();
		assertEquals(10, rezultate.get(0).getMedie(),0);
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidMateria);
		Nota nota1 = new Nota(1, "Isto", 5);
		ctrl.addNota(nota1);
	}


	@Test
	public void test6() throws ClasaException {
		//P->B->A A-valid B-invalid
        NoteController ctrl=new NoteController();
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.emptyRepository);
		ctrl.calculeazaMedii();
		Nota nota = new Nota(1, "Matematica", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
	}

    @Test
    public void test7() throws ClasaException {
        NoteController ctrl=new NoteController();
//		//P->B->A->C B-valid A-invalid C-valid
        Elev e1 = new Elev(1, "Titus");
        ctrl.addElev(e1);
        Nota nota = new Nota(1, "Matematica", 10);
        ctrl.addNota(nota);
        assertEquals(1,ctrl.getElevi().size());
        assertEquals(1, ctrl.getNote().size());
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        assertEquals(1, rezultate.size());
        assertEquals(10,rezultate.get(0).getMedie(),0.0);
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.invalidMateria);
        Nota nota1 = new Nota(1, "TIC", 5);
        ctrl.addNota(nota1);
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(corigenti.size(),0);
    }

    @Test
    public void test8() throws ClasaException {
        NoteController ctrl=new NoteController();
        //	//P->B->A->C B-invalid A-valid C-valid
        expectedEx.expect(ClasaException.class);
        expectedEx.expectMessage(Constants.emptyRepository);
        List<Medie> rezultate = ctrl.calculeazaMedii();
        Elev e1 = new Elev(1, "Titus");
        ctrl.addElev(e1);
        Nota nota = new Nota(1, "Matematica", 10);
        ctrl.addNota(nota);
        assertEquals(1, ctrl.getNote().size());
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(corigenti.size(),0);
    }

    @Test
    public void test9() throws ClasaException {
        //P->B->A->C B-valid A-valid C-valid
        NoteController ctrl=new NoteController();
        Elev e1 = new Elev(1, "Elev1");
        ctrl.addElev(e1);
        Nota nota = new Nota(1, "Desena", 10);
        ctrl.addNota(nota);
        assertEquals(1, ctrl.getNote().size());
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        assertEquals(1, rezultate.size());
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(corigenti.size(),0);

    }










}
