package edu.upc.dsa;

import edu.upc.dsa.models.Abuso;

import java.util.List;

public interface AbusoManager {
    public Abuso addAbuso(Abuso abuso);
    public int abusoListSize();
    public List<Abuso> getAllAbusos();
}
