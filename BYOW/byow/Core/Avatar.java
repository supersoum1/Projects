package byow.Core;

public class Avatar {
    int x; //x- coordinate of its current position
    int y; // y-coordinate of its current position

    public Avatar(int curr_x, int curr_y) {
        x = curr_x;
        y = curr_y;
    }
    public void move(int new_x,int new_y) {
        x= new_x;
        y=new_y;
    }
}
