package project123;
import java.util.*;
import java.io.*;

class node implements Serializable
{
    int u,v,w;
    String start,end;
    public node(int u1,int v1,int w1,String st,String ed)
    {
        u=u1;
        v=v1;
        w=w1;
        start=st;
          end=ed;
    }
    
}

class Graph1 extends Exception{
	  File s=new File("details.txt");	
//	  FileReader s;
	  int adjMat[][]=new int [19][19];
	  int visit[]=new int [19];
	  public  int nverts,wt,ne;
	  Graph1()
	  {
	  nverts=17;

	  }
	    Scanner sc=new Scanner(System.in);
		Vector<node> v1=new Vector<>();
		
	    void create()
	    {
	        node temp;
	                for(int i=0;i<nverts;i++) { //initialising the matrix
	                for(int j=0;j<nverts;j++) {
	                    adjMat[i][j]=999;
	                }
	            }
	            int i=0;
	            int u=0;
	            int v=0;
	            String start,end;
	            
	        	
	        		int ch=0;
	        		do {
	        			 System.out.println("From:");
	                     start=sc.next();
	                     System.out.println("\nCity ID:");
	                     u=sc.nextInt();
	                     System.out.println("To:");
	                     end=sc.next();
	                    System.out.println("\nCity ID:");
	                    v=sc.nextInt();
	                    System.out.println("\nAdd Distance:");
	                    wt=sc.nextInt();
	                    adjMat[u][v]=wt;
	                    adjMat[v][u]=wt;
	           			temp=new node(u,v,wt,start,end);
	        			v1.add(temp);	        
	        			 
	                     i++;
	        			System.out.println("Do you want to continue\nif yes enter 1");
	        			ch=sc.nextInt();
	        		}while(ch==1);
	              
	    
	}
	    
	    void write1()
		{
			try {
			FileOutputStream fout=new FileOutputStream(s);
			ObjectOutputStream oos=new ObjectOutputStream(fout);
			oos.writeObject(v1);
			fout.close();
			}
			catch(FileNotFoundException ex)
			{
				ex.printStackTrace();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
		
		}
public class file {

	public static void main(String[] args) {
	Graph1 g=new Graph1();
	g.create();
	g.write1();

	}

}
