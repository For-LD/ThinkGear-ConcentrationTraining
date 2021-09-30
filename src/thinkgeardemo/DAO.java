package thinkgeardemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

    private static final DBUtil dbUtil = new DBUtil();

    // 添加原始脑电波数据进数据库
    public static void addEgg(String TG_DATETIME, int DELTA, int THETA, int Low_ALPHA, int High_ALPHA, int Low_BETA,
                              int High_BETA, int Low_GAMMA, int Mid_GAMMA) throws Exception {
        Connection con = dbUtil.getCon();
        try {
            // 连接数据库
            Statement stmt = con.createStatement();
            String sqlString = "INSERT INTO TG_Date_EGG(TG_DATETIME,  DELTA,THETA,Low_ALPHA,High_ALPHA,Low_BETA,High_BETA,Low_GAMMA,Mid_GAMMA) values("
                    + " '" + TG_DATETIME + "'," + DELTA + "," + THETA + "," + Low_ALPHA + "," + High_ALPHA + "," + Low_BETA
                    + "," + High_BETA + "," + Low_GAMMA + "," + Mid_GAMMA + ");";
            // 执行SQL语句
            stmt.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Insert failed��");
            e.printStackTrace();
        }
    }

    public static void addEsense(String TG_DATETIME, int ATTENTION, int MEDITATION) throws Exception {
        Connection con = dbUtil.getCon();
        try {
            // 连接数据库
            Statement stmt = con.createStatement();
            String sqlString = "INSERT INTO TG_Date_ESENSE(TG_DATETIME, ATTENTION, MEDITATION) values(" + " '"
                    + TG_DATETIME + "'," + ATTENTION + "," + MEDITATION + ");";
            // 执行SQL语句
            stmt.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Insert failed！！");
            e.printStackTrace();
        }
    }

    // 添加眨眼次数进数据库
    public static void addBlink(String TG_DATETIME, int BLINK_STRENGTH) throws Exception {
        Connection con = dbUtil.getCon();
        try {
            // 连接数据库
            Statement stmt = con.createStatement();
            String sqlString = "INSERT INTO TG_Date_BLINK(TG_DATETIME, BLINK_STRENGTH) values(" + " '" + TG_DATETIME + "',"
                    + BLINK_STRENGTH + ");";
            // 执行SQL语句
            stmt.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Insert failed！！");
            e.printStackTrace();
        }
    }

    // 添加专注度进数据库
    public static void game_Attention(int ATTENTION) throws Exception {
        Connection con = dbUtil.getCon();
        try {
            // 连接数据库
            Statement stmt = con.createStatement();
            String sqlString = "UPDATE SQLValue SET ATTENTION =" + ATTENTION
                    + " WHERE INDEXOF = 'LGL'";
            // 执行SQL语句
            stmt.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Update failed！！");
            e.printStackTrace();
        }
    }

    // 添加眨眼强度进数据库
    public static void game_Strength(int BLINK_STRENGTH) throws Exception {
        Connection con = dbUtil.getCon();
        try {
            // 连接数据库
            Statement stmt = con.createStatement();
            String sqlString = "UPDATE SQLValue SET CHANGEWEAPONINT=" + BLINK_STRENGTH
                    + " WHERE INDEXOF = 'LGL'";
            // 执行SQL语句
            stmt.execute(sqlString);
        } catch (SQLException e) {
            System.out.println("Update failed！！");
            e.printStackTrace();
        }
    }

}