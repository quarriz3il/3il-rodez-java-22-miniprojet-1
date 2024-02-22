package fr.ecole3il.rodez2023.perlin.terrain.carte;

import java.util.Scanner;

import fr.ecole3il.rodez2023.perlin.terrain.elements.Terrain;
import fr.ecole3il.rodez2023.perlin.terrain.elements.TerrainInexistant;
import fr.ecole3il.rodez2023.perlin.terrain.generation.GenerateurCarte;

public class Carte {

    /* Le nom de la carte */
    private String nom;

    /* La largeur de la carte */
    private int largeur;

    /* La hauteur de la carte */
    private int hauteur;

    /* Les coordonées d'un terrain */
    private Terrain [][] terrains;

    /**
     * Constructeur de la classe Carte.
     * @param nom  Le nom de la carte.
     * @param largeur La largeur de la carte
     * @param hauteur La hauteur de la carte
     * @param generateurCarte Meme moi je sais pas 
     */

    public Carte(String nom, int largeur, int hauteur, GenerateurCarte generateurCarte)
    {
        this.nom = nom;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.terrains = generateurCarte.genererCarte(largeur, hauteur);
    }

    /**
     * Deuxième Constructeur de la classe Carte.
     * @param donnesCartes Métadonnées de la carte
     */

    public Carte(String donneesCarte)
    {
        Scanner scanner = new Scanner(donneesCarte);

        this.nom = scanner.nextLine();
        this.largeur = Integer.parseInt(scanner.nextLine());
        this.hauteur = Integer.parseInt(scanner.nextLine());
        this.terrains = new Terrain[largeur][hauteur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
            	double hydrometrie = scanner.nextDouble();
                double altitude = scanner.nextDouble();            
                double temperature = scanner.nextDouble();    
                terrains[x][y] = new Terrain(hydrometrie, altitude, temperature);
            }
        }
        scanner.close();
    }

    /**  
     * Méthode permettant d'obtenir le nom utilisé pour la carte
     * @return Le nom utilisé pour la carte
     */
    public String getNom()
    {
        return nom;
    }

    /**  
     * Méthode permettant d'obtenir la largeur de la carte
     * @return la largeur de la carte
     */
    public int getLargeur()
    {
        return largeur;
    }

    /**  
     * Méthode permettant d'obtenir la hauteur de la carte
     * @return la hauteur de la carte
     */
    public int getHauteur()
    {
        return hauteur;
    }

    /**  
     * Méthode permettant d'obtenir le terrain selon les coordonées fournies
     * @param x Les abscisses
     * @param y Les ordonnées
     * @return le terrain qui se trouve au coordonées fournies
     * @throws TerrainInexistant si les coordonées fournies ne se situe sur aucun terrain
     */
    public Terrain getTerrain(int x, int y) throws TerrainInexistant
    {
        if(x >= 0 && x <= largeur-1 && y >= 0 || y<= hauteur-1)
        {
            return terrains[x][y];
        } 
        throw new TerrainInexistant("Pas dans la map");
    }
}
