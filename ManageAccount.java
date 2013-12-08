
package test_smart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class ManageAccount {

    public static String name;
    public static String passwd;
    public static String role;
    public static ArrayList<account_1> accountarray = new ArrayList<account_1>();
    public static account_1 aAccount = new account_1();

    public static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Account.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                aAccount = null;
                aAccount = new account_1();
                aAccount.setName(line);
                line = br.readLine();
                aAccount.setPasswd(line);
                line = br.readLine();
                aAccount.setRole(line);
                accountarray.add(aAccount);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAccount() {
        aAccount = null;
        boolean isBack = false;
        gui_caccount ca = new gui_caccount();
        ca.setSize(1000, 613);
        ca.setVisible(true);
        boolean isSubmit;
        isSubmit = false;
        while (isSubmit == false && isBack == false) {
            isSubmit = ca.isSubmit();
            isBack = ca.isBack();
            if (isBack==true) {
                ca.setVisible(false);
            }
            if (isSubmit == true) {
                name = ca.getN();
                passwd = ca.getPD();
                role = "manager";
                aAccount = new account_1();
                aAccount.setField(name, passwd, role);
                aAccount.writeFile();
                accountarray.add(aAccount);
            }
        }
    }

    public static void deleteAccount() {
        boolean find = false;
        String nametmp;
        gui_daccount da = new gui_daccount();
        da.setSize(1000, 613);
        da.setVisible(true);
        boolean isSubmit = false;
        boolean isBack = false;
        while (isSubmit == false && isBack == false) {
            isSubmit = da.isSubmit();
            isBack = da.isBack();
            if (isBack) {
                da.setVisible(false);
            }
            if (isSubmit) {
                nametmp = da.getN();
                for (int i = 0; i < accountarray.size(); i++) {
                    if (accountarray.get(i).name.equals(nametmp)) {
                        String choose = null;
                        while (choose == null) {
                            da.setNote();
                            choose = da.getChoose();
                        }
                        if (choose.indexOf("y") >= 0 && choose.indexOf("n") < 0) {
                            accountarray.remove(i);

                        } else {
                        }
                        find = true;
                        updateFile();
                        da.setVisible(false);
                    }
                }
                if (!find) {
                    da.setNote2();

                }
            }
        }
    }

    public static void updateFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Account.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter out = new BufferedWriter(osw);
            out.write("");
            if (accountarray.size() == 0) {
                out.close();
            } else {
                for (int i = 0; i < accountarray.size(); i++) {
                    accountarray.get(i).writeFile();
                }
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public static void printmenu() {

        System.out.print("Enter your choice:\n"
                + " 1 to create account.\n"
                + " 2 to delete account.\n"
                + " 3 to quit.\n");
    }
}
