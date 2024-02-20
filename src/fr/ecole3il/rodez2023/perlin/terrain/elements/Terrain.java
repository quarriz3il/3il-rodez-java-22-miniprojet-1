package fr.ecole3il.rodez2023.perlin.terrain.elements;

import fr.ecole3il.rodez2023.perlin.terrain.visualisation.DetermineurTerrain;

public class Terrain {

    private double hydrometrie;
    private double altitude;
    private double temperature;

    public Terrain(double hydrometrie, double altitude, double temperature) throws MauvaiseValeurException
    {
        if(isGoodValue(hydrometrie,0,1) && isGoodValue(altitude,-1,1) && isGoodValue(temperature,0,1))
        {
            this.hydrometrie = hydrometrie;
            this.altitude = altitude;
            this.temperature = temperature;
        }

        else{
            throw new MauvaiseValeurException("Mauvaise valeurs");
        }
    }

    public double gethydrometrie()
    {
        return hydrometrie;
    }

    public double getaltitute()
    {
        return altitude;
    }

    public double gettemperature()
    {
        return temperature;
    }

    public boolean isGoodValue(double paramEnvironnement, double min, double max)
    {
        if(min <= paramEnvironnement && max >= paramEnvironnement)
            {
                return true;
            }
            return false;
    }

    public TypeTerrain getTypeTerrain(DetermineurTerrain dt)
    {
        return dt.determinerTerrain(altitude, hydrometrie, temperature);
    }

}
