package fr.ecole3il.rodez2023.perlin.terrain.visualisation;

import fr.ecole3il.rodez2023.perlin.terrain.carte.Carte;
import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;
import fr.ecole3il.rodez2023.perlin.terrain.elements.TypeTerrain;

public abstract class VisualiseurTerrain {

    /*Carte qui à été généré */
    private Carte carte;
    /*Création de l'objet détermineurTerrain, on va l'utiliser pour sa méthode */
    private DetermineurTerrain determineurTerrain;

    /** Constructeur VisualiseurTerrain, qui va permettre de donner des valeurs à mes 2 attributs
     * @param carte
     * @param determineurTerrain
     */
    public VisualiseurTerrain(Carte carte, DetermineurTerrain determineurTerrain) 
    {
        this.carte = carte;
        this.determineurTerrain = determineurTerrain;
    }

    /**Méthode qui va donné l'état de température de la case
     * @param x
     * @param y
     * @return L'état de température
     */
    public TempératureAffichee getTemperatureAffichee(int x, int y){
        Terrain terrain = carte.getTerrain(x, y);

        if (terrain.gettemperature() < 0.33)
        {
            return TempératureAffichee.FROID;
        } 
        
        else if (terrain.gettemperature() < 0.66) 
        {
            return TempératureAffichee.TEMPERE;
        }

        return TempératureAffichee.CHAUD;
    }

    /**Méthode qui va donné l'état d'hydrométrie de la case
     * @param x
     * @param y
     * @return L'état d'hydrométrie
     */
    public HydrometrieAffichee getHydrometrieAffichee(int x, int y) {
        Terrain terrain = carte.getTerrain(x, y);

        if (terrain.gethydrometrie() < 0.33) {
            return HydrometrieAffichee.SEC;
        } else if (terrain.gethydrometrie() < 0.66) {
            return HydrometrieAffichee.MOYEN;
        }

        return HydrometrieAffichee.HUMIDE;
    }

    /**Méthode qui va donné l'état d'altitude de la case
     * @param x
     * @param y
     * @return L'état de altitude
     */
    public AltitudeAffichee getAltitudeAffichee(int x, int y) {
        Terrain terrain = carte.getTerrain(x, y);

        if (terrain.getaltitude() < 0) 
        {
            return AltitudeAffichee.FOND_MARIN;
        }
        
        else if (terrain.getaltitude() < 0.33) 
        {
            return AltitudeAffichee.BASSE;
        } 
        
        else if (terrain.getaltitude() < 0.66) 
        {
            return AltitudeAffichee.MOYENNE;
        }
        
        return AltitudeAffichee.ELEVEE;
    }

    /**
     * la méthode Va donner le type de terrain de la case
     * @param x
     * @param y
     * @return le type de terrain
     */
    public TypeTerrain getTypeTerrain(int x, int y) {
        Terrain terrain = carte.getTerrain(x, y);
        return determineurTerrain.determinerTerrain(terrain.getaltitude(), terrain.gethydrometrie(), terrain.gettemperature());
    }

    public Terrain getTerrain(int x, int y) {
        return carte.getTerrain(x, y);
    }
}
