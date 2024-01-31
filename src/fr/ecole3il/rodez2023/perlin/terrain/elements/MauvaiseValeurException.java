package fr.ecole3il.rodez2023.perlin.terrain.elements;

public class MauvaiseValeurException extends IllegalArgumentException
{
    private String raison;
    
    public MauvaiseValeurException(String raison)
    {
        super(raison);
        this.raison = raison;
    }
}
