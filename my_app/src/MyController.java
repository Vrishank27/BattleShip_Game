import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MyController {
    @PostMapping("/api/calculate")
    public void calculate(@RequestBody List<Cell> selectedCells) {
        // Perform some operation on the selected cells
    }
}

class Cell {
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
