package project123;
import java.util.*;
import java.io.*;

class Graph extends Graph1
{
	int adjMat[][]=new int [20][20];
	String arr[]=new String[20];
	int flag[]=new int[20];  
	int arr1[]=new int[17];
	Scanner sc=new Scanner(System.in);
	int total_dist=0;

	void read()
	{
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				adjMat[i][j]=9999;
			}
		}
		try {
			FileInputStream fis=new FileInputStream(s);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Vector<node> ds=(Vector<node>)ois.readObject();
			Iterator<node> itr=ds.iterator();
			System.out.println("Available cities are");
			while(itr.hasNext())
			{
				node s=itr.next();
				adjMat[s.u][s.v]=s.w;
				adjMat[s.v][s.u]=s.w;
				arr[s.u]=s.start;
				arr[s.v]=s.end;

				if(arr1[s.u]==0)
				{
					arr1[s.u]=1;
					System.out.println(s.start+"\t"+s.u);
				}
				if(arr1[s.v]==0)
				{
					arr1[s.v]=1;
					System.out.println(s.end+"\t"+s.v);
				}
			}
			ois.close();
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
	}
	
	void dijikstra(int src,int cost[][],int des)
	{
		int index=src;
		int sum=0;
		int min=0;							
		int dist[]=new int [20];
		int visit[]=new int[20];
		int pred[]=new int [20];
		for(int i=0;i<17;i++)
		{
			visit[i]=0;
			dist[i]=adjMat[src][i];
			pred[i]=src;
		}
		visit[src]=1;
		dist[src]=0;
		while(index!=des)
		{
			min=9999;
			for(int i=0;i<17;i++)
			{
				if(dist[i]<min && visit[i]!=1)
				{
					min=dist[i];
					index=i;
				}

			}
			
			visit[index]=1;
			
			for(int i=0;i<17;i++)	
			{
				if(visit[i]!=1)
				{
					if(dist[i]>(dist[index]+adjMat[index][i]))
					{
						dist[i]=dist[index]+adjMat[index][i];
						pred[i]=index;	
					}
				}
			}	    	
		}
		String a[]=new String[10];
		a[0]=arr[des];
		int q=1;
		int w=des;
		while(pred[w]!=src)
		{
			a[q]=arr[pred[w]];
			q++;
			w=pred[w];
		}
		a[q]=arr[src];
		System.out.println("\n Route:");
		for(int x=q;x>-1;x--)
	    System.out.print(a[x]+"\t");					//Displaying the route it follows
		System.out.println("\nTotal distance required is "+dist[des]);
		total_dist=dist[des];
	}
	
	void billing()					//Total Bill
	{
		int ch,c=1;
		int cost=0,value=0,lab=0;
		int nos=0;

		do
		{
			System.out.println("\nSelect your mode of packers and movers:\n1.Pick-up Trucks\n2.Trucks\n3.Trailer\n");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:System.out.print("Travel cost = Rs.10 per KM\nLabour price = 500Rs/per labour\nLabourers = 2 per vehicle\n");
			       System.out.print("\nEnter Number of Pick-up Trucks you will need for service:");
			       nos=sc.nextInt();
			       cost=(2*nos*500)+cost+(10*total_dist*nos);	    			
			       break;
			
			case 2:System.out.print("Travel cost = Rs.15 per KM\nLabour price = 500Rs/per labour\nLabourers = 4 per vehicle\n");
				   System.out.print("\nEnter Number of Trucks you will need for service:");
				   nos=sc.nextInt();
				   cost=(4*nos*500)+cost+(15*total_dist*nos);
				   break;
				   
			case 3:System.out.print("Travel cost = Rs.20 per KM\nLabour price = 500Rs/per labour\nLabourers = 8 per vehicle\n");
				   System.out.print("\nEnter Number of Trailers you will need for service:");
				   nos=sc.nextInt();
				   cost=(8*nos*500)+cost+(20*total_dist*nos);
			}
			System.out.print("\nDo you want any other service\n1.Yes\n2.No");
			c=sc.nextInt();
		}while(c==1);
		System.out.print("\nTotal Bill = Rs."+cost);
	}
}

public class Project {

	public static void main(String[] args) {
		Graph g=new Graph();
		Graph1 g1=new Graph1();
		String src,des;
		int u,v;
		Scanner s=new Scanner(System.in);
		g.read();
		System.out.println("Enter source to start the Dijikstras");
		src=s.next();
		System.out.println("Enter destination");
		des=s.next();
		System.out.println("Enter stop no. of "+src+"  :");
		u=s.nextInt();
		System.out.println("Enter stop no. of "+des+" :");
		v=s.nextInt();
		g.dijikstra(u,g.adjMat,v);
		g.billing();

	}

}



