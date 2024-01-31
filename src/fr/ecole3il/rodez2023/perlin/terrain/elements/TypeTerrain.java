package fr.ecole3il.rodez2023.perlin.terrain.elements;

import fr.ecole3il.rodez2023.perlin.Utils;

public enum TypeTerrain {
    //On veux changer des images, donc on pour ça on a besoin e récupérer des images
    coniferous_forest("data\\tiles\\coniferous_forest.png"),
    deciduous_forest("data\\tiles\\deciduous_forest.png"),
    desert("data\\tiles\\desert.png"),
    hills("data\\tiles\\hills.png"),
    marsh("data\\tiles\\marsh.png"),
    mountain("data\\tiles\\mountain.png"),
    ocean("data\\tiles\\ocean.png"),
    plain("data\\tiles\\plain.png"),
    tundra("data\\tiles\\tundra.png");
    
    private String nomFichier;
    
    private TypeTerrain(String nomFichier)
    {
        this.nomFichier = nomFichier;
    }

    public java.awt.image.BufferedImage getImage()
    {
        return Utils.chargerTuile(nomFichier);
    }

    public String toString()
    {
        return nomFichier;
    }
}
