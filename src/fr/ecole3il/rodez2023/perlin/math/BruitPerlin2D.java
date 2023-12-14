package fr.ecole3il.rodez2023.perlin.math;


/**
 * @author philibert roquart, fainéant
 */
public class BruitPerlin2D extends Bruit2D {

	// Vecteurs de gradient pour le bruit de Perlin
	private static final float[][] GRADIENT_2D = { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 0 }, { -1, 0 },
			{ 0, 1 }, { 0, -1 } };

	// Tableau de permutations pour le bruit de Perlin
	private static final int[] PERMUTATION = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225,
			140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94,
			252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74,
			165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41,
			55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169,
			200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5,
			202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183,
			170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98,
			108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12,
			191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204,
			176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195,
			78, 66, 215, 61, 156, 180 };

	private final int[] permutation;

	public BruitPerlin2D(long graine, double resolution) {
		super(graine, resolution);
		this.permutation = PERMUTATION;
	}

	@Override
	public double bruit2D(double x, double y) {
		double tempX, tempY;
		int x0, y0, ii, jj, gi0, gi1, gi2, gi3;
		double unit = 1.0f / (double) Math.sqrt(2);
		double tmp, s, t, u, v, Cx, Cy, Li1, Li2;
		// Adapter pour la résolution
		x /= getresolution();
		y /= getresolution();

		// Obtenir les coordonnées de la grille associées à (x, y)
		x0 = (int) (x);
		y0 = (int) (y);

		// Masquage pour récupérer les indices de permutation
		ii = x0 & 255;
		jj = y0 & 255;

		// Récupérer les indices de gradient associés aux coins du quadrilatère
		gi0 = permutation[ii + permutation[jj]] % 8;
		gi1 = permutation[ii + 1 + permutation[jj]] % 8;
		gi2 = permutation[ii + permutation[jj + 1]] % 8;
		gi3 = permutation[ii + 1 + permutation[jj + 1]] % 8;

		// Récupérer les vecteurs de gradient et effectuer des interpolations pondérées
		tempX = x - x0;
		tempY = y - y0;
		s = GRADIENT_2D[gi0][0] * tempX + GRADIENT_2D[gi0][1] * tempY;

		tempX = x - (x0 + 1);
		tempY = y - y0;
		t = GRADIENT_2D[gi1][0] * tempX + GRADIENT_2D[gi1][1] * tempY;

		tempX = x - x0;
		tempY = y - (y0 + 1);
		u = GRADIENT_2D[gi2][0] * tempX + GRADIENT_2D[gi2][1] * tempY;

		tempX = x - (x0 + 1);
		tempY = y - (y0 + 1);
		v = GRADIENT_2D[gi3][0] * tempX + GRADIENT_2D[gi3][1] * tempY;

		// Interpolations pour lisser les valeurs obtenues
		tmp = x - x0;
		Cx = 3 * tmp * tmp - 2 * tmp * tmp * tmp;

		Li1 = s + Cx * (t - s);
		Li2 = u + Cx * (v - u);

		tmp = y - y0;
		Cy = 3 * tmp * tmp - 2 * tmp * tmp * tmp;

		return Li2 + Cy * (Li2 - Li1);
	}
}
