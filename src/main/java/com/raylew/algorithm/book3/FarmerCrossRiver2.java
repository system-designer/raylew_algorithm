package com.raylew.algorithm.book3;

        import java.util.HashSet;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Set;

/**
 * Created by Raymond on 2016/11/2.
 * <warning>未完成</warning>
 * 农夫P、狼w、羊c和菜g过河
 */
public class FarmerCrossRiver2 {
    private static List<String> path;
    public static void main(String[] args) {
        Set aSide=new HashSet();
        aSide.add("P");
        aSide.add("w");
        aSide.add("c");
        aSide.add("g");
        Set bSide=new HashSet();
        crossRiver(aSide,bSide,0);
    }

    public static void crossRiver(Set aSide,Set bSide,int direction){
        if(aSide.size()==0&&bSide.size()==4){
            System.out.println("ok");
            return;
        }else if(isDangerous(aSide, bSide)){
            System.out.println("failed");
            return;
        }else{
            if(direction==0){
                //aSide->bSide
                if(bSide.size()>0){
                    aSide.remove("P");
                    bSide.add("P");
                    //判重
                    crossRiver(aSide,bSide,1-direction);
                    aSide.add("P");
                    bSide.remove("P");
                }
                Iterator iterator = bSide.iterator();
                while (iterator.hasNext()){
                    aSide.remove("P");
                    String element=(String)iterator.next();
                    aSide.remove(element);
                    bSide.add("P");
                    bSide.add(element);
                    //判重
                    crossRiver(aSide, bSide, 1 - direction);
                    aSide.add("P");
                    aSide.add(element);
                    bSide.remove("P");
                    bSide.remove(element);
                }
            }else{
                //bSide->aSide
                if(aSide.size()>0){
                    bSide.remove("P");
                    aSide.add("P");
                    //判重
                    crossRiver(aSide,bSide,1-direction);
                    bSide.add("P");
                    aSide.remove("P");
                }
                Iterator iterator = aSide.iterator();
                while (iterator.hasNext()){
                    bSide.remove("P");
                    String element=(String)iterator.next();
                    bSide.remove(element);
                    aSide.add("P");
                    aSide.add(element);
                    //判重
                    crossRiver(aSide, bSide, 1 - direction);
                    bSide.add("P");
                    bSide.add(element);
                    aSide.remove("P");
                    aSide.remove(element);
                }
            }
        }
    }

    public static boolean isDangerous(Set aSide,Set bSide){
        return dangerous(aSide)||dangerous(bSide);
    }

    public static boolean dangerous(Set side){
        if((side.contains("w")&&side.contains("c")&&!side.contains("P"))
                ||(side.contains("c")&&side.contains("g")&&!side.contains("P"))){
            return true;
        }
        return false;
    }
}

