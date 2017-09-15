package service.impl;

import dao.KongMinHaoDAO;
import entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.KongMinHaoService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by WaterMelon on 2017/8/7.
 */
@Service("KongMinHaoservice")
@Transactional
public class KongMinHaoServicelmpl implements KongMinHaoService {
    final KongMinHaoDAO kongMinHaoDAO;
    @Autowired
    public KongMinHaoServicelmpl(KongMinHaoDAO kongMinHaoDAO) {
        this.kongMinHaoDAO = kongMinHaoDAO;
    }
    @Override
    public void increaseAsset(Asset asset) {
        kongMinHaoDAO.increaseAsset(asset.getName(),100L);
    }

    @Override
    public Asset getAsset(Asset asset) {
       return kongMinHaoDAO.getAssetByName(asset.getName());
    }

    @Override
    public List<Asset> getRank() {
        List<Asset> Rank = kongMinHaoDAO.getAllAsset();
        return Rank;
    }

}
