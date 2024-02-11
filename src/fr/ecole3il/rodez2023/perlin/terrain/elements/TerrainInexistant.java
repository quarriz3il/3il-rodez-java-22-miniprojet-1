package fr.ecole3il.rodez2023.perlin.terrain.elements;

public class TerrainInexistant extends ArrayIndexOutOfBoundsException
{
    private String raison;
    
    public TerrainInexistant(String raison)
    {
        super(raison);
        this.raison = raison;
    }
}