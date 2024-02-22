package fr.ecole3il.rodez2023.perlin.terrain.generation;

import java.util.Random;

import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;

public class GenerateurAleatoire extends GenerateurCarte{
    
    /**Constructeur de la classe, 
    * @param graine La graine utilisée pour initialiser le générateur de bruit.
    */
    public GenerateurAleatoire(long graine)
    {
        super(graine);
        
    }

    /**Méthode redéfini qui va générer un Terrain avec les info climatique qui ont été généré aléatoirement */
    @Override
    protected Terrain genererTerrain(int i, int j, int largeur, int hauteur)
    {
        Random random = new Random();

        double hydrometrie = random.nextDouble();
        double altitude = random.nextDouble();
        double temperature = random.nextDouble();
        return new Terrain(hydrometrie, altitude, temperature);
    }
}
