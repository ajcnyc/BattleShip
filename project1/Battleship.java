/*Alex Cohen
  2/15/18
  Project 1
  This program will play the board game battleship with the user*/

import java.awt.*;
import javax.swing.*;

public class Battleship{
    
    /**
     *Create the grid on which our game of Battleship will be played
     */
    
    public static void CreateGrid(){
	
	Paint.setColor(102,153,204);
	Paint.fillRect(0,0,5000,5000);
	
	for(int j=0; j<10; j++){
	    for(int i=0; i<15; i++){
		Paint.setColor(Color.BLACK);
		Paint.drawRect(50*i,50*j,50,50);
	    }
	}
    }
    
    /**
     *Places 1 ship square when the user fires and DOES hit a ship
     *
     *@param CursorX the x-position of the cursor 
     *@param CursorY the y-position of the cursor
     */
    
    public static void DrawAShip(int i, int j){
	
	//Draw Ship Square
	Paint.setColor(Color.GRAY);
	Paint.fillRect(2+50*i,2+50*j,47,47);
	
    }
    
    /**
     *Places 1 miss when the user fires and does NOT hit a ship
     *
     *@param CursorX the x-position of the cursor 
     *@param CursorY the y-position of the cursor
     */
    public static void DrawAMiss(int i, int j){
	
	//Draw Miss Marker
	{
	    Paint.setColor(Color.RED);
	    Paint.fillOval(5+50*i,5+50*j,40,40);
	}
	
	
    }
    
    /**
     *Paints over old cursor positions with background
     *
     *@param Ships An array 0-14 horizontal, and 0-9 vertical that holds
     *0 if there is no ship and 1 if there is one
     *@param Shots An array 0-14 horizontal, and 0-9 vertical that holds
     *0 if the user has not shot there, and 1 if he/she has
     */
    public static void ReDrawOldStuff(int[][] Ships, int [][] Shots){
	for(int j=0; j<=9; j++){
	    for(int i=0; i<=14; i++){
		if(Ships[i][j]==0){
		    if(Shots[i][j]==0){
			Paint.setColor(102,153,204);
			Paint.fillRect(50*i,50*j,50,50);
			Paint.setColor(Color.BLACK);
			Paint.drawRect(50*i,50*j,50,50);
		    }
		    
		    if(Shots[i][j]==1){
			
			DrawAMiss(i,j);
		    }}
		else if(Ships[i][j]==1){
		    if(Shots[i][j]==1){
			DrawAShip(i,j);
		    }
		}
	    }
	}
    }
    
    
    
    
    
