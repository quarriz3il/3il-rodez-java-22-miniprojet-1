package fr.ecole3il.rodez2023.perlin.terrain.elements;

import fr.ecole3il.rodez2023.perlin.Utils;
import fr.ecole3il.rodez2023.perlin.terrain.visualisation.DetermineurTerrain;

public enum TypeTerrain {
    /*On veux changer des images, donc on pour ça on a besoin e récupérer des images*/
    FORET_CONIFÈRES("data\\tiles\\coniferous_forest.png"),
    FORET_FEUILLUS("data\\tiles\\deciduous_forest.png"),
    DESERT("data\\tiles\\desert.png"),
    COLLINES("data\\tiles\\hills.png"),
    MARAIS("data\\tiles\\marsh.png"),
    MONTAGNE("data\\tiles\\mountain.png"),
    OCEAN("data\\tiles\\ocean.png"),
    PLAINE("data\\tiles\\plain.png"),
    TOUNDRA("data\\tiles\\tundra.png");
    
    /* Nom du Fichier */
    private String nomFichier;

    /** Constructeur de la classe qui va enregistrer le fichier
    * @param NomFichier Nom du fichier 
    */
    private TypeTerrain(String nomFichier)
    {
        this.nomFichier = nomFichier;
    }
    
    /** Methode qui va récupérer l'image à partir d'un fichier spécifier
    * @return l'image 
    */
    public java.awt.image.BufferedImage getImage()
    {
        return Utils.chargerTuile(nomFichier);
    }

    /** Methode qui va afficher le nom du terrain
    * @return le nom du terrain
    */
    @Override
    public String toString()
    {
        return nomFichier;
    }

}
