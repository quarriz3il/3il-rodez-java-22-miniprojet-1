package fr.ecole3il.rodez2023.perlin.terrain.generation;

import java.util.Random;


import fr.ecole3il.rodez2023.perlin.math.Bruit2D;
import fr.ecole3il.rodez2023.perlin.math.BruitAleatoire;
import fr.ecole3il.rodez2023.perlin.math.BruitPerlin2D;
import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;


public class GenerateurPerlin extends GenerateurCarte{

    BruitPerlin2D Bruit2Dhydrometrie;
    BruitPerlin2D Bruit2Daltitude;
    BruitPerlin2D Bruit2Dtemperature;

    public GenerateurPerlin(long graine)
    {
        super(graine);
     
        Bruit2Dhydrometrie = new BruitPerlin2D(graine, 1.0);
        Bruit2Daltitude = new BruitPerlin2D(graine * 2, 1.0);;
        Bruit2Dtemperature = new BruitPerlin2D(graine * 4, 1.0);;
    }

    @Override
    protected Terrain genererTerrain(int i, int j, int largeur, int hauteur)
    {
        double x = (double) i / largeur;
        double y = (double) j / hauteur;
        double hydrometrie = Bruit2Dhydrometrie.bruit2D(x,y);
        double altitude = Bruit2Daltitude.bruit2D(x,y);
        double temperature = Bruit2Dtemperature.bruit2D(x,y);

        return new Terrain(hydrometrie, altitude, temperature);
    }
}