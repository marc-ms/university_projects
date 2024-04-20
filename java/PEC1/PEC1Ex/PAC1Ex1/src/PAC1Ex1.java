import java.util.Scanner;

public class PAC1Ex1 {

    private static long motzkin(int n, long motzkinNMinus1, long motzkinNMinus2) { //metodo que define numeros de motzkin
        // Base cases
        if (n == 0 || n == 1) return 1;

        return ((((2 * n + 1) * motzkinNMinus1 + (3 * n - 3) * motzkinNMinus2)) / (n + 2));
    }

    public static long printMotzkin(int n) { // metodo que muestra por pantalla los n+1 primeros numeros de Motzkin
        long motzkin, motzkinNMinus1 = 1, motzkinNMinus2 = 1;

        for (int i = 0; i <= n; i++) {
            motzkin = motzkin(i, motzkinNMinus1, motzkinNMinus2);
            System.out.print(motzkin + ", ");

            motzkinNMinus2 = motzkinNMinus1;
            motzkinNMinus1 = motzkin;
        }

        return motzkinNMinus1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;

        do {
            System.out.print("Enter the value of N (only whole numbers): ");
            n = in.nextInt();

            if (n == 0) {
                System.out.println("The value of N must be a whole number. Try again.");
            }
        } while (n == 0);

        printMotzkin(n); // ejecuta el metodo
    }
}
