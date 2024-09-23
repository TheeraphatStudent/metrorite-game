package utils;

import javax.swing.SwingUtilities;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class UseGlobal {

    private Scanner scan = new Scanner(System.in);

    public void start() {
        System.out.println("Java Meteorite Game!");

        for (Map.Entry<String, Double> items : this.optionsContain.entrySet()) {
            String key = items.getKey();
            double value = 0;
            boolean validInput = false;

            while (!validInput) {
                System.out.printf("%s: ", key);
                String choose = scan.nextLine();

                try {
                    value = Double.parseDouble(choose);

                    if ((key.equals("Width") || key.equals("Height")) && value < 250) {
                        System.out.println("Minimum screen size is 250x250px");
                        continue;
                    }

                    if (key.equals("Meteorites") && value < 1) {
                        System.out.println("Minimum meteorite is 1");
                        continue;
                    }

                    if (key.equals("Speed")) {
                        value += .75;
                        if (value < 1.5) {
                            System.out.println("Minimum speed is 1");
                            continue;

                        }
                    }

                    validInput = true;

                } catch (NumberFormatException e) {
                    System.out.println("Input Number Only!\n");
                }
            }

            this.optionsContain.put(key, value);
        }

        System.out.println("========== Game Start! ==========");
    }

    private LinkedHashMap<String, Double> optionsContain = new LinkedHashMap<String, Double>() {
        {
            put("Width", 250d);
            put("Height", 250d);
            put("Meteorites", 1d);
            put("Speed", 1.5d);
        }
    };

    public LinkedHashMap<String, Double> getOptions() {
        return this.optionsContain;
    }
}
