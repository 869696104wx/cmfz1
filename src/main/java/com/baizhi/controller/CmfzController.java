package com.baizhi.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.User;
import com.baizhi.service.cmfz.CmfzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName CmfzController
 * @Discription
 * @Author
 * @Date 2019/12/26 0026 10:23
 * @Version 1.0
 */
@RestController
@RequestMapping("cmfz")
public class CmfzController {
    @Autowired
    private CmfzService cmfzService;

    @RequestMapping("first_page")
    public Map<String, Object> first_page(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        if (uid == null || type == null) {
            map.put("error", "'uid'或'type'参数不能为空");
        } else {
            if (type.equals("all")) {
                map.put("header", cmfzService.showAllCmfz_Banner());
            }

            if (type.equals("wen")) {
                map.put("body", cmfzService.showAllAlbumAndArticle(uid).get("wens"));
            }
            if (type.equals("si")) {
                if (sub_type == null) {
                    map.put("error", "'si'参数不能为空");
                } else {
                    if (sub_type.equals("ssyj")) {
                        map.put("body", cmfzService.showAllAlbumAndArticle(uid).get("ssyj"));
                    }
                    if (sub_type.equals("xmfy")) {
                        map.put("body", cmfzService.showAllAlbumAndArticle(uid).get("xmfy"));
                    }
                }
            }
        }
        return map;
    }

    @RequestMapping("wen")
    public Map<String, Object> wen(String id, String uid) {
        Map<String, Object> map = new HashMap<>();
        if (id == null || uid == null) {
            map.put("error", "'id'或'uid'参数不能为空");
            return map;
        } else {
            return cmfzService.getWen(id);
        }
    }

    @RequestMapping("login")
    public Map<String, Object> login(String phone, String password, String code) {
        Map<String, Object> map = new HashMap<>();
        if (phone == null) {
            map.put("error", "'phone'参数不能都为空");
        } else {
            if (password == null && code == null) {
                map.put("error", "'password'或'code'参数不能都为空");
            } else {
                try {
                    map = cmfzService.login(phone, password);
                } catch (Exception e) {
                    map.put("error", "-200");
                    map.put("errmsg", "密码错误");
                }
            }
        }
        return map;
    }

    @RequestMapping("register")
    public Map<String, Object> register(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        if (phone == null || password == null) {
            map.put("error", "'phone'或'password'参数不能为空");
        } else {
            try {
                cmfzService.register(phone);
                map.put("password", password);
                map.put("uid", UUID.randomUUID().toString());
                map.put("phone", phone);
            } catch (Exception e) {
                map.put("errno", "-200");
                map.put("error_msg", "该手机号已经存在");
            }
        }
        return map;
    }

    @RequestMapping("update")
    public Map<String, Object> update(String phone, String uid, String gender, String photo,
                                      String location, String description, String nickname,
                                      String province, String city, String password) {
/*      uid	            用户id	    必填
        gender	        性别	        选填
        photo	        头像  	    选填
        location	    所在地	    选填
        description	    个人签名	    选填
        nickname	    昵称	        选填
        province	    省	        选填
        city	        城市	        选填
        password	    密码	        选填*/
        Map<String, Object> map = new HashMap<>();
        if (phone == null || uid == null) {
            map.put("error", "'phone'或'uid'参数不能为空");
        } else {
            try {
                List<User> user = cmfzService.getUser(uid);
                //cmfzService.register(phone);
                if (password == null) {
                    map.put("password", user.get(0).getU_password());//密码
                } else {
                    map.put("password", password);//密码
                }

                map.put("farmington", user.get(0).getU_dharma());//法名
                map.put("uid", user.get(0).getU_id());//用户id
                if (nickname == null) {
                    map.put("nickname", user.get(0).getU_username());//昵称
                }
                if (gender == null) {
                    map.put("gender", user.get(0).getU_sex());//性别
                } else {
                    map.put("gender", gender);//性别
                }
                if (photo == null) {
                    map.put("photo", "http://" + user.get(0).getU_photo());//头像
                } else {
                    map.put("photo", "http://" + photo);//头像
                }
                if (location == null) {
                    map.put("location", user.get(0).getU_province() + user.get(0).getU_city());//所在地
                } else {
                    map.put("location", location);//所在地
                }
                if (province == null) {
                    map.put("province", user.get(0).getU_province());//省市
                } else {
                    map.put("province", province);//省市
                }
                if (city == null) {
                    map.put("city", user.get(0).getU_city());//地区
                } else {
                    map.put("city", city);//地区
                }
                if (description == null) {
                    map.put("description", user.get(0).getU_sign());//个人签名
                } else {
                    map.put("description", description);//个人签名
                }
                map.put("phone", phone);
            } catch (Exception e) {
                map.put("errno", "-200");
                map.put("error_msg", e.getMessage());
            }
        }
        return map;
    }

    @RequestMapping("getCode")
    public Map<String, Object> getCode(String phone, HttpSession session) throws ClientException {
        Map<String, Object> map = new HashMap<>();
        if (phone == null) {
            map.put("error", "'phone'参数不能为空");
        } else {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = "LTAI4FgcbHyk6yG14JZaQzaD";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "3P0830cWG3hZgh9GBHljb0Tj7xZ2cu";//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("安徒不生");
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode("SMS_181191942");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            int newNum = (int) ((Math.random() * 9 + 1) * 100000);
            String s = String.valueOf(newNum);
            request.setTemplateParam("{\"code\":\"" + s + "\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                session.setAttribute("code", s);
            }
        }
        return map;
    }

    @RequestMapping("makeCode")
    public Map<String, Object> makeCode(String phone, String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (phone == null || code == null) {
            map.put("error", "'phone'或'code'参数不能为空");
        } else {
            String code1 = (String) session.getAttribute("code");
            if (code1.equals(code)) {
                map.put("result", "success");
            } else {
                map.put("result", "fail");
            }
        }
        return map;
    }

    @RequestMapping("getFriendsByU_id")
    public List<Map<String, Object>> getFriendsByU_id(String uid) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (uid == null) {
            map.put("error", "'uid'参数不能为空");
            list.add(map);
        } else {
            list = cmfzService.getFriendsByU_id(uid);
        }
        return list;
    }

}
