package cmu.youchun.recommender.service;

import cmu.youchun.recommender.BusinessException;
import cmu.youchun.recommender.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    Integer countAll();

    UserModel login(String phone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

}
