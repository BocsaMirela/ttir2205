package note.repository;

import java.util.List;

import note.errors.ClasaException;

import note.model.Nota;

public interface NoteRepository {

    public void addNota(Nota nota) throws ClasaException;
    public List<Nota> getNote();
    public void readNote(String fisier);

}
