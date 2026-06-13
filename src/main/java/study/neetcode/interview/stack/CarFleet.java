package study.neetcode.interview.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Car Fleet

You are given n cars going to the same destination.

You are given:

int target;
int[] position;
int[] speed;

Where:

position[i] = starting position of car i
speed[i]    = speed of car i
target      = destination position

A car can never pass another car ahead of it.

If a faster car catches up to a slower car before the destination, they become a fleet and move together at the slower car’s speed.

Return the number of car fleets that will arrive at the destination.

Example 1
target = 12
position = [10,8,0,5,3]
speed    = [2,4,1,1,3]

Output:

3

Explanation:

Car at 10 reaches target in 1 hour.
Car at 8 reaches target in 1 hour. It catches car at 10, so they form one fleet.

Car at 5 reaches target in 7 hours.

Car at 3 reaches target in 3 hours.
Car at 0 reaches target in 12 hours.

Final fleets = 3
Example 2
target = 10
position = [3]
speed = [3]

Output:

1
 */
public class CarFleet {

    private Map<Integer, Integer> sets;

    public int carFleet(int target, int[] position, int[] speed) {
        // create sets of fleet and their speed
        // remove fleets with 0 or negative times
        // remove fleets which have already greater than destination, update result
        // for fleets standing at the target merge them and increment result
        sets = new ConcurrentHashMap<>();

        for(int i=0;i<position.length;i++){
            int minSpeed = speed[i];
            if(minSpeed<=0) continue;
            if(sets.containsKey(position[i])){
                minSpeed = Math.min(sets.get(position[i]), speed[i]);
            }
            sets.put(position[i],minSpeed);
        }

        // run simulation of time
        // at each hour add the speed to the sets
        // if any set is at the same point then union the sets and update the sets Speed
        // count sets which are the destination or greater than destination, and update result
        int fleetsReachedDestination = 0;
        while (!sets.isEmpty()){
            for(int fleet : sets.keySet()){
                if(fleet>=target) {
                    fleetsReachedDestination++;
                    sets.remove(fleet);
                }
            }

            Map<Integer,Integer> updated = new ConcurrentHashMap<>();
            for(int fleet : sets.keySet()){
                int pos = fleet;
                int s = sets.get(pos);
                int newPos = pos + s;
                int minSpeed = s;
                if(updated.containsKey(newPos)){
                    minSpeed = Math.min(updated.get(newPos), minSpeed);
                }
                updated.put(newPos,minSpeed);
            }
            sets = updated;
        }
        return fleetsReachedDestination;
    }
}
