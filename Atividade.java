package aula7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aula7.entities.Product;

public class Atividade{
        public static void main(String[] args) throws ParseException {
        
        Scanner sc = new Scanner(System.in);
        List<Product> list = new ArrayList<>();

        System.out.print("Enter file path: ");
        String sourceFileStr = sc.nextLine();

        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();

        System.out.println(sourceFolderStr);

        boolean success = new File(sourceFolderStr + "\\out").mkdir();

        String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){


            String itemCsv = br.readLine();
            while(itemCsv != null) {

                String[] fields = itemCsv.split(",");
                String name = fields[0];
                Double price = Double.parseDouble(fields[1]);
                Integer quantity = Integer.parseInt(fields[2]);

                list.add(new Product(name, price, quantity));

                itemCsv = br.readLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
                
                for(Product item : list) {
                    bw.write(item.getName() + ", " + item.total());

                    bw.newLine();
                }

                System.out.println("CREATED.");

            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.print("ERROR: " + e.getMessage());
        }

        sc.close();
    }
}