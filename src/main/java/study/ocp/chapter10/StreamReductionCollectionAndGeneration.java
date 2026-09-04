package study.ocp.chapter10;

import java.util.*;
import java.util.stream.*;

class StreamReductionCollectionAndGeneration {
    public static void main(String... args) {
        // Stream<String> s2 = Stream.of("A","B","C","D","E","F");
        // int total = s2.reduce(0,(i,a)->i+a.length(),(i,j)->i+j);
        // System.out.println(total);

        // Stream<String> s1 = Stream.of("A","B","C","D","E","F");
        // String word1 = s1.collect(
        // 	String::new,
        // 	String::concat,
        // 	String::concat);
        // System.out.println(word1);

        // s1 = Stream.of("A","B","C","D","E","F");
        // word1 = s1.collect(
        // 	()->new StringBuilder(),
        // 	(sb,str)->sb.append(str),
        // 	(sb1,sb2)->sb1.append(sb2)
        // ).toString();
        // System.out.println(word1);

        // s1 = Stream.of("A","B","C","D","E","F");
        // StringBuilder word2 = s1.collect(
        // 	()->new StringBuilder(),
        // 	(sb,s)->sb.append(s),
        // 	(sb1,sb2)->sb1.append(sb2)
        // );
        // System.out.println(word2);

        // s1 = Stream.of("Aa","Bb","Cc","Dd","Ee","Ff");
        // Set<String> set = s1.collect(Collectors.toSet());
        // set.forEach(System.out::println);

        // s1 = Stream.of("Aa","Bb","Cc","Dd","Ee","Ff");
        // s1.map((s)->s.length()).forEach(System.out::println);

        Stream<Integer> s3 =
                Stream.generate(() -> ((Double) (Math.random() * 100000)).intValue())
                        .limit(1000000);
        // s3.filter(i->i%7==0).forEach(System.out::println);

        s3 = Stream.generate(() -> ((Double) (Math.random() * 1000)).intValue()).limit(100);
        System.out.println(
                s3.filter(i -> i % 7 == 0)
                        .filter(i -> i % 3 == 0)
                        .filter(i -> i % 13 == 0)
                        .findFirst());
    }
}
