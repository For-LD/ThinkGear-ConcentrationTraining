package thinkgeardemo;

/*
ATTENTION   --专注度
MEDITATION  --冥想度
DELTA   --δ波段
THETA   --θ脑波
Low_ALPHA   --α脑波
High_ALPHA   --α脑波
Low_BETA   --β脑波
High_BETA   --β脑波
Low_GAMMA   --γ脑波
Mid_GAMMA   --γ脑波
BLINK_STRENGTH   --眨眼强度
*/

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import neurosky.ThinkGearSocket;
import processing.core.PApplet;
import processing.core.PFont;

public class ThinkGearDemo extends PApplet {

    public static String time;    //时间戳
    public ThinkGearSocket neuroSocket;
    public int attention = 10;    //专注度
    public int meditation = 10;   //冥想度
    public static int blink = 0;  //眨眼次数
    public PFont font;

    // 初始化
    public void setup() {
        size(600, 600);
        ThinkGearSocket neuroSocket = new ThinkGearSocket(this);
        try {
            neuroSocket.start();
        } catch (Exception e) {
            println("Is ThinkGear running?");
        }
        smooth();
        noFill();
        font = createFont("Verdana", 12);
        textFont(font);
    }

    // 直观查看脑电波耳机是否Work
    public void draw() {
        background(0, 0, 0, 50);
        fill(0, 0, 0, 255);
        noStroke();
        rect(0, 0, 120, 80);
        fill(0, 0, 0, 10);
        noStroke();
        rect(0, 0, width, height);
        fill(0, 116, 168);
        stroke(0, 116, 168);
        text("Attention: " + attention, 10, 30);
        noFill();
        ellipse(width / 2, height / 2, attention * 3, attention * 3);
        fill(209, 24, 117, 100);
        noFill();
        text("Meditation: " + meditation, 10, 50);
        stroke(209, 24, 117, 100);
        noFill();
        ellipse(width / 2, height / 2, meditation * 3, meditation * 3);
    }

    public void poorSignalEvent(int sig) {
        println("SignalEvent " + sig);
    }

    // 获取专注度
    public void attentionEvent(int attentionLevel) {
        println("Attention Level: " + attentionLevel);
        attention = attentionLevel;
        try {
            DAO.game_Attention(attention);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取冥想度
    public void meditationEvent(int meditationLevel) {
        println("Meditation Level: " + meditationLevel);
        meditation = meditationLevel;
        try {
            DAO.addEsense(ThinkGearDemo.timeReturn(), attention, meditation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 检测到眨眼，则存储game_Strength与Blink
    public void blinkEvent(int blinkStrength) {
        blink++;
        try {
            DAO.game_Strength(blink);
            System.out.println("Test_Blink:" + blink);
        } catch (Exception e) {
            e.printStackTrace();
        }

        println("blinkStrength: " + blinkStrength);
        try {
            DAO.addBlink(ThinkGearDemo.timeReturn(), blinkStrength);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加EEG数据至数据库
    public void eegEvent(int delta, int theta, int low_alpha, int high_alpha, int low_beta, int high_beta,
                         int low_gamma, int mid_gamma) {
        println("delta Level: " + delta);
        println("theta Level: " + theta);
        println("low_alpha Level: " + low_alpha);
        println("high_alpha Level: " + high_alpha);
        println("low_beta Level: " + low_beta);
        println("high_beta Level: " + high_beta);
        println("low_gamma Level: " + low_gamma);
        println("mid_gamma Level: " + mid_gamma);
        try {
            DAO.addEgg(ThinkGearDemo.timeReturn(), delta, theta, low_alpha, high_alpha, low_beta, high_beta, low_gamma,
                    mid_gamma);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rawEvent(int[] raw) {
        println("rawEvent Level: " + Arrays.toString(raw));
    }

    // 停止脑电波连接
    public void stop() {
        neuroSocket.stop();
        super.stop();
    }

    // 为EEG数据添加时间戳
    public static String timeReturn() {
        Date t = new Date();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        time = sdf.format(t);
        return time;
    }

    public static void main(String[] _args) {
        PApplet.main(new String[]{thinkgeardemo.ThinkGearDemo.class.getName()});
    }

}
