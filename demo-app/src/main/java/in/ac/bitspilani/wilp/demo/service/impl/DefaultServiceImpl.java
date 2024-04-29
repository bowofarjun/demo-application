package in.ac.bitspilani.wilp.demo.service.impl;

import in.ac.bitspilani.wilp.demo.model.AppDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultServiceImpl implements IDefaultService {

    @Autowired
    private AppDetail appDetail;

    @Override
    public AppDetail getAppDetails() {
        return appDetail;
    }
}
