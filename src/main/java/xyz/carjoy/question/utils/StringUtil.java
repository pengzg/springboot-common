package xyz.carjoy.question.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Random;

/**
 * String工具类
 *
 * @author admin
 *
 */
public class StringUtil {

    /**
     * 格式化字符串
     *
     * 例：formateString("xxx{0}bbb",1) = xxx1bbb
     *
     * @param str
     * @param params
     * @return
     */
    public static String formateString(String str, String... params) {
        for (int i = 0; i < params.length; i++) {
            str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
        }
        return str;
    }

    public static String getRandom() {
        int max = 10000;
        int min = 1000;
        Random random = new Random();
        int rando = random.nextInt(max) % (max - min + 1) + min;
        return rando+"";
    }


    /**
     * 格式化数字，例如：将5转化为4位字符，则得到0005
     *
     * @param dValue
     *            被格式化的数值
     * @param nWidth
     *            需要转换的位数
     * @return
     */
    public static String formatInt(long lValue, int nWidth) {
        String strReturn = "" + lValue;
        int initLength = strReturn.length();
        for (int i = nWidth; i > initLength; i--) {
            strReturn = "0" + strReturn;
        }
        return strReturn;
    }

    /**
     * 格式化数字，例如：将5转化为4位字符，则得到0005
     *
     * @param sValue
     *            被格式化的数值
     *  @param int addValue 增加的值
     * @param nWidth
     *            需要转换的位数
     * @return
     */

    public static String formatLengthPK(String pkValue){
        return formatLengthPK(pkValue, "", 36);
    }

    public static String formatInt(String sValue,int addValue, int nWidth) {
        long lValue = new Long(sValue);
        lValue = lValue+addValue;
        return formatInt(lValue, nWidth);
    }


    public static String formatLengthPK(String pkValue,int nLength){
        return formatLengthPK(pkValue, "", nLength);

    }
    /**
     * 格式化数字，例如：将5转化为4位字符，则得到0005
     *
     * @param sValue
     *            被格式化的数值
     *  @param int addValue 增加的值
     * @param nWidth
     *            需要转换的位数
     * @return
     */
    public static String formatLengthPK(String pkValue, String addValue,int nLength){
        Random random = new Random();
        String strReturn = "" ;
        int lent = (nLength-(pkValue==null?0:pkValue.length()));
        if(lent<=0)
            return pkValue.substring(0,nLength);
        String str = "";
        if (StringUtils.isNotBlank(addValue)) {
            //自定义增加字符串
            strReturn = pkValue + (addValue == null ? "" : addValue);
            if (strReturn.length() > nLength) {
                strReturn = strReturn.substring(0, nLength);
            } else {
                int w = lent/addValue.length();
                for (int i = 0; i < w; i++) {
                    str += addValue;
                }
                strReturn = pkValue +str;
                int initLength = strReturn.length();
                for (int i = nLength; i > initLength; i--) {
                    strReturn = strReturn + "0";
                }
            }
        } else {
            //随机增加字符串
            for (int i = 0; i < lent; i++) {
                str += random.nextInt(9);
            }
            strReturn = pkValue+str;
        }
        return strReturn;
    }

    /**
     * 获取N位随机数
     * @param n
     * @return
     */
    public static String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n
                    + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

    /**
     * 左补0
     * @param srcStr
     * @param num
     * @return
     */
    public static String zeroLeft(String srcStr,int num){
        StringBuilder sb = new StringBuilder("");
        int diff = num;
        int len = StringUtils.isEmpty(srcStr)?0:srcStr.trim().length();
        if(len >= num) diff = 0;
        else if(len < num ) diff = num - len;
        //补零
        for (int i = 0; i < diff; i++) {
            sb.append("0");
        }
        //连接
        sb.append(srcStr);
        return sb.toString();
    }

    /**
     * 模板消息
     * @param template
     * @param args
     * @return
     */
    public static String getMessage(String template,Object ...args){
        if(StringUtils.isNotEmpty(template)){
            return MessageFormat.format(template, args);
        }
        return "";
    }

