import javax.swing.SwingUtilities;

import pages.Frame;
import utils.UseGlobal;

public class App {
    public static void main(String[] args) throws Exception {

        UseGlobal globals = new UseGlobal();
        globals.start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame(globals);
                frame.setVisible(true);
            }
        });

    }
}
