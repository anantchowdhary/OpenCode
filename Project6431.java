import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*Project for 6431 
 * Author : Anant Chowdhary
 


This code has the Timer class - used for time functions
the Diner class - used for each diner Thread
the Cook class - used for each cook Thread
the Order class - used to describe each order

This is a simulation of a Restaurant. A restaurant requires careful coordination of resources, 
which are the tables in the restaurant, the cooks available to cook the order, and the machines 
available to cook the food (we assume that there is no contention on other possible resources, 
like servers to take orders and serve the orders)

  */


class Timer
{
	int i=0;
	int[] time;
	ArrayList<Integer> dinerList;
	public Timer(int [] time)
	{

	this.time = time;
	
	
	}

	
	public static String getMinutes(long time)
	{
		
	
		long seconds  = (long)((time / 1000) % 60);; /*Getting the seconds part of time*/
		long minute = (long) ((time / 1000) / 60); /*Getting the minutes part*/
		
		String minutes="";
		if(minute<10) 
		{
			minutes="0"+minute;
		}
		else
			 
			{
				minutes=""+minute;
			}
		
		if(seconds<10) 
		{
			minutes=minutes+":0"+seconds;
		}
		else
			 
			{
				minutes=minutes+":"+seconds;
			}
		
		
		return minutes+" -";	
	}


}

class Order 
{
	
	int burgers = 0;
	int fries = 0;
	int cokes = 0;
	int status = -1; /*-1=> not picked up yet , 0=>cook working on it, 1=> order completed and ready to eat*/
	int tableID = -1;
	int cookID = -1;
	int dinerID = -1;
	
	public Order(int burgers,int fries, int cokes, int tableID,int dinerID) 

	{

	this.burgers = burgers;
	this.fries = fries;
	this.cokes = cokes;
	this.tableID = tableID;
	this.dinerID = dinerID;

	}

}

class Machine {
	
	int cookID = -1;
	int status = 0;		/* 0=>Free , 1=>Being used */
}

class Table 
{

	int tableID;
	int dinerID = -1;
	int cookID=-1;
	
	public Table(int tableID) 
	{
		
		this.tableID = tableID;
		
	}
	

}

class BurgerMachine  extends Machine
{
	
 

}



class FriesMachine extends Machine

{
	


}

class CokeMachine extends Machine

{


}




class Diner implements Runnable

{
	int status = 0;  /*0 => waiting , 1 => assigned table and  waiting for cook , 2=> placed order waiting for food
	,3 => finished eating */
	int dinerID;
	int tableID = -1 ;
	int timeArrived;
	int burger=0, fries = 0, coke=0;
	ArrayList<Table> tableList;
	ArrayList<Cook> cookList;
	ArrayList<Order> OrderList;
	ArrayList<Integer> dinerWaitList;
	ArrayList<Integer> allDiners;
	Cook cook;
	long[] timer;
	
	public Diner(int dinerID,int time,int burger,int fries,int coke,ArrayList<Table>SharedTables, ArrayList<Order> SharedOrders,long[] timer,ArrayList<Integer> DinerList,ArrayList<Integer> AllDiners) 
	{
		
		this.dinerID = dinerID;
		this.timeArrived = time;
		this.burger = burger;
		this.fries=fries;
		this.coke= coke;
		this.tableList = SharedTables;
		this.OrderList = SharedOrders;
		this.timer = timer;
		this.dinerWaitList = DinerList;
		this.allDiners = AllDiners;
	}
	
	public void run() 
	{	
		try
		{
			Thread.sleep(timeArrived*1000);
			
		}
		catch(Exception e)
		{
			
			
		}
		/*As soon as Diner enters , he/she tries to get a table*/
		
		System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Diner "+dinerID +" arrives.");
		while(true) 
		{
			
			try
			
			{
				if(status>0) break;
				
				tryAndGetTable();
				/*Diner has been assigned Table*/
				
				System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+ " Diner "+dinerID+" is seated at table "+tableID+".");
				
				
			}
			
			catch(Exception e)
			
			{
				
				
			}
			
		}
		
		/*Now the Diner has been allocated a table. The Diner must now place an order with an available cook */
	
	
		
		
		while(true) 
		{
			try
		
			{
			
				if(status>=2) break;	
				OrderFood();
			
		/* Now the Diner thread is sleeping till it is woken up , which is when the food arrives */
			}
		
			catch(Exception e)
		
			{
			
			
			}
			
		}
		
		
		
