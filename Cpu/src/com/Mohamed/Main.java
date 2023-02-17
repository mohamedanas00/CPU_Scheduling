package com.Mohamed;

import java.util.ArrayList;

import java.util.Scanner;


public class Main {
    public static void Copy(ArrayList<Partition> partitionOriginal,ArrayList< Process> processesOriginal,ArrayList<Partition> partitionCopy,ArrayList< Process> processesCopy){
        for (Process process : processesOriginal) {
            Process Copy0 = new Process();
            Copy0.name = process.name;
            Copy0.size = process.size;
            Copy0.flag = process.flag;
            processesCopy.add(Copy0);
        }
        for (Partition partition : partitionOriginal) {
            Partition Copy1 = new Partition();
            Copy1.name = partition.name;
            Copy1.size = partition.size;
            Copy1.numberProcess = partition.numberProcess;
            Copy1.flag = partition.flag;
            partitionCopy.add(Copy1);
        }
    }
    public static void Print(ArrayList<Partition> partitionFit,ArrayList< Process> processesFit){
        for (Partition partition : partitionFit) {
            System.out.print(partition.name + " " + "(" + partition.size + "KB" + ")" +
                    "=>");
            if (partition.numberProcess != -1) {
                System.out.println("Process " + (partition.numberProcess + 1));
            } else {
                System.out.println("External fragment");
            }
        }
        System.out.println();
        for(Process p:processesFit){
            if(p.flag){
                System.out.println(p.name+" can not be allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Process> process  = new ArrayList<Process>();
        ArrayList<Partition> partition  = new ArrayList<Partition>();
        Compaction compaction=new Compaction();

        System.out.print("Enter number of partitions:");
        int partitionsSize=input.nextInt();
        for(int i=0;i<partitionsSize;i++){
            Partition P=new Partition();
            int id= Partition.id;
            Partition.id++;
            P.name= "partition"+id;
            System.out.print("Enter partition"+i+" size:");
            P.size= input.nextInt();
            P.flag=true;
            P.numberProcess=-1;
            partition.add(P);
        }

        System.out.println();
        System.out.print("Enter number of process:");
        int processSize=input.nextInt();
        for(int i=0;i<processSize;i++){
            Process process1=new Process();
            process1.name= "process"+(i+1);

            System.out.print("Enter process"+(i+1)+" size:");
            process1.size= input.nextInt();
            process1.flag=true;
            process.add(process1);
        }
        while (true) {
            System.out.println("Select the policy you want to apply:");
            System.out.println("1.First fit");
            System.out.println("2.Worst fit");
            System.out.println("3.Best fit");
            System.out.println("4.Exit");
            int choice = input.nextInt();
            if (choice == 1) {
                Partition.id=partitionsSize;
                ArrayList<Process> processCopy = new ArrayList<>();
                ArrayList<Partition> partitionCopy = new ArrayList<>();
                Copy(partition,process,partitionCopy,processCopy);
                FirstFit firstFit = new FirstFit();
                firstFit.fitSelection(processCopy, partitionCopy);
                Print(firstFit.getfPartition(), firstFit.getfProcesses());
                System.out.println("Do you want to compact? 1.yes 2.no");
                int c = input.nextInt();
                if (c == 1) {
                    compaction.compactionPolicy(firstFit.getfPartition(), firstFit.getfProcesses());
                    Print(firstFit.getfPartition(), firstFit.getfProcesses());

                }
            }else if(choice==2)
            {
                Partition.id=partitionsSize;
                ArrayList<Process> processCopy3 = new ArrayList<>();
                ArrayList<Partition> partitionCopy3 = new ArrayList<>();
                Copy(partition,process,partitionCopy3,processCopy3);
                WorstFit worstFit = new WorstFit();
                worstFit.worstSelection(processCopy3, partitionCopy3);
                Print(worstFit.getfPartition(), worstFit.getfProcesses());
                System.out.println("Do you want to compact? 1.yes 2.no");
                int c1 = input.nextInt();
                if (c1 == 1)
                {
                    compaction.compactionPolicy(worstFit.getfPartition(), worstFit.getfProcesses());
                    Print(worstFit.getfPartition(), worstFit.getfProcesses());
                }
            }
            else if(choice==3){
                Partition.id=partitionsSize;
                ArrayList<Process> processCopy4 = new ArrayList<>();
                ArrayList<Partition> partitionCopy4 = new ArrayList<>();
                Copy(partition,process,partitionCopy4,processCopy4);
                BestFit bestFit = new BestFit();
                bestFit.BestFitSelection(processCopy4, partitionCopy4);
                Print(bestFit.getfPartition(), bestFit.getfProcesses());
                System.out.println("Do you want to compact? 1.yes 2.no");
                int c1 = input.nextInt();
                if (c1 == 1)
                {
                    compaction.compactionPolicy(bestFit.getfPartition(), bestFit.getfProcesses());
                    Print(bestFit.getfPartition(), bestFit.getfProcesses());
                }

            }else if(choice==4){
                break;
            }
        }
    }

}
