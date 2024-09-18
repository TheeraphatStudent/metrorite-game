package resource.colors;

import java.awt.Color;

abstract class ColorConfigProps {
    public Color primary() {
        return new Color(255, 250, 50);
    }
}

public class Configs extends ColorConfigProps {
    

}
