package service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dao.KongMinHaoDAO;
import dao.KongMinHaoDAOImpl;
import entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import utils.ComparatorAsset;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * Created by WaterMelon on 2017/8/7.
 */
@Service("KongMinHaoservice")
@Transactional
public class KongMinHaoServicelmpl implements KongMinHaoService{
    final KongMinHaoDAO kongMinHaoDAO;
    @Autowired
    public KongMinHaoServicelmpl(KongMinHaoDAO kongMinHaoDAO) {
        this.kongMinHaoDAO = kongMinHaoDAO;
    }
    @Override
    public void increaseAsset(Asset asset) {
        kongMinHaoDAO.increaseAsset(asset.getName(),0L);
    }

    @Override
    public Asset getAsset(Asset asset) {
       return kongMinHaoDAO.getAssetByName(asset.getName());
    }

    @Override
    public List<Asset> getRank() {
        ComparatorAsset comparatorAsset = new ComparatorAsset();
        List<Asset> Rank = kongMinHaoDAO.getAllAsset();
        return Rank;
    }

}
