import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static String[] states = {};
    static int[][] mat;
    static List<Integer> index_list = new ArrayList<>();//list of final states indexes in states array
    static String[] alphabet = {};
    static String initial_state = "";
    static String[] finale_states = {};

    static List<List<Integer>> pairs = new ArrayList<>();

    static HashMap<String, List<Integer>> a = new HashMap<>();
    public static void main(String[] args) {
        Input();
        Init_HashMap();
        Mat_Matrix_Innit();
        Index_List_init();
        Index_List_func();
        RecursiveStaff();
        PairFinder();
        TransitiveProperty();
        scanner.close();
    }
    public static void TransitiveProperty(){
        int i = 0;
        int j = 0;
        boolean flag = true;
        while (flag){
            i = 0;
            j = 0;
            flag = false;
            while (i < pairs.size()) {
                j = 0;
                System.out.print("\npairs on iter " + i + "\n" + pairs);
                while(j < pairs.size()) {
                    if (i != j && intersection(pairs.get(i), pairs.get(j)).size() > 0) {
                        var temp1 = union(pairs.get(i), pairs.get(j));
                        pairs.remove(i);
                        if (i > j) {
                            pairs.remove(j);
                        } else {
                            pairs.remove(j - 1);
                        }
                        pairs.add(temp1);
                        flag = true;
                    }
                    j++;
                }
                i++;
            }
        }
        System.out.print("\nNEW PAIRS:\n");
        System.out.print(pairs);
    }
    public static void PairFinder(){
        for(int i = 0; i < states.length-1; i++){
            for(int j = 0; j < i+1; j++){
                if(mat[i][j] == 0){
                    var a = min(Integer.parseInt(states[i + 1]), Integer.parseInt(states[j]));
                    var b = max(Integer.parseInt(states[i + 1]), Integer.parseInt(states[j]));
                    List<Integer> temp = new ArrayList<>();
                    temp.add(a);
                    temp.add(b);
                    pairs.add(temp);
                }
            }
        }
        //System.out.print(pairs);
    }
//    private static Set<Integer> intersection(List<Integer> a, List<Integer> b){
//        Set<Integer> result = a.stream().distinct().filter(b::contains).collect(Collectors.toSet());
//        return result;
//    }
    static public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    static public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    public static void RecursiveStaff(){
        boolean flag = true;
        while(flag){
            boolean flag3 = true;
            for(int i = 0; i < states.length-1; i++){
                for(int j = 0; j < i+1; j++){
                    if (mat[i][j] == 0){
                        var r = states[i+1];
                        var c = states[j];
                        System.out.print("r:\n" + r + "\nc:\n"+c);
                        int t = 0;
                        boolean flag2 = true;
                        while (t < alphabet.length && flag2){

                            if(a.get(r).get(t) != a.get(c).get(t)){
                                var r1 = Arrays.asList(states).indexOf(a.get(r).get(t)) - 1;
                                var c1 = Arrays.asList(states).indexOf(a.get(c).get(t));
                                if(c1 == states.length - 1 || mat[r1][c1] == -1 || r1 == -1 || c1 == -1){
                                    var temp = c1;
                                    c1 = r1 + 1;
                                    r1 = temp - 1;
                                }
                                if(mat[r1][c1] == 1){
                                    mat[i][j] = 1;
                                    flag3 = false;
                                    flag2 = false;
                                }
                            }
                            t++;
                        }
                    }
                }
            }
            if(flag3){
                flag = false;
            }
        }
        //PrintMat();
    }
    private static void PrintMat(){
        System.out.print("\n");
        for(int a = 0; a < states.length-1; a++) {
            for (int b = 0; b < states.length - 1; b++) {
                System.out.print(mat[a][b] + " ");
            }
            System.out.print("\n");
        }
    }
    public static void Index_List_func(){
        for (var i : index_list){
            for (int j = 0; j < states.length-1; j++){
                for (int k = 0; k < j+1; k++){
                    if (i - 1 == j && !index_list.contains(k)){
                        mat[j][k] = 1;
                    } else if (i == k && !index_list.contains(j+1)) {
                        mat[j][k] = 1;
                    }
                }
            }
        }
        PrintMat();
    }
    public static void Index_List_init() {
        for(int i = 0; i < states.length; i++){
            if (Arrays.asList(finale_states).contains(states[i])){
                index_list.add(i);
            }
        }
        //System.out.print(index_list);
    }

    public static void Mat_Matrix_Innit(){
        int length = states.length;
        mat = new int[length-1][length-1];
        int j = 0;
        for(int i = 0; i < length-1; i++){ //[[0, -1, -1, -1], [0, 0, -1, -1], [0, 0, 0, -1], [0, 0, 0, 0]]
            for(; j < i+1; j++){
                mat[i][j] = 0;
            }
            for(int k = j; k < length-1; k++){
                mat[i][k] = -1;
            }
            j = 0;
        }

//        for(int a = 0; a < length-1; a++){
//            for(int b = 0; b < length-1; b++){
//                System.out.print(mat[a][b] + " ");
//            }
//            System.out.print("\n");
    }
    public static void Init_HashMap(){
        for (var ele : states){
            List<Integer> aboba = new ArrayList<>();
            a.put(ele, aboba);
            //aboba.add(1);
        }
        System.out.print("a:\n");
        System.out.print(a);
        System.out.print("\nEnter transitions:\n");
        for (var ele : states){
            for (var alp : alphabet){
                System.out.print("\n" + ele + " on " + alp + " -> ");
                boolean flag = true;
                while(flag){
                    String b = scanner.nextLine();;
                    if (Arrays.asList(states).contains(b)){
                        a.get(ele).add(Integer.parseInt(b));
                        flag = false;
                    }
                    else{
                        System.out.print("Enter valid state:\n");
                    }
                }
            }
        }
    }
    public static void Input() {
        System.out.print("Enter the state values:\n");
        String states_not_splitted = scanner.nextLine();
        states = states_not_splitted.split(" ");
//        for (var a : states){
//            System.out.print(a + "\n");
//        }
        System.out.print("Enter alphabet:\n");
        String alphabet_not_splitted = scanner.nextLine();
        alphabet = alphabet_not_splitted.split(" ");
//        for (var a : states){
//            System.out.print(a + "\n");
//        }
        System.out.print("Enter the initial state:\n");
        initial_state = scanner.nextLine();

        System.out.print("Enter final state(s):\n");
        String final_states_not_splitted = scanner.nextLine();
        finale_states = final_states_not_splitted.split(" ");
    }
}