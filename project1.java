import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;



public class project1 {

    public static void main(String[] args) {

        if (args.length != 1) {

            System.err.println("Usage: java project1 <input_file>");

            System.exit(1);

        }



        String filename = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            double[] prices = new double[100000]; // Assuming maximum of 100000 entries

            int i = 0;

            while ((line = reader.readLine()) != null) {

                if (!line.isEmpty()) {

                    prices[i++] = Double.parseDouble(line);

                }

            }

            double maxProfit = findMaxProfit(prices);

            System.out.printf("The optimal solution for %s is %.2f.\n", filename, maxProfit);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (NumberFormatException e) {

            System.err.println("Error: Invalid number format in the input file.");

            e.printStackTrace();

        }

    }



    public static double findMaxProfit(double[] prices) {

        return maxProfitHelper(prices, 0, prices.length - 1);

    }



    private static double maxProfitHelper(double[] prices, int start, int end) {

        if (end <= start) {

            return 0; // Base case: If array has <= 1 number, no feasible solution

        }



        int mid = (start + end) / 2;



        // Find the maximum profit in the left half

        double maxProfitLeft = maxProfitHelper(prices, start, mid);



        // Find the maximum profit in the right half

        double maxProfitRight = maxProfitHelper(prices, mid + 1, end);



        // Find the maximum profit by buying in the left half and selling in the right half

        double minLeft = prices[start];

        double maxRight = prices[mid + 1];

        for (int i = start + 1; i <= mid; i++) {

            minLeft = Math.min(minLeft, prices[i]);

        }

        for (int i = mid + 2; i <= end; i++) {

            maxRight = Math.max(maxRight, prices[i]);

        }

        double maxProfitCrossing = maxRight - minLeft;



        // Return the maximum of the three profits

        return Math.max(Math.max(maxProfitLeft, maxProfitRight), maxProfitCrossing);

    }

}


