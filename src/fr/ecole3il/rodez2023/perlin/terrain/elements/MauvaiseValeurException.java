package fr.ecole3il.rodez2023.perlin.terrain.elements;

public class MauvaiseValeurException extends IllegalArgumentException
{
    /*La raison qui à destination de l'utilisateur qui va lui expliquer pourquoi cette exceptions à été lancé*/
    private String raison;
    
    /**
     * L'exception que l'on a créer, elle va se lancé lorsque qu'un argument passé à une méthode est invalide
     * @param raison La raison de cette exception
     */
    public MauvaiseValeurException(String raison)
    {
        super(raison);
        this.raison = raison;
    }
}
