package note.repository;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import note.errors.ClasaException;
import note.model.Elev;
import note.model.Nota;
import note.utils.Constants;

public class EleviRepositoryMock implements EleviRepository{

    private List<Elev> elevi;

    public EleviRepositoryMock() {
        elevi = new LinkedList<Elev>();
    }

    private boolean validareElev(Elev elev) throws ClasaException {
        // TODO Auto-generated method stub
        if(elev.getNume().length() ==0)
            throw new ClasaException(Constants.invalidNume);
        if(elev.getNrmatricol()<Constants.minNrmatricol|| elev.getNrmatricol()> Constants.maxNrmatricol)
            throw new ClasaException(Constants.invalidNrmatricol);
        return true;
    }

    @Override
    public void addElev(Elev e) throws ClasaException {
        if(!validareElev(e))
            return;
        elevi.add(e);
    }

    @Override
    public List<Elev> getElevi() {
        // TODO Auto-generated method stub
        return elevi;
    }

    @Override
    public void readElevi(String fisier) {
        try {
            FileInputStream fstream = new FileInputStream(fisier);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Elev elev = new Elev(Integer.parseInt(values[0]), values[1]);
                elevi.add(elev);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
