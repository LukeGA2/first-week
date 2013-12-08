package test_smart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.io.*;
import java.lang.System.*;
import java.text.SimpleDateFormat;

class Nfloornlift {

    gui_simulationlift si = new gui_simulationlift();
    gui_AllFloorsW af = new gui_AllFloorsW();
    gui_displan dp = new gui_displan();
    private boolean isPlan = false;
    private boolean isMore = false;
    private String contens = "";
    private int liftID = 1;
    private int[] L_cFLoor;
    private static int l_totalnumber;
    private static int total_floor;
    private int d_pnumber_in_lift;
    private int[] c_pnumber_in_lift;
    private int[] tem_c_pnumber_in_lift;
    private int L_weight;
    private int P_weight;
    private int L_mWeight;
    private int L_cWeight;//lift current weight
    private int lift_number;
    private int count_running;
    private int[] l_counter;
    private int[] up_target;
    private int[] down_target;
    private int[] planlift_d_floor;
    private int[][] UpButton;
    private int[][] DownButton;
    private int[][] L_destination;
    private int[][] stand_by_person;
    private int[][] copyTo;
    private int[][] up_or_down_person;
    private int[][] floor_want_to_go_number;
    private int[][] tem_floor_want_to_go_number;
    private int[][] available_floor;//plan lift
    private int enter_lift_person;
    private char[] L_direction;
    private boolean[] L_door;
    private boolean[] planLift;
    private int stand_by_floor;
    private int nn;
    private ArrayList<Plan> planarray = new ArrayList<Plan>();
    private Plan plans = new Plan("tmp");//suppose to add into ArrayList
    private ArrayList<ArrayList<String>> list;
    private ArrayList<String> listSub;
    private int counter = 0;
    private String name;
    private int year, month, day, hour, min;
    private String liftnum;
    private String defloor;
    private String date, time;
    private String freq;
    private String afloor;

    public static void setFL(String f, String l) {
        l_totalnumber = Integer.parseInt(l);
        total_floor = Integer.parseInt(f);
    }

    public void initial() {
        si.setSize(1000, 613);
        si.displayButton();
        if (l_totalnumber == 1) {
            si.showL1();
        }
        if (l_totalnumber == 2) {
            si.showL1();
            si.showL2();
        }
        if (l_totalnumber == 3) {
            si.showL1();
            si.showL2();
            si.showL3();
        }
        if (l_totalnumber == 4) {
            si.showL1();
            si.showL2();
            si.showL3();
            si.showL4();
        }
        if (l_totalnumber == 5) {
            si.showL1();
            si.showL2();
            si.showL3();
            si.showL4();
            si.showL5();
        }
        si.setVisible(true);
        list = new ArrayList<ArrayList<String>>();
        listSub = new ArrayList<String>();
        lift_number = -1;

        planlift_d_floor = new int[l_totalnumber];
        L_cFLoor = new int[l_totalnumber];
        up_target = new int[l_totalnumber];
        down_target = new int[l_totalnumber];
        stand_by_floor = 0;
        enter_lift_person = 0;
        L_weight = 100;
        P_weight = 1;//100kg
        L_mWeight = 3;//300kg
        L_cWeight = 0;//0kg
        l_counter = new int[l_totalnumber];

        UpButton = new int[l_totalnumber][total_floor];
        DownButton = new int[l_totalnumber][total_floor];
        L_destination = new int[l_totalnumber][total_floor];
        L_direction = new char[l_totalnumber];
        L_door = new boolean[l_totalnumber];
        planLift = new boolean[l_totalnumber];
        stand_by_person = new int[2][total_floor];
        copyTo = new int[2][total_floor];
        floor_want_to_go_number = new int[l_totalnumber][total_floor];
        tem_floor_want_to_go_number = new int[l_totalnumber][total_floor];
        up_or_down_person = new int[2][total_floor];
        d_pnumber_in_lift = 10;
        c_pnumber_in_lift = new int[l_totalnumber];
        tem_c_pnumber_in_lift = new int[l_totalnumber];
        available_floor = new int[l_totalnumber][total_floor];

        count_running = 0;
        for (int i = 1; i <= l_totalnumber; i++) {
            L_cFLoor[i - 1] = 1;
            up_target[i - 1] = 0;
            down_target[i - 1] = 0;
            L_direction[i - 1] = 'S';
            planlift_d_floor[i - 1] = 0;
            L_door[i - 1] = false;
            planLift[i - 1] = false;
            c_pnumber_in_lift[i - 1] = 0;
            tem_c_pnumber_in_lift[i - 1] = 0;

            for (int p = 0; p < total_floor; p++) {
                UpButton[i - 1][p] = 0;
                DownButton[i - 1][p] = 0;
                L_destination[i - 1][p] = 0;
                stand_by_person[0][p] = 0;//up person
                stand_by_person[1][p] = 0;//down person
                copyTo[0][p] = 0;
                copyTo[1][p] = 0;
                floor_want_to_go_number[i - 1][p] = 0;//
                tem_floor_want_to_go_number[i - 1][p] = 0;
                available_floor[i - 1][p] = 0;
                up_or_down_person[0][p] = 0;
                up_or_down_person[1][p] = 0;
            }
        }
    }

