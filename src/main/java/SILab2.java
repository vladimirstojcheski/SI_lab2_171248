import java.util.ArrayList;
import java.util.List;

public class SILab2 {

    public List<String> function(List<String> list) {
        if (list.size() <= 0) {
            throw new IllegalArgumentException("List length should be greater than 0");
        }
        List<String> numMines = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("#")) {
                int num = 0;
                if (i - 1 >= 0 && list.get(i - 1).equals("#")) {
                    num++;
                }
                if (i + 1 < list.size() && list.get(i + 1).equals("#")) {
                    num++;
                }
                numMines.add(String.valueOf(num));
            } else {
                numMines.add(list.get(i));
            }
        }
        return numMines;
    }
}