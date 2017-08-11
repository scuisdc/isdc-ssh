package utils;

import entity.Asset;

import java.util.Comparator;

/**
 * Created by WaterMelon on 2017/8/10.
 */
public class ComparatorAsset implements Comparator {

    public int compare(Object obj0, Object obj1) {
        Asset asset0=(Asset) obj0;
        Asset asset1=(Asset) obj1;

        //首先比较money，如果money相同，则比较name

        int flag = Long.compare(asset1.getMoney(),asset0.getMoney());

        if(flag==0){
            return asset1.getName().compareTo(asset0.getName());
        }else{
            return flag;
        }
    }

}