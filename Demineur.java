import java.util.Scanner;
public class Demineur {
    public static void main(String[] a) {
        Scanner clavier = new Scanner(System.in);
        int larg = -1;
        int haut = -1;
        int nbMines = -1;

        // Lecture clavier des paramètres de la partie
        while(haut<5 || haut>=20) {
            System.out.print("Combien de cases de haut pour ce terrain ? [5, 20]: ");
            haut = clavier.nextInt();
        }

        while(larg<5 || larg>=20) {
            System.out.print("Combien de cases de large pour ce terrain ? [5, 20]: ");
            larg = clavier.nextInt();
        }

        while(nbMines<1 || nbMines>=20) {
            System.out.print("Combien de mines à trouver ? [1, 20]: ");
            nbMines = clavier.nextInt();
        }

        // def Terrain de jeu
        Terrain jeu = new Terrain(haut, larg, nbMines);
        System.out.println(jeu);

        while(!jeu.estFini()) {
            // Lecture clavier d'un couple de coordonnées (l,c) où le joueur veut jouer
            int l =-1;
            while(l<0 || l>=haut) {
                System.out.print("Ligne [0.."+(haut-1)+"]: ");
                l = clavier.nextInt();
            }

            int c =-1;
            while(c<0 || c>=larg) {
                System.out.print("Colonne [0.."+(larg-1)+"]: ");
                c = clavier.nextInt();
            } // fin lecture clavier

            jeu.retirerBrouillard(l,c);
            System.out.println(jeu);
        }

        if(jeu.estPerdu()) {
            System.out.println("LOOOOOSEEEEERRRRR >:-}");
        } else {
            System.out.println("Trop la classe ! ! ! ! :D");
        }
    }
}
