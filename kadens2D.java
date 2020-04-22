import java.util.*;
import java.lang.*;
class recFirst
{ 
  
	public Rectangle Kadens2D(int [][]matrix)
	{
		int rows=matrix.length;
		int cols=matrix[0].length;
		
		Rectangle rec=new Rectangle();
		
		int runningMatrix[]=new int[rows];
		
		for(int left=0;left<cols;left++)
		{
			for(int j=0;j<rows;j++)
				runningMatrix[j]=0;
			
			for(int right=left;right<cols;right++)
			{
				for(int i=0;i<rows;i++)
				{
					runningMatrix[i]+=matrix[i][right];
				}
				
				kadenResult result = kaden1D(runningMatrix);
				
				
				if(result.maxSum>rec.interiorSum)
				{
					rec.interiorSum =result.maxSum;
					rec.leftBorder=left;
					rec.rightBorder=right;
					rec.topBorder=result.startIndex;
					rec.bottomBorder=result.endIndex;
				}
			}
		}
		return rec;
	}
	
	
	public kadenResult kaden1D(int []a)
	{
		int maxSoFar=0;
		int bestMaxAtThisIndex=0;
		
		int startIndex=-1;
		int endIndex=-1;
		int current=0;
		
		for(int i=0;i<a.length;i++)
		{
			
			bestMaxAtThisIndex+=a[i];
			
			if(bestMaxAtThisIndex<0)
			{
				current=i+1;
				bestMaxAtThisIndex=0;
			}
			if(bestMaxAtThisIndex>maxSoFar)
			{
				maxSoFar=bestMaxAtThisIndex;
				startIndex=current;
				endIndex=i;
			}
			
		}
		
		return new kadenResult(maxSoFar,startIndex,endIndex);
	}
	
}
class Rectangle
{
	int interiorSum;
	int leftBorder;
	int rightBorder;
	int topBorder;
	int bottomBorder;
}

class kadenResult
{
	int maxSum;
	int startIndex;
	int endIndex;
	
	kadenResult(int mS,int sI,int eI)
	{
		maxSum=mS;
		startIndex=sI;
		endIndex=eI;
	}
}

public class kadens2D
{
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int row=sc.nextInt();
		int col=sc.nextInt();
		int [][]matrix=new int[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				matrix[i][j]=sc.nextInt();
		recFirst m=new recFirst();
	    Rectangle r=m.Kadens2D(matrix);
	    
	    for(int i=r.topBorder;i<=r.bottomBorder;i++)
	    {
	    	for(int j=r.leftBorder;j<=r.rightBorder;j++)
	    	{
	    		System.out.println(matrix[i][j]+" ");
	    	}
	    }
	    System.out.println();
	    System.out.println("sum="+r.interiorSum);
	    
	    
	    
	}
}