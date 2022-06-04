package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.lang.Object;
import java.util.*;


public class AbusoManagerImpl implements AbusoManager{
    //logs
    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);
    private List<Abuso> abusoList;
    private static AbusoManagerImpl instance;

    private AbusoManagerImpl() {
        this.abusoList = new LinkedList<>();
    }
    //Singleton
    public static AbusoManagerImpl getInstance() {
        //logger.info(instance);
        if (instance == null)
            instance = new AbusoManagerImpl();
        //logger.info(instance);
        return instance;
    }

    //Publicamos un abuso
    @Override
    public Abuso addAbuso(Abuso abuso) {

        String issue = abuso.getDate();
        for (Abuso i : this.abusoList) {
            if (i.getDate().equals(issue)) {
                logger.info("Abuso " + issue + " encontrada");
                return null;
            }
        }
        logger.info("Nuevo abuso: " + abuso);
        this.abusoList.add(abuso);
        logger.info("Nueva abuso añadido: " + abuso);
        return abuso;
    }

    @Override
    public int abusoListSize() {
        return this.abusoList.size();
    }

    @Override
    public List<Abuso> getAllAbusos(){
        return this.abusoList;
    }


}
