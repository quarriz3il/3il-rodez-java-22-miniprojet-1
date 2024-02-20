package fr.ecole3il.rodez2023.perlin.terrain.generation;

import java.util.Random;

import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;

public class GenerateurAleatoire extends GenerateurCarte{
    
    private double hydrometrie;
    private double altitude;
    private double temperature;

    public GenerateurAleatoire(long graine)
    {
        super(graine);
        Random random = new Random();

        hydrometrie = random.nextDouble();
        altitude = random.nextDouble() * 2 - 1;
        temperature = random.nextDouble();
    }

    @Override
    protected Terrain genererTerrain(int i, int j, int largeur, int hauteur)
    {
        return new Terrain(hydrometrie, altitude, temperature);
    }
}
