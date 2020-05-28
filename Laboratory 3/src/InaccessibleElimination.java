import java.util.HashMap;
import java.util.HashSet;

public class InaccessibleElimination {
    String[] Vn;
    String[] Vt;
    HashMap<String, HashSet<String>> productions;
    HashSet<String> accessible = new HashSet<>();
    public boolean hasChanged=true;
    public InaccessibleElimination(String[] Vn, String[] Vt, HashMap<String, HashSet<String>> productions){
        this.Vn=Vn;
        this.Vt=Vt;
        this.productions=productions;
        add(Vn[0]);
        remove();
        System.out.println("After removing inaccessible:");
        System.out.println(productions);
        Chomsky chomsky = new Chomsky(Vn,Vt,productions);
    }
    public void remove(){
        HashSet<String> toRemove=new HashSet<>();
        for(String key:productions.keySet()){
            if (!accessible.contains(key)){
                toRemove.add(key);
            }
        }
        for (String key:toRemove){
            productions.remove(key);
        }
    }
    public void add(String str){
        for(String element:productions.get(str)){
            for(int i=0;i<element.length();i++){
                if(Character.isUpperCase(element.charAt(i)) && !accessible.contains(Character.toString(element.charAt(i)))){
                    String string=Character.toString(element.charAt(i));
                    accessible.add(string);
                    add(string);
                }
            }
        }
    }

}