		/* The food is now ready. The diner must now eat */
		
		
		while(true) 
		{
			try
		
			{
			
				if(status>=3) break;	
				Eat();
				if(allDiners.isEmpty()) 
				{
					
					System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" The last diner leaves the restaurant.");
					System.exit(0);
				}
			
		
			}
		
			catch(Exception e)
		
			{
			
			
			}
		}
		
		
		
		
	}
	
	private void Eat() throws InterruptedException
	{
		
	
	
		try
		{	
			System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+ " Diner "+dinerID+"'s order is ready. Diner "+dinerID+" starts eating.");
			Thread.sleep(30000);	
			status=3;
			System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Diner "+dinerID+" finishes. Diner "+dinerID+" leaves the restaurant.");
		
            
			/*Now we free up the Table the diner had used*/
			synchronized(tableList)
			{
				for(int j=0;j<tableList.size();j++) 
				{
					if(tableList.get(j).tableID==tableID) 
					{
						tableList.get(j).dinerID = -1;
					
					}
				
				}
				
				tableList.notifyAll();
				
				/*Remove diner from list of remaining Diners*/
				for(int i=0;i<allDiners.size();i++) 
				{
					
					if(allDiners.get(i)==dinerID) 
					{
						
						allDiners.remove(i);
					}
					
				}
				
			}
		}
	
		catch (Exception e) 
		
		{
		
		}
		
	}
	
	private void OrderFood() throws InterruptedException 
	{
		
		Order order = new Order(burger,fries,coke,tableID,dinerID);  /*Creating a new order. We will put this order into the
														order queue */
		status = 2; /*The Diner has ordered food*/
		
		
		
		synchronized(OrderList)
		{
			OrderList.add(order);
			OrderList.notifyAll();  /*Telling all cooks that an order is available*/
			
			while(order.status<1) /*Wait till the order is ready to eat*/
			{
				
				OrderList.wait();
				
			}
							
		}
		
	}
	
	private void tryAndGetTable() throws InterruptedException
	{
		int freeTable=-1;
		
		
		synchronized(tableList)
		{
			
			while(true) 
			{
				addDinerToWaitList(); 
				/*Get out of the loop when in front of waiting queue and some table is empty */
			
				if(isSomeTableEmpty() && frontOfWaitingQueue()) 
				{
					break;
				}
				
				
				tableList.wait();
			
			}
			
			/*Assign table to Diner*/
			
			
			for(int j=0;j<tableList.size();j++) 
			{
				/*We're finding out whichever table is free here*/
				if(tableList.get(j).dinerID==-1) 
				{
					freeTable = j;
					break;
				}
				
			}
			
			/*Now we've set the Table to the waiting Diner. We now must release the lock on tableList */
		
			tableList.get(freeTable).dinerID = dinerID;
			tableID =tableList.get(freeTable).tableID;
			
			status = 1;
			
			
			synchronized(dinerWaitList) 
			{
				
				
				for(int k=0;k<dinerWaitList.size();k++)
				{
					if(dinerWaitList.get(k)==dinerID)
					{
						
						dinerWaitList.remove(k);
						
						break;
					}
				
				}
				
				
			}
			
			tableList.notifyAll(); /*Wakes up the first process in line that wants a table*/
			
			
		}
		
		
		
	}
	
	private void addDinerToWaitList() 
	{
		/*Adds a diner to the list only if the diner isn't already in the list*/
		
		int found = 0;
		
		synchronized(dinerWaitList) 
		{
			for(int j=0;j<dinerWaitList.size();j++)
			{
				if(dinerWaitList.get(j)==dinerID) 
				{
					
					found = 1;
					
				}
				
			}
			
			if(found==0)
			{
				dinerWaitList.add(dinerID);
			}
			
		}
		
	}
	
	private boolean frontOfWaitingQueue() 
	{
		int front = 0;
		synchronized(dinerWaitList) 
		{
			
			if(dinerWaitList.get(0)==dinerID) 
			{
				
				front = 1;
			}
			
		}
		
		if (front == 1)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	private boolean isSomeTableEmpty()
	{
		/* We don't used synchronized here since isSomeTableEmpty is called within a sycnhronized
		section with tableList synchronized */
		for(int i=0;i<tableList.size();i++) 
		{
			
			if(tableList.get(i).dinerID==-1) return true;
			
		}
		
		return false;
	}
	

}

class Cook implements Runnable

{
	ArrayList<Table> tableList;
	
	ArrayList<Order> OrderList ;

	int cookID;
	
	Order currentOrder;
	
	ArrayList<Machine> MachineList;
	
	
	
	
	int tableID = -1;
	int burgers=-1, fries = -1, cokes = -1; /* Jobs done in order burger > Fries > Coke */
	long [] timer;
	
