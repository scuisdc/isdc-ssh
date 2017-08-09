package dto;

/**
 * Created by WaterMelon on 2017/8/7.
 */
public class AssetRequest {
    private String name;
    private int money;
    public  String getName(){
        return name;
    }
    public int getMoney(){
        return money;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMoney(int money){
        this.money=money;
    }
}
