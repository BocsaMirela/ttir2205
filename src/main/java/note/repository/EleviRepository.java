package note.repository;

import java.util.List;

import note.errors.ClasaException;
import note.model.Elev;

public interface EleviRepository {
    public void addElev(Elev e) throws ClasaException;
    public List<Elev> getElevi();
    public void readElevi(String fisier);
}