	ArrayList<Integer> dinerList;
	
	/*Constructor for cook*/
	
	public Cook (int cookID,ArrayList<Table> tableList,ArrayList<Order> OrderList,ArrayList<Machine> MachineList, long[] timer,ArrayList<Integer> dinerList) 
	{
		
		this.tableList = tableList;
		this.OrderList = OrderList;
		this.MachineList = MachineList;
		this.cookID = cookID;
		this.timer = timer;
		this.dinerList = dinerList;
	}
	
	private boolean isMachineFree(int code)
	{
		
		synchronized(MachineList) 
		{
			
			if(MachineList.get(code).status==0) 
			{
				return true;
			}
			
			else 
			{
				return false;
			}
			
		}
		
		
	}
	
	public void run() 
	{
		/*The cook is now active. The cook will wait for orders and pick them up*/
		
		
		while(true)
		{
			
			try
	         {
				
				cook();
				
				
	         } 
			catch (Exception e)
	        
			{
				e.printStackTrace();
	         }
		}
		
	
		
		
	}
	
	private void cook() throws InterruptedException
	{
		
		synchronized(OrderList)
		{
			
			/*Cook waits for an order*/
			while(OrderList.isEmpty()) 
			{
				
				OrderList.wait();
				
				
			}
			
			currentOrder = OrderList.get(0);
			System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Cook "+cookID+" processes Diner "+currentOrder.dinerID+"'s order.");
			OrderList.remove(0); /*Order has been taken */
			
			currentOrder.status = 0; /*Cook working on it*/
			currentOrder.cookID = cookID;
			
			/*Now we set this cook's job parameters*/
			burgers = currentOrder.burgers;
			fries = currentOrder.fries;
			cokes = currentOrder.cokes;
		
			
		}
		
		
		/*Now that we have an order, we must check which machine is free, and then process the order.
		 * If no machine is free, then we wait till any machine is free. Else , we know  at-least one machine is free,
		 *  and check all machines one by one */
		
		
		while(true) 
		{	
			if(burgers==0 && cokes ==0 && fries ==0) 
			{
				
				break;
				
			} /*If all orders have been processed we get out of the loop*/
		
			synchronized(MachineList) 
			{
				
			
				
				while(MachineList.get(0).status == 1 && MachineList.get(1).status == 1 && MachineList.get(2).status == 1) 
				{
					/*All Machines are busy*/
					MachineList.wait();   
					
				}
				
			}
			
			/*Now we know that a machine may be free , we check the burger machine first, then the fries
			 * machine, and then the coke machine*/
			
			if(burgers>0)	
			{	
				
				if (isMachineFree(0)) /*Check if burger machine is free*/
				{
					
							int flag = 0;
							
							synchronized(MachineList)
							{
							
								if(MachineList.get(0).status == 0)  
								{
									flag =1;
									MachineList.get(0).status = 1;/* BurgerMachine is now busy */
								
								}
							}
							
							if(flag==1)
							{
								/*The Burger Machine is free. Now we cook our Burgers!*/
							
								
						
								while(burgers>0) 
								{
									System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Cook "+cookID+" uses the burger machine.");
									burgers --;
							
									Thread.sleep(5000);   /*The cook can operate on one machine at a time. Hence we
														simulate that by putting the Cook thread to sleep
									 						for 5000 milliseconds*/
							
							
								}
						
								synchronized(MachineList){MachineList.get(0).status = 0;}
						
								 /*Other cooks waiting for a Machine can now check*/
							}
						
					
					
					
				
					
				}
				
				synchronized(MachineList) 
				{
					MachineList.notifyAll();
				
				}
				
				
				
			}
			
			
			
			if(fries>0)
			{
				if (isMachineFree(1))	/*Check if fries machine is free*/
				{
						
					
						int flag = 0;
						
						synchronized(MachineList)
						{
						
							if(MachineList.get(1).status == 0)  
							{
								flag = 1;
								MachineList.get(1).status = 1; /* FriesMachine is now busy */
							
							}
						}
						
						if(flag==1)
						{
								/*The Fries Machine is free. Now we cook our Fries!*/
						
								
						
								while(fries>0) 
								{
									System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Cook "+cookID+" uses the fries machine.");
									fries --;
									Thread.sleep(3000);   /*The cook can operate on one machine at a time. Hence we
														simulate that by putting the Cook thread to sleep
									 						for 3000 milliseconds*/
							
								}
						
								
								synchronized(MachineList){MachineList.get(1).status = 0;}
						
								   /*Other cooks waiting for a Machine can now check*/
					
							}
				
						
					
					
					
				}
				synchronized(MachineList) 
				{
					MachineList.notifyAll();
				
				}
					
			}
			
			if(cokes>0)
			{
				if (isMachineFree(2)) 	/*Check if coke machine is free*/
				{
					
						
						int flag = 0;
						
						synchronized(MachineList)
						{
						
							if(MachineList.get(2).status == 0)  
							{
								flag = 1;
								MachineList.get(2).status = 1;/* FriesMachine is now busy */
							
							}
						}
						
						if(flag==1)
						{
								/*The Coke Machine is free. Now we get our Coke!*/
						
								
						
								while(cokes>0) 
								{
									System.out.println(Timer.getMinutes((System.currentTimeMillis() -timer[0]))+" Cook "+cookID+" uses the coke machine.");
									cokes --;
									Thread.sleep(1000);   /*The cook can operate on one machine at a time. Hence we
														simulate that by putting the Cook thread to sleep
									 						for 3000 milliseconds*/
							
								}
						
								
								synchronized(MachineList){MachineList.get(2).status = 0;}
						
								   /*Other cooks waiting for a Machine can now check*/
					
							}
				
						
					
					
					 
				}
				synchronized(MachineList) 
				{
					MachineList.notifyAll();
				
				}
					
			}
			
			
		
		}
		
		
		
		
		
		
		
		/*Now all orders have been processed. Time to let the Diner know that the food is ready! */
		
		currentOrder.status = 1; /*Order ready to eat*/
		
		synchronized(OrderList)
		{
			OrderList.notifyAll();
		} 	/* Notify Diner that order is ready to eat. Only the Diner with this order's status = 1
		 						*will wake up owing to our check in the while loop */
		
		
	}
	
}
	
	
	       
	

