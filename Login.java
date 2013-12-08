
package test_smart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Login {

    public static String name;
    public static String word;
    public static String stat;
    public static int counter = 0;
    
    public static ArrayList<Account> userarray = new ArrayList<Account>();
    public static Account acc = new Account();
    
    public static void readfile(){
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("Account.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                acc = null;
                acc = new Account();
                name = line;
                word = br.readLine();
                stat = br.readLine();
                acc.setfields(name, word, stat);
                userarray.add(acc);//add to arraylist
            }
        }catch(IOException e){
            System.out.println("No Account info!");
        }
    }
    
    public static int check(String n, String p){
        
        boolean find = false;
        for(int i = 0; i<userarray.size(); i++){
            if(userarray.get(i).username.equals(n) && userarray.get(i).password.equals(p)){
                find  = true;
                return i;
            }
        }
        if(!find){
           return 9;
        }
        return 999;
    }
}

