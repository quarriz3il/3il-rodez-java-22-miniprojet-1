package fr.ecole3il.rodez2023.perlin.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.ecole3il.rodez2023.perlin.terrain.carte.Carte;
import fr.ecole3il.rodez2023.perlin.terrain.elements.TerrainInexistant;
import fr.ecole3il.rodez2023.perlin.terrain.carte.ManipulateurCarte;
import fr.ecole3il.rodez2023.perlin.terrain.concrets.VisualiseurTerrainEnonce;
import fr.ecole3il.rodez2023.perlin.terrain.elements.TypeTerrain;
import fr.ecole3il.rodez2023.perlin.terrain.generation.GenerateurAleatoire;
import fr.ecole3il.rodez2023.perlin.terrain.generation.GenerateurPerlin;

public class VisualiseurCarteTerrain extends JFrame {

    private static final long serialVersionUID = -4664266628089280746L;
    private final JPanel cartePanel;
    private Carte carte;
    private JLabel terrainLabel;
    private VisualiseurTerrainEnonce vte;

    public void drawCarte(Carte carte, Graphics g, int panelWidth, int panelHeight) {
        vte = new VisualiseurTerrainEnonce(carte);
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        int tuileWidth = panelWidth / largeur;
        int tuileHeight = panelHeight / hauteur;

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                try {
                    TypeTerrain type = vte.getTypeTerrain(x, y);
                    BufferedImage image = type.getImage();
                    g.drawImage(image, x * tuileWidth, y * tuileHeight, tuileWidth, tuileHeight, null);
                } catch (TerrainInexistant e) {
                    System.out.println("Terrain inexistant : " + e.getMessage());
                }
            }
        }
    }

    public VisualiseurCarteTerrain() {
        VisualiseurCarteTerrain monObjet = this;
        setTitle("Visualiseur de Carte");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel pour afficher la carte
        cartePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (carte != null) {
                    monObjet.drawCarte(carte, g, getWidth(), getHeight());
                }
            }
        };
        cartePanel.setOpaque(true);

        cartePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (carte == null) {
                    return;
                }
                int tuileWidth = cartePanel.getWidth() / carte.getLargeur();
                int tuileHeight = cartePanel.getHeight() / carte.getHauteur();

                int x = e.getX() / tuileWidth;
                int y = e.getY() / tuileHeight;

                System.out.println("Coordonnées de la souris - X: " + x + ", Y: " + y);

                if (x >= 0 && x < carte.getLargeur() && y >= 0 && y < carte.getHauteur()) {
                    try {
                        String contenu = "Altitude: " + vte.getAltitudeAffichee(x, y) + "\nHydrométrie: "
                                + vte.getHydrometrieAffichee(x, y) + "\nTempérature: "
                                + vte.getTemperatureAffichee(x, y);
                    } catch (TerrainInexistant e1) {
                        System.out.println("Terrain inexistant : " + e1.getMessage());
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (terrainLabel != null) {
                    terrainLabel.setText("Terrain: ");
                }
            }
        });

        // Initialisation de terrainLabel
        terrainLabel = new JLabel("Terrain: ");
        add(terrainLabel, BorderLayout.SOUTH); 

        cartePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int tuileWidth = cartePanel.getWidth() / carte.getLargeur();
                int tuileHeight = cartePanel.getHeight() / carte.getHauteur();

                int x = e.getX() / tuileWidth;
                int y = e.getY() / tuileHeight;

                if (x >= 0 && x < carte.getLargeur() && y >= 0 && y < carte.getHauteur()) {
                    try {
                        String contenu = "Altitude: " + vte.getAltitudeAffichee(x, y) + "\nHydrométrie: "
                                + vte.getHydrometrieAffichee(x, y) + "\nTempérature: "
                                + vte.getTemperatureAffichee(x, y);
                    } catch (TerrainInexistant e1) {
                        System.out.println("Terrain inexistant : " + e1.getMessage());
                    }
                }

            }
        });
        add(cartePanel, BorderLayout.CENTER);

        // Barre de menu avec les options
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Carte");

        JMenuItem chargerItem = new JMenuItem("Charger une carte");
        chargerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choix = fileChooser.showOpenDialog(VisualiseurCarteTerrain.this);

                if (choix == JFileChooser.APPROVE_OPTION) {
                    File fichierSelectionne = fileChooser.getSelectedFile();
                    String cheminFichier = fichierSelectionne.getAbsolutePath();

                    carte = ManipulateurCarte.chargerCarte(cheminFichier);

                    repaint();
                }
            }
        });

        JMenuItem enregistrerItem = new JMenuItem("Enregistrer la carte");
        enregistrerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choix = fileChooser.showSaveDialog(VisualiseurCarteTerrain.this);

                if (choix == JFileChooser.APPROVE_OPTION) {
                    File fichierSelectionne = fileChooser.getSelectedFile();
                    String cheminFichier = fichierSelectionne.getAbsolutePath();

                    try {
                        ManipulateurCarte.enregistrerCarte(carte, cheminFichier);
                    } catch (TerrainInexistant e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JMenuItem genererItem = new JMenuItem("Générer une carte");
        genererItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genererCarteDialogue();
            }
        });

        menu.add(chargerItem);
        menu.add(enregistrerItem);
        menu.add(genererItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void genererCarteDialogue() {
        JTextField largeurField = new JTextField(5);
        JTextField hauteurField = new JTextField(5);
        JTextField graineAlea = new JTextField(5);

        JPanel choixPanel = new JPanel();
        choixPanel.add(new JLabel("Largeur:"));
        choixPanel.add(largeurField);
        choixPanel.add(new JLabel("Hauteur:"));
        choixPanel.add(hauteurField);
        choixPanel.add(new JLabel("Graine aléatoire:"));
        choixPanel.add(graineAlea);
        graineAlea.setText(Long.toString(System.currentTimeMillis()));

        String[] generateurOptions = { "GenerateurPerlin", "GenerateurAleatoire" };
        JComboBox<String> generateurBox = new JComboBox<>(generateurOptions);

        JOptionPane.showMessageDialog(null, choixPanel, "Paramètres de génération", JOptionPane.QUESTION_MESSAGE);
        int result = JOptionPane.showConfirmDialog(null, generateurBox, "Choix du générateur",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int largeur = Integer.parseInt(largeurField.getText());
            int hauteur = Integer.parseInt(hauteurField.getText());
            long graine = Long.parseLong(graineAlea.getText());
            String generateurSelectionne = (String) generateurBox.getSelectedItem();

            if (generateurSelectionne.equals("GenerateurPerlin")) {
                carte = new Carte("Nouvelle carte", largeur, hauteur, new GenerateurPerlin(graine));
            } else {
                carte = new Carte("Nouvelle carte", largeur, hauteur,
                        new GenerateurAleatoire(graine));
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualiseurCarteTerrain visualiseur = new VisualiseurCarteTerrain();
            visualiseur.setVisible(true);
        });
    }
}
