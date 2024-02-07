package byow.Core;
import java.awt.*;
import java.util.ArrayList;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import java.util.List;
import java.util.Random;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;
//make a central input string method and then each input goes to a particular method that handles that input
public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private long seed;
    private static Random random;
    private static TETile[][] world;
    public static Avatar av;
    public static boolean menustate=true;

    public static List<Coin> coins = new ArrayList<>();

    public void main() {
        //ter.initialize(WIDTH, HEIGHT);
        StdDraw_setup();
        ter.initialize(WIDTH, HEIGHT);
        //world=interactWithInputString("N1234SAAA"); //WHY IS THIS HARDCODEDDD
        //world=interactWithInputString(input);
        //ter.renderFrame(world);
        interactWithKeyboard();
        //HUD();
        //interactWithInputStringMoves();
    }
    public void generateRandomWorld(Random random) {
        world = new TETile[WIDTH][HEIGHT];
        fillWithNothing();
        initialize_rooms_hallways(random);
        getWalls();
        makeAvatar(random);
        //HUD();
    }

    public void initialize_rooms_hallways(Random random){
        List<Room> rooms = new ArrayList<>();  //initializes empty list called rooms to store generated rooms
        //List<Coin> coins = new ArrayList<>();
        int numberOfRooms = 20; //creates 20 different rooms
        for (int i = 1; i <= numberOfRooms; i++) { //generates random rooms each time
            int roomWidth = RandomUtils.uniform(random, 8, 12); //random width for room for width between 5 and 10
            int roomHeight = RandomUtils.uniform(random, 13, 17); //room random height between 10 and 12
            int x = RandomUtils.uniform(random, WIDTH - roomWidth); //random x coord for room and ensures it doesnt  go beyond boundary
            int y = RandomUtils.uniform(random, HEIGHT - roomHeight);
            Room room = new Room(x, y, roomWidth, roomHeight); //new room obj with rand x, y
            rooms.add(room); //adds new room to list of rooms
        }

        for (Room room : rooms) {
            if (!checkoverlap(room)) { //if rooms are overlapping, we dont make the room
                createRoom(room);
                createcoin(room);
                room.isroom=true;
            }
            else{
                room.isroom=false;
            }
        }
        makeHorizontalHallways(rooms);
        makeVerticalHallways(rooms);
    }

    public void createcoin(Room room) {
        Coin coin= new Coin(room.x+2,room.y+2) ;
        world[room.x+2][room.y+2]=Tileset.FLOWER;
        coins.add(coin);
    }



    public void makeAvatar(Random random){
        //have to ensure that the avatar is always in on the floor.
        int x = RandomUtils.uniform(random, WIDTH );
        int y = RandomUtils.uniform(random, HEIGHT);
        av= new Avatar (x,y);
        world[x][y] = Tileset.AVATAR;
    }



    public static void makeHorizontalHallways(List<Room> rooms) {
        for(Room room:rooms){
            if(room.isroom){
                if(checknearestRoom(room,room.x-1,room.y,"minus")){ //if there is a room
                    int xcord=room.x-1;
                    int ycord=room.y;
                    while (world[xcord][ycord] == Tileset.NOTHING && xcord<WIDTH && ycord<HEIGHT && xcord>0 && ycord>0) {
                        world[xcord][ycord] = Tileset.FLOOR;
                        xcord -= 1;
                    }
                }
                else{
                    if(checknearestRoom(room,room.x+room.width+1,room.y,"plus")){
                        int xcord=room.x+room.width;
                        int ycord=room.y;
                        while (world[xcord][ycord] == Tileset.NOTHING && xcord<WIDTH && ycord<HEIGHT && xcord>0 && ycord>0) {
                            world[xcord][ycord] = Tileset.FLOOR;
                            xcord += 1;
                        }
                    }
                }
            }
        }
    }

    public static void makeVerticalHallways(List<Room> rooms) {
        for(Room room:rooms){
            if(room.isroom){
                if(checknearestRoom(room,room.x,room.y-1,"down")){ //if there is a room
                    int xcord=room.x;
                    int ycord=room.y-1;
                    while (world[xcord][ycord] == Tileset.NOTHING && xcord<WIDTH && ycord<HEIGHT && xcord>0 && ycord>0) {
                        world[xcord][ycord] = Tileset.FLOOR;
                        ycord -= 1;
                    }
                }
            }
        }
    }


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        /*long seed = System.currentTimeMillis();
        random = new Random(seed);
        generateRandomWorld(random);

        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);*/
        //StdDraw_setup();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                if(c=='Q'){
                    System.exit(0);
                    saveFile();
                }
                /*if(c=='N'){
                    solicitNCharsInput();
                }*/
                moveAvatar(c);

            }
            int x= (int) StdDraw.mouseX();
            int y= (int) StdDraw.mouseY();
            StdDraw.setPenColor(StdDraw.WHITE);
            //StdDraw.textLeft(5,2,"HELLO"); //not displaying anything
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(2,HEIGHT-2,WIDTH,1);//fill the entire thing black
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(2,HEIGHT-2,world[x][y].description()); //ITS OVERWRITING STUFF
            StdDraw.show();
        }

    }

    /*public void solicitNCharsInput() {
        StdDraw.clear();
        StringBuilder inputstrrr = new StringBuilder();
        //drawFrame("");
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char keyTyped = StdDraw.nextKeyTyped();
                if (Character.isLetter(keyTyped)) {
                    inputstrrr.append(keyTyped);
                    StdDraw.text(WIDTH/2,HEIGHT/2,inputstrrr.toString());
                }
            }
        }
        //StdDraw.pause(500);
        interactWithInputString(inputstrrr.toString());
        //return inputstrrr.toString();
    }*/

    public void saveFile(){

    }

    public static void fillWithNothing() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }


    public static boolean checkoverlap(Room room) {
        for (int i = room.x; i < room.x + room.width; i++) {
            for (int j = room.y; j < room.y + room.height; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void getWalls(){ //corner walls missing
        for (int x = 1; x < WIDTH; x++) {
            for (int y = 1; y < HEIGHT; y++) {
                if (world[x][y] == Tileset.FLOOR) {
                    if(world[x+1][y]==Tileset.NOTHING){
                        world[x+1][y]=Tileset.WALL;
                    }
                    if(world[x-1][y]==Tileset.NOTHING){
                        world[x-1][y]=Tileset.WALL;
                    }
                    if(world[x][y-1]==Tileset.NOTHING){
                        world[x][y-1]=Tileset.WALL;
                    }
                    if(world[x][y+1]==Tileset.NOTHING){
                        world[x][y+1]=Tileset.WALL;
                    }

                }
                }
            }
        //fillWallCorners();
    }

    //public void interactWithInputString(String arg) {

        /**
         * Method used for autograding and testing your code. The input string will be a series
         * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
         * behave exactly as if the user typed these characters into the engine using
         * interactWithKeyboard.
         * <p>
         * Recall that strings ending in ":q" should cause the game to quite save. For example,
         * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
         * 7 commands (n123sss) and then quit and save. If we then do
         * interactWithInputString("l"), we should be back in the exact same state.
         * <p>
         * In other words, running both of these:
         * - interactWithInputString("n123sss:q")
         * - interactWithInputString("lww")
         * <p>
         * should yield the exact same world state as:
         * - interactWithInputString("n123sssww")
         * <p>
         * //@param input the input string to feed to your program
         *
         * @return the 2D TETile[][] representing the state of the world
         */
    //}

    public void createRoom(Room room){
        for (int i = room.x; i < room.x + room.width; i++) {
            for (int j = room.y; j < room.y + room.height; j++) {
                //if (j == room.y || j == (room.y + room.height - 1) || i == (room.x + room.width - 1) || i == room.x) {
                //world[i][j] = Tileset.WALL;
                world[i][j] = Tileset.FLOOR;

            }
        }
    }

    public static boolean checknearestRoom(Room room,int xcord,int ycord, String sign){
        //int xcord=room.x-1;
        //int ycord=room.y;
        while (xcord < WIDTH-1 && ycord < HEIGHT-1 && xcord > 0 && ycord>0) {
            if(world[xcord][ycord]!=Tileset.NOTHING){
                return true;
            }
            if(sign=="plus"){
                xcord+=1;
            }
            else if (sign=="minus"){
                xcord-=1;
            }
            else{
                ycord-=1;
            }
        }
        return false;
    }
    public TETile[][] interactWithInputString(String input) {

        ter.initialize(WIDTH, HEIGHT);
        char command = input.charAt(0);
        input = input.substring(1);
        if (command == 'N' || command == 'n') {
            return  inputStringNew(input);
        }
        return null;
    }

   /* public TETile[][] inputStringNew(String input ) {
        StringBuilder seedst = new StringBuilder();
        //char prevc= input.charAt(0);
        for (char c : input.toCharArray()) {
           // c=Character.toUpperCase(c);
            if (Character.isDigit(c)) {
                seedst.append(c);
                //prevc=c;
            }
            else if (c=='s'){
                System.out.println("hi");
                seed = Long.parseLong(seedst.toString());
                random = new Random(seed);
                System.out.println(random);
                generateRandomWorld(random);
                makeAvatar(random);
                break;
                //prevc=c;
            }
             else if(c=='W'||c=='A'||c=='D'){
                moveAvatar(Character.toUpperCase(c));
                //prevc=c;
            }
            //prevc=c;
             else {
                break;
            }
        }

        return world;
    } */

    public TETile[][] inputStringNew(String input ) {
        StringBuilder seedst = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                seedst.append(c);
            }
            else {
                break;
            }
        }
        seed = Long.parseLong(seedst.toString());
        random = new Random(seed);
        System.out.println(random);
        generateRandomWorld(random);
        ter.renderFrame(world);
        //return world;
        for (char c : input.toCharArray()) {
            if(c=='W'||c=='A'||c=='D'||c=='S'){
                moveAvatar(Character.toUpperCase(c));
                //ter.renderFrame(world);
                //prevc=c;
            }
        }
        //interactWithKeyboard();
        return world;
    }

    public void moveAvatar(Character c) {
        int x=av.x;
        int y=av.y;
        world[x][y]=Tileset.FLOOR;

        if(c=='W') {
            if(world[x][y+1] !=Tileset.WALL) {
                y += 1;
            }
        }
        if(c=='A') {
            if(world[x-1][y] !=Tileset.WALL) {
                x-=1;
            }
        }
        if(c=='S') {
            if(world[x][y-1] !=Tileset.WALL) {
                y-=1;
            }
        }
        if(c=='D') {
            if(world[x+1][y] !=Tileset.WALL) {
                x+=1;
            }
        }
        av.move(x,y);
        if(world[x][y]==Tileset.FLOWER){
            collect(x,y);
        }
        world[x][y]=Tileset.AVATAR;
        ter.renderFrame(world);
        //StdDraw.show();
    }

    public void collect(int x, int y){
        System.out.println("i was here");
        for(Coin coin : coins){
            if(coin.x == x){
                coin.exists=false;
                gameOverDisplay();
            }
        }
        /*if(isgameOver()){
            gameOverDisplay();
        }*/

    }

    public boolean isgameOver(){
        System.out.println("i was here");
        for(Coin coin:coins){
            if(coin.exists){
                return false;
            }
        }
        return true;
    }

    public void gameOverDisplay(){
        System.exit(0);
        System.out.println("i was here");
        StdDraw.clear(StdDraw.BLACK); //not working

        //StdDraw.setPenColor(StdDraw.WHITE);
        //StdDraw.textLeft(2,HEIGHT-2,"YOU'RE SO COOL");
        StdDraw.show();
        //StdDraw.text(WIDTH/2,HEIGHT/2,"YOU'RE SO COOL");
    }


    public void interactWithInputStringMoves() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                moveAvatar(c);
            }
        }
    }

    public void StdDraw_setup() {
        //int width = WIDTH;
        //int height = HEIGHT;
        StdDraw.setCanvasSize(WIDTH*16, HEIGHT*16);
        Font font = new Font("Monaco", Font.BOLD, 30);

        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.enableDoubleBuffering();
        StdDraw.text(WIDTH/2,HEIGHT/2,"CS61B : THE GAME");
        font = new Font("Monaco", Font.BOLD, 10);
        StdDraw.text(WIDTH/2,HEIGHT/3,"New Game (N) ");
        StdDraw.text(WIDTH/2,HEIGHT/4,"Load Game (L) ");
        StdDraw.text(WIDTH/2,HEIGHT/6,"Quit (Q) ");
        StdDraw.show();

        /*if(menustate){
            StdDraw.text(WIDTH/2,HEIGHT/2,"CS61B : THE GAME");
            StdDraw.text(WIDTH/2,HEIGHT/2,"CS61B : THE GAME");
            StdDraw.text(WIDTH/2,HEIGHT/2,"CS61B : THE GAME");
            StdDraw.text(WIDTH/2,HEIGHT/2,"CS61B : THE GAME");
            StdDraw.pause(5000);
        }*/

    }

    /*public void HUD() { //displays the description of the tile the mouse is currently on
        //HOW TO CALL, WHEN TO CALL
        //StdDraw.disableDoubleBuffering();
        while(true){
            int x= (int) StdDraw.mouseX();
            int y= (int) StdDraw.mouseY();
            StdDraw.setPenColor(StdDraw.WHITE);
            //StdDraw.textLeft(5,2,"HELLO"); //not displaying anything
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(2,HEIGHT-2,WIDTH,1);//fill the entire thing black
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(2,HEIGHT-2,world[x][y].description()); //ITS OVERWRITING STUFF
            StdDraw.show();
        }
    }*/

    //set up looping condition
    //update hud
    //pull for keyboard input
    //render the frame

    //replay with storing everymove could be a good idea



}

