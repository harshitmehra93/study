package study.coreskills.fenwick;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FenwickTreeTest {

    private FenwickTree ft;

    @BeforeEach
    void setup() {
        ft = new FenwickTree();
    }

    @Test
    void createTree_withNoElements() {
        ft = new FenwickTree();
        assertEquals(0, ft.getSize());
    }

    @Test
    void createTree_nullElements() {
        assertThrows(IllegalArgumentException.class,()->new FenwickTree(null));
    }

    @Test
    void createTree_withElements() {
        int[] arr = {1, 2, 3};
        FenwickTree ft = new FenwickTree(arr);
        assertEquals(3, ft.getSize());
    }

    @Test
    void createTree_modifyingOriginalArrayDoesNotAffectTree() {
        int[] arr = {1, 2, 3};
        FenwickTree ft = new FenwickTree(arr);
        assertEquals(3, ft.getSize());
        assertThat(ft.getArr()).isEqualTo(new int[]{1, 2, 3});

        arr[0]=5;

        assertThat(ft.getArr()).isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    void getNextOfZero_returnsMinusOne() {
        assertEquals(-1, ft.getNext(0));
    }

    @Test
    void getNextOfMinusOne_returnsMinusOne() {
        assertEquals(-1, ft.getNext(-1));
    }

    @Test
    void getNextIndexNotInTree_returnsMinusOne() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(-1, ft.getNext(4));
    }

    @Test
    void getNextOf1Is2() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(2, ft.getNext(1));
    }

    @Test
    void ifGetNextIsBiggerThanTreeReturnsMinus1() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(-1, ft.getNext(3));
    }

    @ParameterizedTest
    @CsvSource({
        "0,-1", "1,2", "2,4", "3,4", "4,8", "5,6", "6,8", "7,8", "8,-1", "9,10", "10,-1", "11,-1",
    })
    void getNext(int index, int expectedNext) {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(expectedNext, ft.getNext(index));
    }

    @Test
    void getParentOfZeroIsMinus1() {
        assertEquals(-1, ft.getParent(0));
    }

    @Test
    void getParentOfMinuxOneReturnsMinusOne() {
        assertEquals(-1, ft.getParent(-1));
    }

    @Test
    void getParentOfIndexOutsideTreeIsMinus1() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(-1, ft.getParent(4));
    }

    @Test
    void getParentOf1IsZero() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(0, ft.getParent(1));
    }

    @Test
    void getParentOf3Is2() {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3});
        assertEquals(2, ft.getParent(3));
    }

    @ParameterizedTest
    @CsvSource({
        "0,-1", "1,0", "2,0", "3,2", "4,0", "5,4", "6,4", "7,6", "8,0", "9,8", "10,8", "11,-1",
    })
    void getParentTests(int index, int expectedParent) {
        FenwickTree ft = new FenwickTree(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(expectedParent, ft.getParent(index));
    }

    @Test
    void buildFenwickTree_singleElement() {
        int[] arr = {1};
        ft = new FenwickTree(arr);

        int[] fenwickTree = ft.getFenwickTree();
        assertThat(fenwickTree).hasSize(2);
        assertThat(fenwickTree).isEqualTo(new int[] {0, 1});
    }

    @Test
    void buildFenwickTree_twoElements() {
        int[] arr = {1, 2};
        ft = new FenwickTree(arr);

        int[] fenwickTree = ft.getFenwickTree();
        assertThat(fenwickTree).hasSize(3);
        assertThat(fenwickTree).isEqualTo(new int[] {0, 1, 3});
    }

    @Test
    void buildFenwickTree_10Elements() {
        int[] arr = {5, -1, 8, 2, 7, 1, 3, -2, 4, 9};
        ft = new FenwickTree(arr);

        int[] fenwickTree = ft.getFenwickTree();
        assertThat(fenwickTree).hasSize(11);
        assertThat(fenwickTree).isEqualTo(new int[] {0, 5, 4, 8, 14, 7, 8, 3, 23, 4, 13});
    }

    @Test
    void getPrefixSum_noElements() {
        assertThrows(IllegalArgumentException.class, () -> ft.getPrefixSum(0));
    }

    @Test
    void getPrefixSum_invalid() {
        assertThrows(IllegalArgumentException.class, () -> ft.getPrefixSum(-1));
    }

    @Test
    void getPrefixSum_singleElement() {
        ft = new FenwickTree(new int[] {1});

        assertThat(ft.getSize()).isEqualTo(1);
        assertThat(ft.getPrefixSum(0)).isEqualTo(1);
    }

    @Test
    void getPrefixSum_twoElement() {
        ft = new FenwickTree(new int[] {1, 2});

        assertThat(ft.getSize()).isEqualTo(2);
        assertThat(ft.getPrefixSum(0)).isEqualTo(1);
        assertThat(ft.getPrefixSum(1)).isEqualTo(3);
        assertThrows(IllegalArgumentException.class, () -> ft.getPrefixSum(2));
    }

    @Test
    void getPrefixSum_threeElement() {
        ft = new FenwickTree(new int[] {1, 2, 3});

        assertThat(ft.getSize()).isEqualTo(3);
        assertThat(ft.getPrefixSum(0)).isEqualTo(1);
        assertThat(ft.getPrefixSum(1)).isEqualTo(3);
        assertThat(ft.getPrefixSum(2)).isEqualTo(6);
        assertThrows(IllegalArgumentException.class, () -> ft.getPrefixSum(3));
    }

    @Test
    void prefixSum_10Elements() {
        int[] arr = {5, -1, 8, 2, 7, 1, 3, -2, 4, 9};
        ft = new FenwickTree(arr);

        int[] fenwickTree = ft.getFenwickTree();
        assertThat(fenwickTree).hasSize(11);
        assertThat(fenwickTree).isEqualTo(new int[] {0, 5, 4, 8, 14, 7, 8, 3, 23, 4, 13});
        assertThat(ft.getPrefixSum(0)).isEqualTo(5);
        assertThat(ft.getPrefixSum(1)).isEqualTo(4);
        assertThat(ft.getPrefixSum(2)).isEqualTo(12);
        assertThat(ft.getPrefixSum(3)).isEqualTo(14);
        assertThat(ft.getPrefixSum(4)).isEqualTo(21);
        assertThat(ft.getPrefixSum(5)).isEqualTo(22);
        assertThat(ft.getPrefixSum(6)).isEqualTo(25);
        assertThat(ft.getPrefixSum(7)).isEqualTo(23);
        assertThat(ft.getPrefixSum(8)).isEqualTo(27);
        assertThat(ft.getPrefixSum(9)).isEqualTo(36);
        assertThrows(IllegalArgumentException.class, () -> ft.getPrefixSum(10));
    }

    @Test
    void updateIndex_noElements() {
        assertThrows(IllegalArgumentException.class, () -> ft.updateIndex(0, 1));
    }

    @Test
    void updateIndex_indexNotAvailable() {
        ft = new FenwickTree(new int[] {1, 2, 3});
        assertThrows(IllegalArgumentException.class, () -> ft.updateIndex(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> ft.updateIndex(3, 1));
    }

    @Test
    void updateIndex_singleElement() {
        int[] arr = {1};
        ft = new FenwickTree(arr);

        assertThat(ft.getSize()).isEqualTo(1);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 1});
        assertThat(ft.getPrefixSum(0)).isEqualTo(1);

        ft.updateIndex(0, 2);

        assertThat(ft.getSize()).isEqualTo(1);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 2});
        assertThat(ft.getPrefixSum(0)).isEqualTo(2);
    }

    @Test
    void updateIndex_twoElements() {
        int[] arr = {1, 2};
        ft = new FenwickTree(arr);

        assertThat(ft.getSize()).isEqualTo(2);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 1, 3});
        assertThat(ft.getPrefixSum(0)).isEqualTo(1);
        assertThat(ft.getPrefixSum(1)).isEqualTo(3);

        ft.updateIndex(0, 2);

        assertThat(ft.getSize()).isEqualTo(2);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 2, 4});
        assertThat(ft.getPrefixSum(0)).isEqualTo(2);
        assertThat(ft.getPrefixSum(1)).isEqualTo(4);
        assertThat(ft.getArr()).isEqualTo(new int[]{2,2});

        ft.updateIndex(1, 5);

        assertThat(ft.getSize()).isEqualTo(2);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 2, 7});
        assertThat(ft.getPrefixSum(0)).isEqualTo(2);
        assertThat(ft.getPrefixSum(1)).isEqualTo(7);
        assertThat(ft.getArr()).isEqualTo(new int[]{2,5});

        assertThrows(IllegalArgumentException.class, () -> ft.updateIndex(2, 1));

        ft.updateIndex(0, 10);

        assertThat(ft.getSize()).isEqualTo(2);
        assertThat(ft.getFenwickTree()).isEqualTo(new int[] {0, 10, 15});
        assertThat(ft.getPrefixSum(0)).isEqualTo(10);
        assertThat(ft.getPrefixSum(1)).isEqualTo(15);
        assertThat(ft.getArr()).isEqualTo(new int[]{10,5});
    }
}
