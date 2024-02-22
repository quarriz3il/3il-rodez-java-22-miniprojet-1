package fr.ecole3il.rodez2023.perlin.terrain.generation;

import java.util.Random;

import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;

public class GenerateurAleatoire extends GenerateurCarte{
    
    /* hydrometrie du terrain */
    private double hydrometrie;

    /* altitude du terrain */
    private double altitude;

    /* température du terrain */
    private double temperature;

    /**Constructeur de la classe, elle va générer aléatoirement des données pour l'hydrométrie, l'altitude et la températue 
    * @param graine La graine utilisée pour initialiser le générateur de bruit.
    */
    public GenerateurAleatoire(long graine)
    {
        super(graine);
        Random random = new Random();

        hydrometrie = random.nextDouble();
        altitude = random.nextDouble() * 2 - 1;
        temperature = random.nextDouble();
    }

    /**Méthode redéfini qui va générer un Terrain avec les info climatique qui ont été généré aléatoirement */
    @Override
    protected Terrain genererTerrain(int i, int j, int largeur, int hauteur)
    {
        return new Terrain(hydrometrie, altitude, temperature);
    }
}
