package edu.uoc.pac1;

public class PAC1Ex3 {

    private static final double[] CINEMA_MULTIPLIER_PER_SESSION = { 1.0, 1.1, 1.2, 1.4, 1.8, 2.5 }; // este vector indica el multiplicador de descuento que se aplica por comprar mas de una sesion
    private static final double[][] CINEMA_SEATS_PRICE = {
            { 4.00, 4.25, 4.50, 4.75, 5.00, 5.25, 5.50, 5.50, 5.25, 5.00, 4.75, 4.50, 4.25, 4.00 },
            { 4.25, 4.50, 4.75, 5.00, 5.25, 5.50, 5.75, 5.75, 5.50, 5.25, 5.00, 4.75, 4.50, 4.25 },
            { 4.50, 4.75, 5.00, 5.25, 5.50, 5.75, 6.00, 6.00, 5.75, 5.50, 5.25, 5.00, 4.75, 4.50 },
            { 4.75, 5.00, 5.25, 5.50, 5.75, 6.00, 6.25, 6.25, 6.00, 5.75, 5.50, 5.25, 5.00, 4.75 },
            { 5.00, 5.25, 5.50, 5.75, 6.00, 6.25, 6.50, 6.50, 6.25, 6.00, 5.75, 5.50, 5.25, 5.00 },
            { 5.25, 5.50, 5.75, 6.00, 6.25, 6.50, 6.75, 6.75, 6.50, 6.25, 6.00, 5.75, 5.50, 5.25 },
            { 5.50, 5.75, 6.00, 6.25, 6.50, 6.75, 7.00, 7.00, 6.75, 6.50, 6.25, 6.00, 5.75, 5.50 },
            { 5.50, 5.75, 6.00, 6.25, 6.50, 6.75, 7.00, 7.00, 6.75, 6.50, 6.25, 6.00, 5.75, 5.50 },
            { 5.25, 5.50, 5.75, 6.00, 6.25, 6.50, 6.75, 6.75, 6.50, 6.25, 6.00, 5.75, 5.50, 5.25 },
            { 5.00, 5.25, 5.50, 5.75, 6.00, 6.25, 6.50, 6.50, 6.25, 6.00, 5.75, 5.50, 5.25, 5.00 },
            { 4.75, 5.00, 5.25, 5.50, 5.75, 6.00, 6.25, 6.25, 6.00, 5.75, 5.50, 5.25, 5.00, 4.75 },
            { 4.50, 4.75, 5.00, 5.25, 5.50, 5.75, 6.00, 6.00, 5.75, 5.50, 5.25, 5.00, 4.75, 4.50 },
            { 4.25, 4.50, 4.75, 5.00, 5.25, 5.50, 5.75, 5.75, 5.50, 5.25, 5.00, 4.75, 4.50, 4.25 },
            { 4.00, 4.25, 4.50, 4.75, 5.00, 5.25, 5.50, 5.50, 5.25, 5.00, 4.75, 4.50, 4.25, 4.00 },
    };

    public static double twoDecimals(double number) {
        return Math.round(number * 100d) / 100d;
    }

    public static boolean isValidSeat(int x, int y) {
        return x >= 0 && x <= 13 && y >= 0 && y <= 13;
    }

    public static double calculatePrice(int[][] selectedSeats) {
        double totalPrice = 0;

        for (int i = 0; i < selectedSeats.length; i++){
                if (isValidSeat(selectedSeats[i][0]-1, selectedSeats[i][1]-1)) {
                    totalPrice += CINEMA_SEATS_PRICE[selectedSeats[i][0] -1][selectedSeats[i][1] -1];
                } else {
                    return -1.0;
                }
            }
        return totalPrice;
        }

    public static double calculateDiscount(double totalPrice, int numberOfSeats) {
        double totalDiscount = 0;
        double[] discounts = new double[]{.05, .1, .15};

        if (numberOfSeats >= 3 && numberOfSeats < 5) {
            totalDiscount += totalPrice * discounts[0];
        } else if (numberOfSeats >= 5 && numberOfSeats <= 9) {
            totalDiscount += totalPrice * discounts[1];
        } else if (numberOfSeats >= 10) {
            totalDiscount += totalPrice * discounts[2];
        }
        return twoDecimals(totalDiscount);
    }

    public static double calculateTotalPrice(int[][] selectedSeats, int session) {
        double costeTotal = 0;
        double priceWithDiscount;

        for (int i = 0; i < selectedSeats.length; i++) {
            if (isValidSeat(selectedSeats[i][0]-1, selectedSeats[i][1]-1)) {
                // precio con el descuento aplicado = precio de los asientos - descuento aplicado en funcion del numero de asientos
                priceWithDiscount = calculatePrice(selectedSeats) - calculateDiscount(calculatePrice(selectedSeats), selectedSeats.length);;
                // ahora podemos calcular el precio total de ir al cine aplicando la tarifa por sesion
                costeTotal = priceWithDiscount * CINEMA_MULTIPLIER_PER_SESSION[session - 1];
            } else {
                return -1.0;
            }
        }
        return twoDecimals(costeTotal);
    }

}
