package top.zzh.service;


import top.zzh.bean.User;

public interface UserService extends BaseService {

    User getByPhonePwd(String phone, String pwd);

    int updatePwd(User user);


}
