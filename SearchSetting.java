
package test_smart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class SearchSetting {

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

    public static void displaySetting() {
        boolean isBack = false;
        SearchSetting.readfile();
        gui_simulation si = new gui_simulation();
        si.setSize(1000, 613);
        si.setVisible(true);

        String nametmp;
        String fa = "5";
        String la = "3";
        boolean isSearch = false;
        boolean isSim = false;
        while (isSearch == false || isSim == false || isBack == false) {
            isBack = si.isBack();
            if (isBack == true) {
                si.setVisible(false);
                Test_Smart a = new Test_Smart();
                a.run();
            }
            isSearch = si.isSearch();
            if (isSearch == true) {
                nametmp = si.getN();
                for (int i = 0; i < barray.size(); i++) {
                    if (barray.get(i).bname.equals(nametmp)) {
                        si.setText(barray.get(i).famount);
                        fa = barray.get(i).famount;
                        si.setText2(barray.get(i).lamount);
                        la = barray.get(i).lamount;
                        break;
                    }
                }
                isSearch = si.setSearch();
            }
            isSim = si.isSim();
            if (isSim == true) {
                // System.out.println(fa+la);
                Nfloornlift a = new Nfloornlift();
                a.setFL(fa, la);
                a.readfile();
                a.initial();
                a.random_stand_by_person();
                a.copy_stand_by_person();
                a.allocate_task();
                isSim = si.setSim();
                si.setVisible(false);
            }
        }
    }
}