    public void random_stand_by_person() {
        for (int i = 0; i < total_floor; i++) {
            int randomNum = 0;
            randomNum = new Random().nextInt(15) + 1;
            if (i == 0) {
                stand_by_person[0][i] = randomNum;
                stand_by_person[1][i] = 0;
                contens = contens + stand_by_person[0][i] + " " + stand_by_person[1][i] + "\n";
            } else if (i == total_floor - 1) {
                stand_by_person[0][i] = 0;
                stand_by_person[1][i] = randomNum;
                contens = contens + stand_by_person[0][i] + " " + stand_by_person[1][i] + "\n";
            } else {
                int ran = 0;
                ran = new Random().nextInt(randomNum) + 1;
                stand_by_person[0][i] = ran;
                stand_by_person[1][i] = randomNum - ran;
                contens = contens + stand_by_person[0][i] + " " + stand_by_person[1][i] + "\n";
            }
            if (i == 0) {
                si.setWP(i + 1);
                si.setwPeople(stand_by_person[0][i]);
                si.setwdPeople(stand_by_person[1][i]);
            }
            if (i == 1) {
                si.setWP1(i + 1);
                si.setwPeople1(stand_by_person[0][i]);
                si.setwdPeople1(stand_by_person[1][i]);
            }
            if (i == 2) {
                si.setWP2(i + 1);
                si.setwPeople2(stand_by_person[0][i]);
                si.setwdPeople2(stand_by_person[1][i]);
            }
            if (i == 3) {
                si.setWP3(i + 1);
                si.setwPeople3(stand_by_person[0][i]);
                si.setwdPeople3(stand_by_person[1][i]);
            }
            if (i == 4) {
                si.setWP4(i + 1);
                si.setwPeople4(stand_by_person[0][i]);
                si.setwdPeople4(stand_by_person[1][i]);
            }
            if (i == 0) {
                af.setWP(i + 1);
                af.setwPeople(stand_by_person[0][i]);
                af.setwdPeople(stand_by_person[1][i]);
            }
            if (i == 1) {
                af.setWP1(i + 1);
                af.setwPeople1(stand_by_person[0][i]);
                af.setwdPeople1(stand_by_person[1][i]);
            }
            if (i == 2) {
                af.setWP2(i + 1);
                af.setwPeople2(stand_by_person[0][i]);
                af.setwdPeople2(stand_by_person[1][i]);
            }
            if (i == 3) {
                af.setWP3(i + 1);
                af.setwPeople3(stand_by_person[0][i]);
                af.setwdPeople3(stand_by_person[1][i]);
            }
            if (i == 4) {
                af.setWP4(i + 1);
                af.setwPeople4(stand_by_person[0][i]);
                af.setwdPeople4(stand_by_person[1][i]);
            }
            if (i == 5) {
                af.setWP5(i + 1);
                af.setwPeople5(stand_by_person[0][i]);
                af.setwdPeople5(stand_by_person[1][i]);
            }
            if (i == 6) {
                af.setWP6(i + 1);
                af.setwPeople6(stand_by_person[0][i]);
                af.setwdPeople6(stand_by_person[1][i]);
            }
            if (i == 7) {
                af.setWP7(i + 1);
                af.setwPeople7(stand_by_person[0][i]);
                af.setwdPeople7(stand_by_person[1][i]);
            }
            if (i == 8) {
                af.setWP8(i + 1);
                af.setwPeople8(stand_by_person[0][i]);
                af.setwdPeople8(stand_by_person[1][i]);
            }
            if (i == 9) {
                af.setWP9(i + 1);
                af.setwPeople9(stand_by_person[0][i]);
                af.setwdPeople9(stand_by_person[1][i]);
            }
            if (i == 10) {
                af.setWP10(i + 1);
                af.setwPeople10(stand_by_person[0][i]);
                af.setwdPeople10(stand_by_person[1][i]);
            }
            if (i == 11) {
                af.setWP11(i + 1);
                af.setwPeople11(stand_by_person[0][i]);
                af.setwdPeople11(stand_by_person[1][i]);
            }
            if (i == 12) {
                af.setWP12(i + 1);
                af.setwPeople12(stand_by_person[0][i]);
                af.setwdPeople12(stand_by_person[1][i]);
            }
            if (i == 13) {
                af.setWP13(i + 1);
                af.setwPeople13(stand_by_person[0][i]);
                af.setwdPeople13(stand_by_person[1][i]);
            }
            if (i == 14) {
                af.setWP14(i + 1);
                af.setwPeople14(stand_by_person[0][i]);
                af.setwdPeople14(stand_by_person[1][i]);
            }
            if (i == 15) {
                af.setWP15(i + 1);
                af.setwPeople15(stand_by_person[0][i]);
                af.setwdPeople15(stand_by_person[1][i]);
            }
            if (i == 16) {
                af.setWP16(i + 1);
                af.setwPeople16(stand_by_person[0][i]);
                af.setwdPeople16(stand_by_person[1][i]);
            }
            if (i == 17) {
                af.setWP17(i + 1);
                af.setwPeople17(stand_by_person[0][i]);
                af.setwdPeople17(stand_by_person[1][i]);
            }
            if (i == 18) {
                af.setWP18(i + 1);
                af.setwPeople18(stand_by_person[0][i]);
                af.setwdPeople18(stand_by_person[1][i]);
            }
            if (i == 19) {
                af.setWP19(i + 1);
                af.setwPeople19(stand_by_person[0][i]);
                af.setwdPeople19(stand_by_person[1][i]);
            }
            if (i == 20) {
                af.setWP20(i + 1);
                af.setwPeople20(stand_by_person[0][i]);
                af.setwdPeople20(stand_by_person[1][i]);
            }
            if (i == 21) {
                af.setWP21(i + 1);
                af.setwPeople21(stand_by_person[0][i]);
                af.setwdPeople21(stand_by_person[1][i]);
            }
            if (i == 22) {
                af.setWP22(i + 1);
                af.setwPeople22(stand_by_person[0][i]);
                af.setwdPeople22(stand_by_person[1][i]);
            }
            if (i == 23) {
                af.setWP23(i + 1);
                af.setwPeople23(stand_by_person[0][i]);
                af.setwdPeople23(stand_by_person[1][i]);
            }
        }
    }

