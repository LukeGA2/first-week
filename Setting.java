
package test_smart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Setting {

    public static String name;
    public static String flooramount;
    public static String liftamount;
    public static int counter = 0;
    public static ArrayList<Building> barray = new ArrayList<Building>();
    public static Building build = new Building();

    public static void readfile() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("Building.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                build = null;
                build = new Building();
                name = line;
                flooramount = br.readLine();
                String floor = flooramount;
                liftamount = br.readLine();
                String lift = liftamount;
                build.setfields(name, floor, lift);
                barray.add(build);//add to arraylist
            }
        } catch (IOException e) {
            System.out.println("No Building info!");
        }

    }

    public static void addbuilding() {
        build = null;
        boolean isSubmit = false;
        boolean isBack = false;
        gui_BSetting bs = new gui_BSetting();
        bs.setSize(1000,613);
        bs.setVisible(true);
        while (isSubmit == false && isBack == false) {
            isSubmit = bs.isSubmit();
            isBack = bs.isBack();
        }
        if (isBack == false) {
            name = bs.getBN();
            String floortmp = bs.getFN();
            String liftmp = bs.getLN();
            build = new Building();
            build.setfields(name, floortmp, liftmp);
            build.writetofile();
            barray.add(build);
            bs.setVisible(false);
        } else {
            bs.setVisible(false);
        }
    }
}
