package fr.ecole3il.rodez2023.perlin.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.ecole3il.rodez2023.perlin.math.Bruit2D;
import fr.ecole3il.rodez2023.perlin.math.BruitPerlin2D;

/**ImageBruitBruit2D
 * 
 * @author proussille
 * Classe représentant une fenêtre pour générer des images de bruit de Perlin.
 */
public class VisualiseurBruitPerlin extends JFrame {

    // Constantes définissant la taille par défaut de l'image
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final double DEFAULT_RESOLUTION = 1.0f;

    private JTextField seedField; // Champ de texte pour la graine
    private JTextField resolutionField; // Champ de texte pour la résolution
    private JLabel imageLabel; // Étiquette pour afficher l'image générée
    private JButton tickImageButton; // Bouton pour mettre à jour l'image
    private ImageBruit noiseImage; // Instance pour manipuler l'image de bruit de Perlin

    /**
     * Constructeur par défaut pour initialiser la fenêtre et les composants.
     */
    public VisualiseurBruitPerlin() {
        setTitle("Génération d'image de bruit de Perlin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel seedLabel = new JLabel("Graine :");
        seedField = new JTextField(10);

        JLabel resolutionLabel = new JLabel("Résolution :");
        resolutionField = new JTextField(String.valueOf(DEFAULT_RESOLUTION), 10);

        JButton generateButton = new JButton("Générer une image");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateImage();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(seedLabel);
        controlPanel.add(seedField);
        controlPanel.add(resolutionLabel);
        controlPanel.add(resolutionField);
        controlPanel.add(generateButton);

        tickImageButton = new JButton("Avancer");
        tickImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickImage();
            }
        });
        tickImageButton.setEnabled(false);
        controlPanel.add(tickImageButton);
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Méthode pour mettre à jour l'image affichée en fonction de l'avancement du bruit de Perlin.
     */
    private void tickImage() {
        BufferedImage image = noiseImage.getNoiseImage(WIDTH, HEIGHT);
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
    }

    /**
     * Méthode pour générer une nouvelle image de bruit de Perlin en fonction des paramètres saisis.
     */
    private void generateImage() {
        String seedText = seedField.getText();
        long seed = seedText.isEmpty() ? System.currentTimeMillis() : Long.parseLong(seedText);

        double resolution = DEFAULT_RESOLUTION;
        try {
            resolution = Float.parseFloat(resolutionField.getText());
        } catch (NumberFormatException e) {
            // Utilise la résolution par défaut si la valeur entrée n'est pas valide
        }
        
        
        ////////// CODE À MODIFIER 
        BruitPerlin2D a = new BruitPerlin2D(seed, resolution);
        noiseImage = new ImageBruit(a);
        
        ////////// FIN CODE À MODIFIER
        tickImageButton.setEnabled(true); // Désactiver le bouton initialement
        tickImage();
    }

    /**
     * Méthode principale pour lancer l'application.
     *
     * @param args Arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualiseurBruitPerlin generator = new VisualiseurBruitPerlin();
            generator.setVisible(true);
        });
    }
}
