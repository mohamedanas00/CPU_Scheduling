package com.Mohamed;

import java.util.ArrayList;

public class FirstFit {
    ArrayList<Process> fProcesses = new ArrayList<Process>();//array of process
    ArrayList<Partition> fPartition = new ArrayList<Partition>();

    public void fitSelection(ArrayList< Process> processes, ArrayList<Partition> partitions) {
        for(int i=0;i<processes.size();i++){
            for(int j=0;j<partitions.size();j++){
                if(!processes.get(i).flag){
                    break;
                }
                //if size of partition>=size of process && partition is not have process && process is not used
                if(partitions.get(j).size>=processes.get(i).size&& partitions.get(j).flag){
                    int sub=partitions.get(j).size-processes.get(i).size;//calculation logic(size of process-size partion)
                    partitions.get(j).flag=false;
                    partitions.get(j).numberProcess=i;
                    partitions.get(j).size=processes.get(i).size;
                    processes.get(i).flag=false;
                    if(sub!=0){//make new partion
                        Partition P=new Partition();
                        P.size=sub;//size of partion = calculated size
                        P.flag=true;//empty
//                        String name="Partition"+partitions.size();//the new name of partion
                        int id= Partition.id;
                        Partition.id++;
                        P.name= "Partition"+ id;
                        P.numberProcess=-1;
                        int jPut=j;//the new place index
                        int newPlace=jPut+1;//set after the partion im standing on
                        partitions.add(newPlace,P);//sentr in the array list the new place
                    }
                    break;
                }
            }
        }
        fProcesses=processes;
        fPartition=partitions;
    }

    public ArrayList<Process> getfProcesses() {
        return fProcesses;
    }

    public ArrayList<Partition> getfPartition() {
        return fPartition;
    }
}