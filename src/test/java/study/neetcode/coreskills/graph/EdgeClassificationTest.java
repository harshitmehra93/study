package study.neetcode.coreskills.graph;

import org.junit.jupiter.api.BeforeEach;
import study.model.Graph;

public class EdgeClassificationTest {

    private Graph<Integer> graph;

    @BeforeEach
    void setup() {
        graph = new UndirectedGraph<Integer>();
    }
}
