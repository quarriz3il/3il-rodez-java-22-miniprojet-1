package fr.ecole3il.rodez2023.perlin.terrain.elements;

import java.util.Scanner;

import fr.ecole3il.rodez2023.perlin.terrain.generation.GenerateurCarte;

public class Carte {

    private String nom;
    private int largeur;
    private int hauteur;
    private Terrain [][] terrains;

    public Carte(String nom, int largeur, int hauteur, GenerateurCarte generateurCarte)
    {
        this.nom = nom;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.terrains = generateurCarte.genererCarte(largeur, hauteur);
    }

    Carte(String donneesCarte)
    {
        Scanner scanner = new Scanner(donneesCarte);

        this.nom = scanner.nextLine();
        this.largeur = Integer.parseInt(scanner.nextLine());
        this.hauteur = Integer.parseInt(scanner.nextLine());

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                double altitude = scanner.nextDouble();
                double hydrometrie = scanner.nextDouble();
                double temperature = scanner.nextDouble();    
            }
        }
        scanner.close();
    }

    public String getNom()
    {
        return nom;
    }

    public int getLargeur()
    {
        return largeur;
    }

    public int getHauteur()
    {
        return hauteur;
    }

    public Terrain getTerrain(int x, int y) throws TerrainInexistant
    {
        if(x >= 0 && x <= largeur-1 && y >= 0 || y<= hauteur-1)
        {
            return terrains[x][y];
        } 
        throw new TerrainInexistant("Pas dans la map");
    }
}
