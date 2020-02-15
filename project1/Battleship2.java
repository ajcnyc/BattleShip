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
 
 //randomly decide ship positions with NO OVERLAP [[[  CURRENT__CURRENT]]]
 
 
 
 
 int[] ShipLengths= new int[5];
 for( int i=0; i<=4; i++){
     ShipLengths[i]=2+i;
 }

 for( int counter=0; counter<5; counter++){
     //choose starting square
     int istart=(int)(Math.random()*15);
     int jstart=(int)(Math.random()*10);
     System.out.println(istart+"i "+jstart+"j");
     
     if(Ships[istart][jstart]==1){
  counter--;
  continue;
     }
     //choose direction
     int urdl; //0 for up, 1 for right, 2 for down, 3 for left, clockwise
     
     
     
     for(;true;){
  urdl=(int)(Math.random()*4);
  System.out.println(urdl);
  
  if(urdl==0){
      if(jstart-(ShipLengths[counter]-1)>=0){
   for(int z=0; z<ShipLengths[counter]; z++){
       if(Ships[istart][(jstart-z)]==1){
    for(int y=jstart-z; y<=jstart;z++){
        DrawAMiss(istart,(jstart+z));
        counter--;
        
        break;
    }
    Ships[istart][(jstart-z)]=1;
       }
       break;
   }
      }
  }
  else if(urdl==1){
  if(istart+(ShipLengths[counter]-1)<=14){
      for(int z=0; z<ShipLengths[counter]; z++){
   if(Ships[(istart+z)][jstart]==1){
       for(int y=istart+z; y>=istart;z--){
    DrawAMiss((istart-z),jstart);
    counter--;
    break;
       }
   }
   Ships[(istart+z)][jstart]=1;
      }
      break;
  }
  }

  else if(urdl==2){
      if(jstart+(ShipLengths[counter]-1)<=9){
   for(int z=0; z<ShipLengths[counter]; z++){
       if(Ships[istart][(jstart+z)]==1){
    for(int y=jstart+z; y>=jstart;z--){
        DrawAMiss(istart,(jstart-z));
        counter--;
        break;
    }
       }
       Ships[istart][(jstart+z)]=1;
   }
   break;
      }
  }
  else if(urdl==3){
      if(istart-(ShipLengths[counter]-1)>=0){
   for(int z=0; z<ShipLengths[counter]; z++){
       if(Ships[(istart-z)][jstart]==1){
    for(int y=istart-z; y<=istart;z++){
        DrawAMiss(istart,(jstart));
        counter--;
        break;
    }
       }
       Ships[(istart-z)][jstart]=1;
   }
   break;
      }
  }
  
     }
     
     
     
 }

 for(int b=0; b<10; b++){
    for(int a=0; a<15; a++){
 System.out.print(Ships[a][b]);
     }
    System.out.println();
 }
 
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
  System.out.println(CursorX+" "+CursorY);
  
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
 }//END OF BIG FOR



    }
}

