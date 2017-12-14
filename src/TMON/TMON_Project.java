package TMON;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TMON_Project {
    public static void main(String[] args){
        BufferedReader br = null;
        String line;
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        String Path = "2017_be_sheet.csv";
        //CSV 파일을 읽어 ArrayList에 자료 넣기
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(Path), "euc-kr"));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(",");

                if(field.length == 5) {
                    //상품명에 ","가 들어 있지 않을때
                    //문자열을 파싱한 값들로 Item 객체 생성 후 itemArrayList에 추가
                    Item item = new Item(field[0], Integer.parseInt(field[1]), Integer.parseInt(field[2]), Integer.parseInt(field[3]), Integer.parseInt(field[4]));
                    itemArrayList.add(item);
                }
                else{
                    //상품명에 ","가 들어 있을때
                    //잘린 상품명들을 다시 붙여줌
                    //문자열을 파싱한 값들로 Item 객체 생성 후 itemArrayList에 추가
                    Item item = new Item();
                    String temp = new String();
                    for(int i = 0 ; i < field.length-4 ; i++){
                        if(i == 0) temp = temp + field[i];
                        else temp = temp + "," + field[i];
                    }
                    item.setI_name(temp);
                    item.setsPrice(Integer.parseInt(field[field.length-4]));
                    item.setnPrice(Integer.parseInt(field[field.length-3]));
                    item.setCount9To10(Integer.parseInt(field[field.length-2]));
                    item.setCount10To11(Integer.parseInt(field[field.length-1]));
                    itemArrayList.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ArrayList<Item> preRank = new ArrayList<Item>();

        for(int i = 0 ; i < itemArrayList.size() ; i++){
            preRank.add(itemArrayList.get(i));
        }

        int size = itemArrayList.size();

        //9~10시 상품 거래액순으로 정렬
        for(int i = 0 ; i< size - 1 ; i++){
            for(int j = 0 ; j < size - 1 - i ; j++){
                if(preRank.get(j).preDeal() < preRank.get(j+1).preDeal()){
                    Collections.swap(preRank, j, j+1);
                }
            }
        }

        //이전 순위 넣어주기
        for(int i = 0 ; i < preRank.size() ; i++){
            preRank.get(i).setPreRanking(i);
        }

        ArrayList<Item> nextRank = new ArrayList<Item>();

        for(int i = 0 ; i < preRank.size() ; i++){
            nextRank.add(preRank.get(i));
        }

        //10~11시 상품 거래액순으로 정렬
        for(int i = 0 ; i< size - 1 ; i++){
            for(int j = 0 ; j < size - 1 - i ; j++){
                if(nextRank.get(j).nextDeal() < nextRank.get(j+1).nextDeal()){
                    Collections.swap(nextRank, j, j+1);
                }
            }
        }

        //현재 순위 넣어주기
        for(int i = 0 ; i < nextRank.size() ; i++){
            nextRank.get(i).setNextRanking(i);
        }

        //출력 타이틀
        System.out.println("현재 순위" + "\t" + "순위변동" + "\t" + "상품명" + "\t\t\t\t\t\t\t" + "정상가" + "\t"+ "판매가" + "\t" + "할인율");
        for(int i = 0; i < nextRank.size() ; i++){
            //현재 순위 출력
            System.out.print(nextRank.get(i).getNextRanking() + "\t\t\t");

            //순위 변동 출력
            if(nextRank.get(i).getNextRanking() == nextRank.get(i).getPreRanking()){
                System.out.print("-"+ "\t\t\t");
            }
            else if(nextRank.get(i).getNextRanking() > nextRank.get(i).getPreRanking()){
                System.out.print("-" + (nextRank.get(i).getNextRanking() - nextRank.get(i).getPreRanking())+"\t\t\t");
            }
            else if(nextRank.get(i).getNextRanking() < nextRank.get(i).getPreRanking()){
                System.out.print("+" + (nextRank.get(i).getPreRanking() - nextRank.get(i).getNextRanking())+"\t\t\t");
            }

            //상품명 출력
            System.out.print(nextRank.get(i).getI_name() + "\t\t\t");
            //정상가 출력
            System.out.print(nextRank.get(i).getnPrice() + "원" + "\t");
            //판매가 출력
            System.out.print(nextRank.get(i).getsPrice() + "원" + "\t");
            //할인률 출력
            System.out.println(nextRank.get(i).getDiscountRate()+"%");
        }
    }
}
