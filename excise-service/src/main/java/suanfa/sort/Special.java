package suanfa.sort;
import com.alibaba.fastjson.JSON;

public class Special{
    public static void main(String[] args) {
        String s="您好，核实上传的面单图片非本单快递面单图片，?提供的照片证据不足，按退换货要求无法受理，你可以上传符合要求的照片?或者联系您对应的BD核查是否可以线下处理?，谢谢！";
        s = JSON.toJSONString(s);
        System.out.println("===============");
        String s1=" 汤臣倍健 胶原蛋白粉（水蜜桃味）";
        System.out.println(s);
    }
}