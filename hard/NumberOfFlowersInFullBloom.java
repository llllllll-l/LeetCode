package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 2251
public class NumberOfFlowersInFullBloom {
    
    public static void main(String[] args) {

        int[][] flowers = {{1,6},{3,7},{9,12},{4,13}};
        int[] people = {2,3,7,11};


        int[] display = fullBloomFlowers(flowers, people);
        for (int i : display) {
            System.out.println(i);
        }
    }

    private static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] sortedPeople = Arrays.copyOf(people, people.length);
        Arrays.sort(sortedPeople);

        Arrays.sort(flowers, (a,b) -> Arrays.compare(a,b));
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue();

        int i = 0;

        for (int person : sortedPeople) {
            while (i < flowers.length && flowers[i][0] <= person) {
                heap.add(flowers[i][1]);
                i++;
            }

            while (!heap.isEmpty() && heap.peek() < person) {
                heap.remove();
            }

            map.put(person, heap.size());
        }
        
        int[] answer = new int[people.length];
        for (int j = 0; j < answer.length; j++) {
            answer[j] = map.get(people[j]);
        }
        return answer;
    }
}
