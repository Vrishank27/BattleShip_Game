package com.example.demo;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:3000")
public class Play {
    @Autowired
    private UserRepository userRepository;
    private int uid1;
    private int uid2;
    private int ships_p1;
    private int ships_p2;
    private Player p1;
    private Player p2;

    public Play(){
        this.p1 = new HumanPlayer();
        p2 = new AiPlayer();
        this.ships_p1 = 5;
        this.ships_p2 = 5;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/test1")
    public void handleCoordinates(@RequestBody List<Map<String, Object>> coordinates) {
        for (Map<String, Object> ship : coordinates) {
          Integer row = (Integer) ship.get("row");
          Integer col = (Integer) ship.get("col");
          String dirString = (String) ship.get("direction");
          int shipId = (Integer)ship.get("id");
          Boolean dir;
          System.out.println(ship.get("id").getClass().getSimpleName());
          if("1".equals(dirString)){
            dir = true;
          }
          else{
            dir = false;
          }
            if(p1.placeShips(col, row, shipId, dir) == true){
                System.out.println("The ship has been placed \n");
                this.ships_p1--;
            }
          
        System.out.println("Coord : " + row + " " + col + " " + dir + " " + (Integer)ship.get("id"));
        }
        this.viewYourBoard();
        this.ships_p1 = 5;

        p2.placeShips();

    }

    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/test2")
    public Map<String, Boolean> executeAttacks(@RequestBody Coordinate coordinate){
        int x = -1;
        int y = -1;
        System.out.println("SHIPS ARE " + p2.getShips() + " " + p1.getShips());
        //System.out.println(coordinate.getX() + " " + coordinate.getY());
        Map<Coordinate , Boolean> res1 = p1.execAttack(coordinate.getY(), coordinate.getX(), p2.getBoardClass());
        Map<Coordinate , Boolean> result = p2.execAttack(x, y, p1.getBoardClass());
        String retval = new String();
        Boolean truthval = false;
        for(Coordinate c : result.keySet()){
            retval+= Integer.toString(c.getX());
            retval+= Integer.toString(c.getY());
        }
        for(Coordinate c : res1.keySet()){
            truthval = res1.get(c);
        }
        //p2.execAttack(x, y, p1.getBoardClass());
        //this.viewOpBoard();
        Map<String , Boolean> r = new HashMap<String , Boolean>();
        r.put(retval, truthval);
        this.viewYourBoard();
        p1.reduceShips();
        p2.reduceShips();
        return r;
    }
    @CrossOrigin(origins = "http://localhost:3000/login")
    @PostMapping("/test3")
    public void addUser(@RequestBody Map<String, String> usn_pwd) {
        String password = usn_pwd.get("password");
        String username = usn_pwd.get("username");
        // User user = User.getInstance(username, password);
        User user = new User(username, password);
        userRepository.save(user);
        // BattleshiptestApplication.restart();
    }
    @CrossOrigin(origins = "http://localhost:3000/login")
    @PostMapping("/test4")
    public User getUser(@RequestBody Map<String, String> usn_pwd) {
        String username = usn_pwd.get("username");
        String password = usn_pwd.get("password");
        return userRepository.findByUsernameAndPassword(username, password);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/resack")
    public void restartGame(){
        BattleshiptestApplication.restart();
    }
  
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/result")
    public int getWinner(){
        if(p1.getShips() == 0){
            return 0;
        }
        else if(p2.getShips() == 0){
            return 1;
        }
        if(this.ships_p1 > p1.getShips()){
            ships_p1 = p1.getShips();
            return 3;
        }
        if(this.ships_p2 > p2.getShips()){
            ships_p2 = p2.getShips();
            return 4;
        }
        return 2;
    }



    

    public void viewYourBoard(){
        char[][] myBoard  = p1.getBoard();
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                System.out.print(myBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void viewOpBoard(){
        char[][] myBoard  = p2.getBoard();
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                System.out.print(myBoard[i][j]+" ");
            }
            System.out.println();
        }

    }

    public void viewOpponentBoard(){
        char [][] opBoard = p1.getOpBoardView();
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                System.out.print(opBoard[i][j]+" ");
            }
            System.out.println();
        }
    }
}
