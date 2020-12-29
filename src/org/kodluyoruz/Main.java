package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int panelSize;
    static Scanner scanner = new Scanner(System.in);
    static char[][] sosPanel = new char[panelSize][panelSize];
    static char player;
    static char you;
    static int moveSize;
    static int youPoint = 0;
    static int playerPoint =0;

    public static boolean checkPoint(){
        for (int i=0; i<panelSize; i++){
            for (int j=0; j<panelSize; j++){
                if (j+2>=panelSize){
                    break;
                }
                if ("SOS".equals(sosPanel[i][j]+""+sosPanel[i][j+1]+""+sosPanel[i][j+2])){
                    System.out.println("Puan Kazanıldı!");
                    sosPanel[i][j]='s';
                    sosPanel[i][j+1]='o';
                    sosPanel[i][j+2]='s';
                    return true;
                }
                if (i+2>=panelSize){
                    break;
                }
                if ("SOS".equals(sosPanel[i][j]+""+sosPanel[i+1][j]+""+sosPanel[i+2][j])){
                    System.out.println("Puan Kazanıldı!");
                    sosPanel[i][j]='s';
                    sosPanel[i+1][j]='o';
                    sosPanel[i+2][j]='s';
                    return true;
                }
            }

        }
        return false;
    }

    public static void makeMove(char yourCharacter){
        int x,y;

        System.out.println("İşlem yapacağınız satırını seçiniz: ");
        x = scanner.nextInt();

        while (x<0 || x>=panelSize){
            System.out.println("Girilen değer 0-"+(panelSize-1)+" aralığında olmalıdır");
            x = scanner.nextInt();
        }

        System.out.println("İşlem yapacağınız sutunu seçiniz: ");
        y = scanner.nextInt();

        while (y<0 || y>=panelSize){
            System.out.println("Girilen değer 0-"+(panelSize-1)+" aralığında olmalıdır");
            y = scanner.nextInt();
        }

        if (sosPanel[x][y] == '-'){
            sosPanel[x][y] = yourCharacter;
            moveSize--;
            System.out.println("Satır no: "+ x + " sutun no: " + y + " karakter: " + yourCharacter);
            if (checkPoint()){
                youPoint++;
            }
            System.out.println("SKOR: you: "+youPoint + " player: "+playerPoint);
        }else{
            System.out.println("Bu satıra daha önceden işlem yapılmış. Tekrar deneyin!");
            makeMove(yourCharacter);
        }

        for (int i=0; i<panelSize; i++){
            for (int j=0; j<panelSize; j++){
                System.out.print(sosPanel[i][j] + " ");
            }
            System.out.println(" ");
        }
        if (moveSize > 0){
            makeRandomMove(player);
        }else {
            System.out.println("Oyun Sona Erdi.");
            System.exit(0);
        }

    }

    public static void makeRandomMove(char playerCharacter){
        Random random = new Random();
        int x,y;
        x = random.nextInt(panelSize);
        y = random.nextInt(panelSize);

        if (sosPanel[x][y] == '-'){
            sosPanel[x][y] = playerCharacter;
            moveSize--;
            System.out.println("Player hamlesini yaptı, sıra sende!");
            if (checkPoint()){
                playerPoint++;
            }
            System.out.println("SKOR: you: "+youPoint + " player: "+playerPoint);
        }else{
            makeRandomMove(playerCharacter);
        }

        for (int i=0; i<panelSize; i++){
            for (int j=0; j<panelSize; j++){
                System.out.print(sosPanel[i][j] + " ");
            }
            System.out.println(" ");
        }
        if (moveSize > 0) {
            makeMove(you);
        }else {
            System.out.println("Oyun Sona Erdi.");
            System.exit(0);
        }
    }

    public static void play(){
        char firstMove;

        while (panelSize<3 || panelSize>7){
            System.out.println("lütfen 3 ile 7 arasında bir rakam giriniz.");
            panelSize = scanner.nextInt();
        }

        for (int i=0; i<panelSize; i++){
            for (int j=0; j<panelSize; j++){
                sosPanel[i][j] = '-';
            }
        }
        System.out.println("Oyun Başlıyor!");

        for (int i=0; i<panelSize; i++){
            for (int j=0; j<panelSize; j++){
                System.out.print(sosPanel[i][j] + " ");
            }
            System.out.println(" ");
        }

        String sosChar = "SO";
        Random random = new Random();

        you = sosChar.charAt(random.nextInt(sosChar.length()));
        firstMove = sosChar.charAt(random.nextInt(sosChar.length()));

        if (you == 'S'){
            player = 'O';
        }else{
            player = 'S';
        }

        System.out.println("Oyunda " + you + " karakteri sizin olacak. Karşı oyuncu ise " + player + " ile size karşılık verecek :)");
        System.out.println("Oyuna ilk " + firstMove + " başlıyor!");

        if (firstMove == you){
            makeMove(you);
        }else {
            makeRandomMove(player);
        }

    }

    public static void main(String[] args) {
        System.out.println("...Kodluyoruz.org SOS Game...");
        System.out.println("Lütfen panel büyüklüğü için 3-7 arası bir rakam giriniz.");
        panelSize = scanner.nextInt();
        sosPanel = new char[panelSize][panelSize];
        moveSize = panelSize*panelSize;
        play();
    }
}

