package com.zr.staff.service;

public interface IFaceService {
    int faceVerify(String base64);
    boolean faceRegister(String base64, Integer userId);
}
