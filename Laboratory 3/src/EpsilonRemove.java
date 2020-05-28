import java.sql.DataTruncation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class EpsilonRemove {
    public String[] Vn;
    public String[] Vt;
    public HashMap<String, HashSet<String>> productions;
    public HashMap<String, HashSet<String>> productions2;
    public HashSet<String> toChange;
    public boolean hasChanged=true;
    String lettertoChange ;

    public EpsilonRemove (String[] Vn, String[] Vt, HashMap<String, HashSet<String>> productions) {
        this.Vn = Vn;
        this.Vt = Vt;
        this.productions = productions;
        toChange=new HashSet<String>();
        productions2=new HashMap<String, HashSet<String>>();

        this.nextState();

    }

    public void nextState () {
        while (hasChanged){
            hasChanged=false;
            toChange=new HashSet<>();
            check();
            substitute();
        }
        UnitProductiveRemove unit= new UnitProductiveRemove(Vn,Vt,productions);
    }
    public void substitute(){


        for (String key : productions.keySet()) {
            for (String setElement : productions.get(key)) {
                if (contains(setElement,toChange)){
                    add(productions2,key,setElement,lettertoChange); //will add new production in temporar map
                }
            }
        }
        for(String key:productions2.keySet()){
            HashSet<String> tempSet= new HashSet<String>() {{
                addAll(productions.get(key));
                addAll(productions2.get(key));
            } };

            productions.put(key,tempSet);
        }
        productions2=new HashMap<>();
        for(String key:toChange)
            productions.get(key).remove("*");
        System.out.println("New State:");
        System.out.println(productions);
    }
    public void add(HashMap<String,HashSet<String>> map, String key, String str,String letter ){
        char character=letter.charAt(0);
        for (int i=0;i<str.length();i++){
            if(str.charAt(i)==character){
                HashSet<String> set = map.get(key);
                if (set==null){
                    set=new HashSet<String>();
                    map.put(key,set);
                }
                try {

                }catch (Exception e){

                }
                String strtoAdd="";
                if(i!=0){
                    strtoAdd=strtoAdd+str.substring(0,i);
                }
                strtoAdd+=str.substring(i+1);
                if(strtoAdd.length()>0)
                    set.add(strtoAdd);
                else{
                    strtoAdd="*";
                    set.add(strtoAdd);
                }
                System.out.println("For key:"+key+" new production after Epsilon Elimination:"+strtoAdd);
            }
        }
    }
    public boolean contains(String mystr, HashSet<String>set){

        for (String s : set) {
            if (mystr.contains(s)) {
                lettertoChange=s;
                return true;
            }
        }
        return false;
    }
    public void check() {
        for (String key : productions.keySet()) {
            for (String setElement : productions.get(key)) {
                if (setElement.equals("*")){
                    hasChanged=true;
                    toChange.add(key);
                }
            }


        }


    }
}