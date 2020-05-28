import javafx.beans.binding.StringBinding;

import java.util.*;

public class Chomsky {
    List<String> Vn=new ArrayList<>();
    String[] Vt;
    HashMap<String, HashSet<String>> productions;
    HashMap<String, HashSet<String>> toRemove=new HashMap<>();
    HashMap<String, HashSet<String>> toAdd=new HashMap<>();
    HashMap<String,String> shortermap=new HashMap<>();
    HashMap<String,String> mask=new HashMap<>();
    boolean hasChanged=true;
    int j=65;
    public Chomsky(String[] Vn, String[] Vt, HashMap<String, HashSet<String>> productions){
        this.productions=productions;
        this.Vn.addAll(Arrays.asList(Vn));
        this.Vt=Vt;
        replaceWithNonTerminal();
        System.out.println(" Derivation in maximum 2 Non-terminal and adding new production:");
        makeShorter();
        System.out.println(productions);
    }
    public void makeShorter(){
        while (hasChanged){
            hasChanged=false;

            toRemove=new HashMap<>();
            toAdd=new HashMap<>();

            for (String key:productions.keySet()){
                for(String element:productions.get(key)){
                    if(element.length()>2 ){
                        HashSet<String> set = new HashSet<>();
                        HashSet<String> removeset= toRemove.get(key);
                        if(removeset==null){
                            removeset=new HashSet<>();
                            toRemove.put(key,removeset);
                        }
                        if(!shortermap.containsKey(element.substring(0,2))){
                            removeset.add(element);
                            shortermap.put(element.substring(0,2),Character.toString(++j));
                            set.add(element.replace(element.substring(0,2),Character.toString(j)));}
                        else {
                            removeset.add(element);
                            String str=element.replace(element.substring(0,2),shortermap.get(element.substring(0,2)));
                            set.add(str);
                        }
                        toAdd.put(key,set);
                    }
                }
            }
            for (String key:toRemove.keySet()){
                HashSet<String> setToAdd= toAdd.get(key);
                HashSet<String> setRemove=toRemove.get(key);
                for(String element:setRemove){
                    productions.get(key).remove(element);
                }
                for(String element:setToAdd){
                    productions.get(key).add(element);
                }
                for (String key2:shortermap.keySet()){
                    HashSet<String> set = new HashSet<>();
                    set.add(key2);
                    productions.put(shortermap.get(key2),set);
                }

            }
        }
    }
    public void replaceWithNonTerminal(){

        for(String key:productions.keySet()){
            if(j<key.charAt(0)){
                j=key.charAt(0);
            }
        }
        for(String terminal:Vt){
            mask.put(Character.toString(++j),terminal);
        }
        for(String key:productions.keySet()){
            for(String element:productions.get(key)){
                String str = element;
                for(String keymask:mask.keySet()){
                    if(str.contains(mask.get(keymask))){
                        if(str.length()>1)
                            str=str.replace(mask.get(keymask),keymask);


                    }
                }
                HashSet<String> set=toRemove.get(key);
                if(set==null){
                    set=new HashSet<>();
                    toRemove.put(key,set);
                }
                set.add(element);
                set=toAdd.get(key);
                if(set==null){
                    set=new HashSet<>();
                    toAdd.put(key,set);
                }
                set.add(str);
                System.out.println("In "+key+" '"+element+"' was replaced with: "+str);
            }
        }
        for(String key:toRemove.keySet()){
            for(String element:toRemove.get(key)){
                productions.get(key).remove(element);
            }
        }

        for(String key:toAdd.keySet()){
            for(String element:toAdd.get(key)){
                productions.get(key).add(element);
            }
        }

        for(String key:mask.keySet()){
            HashSet<String> set = new HashSet<>();
            set.add(mask.get(key));
            productions.put(key,set);
        }
        System.out.println(productions);

    }
}