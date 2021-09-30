package thinkgeardemo;

/*
ATTENTION   --רע��
MEDITATION  --ڤ���
DELTA   --�Ĳ���
THETA   --���Բ�
Low_ALPHA   --���Բ�
High_ALPHA   --���Բ�
Low_BETA   --���Բ�
High_BETA   --���Բ�
Low_GAMMA   --���Բ�
Mid_GAMMA   --���Բ�
BLINK_STRENGTH   --գ��ǿ��
*/

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import neurosky.ThinkGearSocket;
import processing.core.PApplet;
import processing.core.PFont;

public class ThinkGearDemo extends PApplet {

    public static String time;    //ʱ���
    public ThinkGearSocket neuroSocket;
    public int attention = 10;    //רע��
    public int meditation = 10;   //ڤ���
    public static int blink = 0;  //գ�۴���
    public PFont font;

    // ��ʼ��
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

    // ֱ�۲鿴�Ե粨�����Ƿ�Work
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

    // ��ȡרע��
    public void attentionEvent(int attentionLevel) {
        println("Attention Level: " + attentionLevel);
        attention = attentionLevel;
        try {
            DAO.game_Attention(attention);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ��ȡڤ���
    public void meditationEvent(int meditationLevel) {
        println("Meditation Level: " + meditationLevel);
        meditation = meditationLevel;
        try {
            DAO.addEsense(ThinkGearDemo.timeReturn(), attention, meditation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ��⵽գ�ۣ���洢game_Strength��Blink
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

    // ���EEG���������ݿ�
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

    // ֹͣ�Ե粨����
    public void stop() {
        neuroSocket.stop();
        super.stop();
    }

    // ΪEEG�������ʱ���
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
