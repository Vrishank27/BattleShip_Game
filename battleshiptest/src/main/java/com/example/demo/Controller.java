// package com.example.demo;

// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/test")
// public class Controller {
//     @CrossOrigin(origins = "http://localhost:3000")
//     @PostMapping("/test1")
//     public void handleCoordinates(@RequestBody List<Map<String, Object>> coordinates) {
//         for (Map<String, Object> ship : coordinates) {
//           Integer row = (Integer) ship.get("row");
//           Integer col = (Integer) ship.get("col");
//           String dirString = (String) ship.get("direction");
//           Boolean dir;
//           System.out.println(dirString.getClass().getSimpleName());
//           if("1".equals(dirString)){
//             dir = false;
//           }
//           else{
//             dir = true;
//           }
//           System.out.println("Coord : " + row + " " + col + " " + dir);
//         }
        
    
// }
// }