public class Project6431 {
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		
		/*Assuming 1 minute to be equivalent to 1000 milliseconds*/
		
		ArrayList<Table> SharedTableList = new ArrayList<Table>();
		ArrayList<Order> SharedOrderList = new ArrayList<Order>();
		ArrayList<Machine> SharedMachineList = new ArrayList<Machine>();
		ArrayList<Integer> SharedDinersWaitingList = new ArrayList<Integer>();
		ArrayList<Integer> dinerList = new ArrayList<Integer> ();
		
		SharedMachineList.add(new BurgerMachine());
		SharedMachineList.add(new FriesMachine());
		SharedMachineList.add(new CokeMachine());
		
		
		
		/*All three machines are free initially. Machine 0 is the Burger Machine. Machine 1 is the Fries Machine. Machine 2 is the Coke Machine*/
		
		
		
		long [] time = new long[1];
		time[0] = System.currentTimeMillis() ;
		
			
		File inFile = new File(args[0]);
		Scanner in = new Scanner(inFile);
		
		
		
		ArrayList<Diner> diners = new ArrayList<Diner>();
		int num_diners = in.nextInt();
		int num_tables = in.nextInt();
		int num_cooks = in.nextInt();
		
		/*Add tables*/
		
		for(int q=1; q<=num_tables;q++)
		{
			
			SharedTableList.add(new Table(q));
			
		}
		
		/*Start Threads for Cooks*/
		
		for(int j=1;j<=num_cooks;j++) 
		{
			
			Thread cook  = new Thread(new Cook(j,SharedTableList,SharedOrderList,SharedMachineList,time,dinerList),"Cook "+j) ;
			cook.start();
			
		}
		
		/*Get diner details and then start threads for diners*/
		in.nextLine();
		for(int i=1; i<=num_diners ; i++) 
		{
			
			String input = in.nextLine();
			String[] parts = input.split(",");
			int inTime = Integer.parseInt(parts[0]);
			int burgers = Integer.parseInt(parts[1]);
			int fries = Integer.parseInt(parts[2]);
			int cokes = Integer.parseInt(parts[3]);
			if(burgers<1 || cokes >=2 || inTime > 120) 
			{
				System.out.println("Invalid input. Exiting");
				System.exit(0);
				
			}
			Diner diner = new Diner(i,inTime,burgers,fries,cokes,SharedTableList,SharedOrderList,time,SharedDinersWaitingList,dinerList);
			diners.add(diner);
			dinerList.add(i);
			
			
		}
		

		/*Start diners*/
		for(int i=0;i<num_diners;i++) 
		{
			
			Diner diner = diners.get(i);
			Thread curr_diner = new Thread(diner);
			curr_diner.start();
			
		}
		
		
		
		
	}
	
	

}