    public void running() {
        boolean isRestart = false;
        isRestart = si.isRe();
        if (isRestart == true) {
            si.setVisible(false);
            af.setVisible(false);
            SearchSetting.displaySetting();
        }
        isPlan = si.isPlan();
        if (isPlan == true) {
            dp.setSize(400, 250);
            dp.setVisible(true);
            si.setIsPlan();;
        }
        isMore = si.isMore();
        if (isMore == true) {
            af.setSize(360, 613);
            af.setVisible(true);
            si.setIsMore();
        }
        int high_ava_floor = 0;
        int lowest_ava_floor = 0;
        boolean has_s = false;
        for (int i = 0; i < l_totalnumber; i++) {
            if (L_direction[i] == 'U') {
                L_cFLoor[i]++;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↑");
                }

                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↑");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↑");
                }

                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↑");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↑");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Up " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'D') {
                L_cFLoor[i]--;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↓");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↓");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↓");
                }

                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↓");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↓");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Down " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'A')//stop-up
            {
                l_counter[i]++;
                if (l_counter[i] == 2) {
                    l_counter[i] = 0;
                    L_door[i] = false;
                    L_direction[i] = 'H';
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.closeLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir1("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir2("-");
                    }

                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir3("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir4("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Stop " + c_pnumber_in_lift[i] + "\n";
                } else {
                    L_door[i] = true;
                    person_calculate(i);
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.openLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir1("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir2("-");
                    }
                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir3("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir4("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Open door " + c_pnumber_in_lift[i] + "\n";
                }
            } else if (L_direction[i] == 'B')//stop-down
            {
                l_counter[i]++;
                if (l_counter[i] == 2) {
                    l_counter[i] = 0;
                    L_door[i] = false;
                    L_direction[i] = 'K';
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.closeLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir1("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir2("-");
                    }
                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir3("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir4("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Stop " + c_pnumber_in_lift[i] + "\n";
                } else {
                    L_door[i] = true;
                    person_calculate(i);
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.openLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }

                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Open door " + c_pnumber_in_lift[i] + "\n";
                }

            } else if (L_direction[i] == 'S') {
                has_s = true;
                planLift[i] = false;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                    si.closeLiftD();
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("-");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("-");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("-");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("-");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("-");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Stop " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'C')//stop-open
            {
                l_counter[i]++;
                if (l_counter[i] == 2) {
                    l_counter[i] = 0;
                    L_door[i] = false;
                    L_direction[i] = 'S';
                    planLift[i] = false;
                    has_s = true;
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.closeLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir1("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir2("-");
                    }
                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir3("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir4("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Stop " + c_pnumber_in_lift[i] + "\n";
                } else {
                    L_door[i] = true;
                    person_calculate(i);
                    if (i + 1 == liftID) {
                        si.setCfloor(L_cFLoor[i]);
                        si.setpinLift(c_pnumber_in_lift[i]);
                        si.setLiftID(liftID);
                        si.closeLiftD();
                    }
                    if (i + 1 == 1) {
                        si.setLift(i + 1);
                        si.setcLift(L_cFLoor[i]);
                        si.setPinLift(c_pnumber_in_lift[i]);
                        si.setDir("-");
                    }
                    if (i + 1 == 2) {
                        si.setLift1(i + 1);
                        si.setcLift1(L_cFLoor[i]);
                        si.setPinLift1(c_pnumber_in_lift[i]);
                        si.setDir1("-");
                    }
                    if (i + 1 == 3) {
                        si.setLift2(i + 1);
                        si.setcLift2(L_cFLoor[i]);
                        si.setPinLift2(c_pnumber_in_lift[i]);
                        si.setDir2("-");
                    }
                    if (i + 1 == 4) {
                        si.setLift3(i + 1);
                        si.setcLift3(L_cFLoor[i]);
                        si.setPinLift3(c_pnumber_in_lift[i]);
                        si.setDir3("-");
                    }
                    if (i + 1 == 5) {
                        si.setLift4(i + 1);
                        si.setcLift4(L_cFLoor[i]);
                        si.setPinLift4(c_pnumber_in_lift[i]);
                        si.setDir4("-");
                    }
                    contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Open door " + c_pnumber_in_lift[i] + "\n";
                }

            } else if (L_direction[i] == 'X')//direction=stop,move up
            {
                L_cFLoor[i]++;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↑");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↑");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↑");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↑");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↑");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Up " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'Y')//direction=stop,move down
            {
                L_cFLoor[i]--;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↓");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↓");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↓");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↓");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↓");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Down " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'V') {
                L_cFLoor[i]++;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↑");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↑");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↑");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↑");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↑");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Up " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'W') {
                L_cFLoor[i]--;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↓");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↓");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↓");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↓");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↓");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Down " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'E') {
                L_cFLoor[i]++;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↑");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↑");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↑");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↑");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↑");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Up " + c_pnumber_in_lift[i] + "\n";
            } else if (L_direction[i] == 'F') {
                L_cFLoor[i]--;
                if (i + 1 == liftID) {
                    si.setCfloor(L_cFLoor[i]);
                    si.setpinLift(c_pnumber_in_lift[i]);
                    si.setLiftID(liftID);
                }
                if (i + 1 == 1) {
                    si.setLift(i + 1);
                    si.setcLift(L_cFLoor[i]);
                    si.setPinLift(c_pnumber_in_lift[i]);
                    si.setDir("↓");
                }
                if (i + 1 == 2) {
                    si.setLift1(i + 1);
                    si.setcLift1(L_cFLoor[i]);
                    si.setPinLift1(c_pnumber_in_lift[i]);
                    si.setDir1("↓");
                }
                if (i + 1 == 3) {
                    si.setLift2(i + 1);
                    si.setcLift2(L_cFLoor[i]);
                    si.setPinLift2(c_pnumber_in_lift[i]);
                    si.setDir2("↓");
                }
                if (i + 1 == 4) {
                    si.setLift3(i + 1);
                    si.setcLift3(L_cFLoor[i]);
                    si.setPinLift3(c_pnumber_in_lift[i]);
                    si.setDir3("↓");
                }
                if (i + 1 == 5) {
                    si.setLift4(i + 1);
                    si.setcLift4(L_cFLoor[i]);
                    si.setPinLift4(c_pnumber_in_lift[i]);
                    si.setDir4("↓");
                }
                contens = contens + "lift " + (i + 1) + " is at " + L_cFLoor[i] + " " + "Down " + c_pnumber_in_lift[i] + "\n";
            }
        }
        liftID = si.getLiftID();
        si.setCfloor(L_cFLoor[liftID - 1]);
        si.setpinLift(c_pnumber_in_lift[liftID - 1]);
        si.setLiftID(liftID);
        for (int i = 0;
                i < total_floor;
                i++) {
            if (i == 0) {
                si.setwPeople(stand_by_person[0][i]);
                si.setwdPeople(stand_by_person[1][i]);
            }
            if (i == 1) {
                si.setwPeople1(stand_by_person[0][i]);
                si.setwdPeople1(stand_by_person[1][i]);
            }
            if (i == 2) {
                si.setwPeople2(stand_by_person[0][i]);
                si.setwdPeople2(stand_by_person[1][i]);
            }
            if (i == 3) {
                si.setwPeople3(stand_by_person[0][i]);
                si.setwdPeople3(stand_by_person[1][i]);
            }
            if (i == 4) {
                si.setwPeople4(stand_by_person[0][i]);
                si.setwdPeople4(stand_by_person[1][i]);
            }
            if (i == 0) {
                af.setwPeople(stand_by_person[0][i]);
                af.setwdPeople(stand_by_person[1][i]);
            }
            if (i == 1) {
                af.setwPeople1(stand_by_person[0][i]);
                af.setwdPeople1(stand_by_person[1][i]);
            }
            if (i == 2) {
                af.setwPeople2(stand_by_person[0][i]);
                af.setwdPeople2(stand_by_person[1][i]);
            }
            if (i == 3) {
                af.setwPeople3(stand_by_person[0][i]);
                af.setwdPeople3(stand_by_person[1][i]);
            }
            if (i == 4) {
                af.setwPeople4(stand_by_person[0][i]);
                af.setwdPeople4(stand_by_person[1][i]);
            }
            if (i == 5) {
                af.setwPeople5(stand_by_person[0][i]);
                af.setwdPeople5(stand_by_person[1][i]);
            }
            if (i == 6) {
                af.setwPeople6(stand_by_person[0][i]);
                af.setwdPeople6(stand_by_person[1][i]);
            }
            if (i == 7) {
                af.setwPeople7(stand_by_person[0][i]);
                af.setwdPeople7(stand_by_person[1][i]);
            }
            if (i == 8) {
                af.setwPeople8(stand_by_person[0][i]);
                af.setwdPeople8(stand_by_person[1][i]);
            }
            if (i == 9) {
                af.setwPeople9(stand_by_person[0][i]);
                af.setwdPeople9(stand_by_person[1][i]);
            }
            if (i == 10) {
                af.setwPeople10(stand_by_person[0][i]);
                af.setwdPeople10(stand_by_person[1][i]);
            }
            if (i == 11) {
                af.setwPeople11(stand_by_person[0][i]);
                af.setwdPeople11(stand_by_person[1][i]);
            }
            if (i == 12) {
                af.setwPeople12(stand_by_person[0][i]);
                af.setwdPeople12(stand_by_person[1][i]);
            }
            if (i == 13) {
                af.setwPeople13(stand_by_person[0][i]);
                af.setwdPeople13(stand_by_person[1][i]);
            }
            if (i == 14) {
                af.setwPeople14(stand_by_person[0][i]);
                af.setwdPeople14(stand_by_person[1][i]);
            }
            if (i == 15) {
                af.setwPeople15(stand_by_person[0][i]);
                af.setwdPeople15(stand_by_person[1][i]);
            }
            if (i == 16) {
                af.setwPeople16(stand_by_person[0][i]);
                af.setwdPeople16(stand_by_person[1][i]);
            }
            if (i == 17) {
                af.setwPeople17(stand_by_person[0][i]);
                af.setwdPeople17(stand_by_person[1][i]);
            }
            if (i == 18) {
                af.setwPeople18(stand_by_person[0][i]);
                af.setwdPeople18(stand_by_person[1][i]);
            }
            if (i == 19) {
                af.setwPeople19(stand_by_person[0][i]);
                af.setwdPeople19(stand_by_person[1][i]);
            }
            if (i == 20) {
                af.setwPeople20(stand_by_person[0][i]);
                af.setwdPeople20(stand_by_person[1][i]);
            }
            if (i == 21) {
                af.setwPeople21(stand_by_person[0][i]);
                af.setwdPeople21(stand_by_person[1][i]);
            }
            if (i == 22) {
                af.setwPeople22(stand_by_person[0][i]);
                af.setwdPeople22(stand_by_person[1][i]);
            }
            if (i == 23) {
                af.setwPeople23(stand_by_person[0][i]);
                af.setwdPeople23(stand_by_person[1][i]);
            }
        }

        tem_copy();
        boolean has_f = false;
        boolean has_f2 = false;
        for (int i = 0;
                i < l_totalnumber;
                i++) {
            if (L_direction[i] == 'H') {
                L_direction[i] = 'U';
            } else if (L_direction[i] == 'K') {
                if (L_cFLoor[i] == 1) {
                }
                L_direction[i] = 'D';
            } else if (L_direction[i] == 'U') {
                if (planLift[i] == true) {
                    for (int f = total_floor - 1; f >= 0; f--) {

                        if (available_floor[i][f] == 1) {
                            high_ava_floor = f + 1;
                            break;
                        }
                    }
                    for (int e = 0; e < total_floor; e++) {

                        if (L_cFLoor[i] == e + 1 && available_floor[i][e] == 1) {
                            if (L_cFLoor[i] == high_ava_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'S';//stop_open
                            } else if (L_cFLoor[i] == high_ava_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'C';
                            } else if (L_cFLoor[i] == high_ava_floor && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'C';
                            } else if (L_cFLoor[i] != high_ava_floor) {
                                for (int q = L_cFLoor[i]; q <= total_floor - 1; q++) {
                                    if (tem_floor_want_to_go_number[i][q] != 0) {
                                        has_f = true;
                                    }
                                }
                                if (has_f == true) {
                                    has_f = false;
                                    if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'A';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'A';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'A';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                        L_direction[i] = 'U';
                                    }

                                } else {
                                    if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {

                                        tem_person_calculate(i);
                                        L_direction[i] = 'A';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {

                                        tem_person_calculate(i);
                                        L_direction[i] = 'C';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {

                                        tem_person_calculate(i);
                                        L_direction[i] = 'A';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {

                                        L_direction[i] = 'S';
                                    }

                                }

                            }
                        }
                    }
                } else {
                    if (L_cFLoor[i] == total_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0) {
                        L_direction[i] = 'S';//stop_open
                    } else if (L_cFLoor[i] == total_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0) {
                        tem_person_calculate(i);
                        L_direction[i] = 'C';
                    } else if (L_cFLoor[i] != total_floor) {
                        for (int q = L_cFLoor[i]; q <= total_floor - 1; q++) {
                            if (tem_floor_want_to_go_number[i][q] != 0) {
                                has_f = true;
                            }
                        }
                        if (has_f == true) {
                            has_f = false;
                            if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'A';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'A';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'A';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'U';
                            }
                        } else {
                            if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'A';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'C';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'A';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[0][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'S';
                            }
                        }
                    }
                }
            } else if (L_direction[i] == 'D') {
                if (planLift[i] == true) {
                    for (int f = 0; f < total_floor; f++) {
                        if (available_floor[i][f] == 1) {
                            lowest_ava_floor = f + 1;
                            break;
                        }
                    }
                    for (int e = 0; e < total_floor; e++) {
                        if (L_cFLoor[i] == e + 1 && available_floor[i][e] == 1) {
                            if (L_cFLoor[i] == lowest_ava_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'S';//stop_open
                            } else if (L_cFLoor[i] == lowest_ava_floor && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'C';
                            } else if (L_cFLoor[i] != lowest_ava_floor) {
                                for (int q = L_cFLoor[i] - 1; q > 0; q--) {
                                    if (tem_floor_want_to_go_number[i][q - 1] != 0) {
                                        has_f = true;
                                    }
                                }
                                if (has_f == true) {
                                    has_f = false;
                                    if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'B';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'B';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'B';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                        L_direction[i] = 'D';
                                    }
                                } else {
                                    if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'B';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'C';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                        tem_person_calculate(i);
                                        L_direction[i] = 'B';
                                    } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                        L_direction[i] = 'S';
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (L_cFLoor[i] == 1 && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0) {
                        L_direction[i] = 'S';//stop_open
                    } else if (L_cFLoor[i] == 1 && tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0) {
                        tem_person_calculate(i);
                        L_direction[i] = 'C';
                    } else if (L_cFLoor[i] != 1) {
                        for (int q = L_cFLoor[i] - 1; q > 0; q--) {
                            if (tem_floor_want_to_go_number[i][q - 1] != 0) {
                                has_f = true;
                            }
                        }
                        if (has_f == true) {
                            has_f = false;
                            if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'B';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'B';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'B';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'D';
                            }

                        } else {
                            if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'B';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] != 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'C';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] != 0) {
                                tem_person_calculate(i);
                                L_direction[i] = 'B';
                            } else if (tem_floor_want_to_go_number[i][L_cFLoor[i] - 1] == 0 && copyTo[1][L_cFLoor[i] - 1] == 0) {
                                L_direction[i] = 'S';
                            }
                        }
                    }
                }

            } else if (L_direction[i] == 'X') {
                if (L_cFLoor[i] == up_target[i] && up_target[i] != 0 && copyTo[0][up_target[i] - 1] != 0) {
                    L_direction[i] = 'A';
                } else if (copyTo[1][up_target[i] - 1] == 0 && up_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            } else if (L_direction[i] == 'Y') {
                if (L_cFLoor[i] == down_target[i] && down_target[i] != 0 && copyTo[1][down_target[i] - 1] != 0) {
                    L_direction[i] = 'B';
                } else if (copyTo[1][down_target[i] - 1] == 0 && down_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            } else if (L_direction[i] == 'W') {
                if (L_cFLoor[i] == up_target[i] && up_target[i] != 0 && copyTo[0][up_target[i] - 1] != 0) {
                    L_direction[i] = 'A';
                } else if (copyTo[1][up_target[i] - 1] == 0 && up_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            } else if (L_direction[i] == 'V') {
                if (L_cFLoor[i] == down_target[i] && down_target[i] != 0 && copyTo[1][down_target[i] - 1] != 0) {
                    L_direction[i] = 'B';
                } else if (copyTo[1][down_target[i] - 1] == 0 && down_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            } else if (L_direction[i] == 'E') {
                if (L_cFLoor[i] == up_target[i] && up_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            } else if (L_direction[i] == 'F') {

                if (L_cFLoor[i] == down_target[i] && down_target[i] != 0) {
                    L_direction[i] = 'S';
                }
            }
        }
        if (has_s == true) {
            has_s = false;
            if (allocate_task() == 1) {
                return;
            }
        } else {
            java.util.TimerTask timerOpen = new java.util.TimerTask() {
                public void run() {
                    liftID = si.getLiftID();
                    running();
                }
            };
            java.util.Timer myTimer = new java.util.Timer();
            myTimer.schedule(timerOpen, 3 * 1000);
        }
    }

    public void tem_copy() {
        for (int i = 0; i < stand_by_person.length; i++) {
            int[] arrRow = new int[stand_by_person[i].length];
            for (int j = 0; j < stand_by_person[i].length; j++) {
                arrRow[j] = stand_by_person[i][j];
            }
            copyTo[i] = arrRow;

        }
        for (int i = 0; i < floor_want_to_go_number.length; i++) {
            int[] arrRow = new int[floor_want_to_go_number[i].length];
            for (int j = 0; j < floor_want_to_go_number[i].length; j++) {

                arrRow[j] = floor_want_to_go_number[i][j];
            }
            tem_floor_want_to_go_number[i] = arrRow;

        }
        for (int i = 0; i < floor_want_to_go_number.length; i++) {
            int[] arrRow = new int[floor_want_to_go_number[i].length];
            for (int j = 0; j < floor_want_to_go_number[i].length; j++) {

                arrRow[j] = floor_want_to_go_number[i][j];
            }
            tem_floor_want_to_go_number[i] = arrRow;

        }
        System.arraycopy(c_pnumber_in_lift, 0, tem_c_pnumber_in_lift, 0, c_pnumber_in_lift.length);
    }

    public void copy_stand_by_person() {
        for (int i = 0; i < stand_by_person.length; i++) {
            int[] arrRow = new int[stand_by_person[i].length];
            for (int j = 0; j < stand_by_person[i].length; j++) {
                arrRow[j] = stand_by_person[i][j];
            }
            copyTo[i] = arrRow;
        }
    }

    public int allocate_task() {
        int high_ava_floor = 0;
        int lowest_ava_floor = 0;
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        boolean plan_upsearch_break = false;
        boolean plan_downsearch_break = false;
        int tem_l = 0;
        char di = 'S';
        int tem_u = 0;
        int tem_d = 0;
        int u_result = 0;
        int d_result = 0;
        int tem_u_counter = 0;
        int tem_d_counter = total_floor - 1;
        if (check_all_stop() == 1) {
            return 1;
        }
        try {
            plan_check_person();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int p = 0; p < l_totalnumber; p++) {
            if (L_direction[p] == 'S') {
                up_target[p] = 0;
                down_target[p] = 0;
                tem_u = 0;
                tem_d = 0;
                if (planLift[p] == true) {
                    if (L_cFLoor[p] < planlift_d_floor[p] && planlift_d_floor[p] != 0) {
                        L_direction[p] = 'E';//UP UNTIL TARGET FLOOR THEN STOP
                        tem_u = planlift_d_floor[p];
                    } else if (L_cFLoor[p] > planlift_d_floor[p] && planlift_d_floor[p] != 0) {
                        L_direction[p] = 'F';//DOWN UNTIL TARGET FLOOR THEN STOP
                        tem_d = planlift_d_floor[p];
                    }
                }
                for (int i = tem_u_counter; i < total_floor; i++) {
                    if (planLift[p] == true) {
                        for (int f = 0; f < total_floor; f++) {
                            if (available_floor[p][f] == 1) {
                                high_ava_floor = f + 1;
                            }
                        }
                        for (int e = 0; e < total_floor; e++) {
                            if (available_floor[p][e] == 1 && e == i && e + 1 < high_ava_floor) {
                                if (copyTo[0][i] != 0) {
                                    tem_u = i + 1;
                                    if (tem_u_counter != total_floor - 1) {
                                        if (copyTo[0][i] <= d_pnumber_in_lift) {
                                            copyTo[0][i] = 0;
                                            tem_u_counter = i + 1;
                                        } else if (copyTo[0][i] > d_pnumber_in_lift) {
                                            copyTo[0][i] = copyTo[0][i] - d_pnumber_in_lift;
                                        }
                                    }
                                    plan_upsearch_break = true;
                                    break;
                                }
                            }
                        }
                        if (plan_upsearch_break == true) {
                            plan_upsearch_break = false;
                            break;
                        }
                    } else {
                        if (copyTo[0][i] != 0) {
                            tem_u = i + 1;
                            if (tem_u_counter != total_floor - 1) {
                                if (copyTo[0][i] <= d_pnumber_in_lift) {
                                    copyTo[0][i] = 0;
                                    tem_u_counter = i + 1;
                                } else if (copyTo[0][i] > d_pnumber_in_lift) {
                                    copyTo[0][i] = copyTo[0][i] - d_pnumber_in_lift;
                                }
                            }
                            break;
                        }
                    }
                }
                for (int i = tem_d_counter; i >= 0; i--) {
                    if (planLift[p] == true) {
                        for (int f = total_floor - 1; f >= 0; f--) {
                            if (available_floor[p][f] == 1) {
                                lowest_ava_floor = f + 1;
                            }
                        }
                        for (int e = 1; e < total_floor; e++) {
                            if (available_floor[p][e] == 1 && e == i && e + 1 > lowest_ava_floor) {
                                if (copyTo[1][i] != 0) {
                                    tem_d = i + 1;
                                    if (tem_d_counter != 0) {
                                        if (copyTo[1][i] <= d_pnumber_in_lift) {
                                            copyTo[1][i] = 0;
                                            tem_d_counter = i - 1;
                                        } else if (copyTo[1][i] > d_pnumber_in_lift) {
                                            copyTo[1][i] = copyTo[1][i] - d_pnumber_in_lift;
                                        }
                                    }
                                    plan_downsearch_break = true;
                                    break;
                                }
                            }
                        }
                        if (plan_downsearch_break == true) {
                            plan_downsearch_break = false;
                            break;
                        }
                    } else {
                        if (copyTo[1][i] != 0) {
                            tem_d = i + 1;
                            if (tem_d_counter != 0) {
                                if (copyTo[1][i] <= d_pnumber_in_lift) {
                                    copyTo[1][i] = 0;
                                    tem_d_counter = i - 1;
                                } else if (copyTo[1][i] > d_pnumber_in_lift) {
                                    copyTo[1][i] = copyTo[1][i] - d_pnumber_in_lift;
                                }
                            }
                            break;
                        }
                    }
                }

                if (tem_u > tem_d && tem_u != 0 && tem_d != 0) {
                    if (L_cFLoor[p] == tem_u) {
                        L_direction[p] = 'A';
                    } else if (L_cFLoor[p] == tem_d) {
                        L_direction[p] = 'B';
                    } else if (tem_d < L_cFLoor[p] && L_cFLoor[p] < tem_u) {
                        u_result = tem_u - L_cFLoor[p];
                        d_result = L_cFLoor[p] - tem_d;
                        if (u_result > d_result) {
                            L_direction[p] = 'Y';
                        } else if (u_result < d_result) {
                            L_direction[p] = 'X';
                        } else if (u_result == d_result) {
                            L_direction[p] = 'X';
                        }
                    } else if (tem_u < L_cFLoor[p]) {
                        L_direction[p] = 'W';
                    } else if (L_cFLoor[p] < tem_d) {
                        L_direction[p] = 'V';
                    }

                } else if (tem_u < tem_d && tem_u != 0 && tem_d != 0) {
                    if (L_cFLoor[p] == tem_u) {
                        L_direction[p] = 'A';
                    } else if (L_cFLoor[p] == tem_d) {
                        L_direction[p] = 'B';
                    } else if (tem_u < L_cFLoor[p] && L_cFLoor[p] < tem_d) {
                        u_result = L_cFLoor[p] - tem_u;
                        d_result = tem_d - L_cFLoor[p];
                        if (u_result > d_result) {
                            L_direction[p] = 'W';
                        } else if (u_result < d_result) {
                            L_direction[p] = 'V';
                        } else if (u_result == d_result) {
                            L_direction[p] = 'W';
                        }
                    } else if (L_cFLoor[p] < tem_u) {
                        L_direction[p] = 'X';
                    } else if (L_cFLoor[p] > tem_d) {
                        L_direction[p] = 'Y';
                    }
                } else if (tem_u == tem_d && tem_u != 0 && tem_d != 0) {
                    if (L_cFLoor[p] < tem_u) {
                        L_direction[p] = 'X';
                    } else if (L_cFLoor[p] > tem_u) {
                        L_direction[p] = 'W';
                    } else if (L_cFLoor[p] == tem_u) {
                        L_direction[p] = 'A';
                    }
                } else if (tem_u == 0 && tem_d != 0) {
                    if (L_cFLoor[p] < tem_d) {
                        L_direction[p] = 'V';
                    } else if (L_cFLoor[p] > tem_d) {
                        L_direction[p] = 'Y';
                    } else if (L_cFLoor[p] == tem_d) {
                        L_direction[p] = 'B';
                    }
                } else if (tem_u != 0 && tem_d == 0) {
                    if (L_cFLoor[p] < tem_u) {
                        L_direction[p] = 'X';
                    } else if (L_cFLoor[p] > tem_u) {
                        L_direction[p] = 'W';
                    } else if (L_cFLoor[p] == tem_u) {
                        L_direction[p] = 'A';
                    }
                } else if (tem_u == 0 && tem_d == 0) {
                    L_direction[p] = 'S';
                }
                up_target[p] = tem_u;
                down_target[p] = tem_d;
            }
        }

        java.util.TimerTask timerOpen = new java.util.TimerTask() {
            public void run() {
                liftID = si.getLiftID();
                running();
            }
        };
        java.util.Timer myTimer = new java.util.Timer();
        myTimer.schedule(timerOpen, 3 * 1000);
        return 0;

    }

    public int check_all_stop() {
        boolean noperson_one = true;
        boolean noperson_two = true;
        boolean no_lift_run = true;

        for (int i = 0; i < l_totalnumber; i++) {
            for (int p = 0; p < total_floor; p++) {
                if (floor_want_to_go_number[i][p] != 0) {
                    noperson_one = false;
                }
            }
        }
        for (int i = 0; i < total_floor; i++) {
            if (stand_by_person[0][i] != 0 || stand_by_person[1][i] != 0) {
                noperson_two = false;
            }
        }
        for (int i = 0; i < l_totalnumber; i++) {
            if (L_direction[i] != 'S') {
                no_lift_run = false;
            }
        }
        if (noperson_one == true && noperson_two == true && no_lift_run == true) {

            plan_control_readfile();
            makeOutput();
            boolean isBack = false;
            while (isBack == false) {
                isBack = si.isRe();
                if (isBack == true) {
                    si.setVisible(false);
                    af.setVisible(false);
                    SearchSetting.displaySetting();
                }
            }

            return 1;
        }
        return 0;
    }

    public void person_calculate(int li_num) {
        if (L_direction[li_num] == 'A') {
            if (floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                c_pnumber_in_lift[li_num] = c_pnumber_in_lift[li_num] - floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;

            }
            if (stand_by_person[0][L_cFLoor[li_num] - 1] <= (d_pnumber_in_lift - c_pnumber_in_lift[li_num])) {
                enter_lift_person = stand_by_person[0][L_cFLoor[li_num] - 1];
                c_pnumber_in_lift[li_num] += enter_lift_person;
                stand_by_person[0][L_cFLoor[li_num] - 1] = 0;
                randomfloornumber(li_num);

            } else {
                enter_lift_person = d_pnumber_in_lift - c_pnumber_in_lift[li_num];
                c_pnumber_in_lift[li_num] += enter_lift_person;
                stand_by_person[0][L_cFLoor[li_num] - 1] = stand_by_person[0][L_cFLoor[li_num] - 1] - enter_lift_person;
                randomfloornumber(li_num);

            }
        }
        if (L_direction[li_num] == 'B') {
            if (floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                c_pnumber_in_lift[li_num] = c_pnumber_in_lift[li_num] - floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;
            }

            if (stand_by_person[1][L_cFLoor[li_num] - 1] <= (d_pnumber_in_lift - c_pnumber_in_lift[li_num])) {
                enter_lift_person = stand_by_person[1][L_cFLoor[li_num] - 1];
                c_pnumber_in_lift[li_num] += enter_lift_person;
                stand_by_person[1][L_cFLoor[li_num] - 1] = 0;
                randomfloornumber(li_num);

            } else {
                enter_lift_person = d_pnumber_in_lift - c_pnumber_in_lift[li_num];
                c_pnumber_in_lift[li_num] += enter_lift_person;
                stand_by_person[1][L_cFLoor[li_num] - 1] = stand_by_person[1][L_cFLoor[li_num] - 1] - enter_lift_person;
                randomfloornumber(li_num);

            }
        }
        if (L_direction[li_num] == 'C') {
            if (floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                c_pnumber_in_lift[li_num] = c_pnumber_in_lift[li_num] - floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;

            }
        }
    }

    public void tem_person_calculate(int li_num) {
        if (L_direction[li_num] == 'U') {
            if (tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                tem_c_pnumber_in_lift[li_num] = tem_c_pnumber_in_lift[li_num] - tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;

            }
            if (copyTo[0][L_cFLoor[li_num] - 1] <= (d_pnumber_in_lift - tem_c_pnumber_in_lift[li_num])) {
                enter_lift_person = copyTo[0][L_cFLoor[li_num] - 1];
                tem_c_pnumber_in_lift[li_num] += enter_lift_person;
                copyTo[0][L_cFLoor[li_num] - 1] = 0;
            } else {
                enter_lift_person = d_pnumber_in_lift - tem_c_pnumber_in_lift[li_num];
                tem_c_pnumber_in_lift[li_num] += enter_lift_person;
                copyTo[0][L_cFLoor[li_num] - 1] = copyTo[0][L_cFLoor[li_num] - 1] - enter_lift_person;
            }
        }
        if (L_direction[li_num] == 'D') {
            if (tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                tem_c_pnumber_in_lift[li_num] = tem_c_pnumber_in_lift[li_num] - tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;
            }

            if (copyTo[1][L_cFLoor[li_num] - 1] <= (d_pnumber_in_lift - tem_c_pnumber_in_lift[li_num])) {
                enter_lift_person = copyTo[1][L_cFLoor[li_num] - 1];
                tem_c_pnumber_in_lift[li_num] += enter_lift_person;
                copyTo[1][L_cFLoor[li_num] - 1] = 0;

            } else {
                enter_lift_person = d_pnumber_in_lift - tem_c_pnumber_in_lift[li_num];
                tem_c_pnumber_in_lift[li_num] += enter_lift_person;
                copyTo[1][L_cFLoor[li_num] - 1] = copyTo[1][L_cFLoor[li_num] - 1] - enter_lift_person;
            }
        }
        if (L_direction[li_num] == 'C') {
            if (tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] != 0) {
                tem_c_pnumber_in_lift[li_num] = tem_c_pnumber_in_lift[li_num] - tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1];
                tem_floor_want_to_go_number[li_num][L_cFLoor[li_num] - 1] = 0;
            }
        }
    }

    public void randomfloornumber(int li_num) {
        int randomNum = 0;
        boolean ava_ran = false;
        for (int i = 1; i <= enter_lift_person; i++) {
            ava_ran = false;
            if (planLift[li_num] == true) {
                if (L_direction[li_num] == 'U' || L_direction[li_num] == 'A') {
                    while (ava_ran == false) {

                        randomNum = new Random().nextInt(total_floor - L_cFLoor[li_num]) + L_cFLoor[li_num] + 1;

                        for (int e = 0; e < total_floor; e++) {
                            if (randomNum == e + 1 && available_floor[li_num][e] == 1 && randomNum != L_cFLoor[li_num]) {

                                floor_want_to_go_number[li_num][randomNum - 1]++;
                                tem_floor_want_to_go_number[li_num][randomNum - 1]++;
                                ava_ran = true;
                            }

                        }
                    }
                } else if (L_direction[li_num] == 'D' || L_direction[li_num] == 'B') {
                    while (ava_ran == false) {
                        randomNum = new Random().nextInt(L_cFLoor[li_num]);
                        for (int e = 0; e < total_floor; e++) {
                            if (randomNum == e + 1 && available_floor[li_num][e] == 1 && randomNum != L_cFLoor[li_num]) {
                                if (randomNum == 0) {
                                    floor_want_to_go_number[li_num][randomNum]++;
                                    tem_floor_want_to_go_number[li_num][randomNum]++;
                                } else {
                                    floor_want_to_go_number[li_num][randomNum - 1]++;
                                    tem_floor_want_to_go_number[li_num][randomNum - 1]++;

                                }
                                ava_ran = true;
                            }
                        }
                    }

                }

            } else {
                if (L_direction[li_num] == 'U' || L_direction[li_num] == 'A') {
                    randomNum = new Random().nextInt(total_floor - L_cFLoor[li_num]) + L_cFLoor[li_num] + 1;
                    floor_want_to_go_number[li_num][randomNum - 1]++;
                    tem_floor_want_to_go_number[li_num][randomNum - 1]++;
                } else if (L_direction[li_num] == 'D' || L_direction[li_num] == 'B') {
                    randomNum = new Random().nextInt(L_cFLoor[li_num]);
                    if (randomNum == 0) {
                        floor_want_to_go_number[li_num][randomNum]++;
                        tem_floor_want_to_go_number[li_num][randomNum]++;
                    } else {
                        floor_want_to_go_number[li_num][randomNum - 1]++;
                        tem_floor_want_to_go_number[li_num][randomNum - 1]++;

                    }
                }
            }

        }


    }

    public void tem_randomfloornumber(int li_num) {
        int randomNum = 0;
        for (int i = 1; i <= enter_lift_person; i++) {

            if (L_direction[li_num] == 'U' || L_direction[li_num] == 'A') {
                randomNum = new Random().nextInt(total_floor - L_cFLoor[li_num]) + L_cFLoor[li_num] + 1;
                tem_floor_want_to_go_number[li_num][randomNum - 1]++;
            } else if (L_direction[li_num] == 'D' || L_direction[li_num] == 'B') {
                randomNum = new Random().nextInt(L_cFLoor[li_num]);
                if (randomNum == 0) {
                    tem_floor_want_to_go_number[li_num][randomNum]++;
                } else {
                    tem_floor_want_to_go_number[li_num][randomNum - 1]++;
                }
            }

        }


    }

    public void readfile() {
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

    public void plan_control_readfile() {

        list = new ArrayList<ArrayList<String>>();
        listSub = new ArrayList<String>();
        String time1 = null;
        String time2 = null;
        String hour_s = null;
        String min_s = null;
        String day_s = null;
        String month_s = null;
        String year_s = null;
        String start_time_s = null;
        String end_time_s = null;
        String start_date_s = null;
        String end_date_s = null;

        int len = 0;
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
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
                    //get time
                    boolean flag = true;
                    time = br.readLine();
                    String[] tmpstime = time.split(":");
                    hour = Integer.parseInt(tmpstime[0]);
                    len = String.valueOf(hour).length();
                    hour_s = ("0" + String.valueOf(hour)).substring(len - 1, len + 1);
                    min = Integer.parseInt(tmpstime[1]);
                    len = String.valueOf(min).length();
                    min_s = ("0" + String.valueOf(min)).substring(len - 1, len + 1);
                    start_time_s = hour_s + min_s;
                    listSub.add(start_time_s);
                    dp.setST(hour_s + ":" + min_s);
                    flag = false;
                    time = br.readLine();
                    String[] tmpetime = time.split(":");
                    hour = Integer.parseInt(tmpetime[0]);
                    len = String.valueOf(hour).length();
                    hour_s = ("0" + String.valueOf(hour)).substring(len - 1, len + 1);
                    min = Integer.parseInt(tmpetime[1]);
                    len = String.valueOf(min).length();
                    min_s = ("0" + String.valueOf(min)).substring(len - 1, len + 1);
                    end_time_s = hour_s + min_s;
                    listSub.add(end_time_s);
                    //get lift number
                    dp.setET(hour_s + ":" + min_s);
                    liftnum = br.readLine();
                    listSub.add(liftnum);
                    dp.setLF(liftnum);
                    //get default floor
                    defloor = br.readLine();
                    dp.setDF(defloor);
                    listSub.add(defloor);

                    //get date
                    flag = true;
                    date = br.readLine();
                    String[] tmpstart = date.split("/");
                    day = Integer.parseInt(tmpstart[0]);
                    len = String.valueOf(day).length();
                    day_s = ("0" + String.valueOf(day)).substring(len - 1, len + 1);
                    month = Integer.parseInt(tmpstart[1]);
                    len = String.valueOf(month).length();
                    month_s = ("0" + String.valueOf(month)).substring(len - 1, len + 1);
                    year = Integer.parseInt(tmpstart[2]);
                    year_s = String.valueOf(year);
                    start_date_s = year_s + month_s + day_s;
                    listSub.add(start_date_s);

                    flag = false;
                    date = br.readLine();
                    String[] tmpend = date.split("/");
                    day = Integer.parseInt(tmpend[0]);
                    len = String.valueOf(day).length();
                    day_s = ("0" + String.valueOf(day)).substring(len - 1, len + 1);
                    month = Integer.parseInt(tmpend[1]);
                    len = String.valueOf(month).length();
                    month_s = ("0" + String.valueOf(month)).substring(len - 1, len + 1);
                    year = Integer.parseInt(tmpend[2]);
                    year_s = String.valueOf(year);
                    end_date_s = year_s + month_s + day_s;
                    listSub.add(end_date_s);
                    //get frequency
                    freq = br.readLine();
                    listSub.add(freq);

                    //get avaliable floors
                    afloor = br.readLine();
                    listSub.add(afloor);
                    dp.setAF(afloor);
                    list.add(listSub);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Currently no plan has been recorded.");
        }

    }

    public void plan_check_person() throws Exception {
        plan_control_readfile();
        String s_date = null;
        String e_date = null;
        String s_time = null;
        String e_time = null;
        String frequ = null;
        boolean plan_lift = false;
        boolean has_f = false;
        boolean c = false;
        boolean d = false;
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        for (int i = 0; i < l_totalnumber; i++) {
            if (planLift[i] == false) {
                planlift_d_floor[i] = 0;
                for (int p = 0; p < total_floor; p++) {
                    available_floor[i][p] = 0;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            s_date = list.get(i).get(4);
            e_date = list.get(i).get(5);
            cal1.setTime(now);
            cal2.setTime(sdf.parse(s_date));
            cal3.setTime(sdf.parse(e_date));

            if (cal2.before(cal1) && cal3.after(cal1)) {

                frequ = list.get(i).get(6);
                if (frequ.equals("weekend")) {
                    if (cal1.get(Calendar.DAY_OF_WEEK) == 7 || cal1.get(Calendar.DAY_OF_WEEK) == 1) {
                        s_time = list.get(i).get(0);
                        e_time = list.get(i).get(1);

                        SimpleDateFormat sdf1 = new SimpleDateFormat("HHmm");
                        String hou = String.valueOf(cal1.get(Calendar.HOUR_OF_DAY));
                        int h = Integer.parseInt(hou);
                        int len = String.valueOf(h).length();
                        hou = ("0" + String.valueOf(h)).substring(len - 1, len + 1);
                        String min = String.valueOf(cal1.get(Calendar.MINUTE));
                        int t_m = Integer.parseInt(min);
                        len = String.valueOf(t_m).length();
                        min = ("0" + String.valueOf(t_m)).substring(len - 1, len + 1);
                        String c_time = hou + min;

                        cal1.setTime(sdf1.parse(c_time));
                        cal2.setTime(sdf1.parse(s_time));
                        cal3.setTime(sdf1.parse(e_time));



                        if (cal2.before(cal1) && cal3.after(cal1)) {

                            String[] a = list.get(i).get(2).split(",");

                            for (int q = 0; q < l_totalnumber; q++) {
                                for (int r = 0; r < a.length; r++) {
                                    int tem_l_number = Integer.valueOf(a[r]).intValue();
                                    if (q + 1 == tem_l_number) {
                                        planLift[q] = true;
                                        planlift_d_floor[q] = Integer.valueOf(list.get(i).get(3)).intValue();
                                        available_floor[q][planlift_d_floor[q] - 1] = 1;
                                        String[] b = list.get(i).get(7).split(",");
                                        for (int m = 0; m < b.length; m++) {
                                            available_floor[q][(Integer.valueOf(b[m]).intValue()) - 1] = 1;
                                        }
                                    }
                                }

                            }
                        }
                    }
                } else if (frequ.equals("weekday")) {


                    if (cal1.get(Calendar.DAY_OF_WEEK) == 6 || cal1.get(Calendar.DAY_OF_WEEK) == 5 || cal1.get(Calendar.DAY_OF_WEEK) == 4 || cal1.get(Calendar.DAY_OF_WEEK) == 3 || cal1.get(Calendar.DAY_OF_WEEK) == 2) {

                        s_time = list.get(i).get(0);
                        e_time = list.get(i).get(1);

                        SimpleDateFormat sdf1 = new SimpleDateFormat("HHmm");
                        String hou = String.valueOf(cal1.get(Calendar.HOUR_OF_DAY));
                        int h = Integer.parseInt(hou);
                        int len = String.valueOf(h).length();
                        hou = ("0" + String.valueOf(h)).substring(len - 1, len + 1);
                        String min = String.valueOf(cal1.get(Calendar.MINUTE));
                        int t_m = Integer.parseInt(min);
                        len = String.valueOf(t_m).length();
                        min = ("0" + String.valueOf(t_m)).substring(len - 1, len + 1);
                        String c_time = hou + min;
                        cal1.setTime(sdf1.parse(c_time));
                        cal2.setTime(sdf1.parse(s_time));
                        cal3.setTime(sdf1.parse(e_time));
                        if (cal2.before(cal1) && cal3.after(cal1)) {
                            String[] a = list.get(i).get(2).split(",");
                            for (int q = 0; q < l_totalnumber; q++) {
                                for (int r = 0; r < a.length; r++) {
                                    int tem_l_number = Integer.valueOf(a[r]).intValue();
                                    if (q + 1 == tem_l_number) {
                                        planLift[q] = true;
                                        planlift_d_floor[q] = Integer.valueOf(list.get(i).get(3)).intValue();
                                        available_floor[q][planlift_d_floor[q] - 1] = 1;
                                        String[] b = list.get(i).get(7).split(",");
                                        for (int m = 0; m < b.length; m++) {
                                            available_floor[q][(Integer.valueOf(b[m]).intValue()) - 1] = 1;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }

            }

        }
        listSub.clear();
        list.clear();

    }

    public void makeOutput() {
        System.out.println(contens);
        try {

            FileWriter fw = new FileWriter("output.txt", true); //the true will append the new data
            fw.write(contens);//appends the string to the file
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
