package com.Mohamed;

import java.util.ArrayList;

public class Compaction {
    public void compactionPolicy(ArrayList<Partition> partitions,ArrayList< Process> processes){
        Partition P=new Partition();
        boolean check=false;
        int id= Partition.id;
        Partition.id++;
        P.name="partition"+id;
        P.flag=true;
        P.numberProcess=-1;
        for(int i=0;i<partitions.size();i++){
            if(partitions.get(i).numberProcess == -1){
                P.size+=partitions.get(i).size;
                partitions.remove(i);
                check=true;
                i--;
            }
        }
        if(check){
          partitions.add(partitions.size(),P);
        }
        System.out.println();
        FirstFit firstFit =new FirstFit();
        firstFit.fitSelection(processes,partitions);
    }
}
