package test_smart;

import java.io.*;
import java.lang.System.*;
import java.util.*;


public class Plan
{
    String name = null;       //plan name
    String[] liftnum;       //lift number
    String defaultfloor; //default floor
    String freq;       //frequency
    String[] floors;      //Avaliable floors
    
    class datetype{   //define datetype to record start/end date
        public int y;
        public int m;
        public int d;
        public datetype(int y, int m, int d){
            this.y = y;
            this.m = m;
            this.d = d;
        }

    }
    
    class timetype{ //define timetype to record start/end time
        public int h;
        public int m;
        public timetype(int h, int m){
            this.h = h;
            this.m = m;
        }

    }
    
    datetype startdate;
    datetype endate;
    datetype getstartdate;
    datetype getendate;
    
    timetype startime;
    timetype endtime;
    
    //constructor
    public Plan(String name){
        this.name =name;
    }
    
    /*all set functions*/
    public void setname(String n){
        name = n;
    }
    
    public void setliftnum(String[] n){
        int length = n.length;
        liftnum = new String[length];
        System.arraycopy(n, 0, liftnum, 0, n.length);
    }
    
    public void setdef(String def){
        defaultfloor = def;
    }
    
    public void setdate(int yy, int mm, int dd, boolean f){
        if(f){
            startdate = new datetype(yy,mm,dd);
        }
        else{
            endate = new datetype(yy,mm,dd);
        }
    }
    
    public void setime(int hh, int mm, boolean f){
        if(f){
            startime = new timetype(hh,mm);            
        }
        else{
            endtime = new timetype(hh,mm);
        }
    }
    
    public void setfreq(String f){
        freq = f;
    }
    
    public void setfloors(String[] fl){
        int length = fl.length;
        floors = new String[length];
        System.arraycopy(fl, 0, floors, 0, fl.length);
    }
    
    /*all return functions*/
    
    public String getname(){
        return name;
    }
   
    
    /*print plan*/
    public void printplan(){
        System.out.println("Detail about " + name + " plan:");
        System.out.println("Start time: " + startime.h + ":" + startime.m);
        System.out.println("End time " + endtime.h + ":" + endtime.m);
        System.out.println("Lift No: ");
        for(int i = 0; i < liftnum.length; i++){
             System.out.print(liftnum[i] + ",");
        }
        System.out.println("\n");
        System.out.println("Defaultfloor: " + defaultfloor);
        System.out.println("Start date: " + startdate.d + "/" + startdate.m + "/" + startdate.y);
        System.out.println("End date: "+ endate.d + "/"+ endate.m + "/" + endate.y);
        System.out.println("Frequency: " + freq);
        System.out.print("Avaliable Floors: ");
        for(int i = 0; i < floors.length; i++){
            System.out.print(floors[i] + " ");
        }
        System.out.println("\n");
        System.out.println("End of plan.");
      
    }
    
    /*write current object into txt file*/
    public void writetofile(){

        try{
            
            FileOutputStream fos = new FileOutputStream("PlanRecord.txt", true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter out = new BufferedWriter(osw);
            
            out.write("Plan name:" + name + "\n");
            out.write(startime.h + ":" + startime.m + "\n");
            out.write(endtime.h + ":" + endtime.m + "\n");
            for(int i = 0; i < liftnum.length; i++){
                out.write(liftnum[i] + ",");
            }
            out.write("\n");
            out.write(defaultfloor + "\n");
            out.write(startdate.d + "/" + startdate.m + "/" + startdate.y + "\n");
            out.write(endate.d + "/"+ endate.m + "/" + endate.y + "\n");
            out.write(freq + "\n");
            for(int i = 0; i < floors.length; i++){
                out.write(floors[i] + ",");
            }
            out.write("\n");
            out.write("#######################\n" );
            out.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
        
        
    }

}
