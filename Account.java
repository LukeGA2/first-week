
package test_smart;

import static test_smart.ManageAccount.readFile;

public class Account {

    String username;
    String password;
    String status;

    public Account() {
        username = null;
        password = null;
        status = null;
    }

    public void setfields(String na, String p, String sta) {
        username = na;
        password = p;
        status = sta;
    }

    public void setname(String n) {
        username = n;
    }

    public void setpass(String pa) {
        password = pa;
    }

    public void setstatus(String st) {
        status = st;
    }

    public String getname() {
        return username;
    }

    public String getpword() {
        return password;
    }

    public String getstatus() {
        return status;
    }

    public void printuser() {
        if (status.equals("admin")) {
            int choice = 0;
            readFile();
            do {
                gui_weladmin wa = new gui_weladmin();
                wa.setSize(1000, 613);
                wa.setVisible(true);
                while (choice == 0) {
                    choice = wa.getChoice();
                }
                wa.setVisible(false);
                if (choice == 1) {
                    Setting.readfile();
                    Setting.addbuilding();
                    choice = 0;
                } else if (choice == 2) {
                    do {
                        gui_maccount ma = new gui_maccount();
                        ma.setSize(1000, 613);
                        ma.setVisible(true);
                        choice = 0;
                        while (choice == 0) {
                            choice = ma.getCbutton();
                        }
                        ma.setVisible(false);
                        if (choice == 1) {
                            ManageAccount.createAccount();
                            choice = 0;
                        } else if (choice == 2) {
                            ManageAccount.deleteAccount();
                            choice = 0;
                        } else if (choice == 3) {
                            printuser();
                        }
                    } while (choice != 3);
                } else if (choice == 3) {
                    Test_Smart a = new Test_Smart();
                    a.run();
                }else if(choice ==4){
                    SearchSetting.displaySetting();
                    choice=0;
                }else if(choice==5){
                     Manageplan.readfile();
                     choice=0;
            do {
                gui_welmanager wm = new gui_welmanager();
                wm.setSize(1000, 613);
                wm.setVisible(true);
                while (choice == 0) {
                    choice = wm.getChoice();
                }
                wm.setVisible(false);

                if (choice == 1) {
                    Manageplan.createPlan();
                    choice = 0;
                } else if (choice == 2) {
                    Manageplan.editPlan();
                    Manageplan.updatefile();
                    choice = 0;
                } else if (choice == 3) {
                    Manageplan.deletePlan();
                    Manageplan.updatefile();
                    choice = 0;
                } else if (choice == 4) {
                     SearchSetting.displaySetting();
                    choice = 0;
                } else if (choice == 5) {
                    Test_Smart a = new Test_Smart();
                    a.run();
                }
            } while (choice != 5);
                }
            } while (choice != 3);
        } else if (status.equals("manager")) {
            int choice = 0;
            Manageplan.readfile();
            do {
                gui_welmanager wm = new gui_welmanager();
                wm.setSize(1000, 613);
                wm.setVisible(true);
                while (choice == 0) {
                    choice = wm.getChoice();
                }
                wm.setVisible(false);

                if (choice == 1) {
                    Manageplan.createPlan();
                    choice = 0;
                } else if (choice == 2) {
                    Manageplan.editPlan();
                    Manageplan.updatefile();
                    choice = 0;
                } else if (choice == 3) {
                    Manageplan.deletePlan();
                    Manageplan.updatefile();
                    choice = 0;
                } else if (choice == 4) {
                     SearchSetting.displaySetting();
                    choice = 0;
                } else if (choice == 5) {
                    Test_Smart a = new Test_Smart();
                    a.run();
                }
            } while (choice != 5);
        }
    }
}
