package study.neetcode.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class PartitionLabelsTest {

    @Test
    void example1() {
        PartitionLabels solution = new PartitionLabels();

        String s = "ababcbacadefegdehijhklij";

        assertEquals(List.of(9, 7, 8), solution.partitionLabels(s));
    }

    @Test
    void singleCharacter() {
        PartitionLabels solution = new PartitionLabels();

        String s = "a";

        assertEquals(List.of(1), solution.partitionLabels(s));
    }

    @Test
    void allUniqueCharacters() {
        PartitionLabels solution = new PartitionLabels();

        String s = "abc";

        assertEquals(List.of(1, 1, 1), solution.partitionLabels(s));
    }

    @Test
    void allSameCharacters() {
        PartitionLabels solution = new PartitionLabels();

        String s = "aaaaa";

        assertEquals(List.of(5), solution.partitionLabels(s));
    }

    @Test
    void oneLargePartitionDueToRepeatedFirstCharacter() {
        PartitionLabels solution = new PartitionLabels();

        String s = "abac";

        assertEquals(List.of(3, 1), solution.partitionLabels(s));
    }

    @Test
    void partitionBoundaryGetsExtendedByInnerCharacter() {
        PartitionLabels solution = new PartitionLabels();

        String s = "abccaddbeffe";

        assertEquals(List.of(8, 4), solution.partitionLabels(s));
    }

    @Test
    void twoCleanPartitions() {
        PartitionLabels solution = new PartitionLabels();

        String s = "eccbbbbdec";

        assertEquals(List.of(10), solution.partitionLabels(s));
    }

    @Test
    void multipleSmallAndLargePartitions() {
        PartitionLabels solution = new PartitionLabels();

        String s = "caedbdedda";

        assertEquals(List.of(1, 9), solution.partitionLabels(s));
    }

    @Test
    void repeatedPairsCreateSeparatePartitions() {
        PartitionLabels solution = new PartitionLabels();

        String s = "aabbcc";

        assertEquals(List.of(2, 2, 2), solution.partitionLabels(s));
    }

    @Test
    void nestedCharacterRangesForceOnePartition() {
        PartitionLabels solution = new PartitionLabels();

        String s = "abcab";

        assertEquals(List.of(5), solution.partitionLabels(s));
    }
}
