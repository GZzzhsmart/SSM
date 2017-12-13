package top.zzh.service;


import top.zzh.bean.User;

public interface UserService extends BaseService {

    User getByPhonePwd(String phone, String pwd);

    String checkPwd(String phone);
    void updatePwd(Long id,String pwd);


}
