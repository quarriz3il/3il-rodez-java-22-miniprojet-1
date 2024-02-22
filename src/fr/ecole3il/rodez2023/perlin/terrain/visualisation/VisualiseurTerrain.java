package fr.ecole3il.rodez2023.perlin.terrain.visualisation;

import fr.ecole3il.rodez2023.perlin.terrain.carte.Carte;
import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;
import fr.ecole3il.rodez2023.perlin.terrain.elements.TypeTerrain;

public abstract class VisualiseurTerrain {

    private Carte carte;
    private DetermineurTerrain determineurTerrain;

    public VisualiseurTerrain(Carte carte, DetermineurTerrain determineurTerrain) 
    {
        this.carte = carte;
        this.determineurTerrain = determineurTerrain;
    }

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

    public HydrometrieAffichee getHydrometrieAffichee(int x, int y) {
        Terrain terrain = carte.getTerrain(x, y);

        if (terrain.gethydrometrie() < 0.33) {
            return HydrometrieAffichee.SEC;
        } else if (terrain.gethydrometrie() < 0.66) {
            return HydrometrieAffichee.MOYEN;
        }

        return HydrometrieAffichee.HUMIDE;
    }

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

    public TypeTerrain getTypeTerrain(int x, int y) {
        Terrain terrain = carte.getTerrain(x, y);
        return determineurTerrain.determinerTerrain(terrain.gethydrometrie(),terrain.getaltitude(), terrain.gettemperature());
    }

    public Terrain getTerrain(int x, int y) {
        return carte.getTerrain(x, y);
    }
}
