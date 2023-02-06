public class Piece {
    private boolean eaten = false;
    private String color = "white";

    public Piece(String color) {
        this.color = color;
    }
    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public String getColor() {
        return color;
    }
}
