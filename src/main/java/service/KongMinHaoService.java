package service;

import entity.Asset;

/**
 * Created by WaterMelon on 2017/8/7.
 */
public interface KongMinHaoService {

    void increaseAsset(Asset asset);
    Asset getAsset(Asset asset);
}
