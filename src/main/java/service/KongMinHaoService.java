package service;

import entity.Asset;

import java.util.List;

/**
 * Created by WaterMelon on 2017/8/7.
 */
public interface KongMinHaoService {

    void increaseAsset(Asset asset);
    Asset getAsset(Asset asset);
    List<Asset> getRank();
}
