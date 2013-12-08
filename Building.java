package test_smart;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Building {
    
    String bname;
    String famount;
    String lamount;
    
    public Building(){
        bname = null;
        famount = null;
        lamount = null;
    }
    
    public void setfields(String na, String fa, String la){
        bname = na;
        famount = fa;
        lamount = la;
    }
    
    public void setname(String n){
        bname  = n;
    }
    
    public void setfloor(String fl){
        famount = fl;
    }
    
    public void setlift(String li){
        lamount = li;
    }
    
    public String getname(){
        return bname;
    }
    
    public String getfloor(){
        return famount;
    }
    
    public String getlift(){
        return lamount;
    }
    
    public void printbuilding(){
     
    }
    
    public void writetofile(){
        try{
            
            FileOutputStream fos = new FileOutputStream("Building.txt", true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter out = new BufferedWriter(osw);
            //out.write("\n");
            out.write(bname + "\n");
            out.write(famount + "\n");
            out.write(lamount + "\n");
            out.close();
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    

}

