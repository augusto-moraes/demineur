public class Terrain {
    private boolean [][] map;
    private boolean [][] fog;
    private boolean perdu;
    private int nbMines;
    private int nbBrouillard;

    public Terrain(int haut, int larg, int nbMines) {
        this.map = new boolean[haut][larg];
        this.fog = new boolean[haut][larg];
        this.perdu = false;
        this.nbMines = nbMines;
        this.nbBrouillard = haut*larg;

        for(int i=0; i<haut; i++) {
            for(int j=0; j<larg; j++) {
                this.fog[i][j] = true;
            }
        }

        int i=0;
        while(i<nbMines) {
            int ranX = (int) (Math.random()*haut);
            int ranY = (int) (Math.random()*larg);

            if(!this.map[ranX][ranY]) {
                this.map[ranX][ranY] = true;
                i++;
            }
        }
    }


    // Renvoie vrai si le joueur a perdu ou s'il reste autant de brouillard que de mines.
    public boolean estFini(){
        return this.perdu || this.nbBrouillard == this.nbMines;
    }


    // Retire le brouillard sur la case de coordonnées (y,x)
    // Si la case contient une mine, alors la partie est perdue
    // Version évoluée (Partie 2) : si la case n'a pas de mine pour voisine,
    // alors propage le retrait du brouillard jusqu'aux cases voisines
    // de mines
    public void retirerBrouillard(int x, int y) {
        this.fog[x][y] = false;
        if(this.map[x][y]) {
            this.perdu = true;
        } else {
            this.nbBrouillard--;
        }
    }


    // Renvoie le nombre de mines présentes dans les cases voisines.
    // Si la case est sur le bord, alors seuls les voisines dans la carte sont considérées
    public int nbMinesVoisines(int x, int y){
        int [] dir = {-1,0,1};
        int res = 0;

        for(int i=0;i<dir.length; i++) {
            for(int j=0;j<dir.length;j++) {
                if(
                    (dir[i] != 0 || dir[j] != 0) && 
                    x+dir[i] >= 0 && x+dir[i] < this.map.length &&
                    y+dir[j] >= 0 && y+dir[j] < this.map[0].length &&
                    this.map[x+dir[i]][y+dir[j]]
                ) {
                    res++;
                }
            }
        }

        return res;
    }

    // Renvoie vrai si et seulement si la partie a été perdue
    // (le joueur a choisi une case contenant une mine)
    public boolean estPerdu(){
        return perdu;
    }



    public String toString(){
        String res="    ";
        for(int n=0;n<map[0].length ; n++) {
            if((n/10)%10>0) {
                res=res+((n/10)%10);
            } else {
                res=res+" ";
            }
        }
        res=res+"\n    ";
        for(int n=0;n<map[0].length ; n++) {
            res=res+(n%10);
        }

        res=res+"\n   +";
        for(int c=0;c<map[0].length ; c++) {
            res=res+"-";
        }
        res=res+"+\n";
        for(int l=0;l<map.length ; l++) {
            res=res+String.format("%3d", l)+"|";
            for(int c=0;c<map[0].length ; c++) {
                if(fog[l][c]) {
                    res=res+"#";
                } else if(map[l][c]) {
                    res=res+"*";
                } else {
                    int n=nbMinesVoisines(l,c);
                    if(n>0) {
                        res=res+n;
                    } else {
                        res=res+' ';
                    }
                }
            }
            res=res+"|\n";
        }
        res=res+"   +";
        for(int c=0;c<map[0].length ; c++) {
            res=res+"-";
        }
        res=res+"+\n";
        return res;
    }
}
