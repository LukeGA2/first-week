/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_smart;

/**
 *
 * @author Administrator
 */
public class About {

    public static void goBack() {
        gui_about ab = new gui_about();
        ab.setSize(1000, 613);
        ab.setVisible( true);
        boolean isBack = false;
        while (isBack == false) {
            isBack = ab.isBack();
            if(isBack==true){
                 ab.setVisible(false);
                 Test_Smart.run();
            }
        }
    }
}
