package com.zr.staff.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zr.staff.service.IFaceService;

import com.baidu.aip.face.AipFace;

public class FaceService implements IFaceService{
    final static String APP_ID = "19391142";

    final static String API_KEY = "OS69z5q5znCmzCEIBBlqQpVY";

    final static String SECRET_KEY = "37G5SXrd5syh3vmRyLkLplemWKqaVfSQ";

 
    final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    public int faceVerify(String base64) {
        String groupIdList = "CSI"; // �ٶ�����������

        JSONObject res = client.search(base64, "BASE64", groupIdList,null);

        if(res.get("error_code").toString().equals("0")){
            JSONObject result = (JSONObject)res.get("result");
            JSONArray userList = (JSONArray) result.get("user_list");
            JSONObject userList01 = (JSONObject)userList.get(0);
            return Integer.parseInt((String) userList01.get("user_id"));
        }
        else
            return -1;
    }

    public boolean faceRegister(String base64, Integer userId) {
        HashMap<String, String> options = new HashMap<String, String>();
        System.out.println("CSI");

        //ͼƬ���� NONE: �����п��� LOW:�ϵ͵�����Ҫ�� NORMAL: һ�������Ҫ�� HIGH: �ϸߵ�����Ҫ�� Ĭ�� NONE
        options.put("quality_control","NORMAL");

        //������ NONE: �����п��� LOW:�ϵ͵Ļ���Ҫ��(��ͨ���� �͹����ܾ���)  NORMAL: һ��Ļ���Ҫ��(ƽ��Ĺ����ܾ���, ͨ����) HIGH: �ϸߵĻ���Ҫ��(�߹����ܾ��� ��ͨ����)
        options.put("liveness_control","LOW");

        //�ٶ�����������
        String groupId = "CSI";

        JSONObject res = client.addUser(base64, "BASE64", groupId, userId.toString(), options);

        if(res.get("error_code").toString().equals("0"))
            return true;
        else
            return false;
    }
}
