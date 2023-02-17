package com.Mohamed;

import java.util.ArrayList;

public class BestFit {
    ArrayList<Process> BestProcess = new ArrayList<Process>();//array of process
    ArrayList<Partition> BestPartition = new ArrayList<Partition>();
    public void BestFitSelection(ArrayList< Process> processes,ArrayList<Partition>partitions){
        for(int i=0;i<processes.size();i++) {
            int check = -1;
            for (int j=0; j < partitions.size(); j++) {
                if (!processes.get(i).flag) {
                    break;
                }
                if (partitions.get(j).size >= processes.get(i).size && partitions.get(j).flag) {
                    if (check == -1) {
                        check = j;
                    } else if (partitions.get(j).size <= partitions.get(check).size) {
                        check = j;
                    }

                }

            }
            if (check != -1) {
                int difference = partitions.get(check).size - processes.get(i).size;//calculation logic(size of process-size partion)
                partitions.get(check).flag = false;
                partitions.get(check).numberProcess = i;
                partitions.get(check).size = processes.get(i).size;
                processes.get(i).flag = false;
                if (difference != 0) {//make new partition
                    Partition best = new Partition();
                    best.size = difference;//size of partition = calculated size
                    best.flag = true;//empty
                    int id = Partition.id;
                    Partition.id++;
                    best.name = "Partition " + id;
                    best.numberProcess = -1;
                    int newPlace = check + 1;//set after the partition im standing on
                    partitions.add(newPlace, best);//sen

                }
            }
        }
        BestProcess=processes;
        BestPartition=partitions;
    }
    public ArrayList<Process> getfProcesses() {
        return BestProcess;
    }

    public ArrayList<Partition> getfPartition() {
        return BestPartition;
    }


}
