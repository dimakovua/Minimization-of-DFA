import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static String[] states = {};
    static int[][] mat;
    static List<Integer> index_list = new ArrayList<>();//list of final states indexes in states array
    static String[] alphabet = {};
    static String initial_state = "";
    static String[] finale_states = {};

    static HashMap<String, String> a = new HashMap<>();
    public static void main(String[] args) {
        Input();
        Init_HashMap();
        Mat_Matrix_Innit();
        Index_List_init();
        scanner.close();
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
            a.put(ele, "");
        }
        System.out.print("\nEnter transitions:\n");
        for (var ele : states){
            for (var alp : alphabet){
                System.out.print("\n" + ele + " on " + alp + " -> ");
                boolean flag = true;
                while(flag){
                    String b = scanner.nextLine();;
                    if (Arrays.asList(states).contains(b)){
                        a.put(ele, b);
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