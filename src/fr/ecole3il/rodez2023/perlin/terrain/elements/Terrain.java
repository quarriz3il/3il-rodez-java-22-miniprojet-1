package fr.ecole3il.rodez2023.perlin.terrain.elements;

import fr.ecole3il.rodez2023.perlin.terrain.visualisation.DetermineurTerrain;

public class Terrain {

    /* hydrometrie du terrain */
    private double hydrometrie;

    /* altitude du terrain */
    private double altitude;

    /* température du terrain */
    private double temperature;

    /**  
     * Constructeur de la classe, il va enregistrer les information climatique d'un terrain lorsqu'elle sont bonne
     * @param hydrometrie hydrometrie du terrain
     * @param altitude altitude du terrain
     * @param temperature température du terrain
     * @throws MauvaiseValeurException Se lance lorsque les valeur climatique donnée en paramètre ne correspondent pas au valeurs minimum et maximum que le terrain accepte
     */
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

    /**  
     * Méthode permettant d'obtenir l'hydrométrie du terrain
     * @return l'hydrométrie du terrain
     */
    public double gethydrometrie()
    {
        return hydrometrie;
    }

    /**  
     * Méthode permettant d'obtenir l'altitude du terrain
     * @return l'altitude du terrain
     */
    public double getaltitude()
    {
        return altitude;
    }

    /**  
     * Méthode permettant d'obtenir la temperature terrain
     * @return la temperature du terrain
     */
    public double gettemperature()
    {
        return temperature;
    }

    /**  
     * Méthode qui vérifie la valeur de l'hydrométrie, l'altitue et la température sont correct
     * @param paramEnvironnement c'est la valeur l'hydrométrie ou l'altitue ou la température
     * @param min le minimum que prend la valeur climatique
     * @param max le maximum que prend la valeur climatique
     * @return true si la valeur climatique est bnne, sinon false
     */
    public boolean isGoodValue(double paramEnvironnement, double min, double max)
    {
        if(min <= paramEnvironnement && max >= paramEnvironnement)
            {
                return true;
            }
            return false;
    }

    /**  
     * Méthode qui détermine le type de terrain selon l'altitude, l'hydrométrie et la temperature
     * @param dt 
     * @return le type de terrain
     */
    public TypeTerrain getTypeTerrain(DetermineurTerrain dt)
    {
        return dt.determinerTerrain(altitude, hydrometrie, temperature);
    }

}