/*DATA BASE:

From:
Delhi

City ID:
0
To:
Jaipur

City ID:
5

Add Distance:
450
Do you want to continue
if yes enter 1
1
From:
Delhi

City ID:
0
To:
Chandigarh

City ID:
11

Add Distance:
300
Do you want to continue
if yes enter 1
1
From:
Chandigarh

City ID:
11
To:
Ahmedabad

City ID:
3

Add Distance:
800
Do you want to continue
if yes enter 1
1
From:
Chandigarh

City ID:
11
To:
Indore

City ID:
13

Add Distance:
1200
Do you want to continue
if yes enter 1
1
From:
Ahmedabad

City ID:
3
To:
Jaipur

City ID:
5

Add Distance:
150
Do you want to continue
if yes enter 1
1
From:
Ahmedabad

City ID:
3
To:
Surat

City ID:
4

Add Distance:
100
Do you want to continue
if yes enter 1
1
From:
Surat

City ID:
4
To:
Jaipur

City ID:
5

Add Distance:
200
Do you want to continue
if yes enter 1
1
From:
Jaipur

City ID:
5
To:
Mumbai

City ID:
1

Add Distance:
300
Do you want to continue
if yes enter 1
1
From:
Mumbai

City ID:
1
To:
Patna

City ID:
12

Add Distance:
400
Do you want to continue
if yes enter 1
1
From:
Patna

City ID:
12
To:
Kanpur

City ID:
14

Add Distance:
600
Do you want to continue
if yes enter 1

1
From:
Kanpur

City ID:
14
To:
Jaipur

City ID:
5

Add Distance:
700
Do you want to continue
if yes enter 1
1
From:
Kanpur

City ID:
14
To:
Nagpur

City ID:
6

Add Distance:
750
Do you want to continue
if yes enter 1
1
From:
Nagpur

City ID:
6
To:
Indore

City ID:
13

Add Distance:
650
Do you want to continue
if yes enter 1
1
From:
Indore

City ID:
13
To:
Chennai

City ID:
9

Add Distance:
1000
Do you want to continue
if yes enter 1
1
From:
Chennai

City ID:
9
To:
Vellore

City ID:
10

Add Distance:
300
Do you want to continue
if yes enter 1
1
From:
Vellore

City ID:
10
To:
Aurangabad

City ID:
16

Add Distance:
800
Do you want to continue
if yes enter 1
1
From:
Aurangabad

City ID:
16
To:
Nagpur

City ID:
6

Add Distance:
600
Do you want to continue
if yes enter 1
1
From:
Aurangabad

City ID:
16
To:
Bengaluru

City ID:
7

Add Distance:
650
Do you want to continue
if yes enter 1
1
From:
Bengaluru

City ID:
7
To:
Nashik

City ID:
15

Add Distance:
900
Do you want to continue
if yes enter 1
1
From:
Bengaluru

City ID:
7
To:
Mysore

City ID:
8

Add Distance:
500
Do you want to continue
if yes enter 1
1
From:
Mysore

City ID:
8
To:
Pune

City ID:
2

Add Distance:
850
Do you want to continue
if yes enter 1
1
From:
Pune

City ID:
2
To:
Mumbai

City ID:
1

Add Distance:
100
Do you want to continue
if yes enter 1
1
From:
Mumbai

City ID:
1
To:
Nashik

City ID:
15

Add Distance:
150
Do you want to continue
if yes enter 1
1
From:
Pune

City ID:
2
To:
Nashik

City ID:
15

Add Distance:
200
Do you want to continue
if yes enter 1
1
From:
Mysore

City ID:
8
To:
Vellore

City ID:
10

Add Distance:
800
Do you want to continue
if yes enter 1
0
 */

/*Final path with cost

Available cities are
Delhi	0
Jaipur	5
Chandigarh	11
Ahmedabad	3
Indore	13
Surat	4
Mumbai	1
Patna	12
Kanpur	14
Nagpur	6
Chennai	9
Vellore	10
Aurangabad	16
Bengaluru	7
Nashik	15
Mysore	8
Pune	2
Enter source to start the Dijikstras
Chandigarh 
Enter destination
Mysore
Enter stop no. of Chandigarh  :
11
Enter stop no. of Mysore :
8

 Route:
Chandigarh	Delhi	Jaipur	Mumbai	Pune	Mysore	
Total distance required is 2000

Select your mode of packers and movers:
1.Pick-up Trucks
2.Trucks
3.Trailer

1
Travel cost = Rs.10 per KM
Labour price = 500Rs/per labour
Labourers = 2 per vehicle

Enter Number of Pick-up Trucks you will need for service:2

Do you want any other service
1.Yes
2.No1

Select your mode of packers and movers:
1.Pick-up Trucks
2.Trucks
3.Trailer

3
Travel cost = Rs.20 per KM
Labour price = 500Rs/per labour
Labourers = 8 per vehicle

Enter Number of Trailers you will need for service:1

Do you want any other service
1.Yes
2.No2

Total Bill = Rs.86000
*/