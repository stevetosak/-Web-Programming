package mk.finki.ukim.wp.lab.util;

import mk.finki.ukim.wp.lab.entity.Event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchQuery {
    //v3
    public static List<Event> joinH(List<List<Event>> rs) {
        long start = System.nanoTime();
        Set<Event> join = new HashSet<>(rs.get(0));
        for (int i = 1; i < rs.size(); i++) {
            Set<Event> currentSet = new HashSet<>(rs.get(i));
            if (join.isEmpty()) {
                join = new HashSet<>(currentSet);
            } else {
                join.retainAll(currentSet);
            }
        }

        long end = System.nanoTime();

        System.out.println("Time for search join: " + ((end - start) / (Math.pow(10,6))) + "ms");
        return new ArrayList<>(join);
    }


    //v2
    public static List<Event> joinL(List<List<Event>> rs) {
        List<Event> join = new ArrayList<>(rs.get(0));
        for (int i = 1; i < rs.size(); i++) {
            List<Event> currentSet = new ArrayList<>(rs.get(i));
            if (join.isEmpty()) {
                join = new ArrayList<>(currentSet);
            } else {
                join.retainAll(currentSet);
            }
        }
        return new ArrayList<>(join);
    }


    //v1

    //    @Override
//    public List<Event> dynamicSearch(String text, String rating) {
//        if ((text == null || text.isEmpty()) && (rating == null || rating.isEmpty())) {
//            return new ArrayList<>();
//        }
//
//
//        // ustvari ke ti dajt najdobar match so rabotite so gi imas dadeno; ne morat se da imas vneseno
//
//        List<List<Event>> rs = new ArrayList<>();
//        List<Event> join;
//        rs.add(searchEventsByText(text));
//        rs.add(searchEventsByRating(rating));
//
//        for (int i = 0; i < rs.size() - 1; i++) {
//            List<Event> curr = rs.get(i);
//            List<Event> next = rs.get(i + 1);
//
//            if (curr.isEmpty() && next.isEmpty()) {
//                continue;
//            }
//
//            join = new ArrayList<>(next);
//
//            if (curr.isEmpty()) {
//                join = new ArrayList<>(next);
//            } else if (next.isEmpty()) {
//                join = new ArrayList<>(curr);
//            } else {
//                join.retainAll(curr);
//            }
//
//            rs.set(i + 1, join);
//        }
//
//
//        return rs.get(rs.size() - 1);
//
//    }
}
