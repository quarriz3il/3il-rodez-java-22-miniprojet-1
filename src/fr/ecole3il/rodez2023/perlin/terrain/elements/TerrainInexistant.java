package fr.ecole3il.rodez2023.perlin.terrain.elements;

public class TerrainInexistant extends ArrayIndexOutOfBoundsException
{
    /*La raison qui à destination de l'utilisateur qui va lui expliquer pourquoi cette exceptions à été lancé*/
    private String raison;
    
    /**
     * L'exception que l'on a créer, elle va se lancé lorsque qu'on tente d'accéder à un élément d'un tableau en utilisant un index qui est en dehors de la plage valide pour ce tableau
     * @param raison La raison de cette exception
     */
    public TerrainInexistant(String raison)
    {
        super(raison);
        this.raison = raison;
    }
}