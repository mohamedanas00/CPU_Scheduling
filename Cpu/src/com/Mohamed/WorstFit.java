package com.Mohamed;

import java.util.ArrayList;

public class WorstFit {
    ArrayList<Process> wProcesses = new ArrayList<>();
    ArrayList<Partition> wPartition = new ArrayList<>();

    public void worstSelection(ArrayList<Process> processesw, ArrayList<Partition> partitionsw) {
        for (int i = 0; i < processesw.size(); i++)
        {
            int check=-1;
            for (int j = 0; j < partitionsw.size(); j++)
            {
                if (!processesw.get(i).flag)
                {
                    break;
                }
                if(partitionsw.get(j).size>=processesw.get(i).size && partitionsw.get(j).flag)
                {
                    if(check==-1)
                    {
                        check=j;
                    }
                    else if (partitionsw.get(j).size>=partitionsw.get(check).size)
                    {
                        check=j;
                    }
                }
            }
            if(check!=-1)
            {
                int remain=partitionsw.get(check).size-processesw.get(i).size;//calculation logic(size of process-size partion)
                partitionsw.get(check).flag=false;
                partitionsw.get(check).numberProcess=i;
                partitionsw.get(check).size=processesw.get(i).size;
                processesw.get(i).flag=false;
                if(remain!=0){//make new partition
                    Partition w=new Partition();
                    w.size=remain;//size of partition = calculated size
                    w.flag=true;//empty
                    int id =Partition.id;
                    Partition.id++;
                    w.name="Partition "+id;
                    w.numberProcess=-1;
                    int newPlace=check+1;//set after the partition im standing on
                    partitionsw.add(newPlace,w);//sent in the array list the new place
                }
            }
        }
        wProcesses=processesw;
        wPartition=partitionsw;
    }

    public ArrayList<Process> getfProcesses() {return wProcesses;}
    public ArrayList<Partition> getfPartition(){return wPartition;}
}




