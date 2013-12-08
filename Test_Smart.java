
package test_smart;

import static test_smart.Login.userarray;

public class Test_Smart {

    public static void run() {
        gui_login lo = new gui_login();
        lo.setSize(1000, 613);
        lo.setVisible(true);
        Login.readfile();
        int s = 999;
        boolean isSim = false;
        boolean isAbout = false;
        while ((s == 999 || s == 9) && isSim == false && isAbout == false) {
            isSim = lo.isSim();
            s = lo.getCheck();
            isAbout = lo.isAbout();
            if (isSim == true) {
                isSim = lo.setSim();
                lo.setVisible(false);
                SearchSetting.displaySetting();
            }
            if (isAbout == true) {
                lo.setAbout();
                lo.setVisible(false);
                About.goBack();
                
            }


        }
        lo.setVisible(false);
        userarray.get(s).printuser();
    }

    public static void main(String[] args) {
        run();
    }
}
