import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

//import org.javatuples.Triplet;

//Task N 15
/* Виявити, чи допускає (сприймає) скінчений автомат [скінчені] слова довільної
(будь-якої) непарної довжини (?(k?{1,3,5,7,9,…}) ?(w?A*: |w|=k) : A(w)=true,
тобто для будь-якого натурального k існує деяке слово w довжини k (яке складається
з літер вхідного алфавіту A автомата A) таке, що автомат A сприймає це слово).*/


public class SP15_Lab2 {
    int beginState;
    int numFinalStates;

    Map<Integer, Map<Character, Set<Integer>>> transitions;
    Set<Integer> finalStates;

    private void readData(String path) {
        try {
            BufferedReader input = new BufferedReader(new FileReader("src\\" + path));

            System.out.println("Enter the begin state");
            try {
                beginState = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!(1)");
            }

            int n = 0;
            System.out.println("Enter the number of final states");
            try {
                n = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!(2)");
            }

            finalStates = new HashSet();
            System.out.println("Enter N final states");
            for (int i = 0; i < n; i++) {
                try {
                    int final_state = Integer.parseInt(input.readLine());
                    finalStates.add(final_state);

                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid Format!(3)");
                }
            }
            int m = 0;
            System.out.println("Enter the number of transations");
            try {
                m = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!(4)");
            }

            transitions = new HashMap<>();
            System.out.println("Enter M transitions");
            for (int i = 0; i < m; i++) {
                try {
                    System.out.println("Enter state, transition_letter, next_state");
                    int state = Integer.parseInt(input.readLine());
                    Character transition_letter = input.readLine().charAt(0);
                    int next_state = Integer.parseInt(input.readLine());
                    if (!this.transitions.containsKey(state)) {
                        this.transitions.put(state,
                                new HashMap<Character, Set<Integer>>());
                    }
                    Map<Character, Set<Integer>> state_transition =
                            this.transitions.get(state);

                    if (!state_transition.containsKey(transition_letter)) {
                        state_transition.put(transition_letter,
                                new HashSet<Integer>());
                    }
                    state_transition.get(transition_letter).
                            add(next_state);
                    //transitions.add(new Triple(state, transition_letter, next_state));
                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid Format!(5)");
                }
            }
        } catch (
                Exception e
                )

        {
            System.out.println(e.toString());
        }

    }

    public boolean check() {
        HashSet<Integer> prev_states = new HashSet();
        HashSet<Integer> current_states = new HashSet();
        HashSet<HashSet<Integer>> used_set_states = new HashSet();
        current_states.add(this.beginState);

        while (!used_set_states.contains(current_states)) {
            used_set_states.add(current_states);
            prev_states = current_states;
            current_states = new HashSet();

            for (Integer state : prev_states) {
                if (this.transitions.containsKey(state)) {
                    for (Set<Integer> values_set : this.transitions.get(state).values()) {
                        current_states.addAll(values_set);
                    }
                }
            }

            boolean is_final_state = false;
            for (Integer state : current_states) {
                if (this.finalStates.contains(state)) {
                    is_final_state = true;
                }
            }

            if (!is_final_state) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SP15_Lab2 program = new SP15_Lab2();
        program.readData("input.txt");
        System.out.println(program.check());
        System.out.println("done");
    }

    static class Triple<T, S, E> {
        public final T first;
        public final S second;
        public final E third;

        public Triple(T first, S second, E third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
}