    /**
     * 生成订单号
     * @return
     */
    public static String createBillNumber() {
        // return DateUtil.getCurrentDateToString3()+random(3);
        return DateUtil.getCurrentDateToString1() + "-" + random(6);
    }

    /**
     * 生成订单号
     * @return
     */
    public static String createBillNumberWithoutLine() {
        // return DateUtil.getCurrentDateToString3()+random(3);
        return DateUtil.getCurrentDateToString1() + random(6);
    }

    /*
     * 替换字签串里指定宽度的字符
     */
    public static String replace(String str, int width) {
        StringBuffer sb = new StringBuffer();
        int lent = str == null ? 0 : str.length();
        if (lent == 0)
            return "";
        if (lent <= width) {
            for (int i = 0; i < width; i++) {
                sb.append("*");
            }
            return sb.toString();
        } else {
            str = str.substring(0, str.length() - width);
            for (int i = 0; i < width; i++) {
                sb.append("*");
            }
            return str + sb.toString();
        }
    }

    /**
     * 数字转换 大写
     * @param value
     * @return
     */
    public static String changeToBig(double value){
        char[] hunit={'拾','佰','仟'};                                    //段内位置表示
        char[] vunit={'万','亿'};                                         //段名表示
        char[] digit={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'}; //数字表示
        //  long midVal = (long)(value*100);                                  ////存在精度问题,如0.9->0.8999...
        BigDecimal midVal = new BigDecimal(Math.round(value*100));    //转化成整形,替换上句
        if(midVal.compareTo(BigDecimal.ZERO)==0){
            return "零";
        }
        String valStr=String.valueOf(midVal);                             //转化成字符串
        String head=valStr.substring(0,valStr.length()-2);                //取整数部分
        String rail=valStr.substring(valStr.length()-2);                  //取小数部分

        String prefix="";                                                 //整数部分转化的结果
        String suffix="";                                                 //小数部分转化的结果
        //处理小数点后面的数
        if(rail.equals("00")){                                           //如果小数部分为0
            suffix="整";
        } else{
            suffix=digit[rail.charAt(0)-'0']+"角"+digit[rail.charAt(1)-'0']+"分"; //否则把角分转化出来
        }
        //处理小数点前面的数
        char[] chDig=head.toCharArray();                   //把整数部分转化成字符数组
        boolean preZero=false;                             //标志当前位的上一位是否为有效0位（如万位的0对千位无效）
        byte zeroSerNum = 0;                               //连续出现0的次数
        for(int i=0;i<chDig.length;i++){                   //循环处理每个数字
            int idx=(chDig.length-i-1)%4;                    //取段内位置
            int vidx=(chDig.length-i-1)/4;                   //取段位置
            if(chDig[i]=='0'){                               //如果当前字符是0
                preZero=true;
                zeroSerNum++;                                  //连续0次数递增
                if(idx==0 && vidx >0 &&zeroSerNum < 4){
                    prefix += vunit[vidx-1];
                    preZero=false;                                //不管上一位是否为0，置为无效0位
                }
            }else{
                zeroSerNum = 0;                                 //连续0次数清零
                if(preZero) {                                   //上一位为有效0位
                    prefix+=digit[0];                                //只有在这地方用到'零'
                    preZero=false;
                }
                prefix+=digit[chDig[i]-'0'];                    //转化该数字表示
                if(idx > 0) prefix += hunit[idx-1];
                if(idx==0 && vidx>0){
                    prefix+=vunit[vidx-1];                      //段结束位置应该加上段名如万,亿
                }
            }
        }

        if(prefix.length() > 0) prefix += '圆';                               //如果整数部分存在,则有圆的字样
        return prefix+suffix;                                                            //返回正确表示
    }



    public static String getRandomNum6() {
        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++)
            result = result * 10 + array[i];
        return result + "";
    }

    public static String getRandomNum4() {
        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 4; i++)
            result = result * 10 + array[i];
        return result + "";
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(getRandomString(32));
        //System.out.println(System.currentTimeMillis());
        //System.out.println(changeToBig(0.0));
    }
}