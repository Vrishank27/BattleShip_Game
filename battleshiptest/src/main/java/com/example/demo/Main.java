// package com.example.demo;

// import java.util.*;

// public class Main {
//     public static void main(String [] args){
//         System.out.println("Welcome to the game of battleship");
//         int choice = 0;
//         Scanner sc = new Scanner(System.in);
//         while(choice != 1){
//             Play game = new Play(1, 0);
//             game.setupShips();
//             int c2 = 0;
//             System.out.println(game.getWinner());
//             while(game.getWinner() == 2){
//                 System.out.println("Enter 0 to execute attack, 1 to see ur board and 2 to see opponent's board 3 for hax");
//                 c2 = sc.nextInt();
//                 if(c2 == 0)
//                     game.executeAttacks();
//                 else if(c2 == 1){
//                     game.viewYourBoard();
//                 }
//                 else if(c2 == 2){
//                     game.viewOpponentBoard();
//                 }
//                 else if(c2 == 3){
//                     game.viewOpBoard();
//                 }
//                 else{
//                     System.out.println("Invalid option");
//                 }
//             }
//             if(game.getWinner() == 0){
//                 System.out.println("AI has won ");
//             }
//             else{
//                 System.out.println("You have won "); 
//             }
//             choice = sc.nextInt();
//         }

//     }
// }
