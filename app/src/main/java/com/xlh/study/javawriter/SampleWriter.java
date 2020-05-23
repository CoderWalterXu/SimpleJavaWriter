package com.xlh.study.javawriter;

import android.util.Log;

/**
 * @author: Watler Xu
 * time:2020/5/23
 * description:该类作为JavaWriter要生成类的模板，注意生成的类名不要和该模板类名相同
 * version:0.0.1
 */
public class SampleWriter {

    String str = "Hello Java Writer";

    public void say(){
        Log.e("TAG", "say:" + str);
    }

    public void testFor(){
        for (int i = 0; i < 10; i++) {
            Log.e("TAG", "i--->" + i);
        }
    }

}
