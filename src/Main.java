import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    static boolean[][] game=new boolean[30][100];
    static boolean rollOver=false;
    public static void main(String[] args) {
        for(int i=0; i<game.length; i++) {
            for(int j=0; j<game[i].length; j++) {
                game[i][j]=Math.random()>0.5;
            }
        }
        while(true) {
            for(int i=0; i<game.length; i++) {
                for(int j=0; j<game[i].length; j++) {
                    System.out.print(game[i][j]?"▓":"░");
                }
                System.out.println();
            }
            sc.nextLine();
            System.out.println("Next generation");
            boolean[][] temp=new boolean[game.length][game[0].length];
            for(int i=0; i<game.length; i++) {
                for(int j=0; j<game[i].length; j++) {
                    int count=0;
                    if(rollOver) {
                        for(int k=i-1; k<=i+1; k++) {
                            for(int l=j-1; l<=j+1; l++) {
                                count+=(game[(game.length+k)%game.length][(game[0].length+l)%game[0].length]&&!(k==i&&l==j))?1:0;
                            }
                        }
                    } else {
                        for(int k=(i>0)?i-1:0; k<=((i<game.length-1)?i+1:game.length-1); k++) {
                            for(int l=(j>0)?j-1:0; l<=((j<game[0].length-1)?j+1:game[0].length-1); l++) {
                                count+=(game[k][l]&&!(k==i&&l==j))?1:0;
                            }
                        }
                    }
                    if(game[i][j]) {
                        if(count<2) {
                            temp[i][j]=false;
                        } else if(count<4) {
                            temp[i][j]=true;
                        } else temp[i][j]=false;
                    } else {
                        if(count==3) {
                            temp[i][j]=true;
                        }
                    }
                }
            }
            game=temp;
        }
    }
}
