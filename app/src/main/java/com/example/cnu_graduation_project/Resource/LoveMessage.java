package com.example.cnu_graduation_project.Resource;

import java.util.Random;

public class LoveMessage {
    static final String[] MESSAGE = {
            // 메세지 추가 필요
            "오늘 하루도 고생했어\n 집가서 푹 쉬자",

    };

    static final int MESSAGE_LENGTH = MESSAGE.length;

    public static String getMessage(){
        return MESSAGE[new Random().nextInt(MESSAGE_LENGTH)];
    }

}
