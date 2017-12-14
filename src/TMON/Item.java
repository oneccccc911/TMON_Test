package TMON;

public class Item {
    private String i_name;      //상품명
    private int sPrice;         //판매가
    private int nPrice;         //정상가
    private int count9To10;     //9시~10시 사이 판매량
    private int count10To11;    //10시~11시 사이 판매량
    private int preRanking;     //이전 순위
    private int nextRanking;    //다음 순위

    //생성자
    public  Item(){

    }

    public Item(String i_name, int sPrice, int nPrice, int count9To10, int count10To11) {
        this.i_name = i_name;
        this.sPrice = sPrice;
        this.nPrice = nPrice;
        this.count9To10 = count9To10;
        this.count10To11 = count10To11;
    }

    //Getter
    public String getI_name() {
        return i_name;
    }

    public int getnPrice() {
        return nPrice;
    }

    public int getsPrice() {
        return sPrice;
    }

    public int getCount9To10() {
        return count9To10;
    }

    public int getCount10To11() {
        return count10To11;
    }

    public int getPreRanking() {
        return preRanking;
    }

    public int getNextRanking() {
        return nextRanking;
    }

    //Setter
    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public void setnPrice(int nPrice) {
        this.nPrice = nPrice;
    }

    public void setsPrice(int sPrice) {
        this.sPrice = sPrice;
    }

    public void setCount9To10(int count9To10) {
        this.count9To10 = count9To10;
    }

    public void setCount10To11(int count10To11) {
        this.count10To11 = count10To11;
    }

    public void setPreRanking(int preRanking) {
        this.preRanking = preRanking;
    }

    public void setNextRanking(int nextRanking) {
        this.nextRanking = nextRanking;
    }

    //할인률 구하기
    public int getDiscountRate(){
        return (this.getnPrice()-this.getsPrice())*100/this.getnPrice();
    }

    //9~10시 상품거래액
    public int preDeal(){
        return (this.getsPrice()*this.getCount9To10());
    }

    //10~11시 상품거래액
    public int nextDeal(){
        return (this.getsPrice()*this.getCount10To11());
    }
}
