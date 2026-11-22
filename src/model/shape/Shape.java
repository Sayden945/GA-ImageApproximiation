package model.shape;

import java.awt.*;

public interface Shape {
    void draw(Graphics2D g);
    void mutate(double rate);
    Shape clone();
}
