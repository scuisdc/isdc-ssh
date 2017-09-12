package dao;

import entity.Announce;
import entity.ApplicationForm;

import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */
public interface ApplicationFormDAO {
    List<Announce> getAllForms();

    void addForm(ApplicationForm form);

    void updateForm(ApplicationForm form);

    Optional<ApplicationForm> queryByOpenid(String openid);
}
