package com.ice.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.Scanner;

/**
 * Created by zipple on 2017/9/9.
 */
public class HanLPUtil {
    public static void main(String[] args) {
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入文本:");
            String str = sc.next();
            standardWord(str);
        }
    }

    public static List<Term> standardWord(String values){
        List<Term> termList = HanLP.segment(values);
        System.out.println(termList);
        return termList;
    }
}