    public static void main ( String[] args ){
	
	//SET UP THE GAME______________________________________________________
	
	//Build game window and grid
	Paint.buildWindow("Battleship",100,100,750,500,Color.BLACK);
	
	
	//Create array and store ship positions in it 
	int[][] Ships;
	Ships= new int[15][10];

	for(int b=0; b<10; b++){
	   for(int a=0; a<15; a++){
	       Ships[a][b]=0;
	    }
	}
		
	
	// set up the ships array (place ships)

	int[] ShipLengths= new int[5];
	for( int i=0; i<5; i++){
	    ShipLengths[i]=2+i;
	}
	
	// repeat for each ship
	for( int counter=0; counter<5; counter++){
	    
	    //   repeat until the current ship has been placed
	    //System.out.print("got here");
	    for(boolean shipPlaced=false; shipPlaced==false; ){
	    //     pick a random position and orientation
	    int istart=(int)(Math.random()*15);
	    int jstart=(int)(Math.random()*10);
	    int direction=(int)(Math.random()*2);
	    //     check for overlaps
	    int overlaps=0; //the number of overlaping squares
	    if(direction==0){
		if(ShipLengths[counter]+jstart>10){
		    continue;
		}
		    for(int q=0; q<ShipLengths[counter]; q++){

			//	System.out.print("hello"+jstart+" "+q);
			if(Ships[istart][jstart+q]==1){
			    overlaps++;
			}
		    }
	    }
	    else if(direction==1){
		if(ShipLengths[counter]+istart>15){
		    continue;
		}
		    for(int q=0; q<ShipLengths[counter]; q++){
			if(Ships[istart+q][jstart]==1){
			    overlaps++;
			}
		    }
	    }
	    //     if there are no overlaps, place the ship
	    // System.out.print("got here"+overlaps+" "+direction);
	    if(overlaps==0 && direction==0){
		for(int q=0; q<ShipLengths[counter]; q++){
		    Ships[istart][jstart+q]=1;
		}
		    shipPlaced=true;
	    
	    }
		else if(overlaps==0 && direction==1){
		    for(int q=0; q<ShipLengths[counter]; q++){
			//System.out.print("got here***");
		    Ships[istart+q][jstart]=1;
		    }
		    shipPlaced=true;
		
		}	    
	    }
	    }

	
	//Uncomment the loop below to see the placement of ships without
	//needing to shoot every square
	/*	for(int b=0; b<10; b++){
	   for(int a=0; a<15; a++){
	System.out.print(Ships[a][b]);
	    }
	   System.out.println();
	}
	*/
	
	//Create array and store shots in it
	int[][] Shots;
	Shots= new int[15][10];
	for(int j=0; j<=9; j++){
	    for(int i=0; i<=14; i++){
		Shots[i][j]=0;
	    }}
	
	//PLAY THE GAME________________________________________________________
	
	//Create yellow square cursor for current square and allow it to move
	//with arrow keys. Possibly make it blink on a timer for extra credit
	//make space bar or any other key fire
	
	int   CursorX=0;//x shift for yellow cursor
	int   CursorY=0;//y shift for yelow cursor
	
	CreateGrid();
	Paint.setColor(Color.YELLOW);
	Paint.fillRect(1+50* CursorX,1+50*CursorY,49,49);
	for(int z=0;true;z++){
	    
	    
	    int arrow =Paint.getArrow();
	    
	    if (arrow==Paint.OTHER){
		
		//Create array and store shots in it
		
		Shots[CursorX][CursorY]=1;
		
		ReDrawOldStuff(Ships, Shots);
		
	    }
	    
	    else if(arrow==Paint.LEFT && CursorX>0){
		CursorX--;
	    }
	    
	    else if(arrow==Paint.RIGHT && CursorX<14 ){
		CursorX++;
	    }
	    
	    else if(arrow==Paint.UP && CursorY>0 ){
		CursorY--;
	    }
	    
	    else if(arrow==Paint.DOWN && CursorY<9 ){
		CursorY++;  
	    }
	    
	    Paint.clear();
	    CreateGrid();
	    ReDrawOldStuff(Ships, Shots);
	    
	    Paint.setColor(Color.YELLOW);
	    Paint.fillRect(1+50* CursorX,1+50*CursorY,49,49);
	    if (Shots[CursorX][CursorY]==1){
		if (Ships[CursorX][CursorY]==0){ 
		    //Draw Miss Marker
		    Paint.setColor(Color.RED);
		    Paint.fillOval(5+50*CursorX,5+50*CursorY,40,40);
		}
		else{
		    //Draw Ship Square
		    Paint.setColor(Color.GRAY);
		    Paint.fillRect(2+50*CursorX,2+50*CursorY,47,47);
		}
	    }
	    int trueness=0;
	    for(int j=0; j<=9; j++){
	    for(int i=0; i<=14; i++){
		if(Ships[i][j]==1 && Shots[i][j]==1){
		    trueness++;
		}

		
		    
	    }}
	 
	    if(trueness==20){
		
		int totShots=0;
		for(int j=0; j<=9; j++){
	    for(int i=0; i<=14; i++){
		if(Shots[i][j]==1){
		    totShots++;
		}
		    
		}}
		Paint.setFont("SansSerif", Font.BOLD, 36);
		Paint.setColor(Color.WHITE);
		Paint.drawString("You won with only "+totShots+
				 " shots!!!", 50, 200);
		    break;
		    }
	}//END OF BIG FOR



    }
}

