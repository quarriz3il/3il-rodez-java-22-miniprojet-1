package fr.ecole3il.rodez2023.perlin.terrain.visualisation;

import fr.ecole3il.rodez2023.perlin.terrain.elements.TypeTerrain;

public interface DetermineurTerrain {

	/*C'est une méthode que je dois implémenter qui va me permettre de connaître le type de terrain selon l'altitude, l'hydrométrie et la température */
	public TypeTerrain determinerTerrain(double altitude, double hydrometrie, double temperature);


}
