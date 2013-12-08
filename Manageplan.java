
package test_smart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Manageplan {

    public static ArrayList<Plan> planarray = new ArrayList<Plan>();
    public static Plan plans = new Plan("tmp");//suppose to add into ArrayList
    public static int counter = 0;
    public static String name;
    public static int year, month, day, hour, min;
    public static String liftnum;
    public static String defloor;
    public static String date, time;
    public static String freq;
    public static String afloor;
    public static boolean duplicate = true;

    public static void readfile() {

        try {

            BufferedReader br = new BufferedReader(new FileReader("PlanRecord.txt"));
            String line = null;
            int linenum = 0;

            while ((line = br.readLine()) != null) {

                plans = null;

                if (line.indexOf("####") >= 0) {
                    counter++;
                } else {
                    //read plan detail into plan[]
                    //get name
                    String[] tmp = line.split(":");

                    name = tmp[1];
                    plans = new Plan(name);

                    //get time
                    boolean flag = true;
                    time = br.readLine();
                    String[] tmpstime = time.split(":");
                    hour = Integer.parseInt(tmpstime[0]);
                    min = Integer.parseInt(tmpstime[1]);
                    plans.setime(hour, min, flag);

                    flag = false;
                    time = br.readLine();
                    String[] tmpetime = time.split(":");
                    hour = Integer.parseInt(tmpetime[0]);
                    min = Integer.parseInt(tmpetime[1]);
                    plans.setime(hour, min, flag);

                    //get lift number
                    liftnum = br.readLine();
                    String[] tmplift = liftnum.split(",");
                    int le = tmplift.length;
                    String[] str2 = new String[le];
                    System.arraycopy(tmplift, 0, str2, 0, tmplift.length);
                    plans.setliftnum(str2);

                    //get default floor
                    defloor = br.readLine();
                    plans.setdef(defloor);

                    //get date
                    flag = true;
                    date = br.readLine();
                    String[] tmpstart = date.split("/");
                    day = Integer.parseInt(tmpstart[0]);
                    month = Integer.parseInt(tmpstart[1]);
                    year = Integer.parseInt(tmpstart[2]);
                    plans.setdate(year, month, day, flag);

                    flag = false;
                    date = br.readLine();
                    String[] tmpend = date.split("/");
                    day = Integer.parseInt(tmpend[0]);
                    month = Integer.parseInt(tmpend[1]);
                    year = Integer.parseInt(tmpend[2]);
                    plans.setdate(year, month, day, flag);


                    //get frequency
                    freq = br.readLine();
                    plans.setfreq(freq);

                    //get avaliable floors
                    afloor = br.readLine();
                    String[] tmpfloor = afloor.split(",");
                    int len = tmpfloor.length;
                    String[] str1 = new String[len];
                    System.arraycopy(tmpfloor, 0, str1, 0, tmpfloor.length);
                    plans.setfloors(str1);
                    planarray.add(plans);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Currently no plan has been recorded.");
        }

    }

    public static boolean checkduplicate(String n) {
        for (int i = 0; i < planarray.size(); i++) {
            if (planarray.get(i).name.equals(name)) {

                return true;
            }
        }
        return false;
    }

    //create plan
    public static void createPlan() {
        plans = null;
        boolean isSubmit = false;
        boolean isBack = false;
        gui_cplan cp = new gui_cplan();
        cp.setSize(1000, 613);
        cp.setVisible(true);
        while (isSubmit == false && isBack == false) {
            isSubmit = cp.isSubmit();
            isBack = cp.isBack();
            if (isBack) {
                cp.setVisible(false);
            }
            if (isSubmit) {
                name = cp.getN();
                if (cp.getN().equals("")) {
                    isSubmit = false;
                } else {
                    duplicate = checkduplicate(name);
                    if (duplicate) {
                        cp.setWarning();
                        isSubmit = false;
                    }
                }
            }
        }
        plans = new Plan(name);
        liftnum = cp.getLN();
        String[] tmpdef = liftnum.split("/");
        int le = tmpdef.length;
        String[] str2 = new String[le];
        System.arraycopy(tmpdef, 0, str2, 0, tmpdef.length);
        plans.setliftnum(str2);
        defloor = cp.getDF();
        plans.setdef(defloor);

        boolean flag = true;
        date = cp.getSD();
        String[] tmpstart = date.split("/");
        day = Integer.parseInt(tmpstart[0]);
        month = Integer.parseInt(tmpstart[1]);
        year = Integer.parseInt(tmpstart[2]);
        plans.setdate(year, month, day, flag);


        flag = false;
        date = cp.getED();
        String[] tmpend = date.split("/");
        day = Integer.parseInt(tmpend[0]);
        month = Integer.parseInt(tmpend[1]);
        year = Integer.parseInt(tmpend[2]);
        plans.setdate(year, month, day, flag);


        flag = true;
        time = cp.getST();
        String[] tmpstime = time.split(":");
        hour = Integer.parseInt(tmpstime[0]);
        min = Integer.parseInt(tmpstime[1]);
        plans.setime(hour, min, flag);

        flag = false;
        time = cp.getET();
        String[] tmpetime = time.split(":");
        hour = Integer.parseInt(tmpetime[0]);
        min = Integer.parseInt(tmpetime[1]);
        plans.setime(hour, min, flag);
        freq = cp.getFR();
        plans.setfreq(freq);

        afloor = cp.getAF();
        String[] tmpfloor = afloor.split("/");
        int len = tmpfloor.length;
        String[] str1 = new String[len];
        System.arraycopy(tmpfloor, 0, str1, 0, tmpfloor.length);
        plans.setfloors(str1);
        planarray.add(plans);
        plans.writetofile();
        counter++;
        cp.setVisible(false);
    }

    public static void editPlan() {
        boolean isBack = false;
        gui_eplan ep = new gui_eplan();
        ep.setSize(1000, 613);
        ep.setVisible(true);
        plans = null;
        boolean find = false;
        boolean flag = true;
        int position = 0;
        String nametmp;
        boolean isSearch = false;
        while (isSearch == false && isBack == false) {
            isSearch = ep.isSearch();
            isBack = ep.isBack();
        }
        if (isBack == true) {
            ep.setVisible(false);
        }
        nametmp = ep.getN();
        for (int i = 0; i < planarray.size(); i++) {
            if (planarray.get(i).name.equals(nametmp)) {
                String LN = null;
                int lnLength = planarray.get(i).liftnum.length;
                for (int j = 0; j < lnLength; j++) {
                    if (LN == null) {
                        LN = planarray.get(i).liftnum[j];
                    } else {
                        LN += "/" + planarray.get(i).liftnum[j];
                    }
                }
                ep.setDF(planarray.get(i).defaultfloor);
                ep.setFR(planarray.get(i).freq);
                ep.setST(planarray.get(i).startime.h + ":" + planarray.get(i).startime.m);
                ep.setET(planarray.get(i).endtime.h + ":" + planarray.get(i).endtime.m);
                ep.setSD(planarray.get(i).startdate.d + "/" + planarray.get(i).startdate.m + "/" + planarray.get(i).startdate.y);
                ep.setED(planarray.get(i).endate.d + "/" + planarray.get(i).endate.m + "/" + planarray.get(i).endate.y);
                ep.setLN(LN);
                ep.setAF(afloor);
                find = true;
                position = i;
            }
        }

        if (!find) {
            ep.setNote();
            ep.setVisible(false);
            return;
        }

        if (find) {
            boolean isSubmit = false;
            while (isSubmit == false && isBack == false) {
                isSubmit = ep.isSubmit();
                isBack = ep.isBack();
                if (isBack == true) {
                    ep.setVisible(false);
                }
                if (isSubmit == true) {
                    do {
                        name = ep.getN();
                        if (ep.getN().equals("")) {
                            isSubmit = false;
                        } else {
                            if (planarray.get(position).name.equals(name)) {
                                duplicate = false;
                            } else {
                                duplicate = checkduplicate(name);
                                if (duplicate) {
                                    ep.setWarning();
                                    isSubmit = false;
                                }
                            }
                        }
                    } while (duplicate);
                    planarray.get(position).setname(name);
                    flag = true;
                    time = ep.getST();
                    String[] tmpstime = time.split(":");
                    hour = Integer.parseInt(tmpstime[0]);
                    min = Integer.parseInt(tmpstime[1]);
                    planarray.get(position).setime(hour, min, flag);
                    flag = false;
                    time = ep.getET();
                    String[] tmpetime = time.split(":");
                    hour = Integer.parseInt(tmpetime[0]);
                    min = Integer.parseInt(tmpetime[1]);
                    planarray.get(position).setime(hour, min, flag);
                    liftnum = ep.getLN();
                    String[] tmpdef = liftnum.split("/");
                    int le = tmpdef.length;
                    String[] str2 = new String[le];
                    System.arraycopy(tmpdef, 0, str2, 0, tmpdef.length);
                    planarray.get(position).setliftnum(str2);
                    defloor = ep.getDF();
                    planarray.get(position).setdef(defloor);
                    flag = true;
                    date = ep.getSD();
                    String[] tmpstart = date.split("/");
                    day = Integer.parseInt(tmpstart[0]);
                    month = Integer.parseInt(tmpstart[1]);
                    year = Integer.parseInt(tmpstart[2]);
                    planarray.get(position).setdate(year, month, day, flag);
                    flag = false;
                    date = ep.getED();
                    String[] tmpend = date.split("/");
                    day = Integer.parseInt(tmpend[0]);
                    month = Integer.parseInt(tmpend[1]);
                    year = Integer.parseInt(tmpend[2]);
                    planarray.get(position).setdate(year, month, day, flag);
                    freq = ep.getFR();
                    planarray.get(position).setfreq(freq);
                    afloor = ep.getAF();
                    String[] tmpfloor = afloor.split("/");
                    int len = tmpfloor.length;
                    String[] str1 = new String[len];
                    System.arraycopy(tmpfloor, 0, str1, 0, tmpfloor.length);
                    planarray.get(position).setfloors(str1);
                }
            }
            ep.setVisible(false);
        }
    }

    public static void printEditMenu() {

        System.out.print("Enter your edit choice:\n"
                + " 1 to edit name.\n"
                + " 2 to edit Start time.\n"
                + " 3 to edit End time.\n"
                + " 4 to Lift Number.\n"
                + " 5 to Default floor.\n"
                + " 6 to Start Date.\n"
                + " 7 to End Date.\n"
                + " 8 to Frequency.\n"
                + " 9 to Avaliable Floors.\n"
                + " 10 to quit without any edit.\n");
    }

    public static void deletePlan() {
        gui_dplan dp = new gui_dplan();
        dp.setSize(1000, 613);
        dp.setVisible(true);
        boolean find = false;
        String nametmp;
        boolean isSubmit = false;
        boolean isBack = false;
        while (isSubmit == false && isBack == false) {
            isSubmit = dp.isSubmit();
            isBack = dp.isBack();
            if (isBack) {
                dp.setVisible(false);
            }
            if (isSubmit) {
                nametmp = dp.getN();
                for (int i = 0; i < planarray.size(); i++) {
                    if (planarray.get(i).name.equals(nametmp)) {
                        String choose = null;
                        while (choose == null) {
                            dp.setNote();
                            choose = dp.getChoose();
                        }
                        if (choose.indexOf("y") >= 0 && choose.indexOf("n") < 0) {
                            planarray.remove(i);
                            counter -= 1;
                        } else {
                        }
                        find = true;
                        dp.setVisible(false);
                    }
                }
                if (!find) {
                    dp.setNote2();
                    dp.setVisible(false);
                }
            }
        }
    }

    public static void displayPlan() {
        if (planarray.size() == 0) {
            System.out.println("No record!");
            return;
        }

        for (int i = 0; i < planarray.size(); i++) {
            planarray.get(i).printplan();
        }
    }

    //update the file when the data has been changed
    public static void updatefile() {
        try {
            FileOutputStream fos = new FileOutputStream("planRecord.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter out = new BufferedWriter(osw);
            out.write("");
            if (planarray.size() == 0) {
                out.close();
            } else {
                for (int i = 0; i < planarray.size(); i++) {
                    planarray.get(i).writetofile();
                }
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public static void printmenu() {

        System.out.print("Enter your choice:\n"
                + " 1 to create plan.\n"
                + " 2 to edit plan.\n"
                + " 3 to delete plan.\n"
                + " 4 to display plan.\n"
                + " 5 to quit.\n");
    }
}
