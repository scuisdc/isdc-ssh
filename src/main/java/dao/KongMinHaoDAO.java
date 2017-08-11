package dao;
import entity.Asset;

import java.util.List;

/**
 * Created by WaterMelon on 2017/8/7.
 */
public  interface KongMinHaoDAO {

    void increaseAsset(String name,long money);
    Asset getAssetByName(String name);
    void addAsset(Asset asset);
    List<Asset> getAllAsset();
}
