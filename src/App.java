import javax.swing.SwingUtilities;

import pages.Frame;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();
                frame.setVisible(true);

            }
        });

    }
}
