package main;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import main.CosineSimilarityCalculator;
import com.huaban.analysis.jieba.JiebaSegmenter;
public class Test1 {

	public static void main(String[] args) throws IOException {
		String[] a=new String[3];
		a[0]="原文文件路径：D:\\orig.txt";
		a[1]="抄袭文件路径：D:\\orig_add.txt";
		a[2]="答案文件：D:\\ans.txt";
			for(int i=0;i<3;i++){
				System.out.println(a[i]);
			}
		
    File file1=new File("D:\\orig.txt");
    File file2=new File("D:\\orig_add.txt");
    try {
    	FileReader inOne=new FileReader(file1);
    	BufferedReader inTwo=new BufferedReader(inOne);
    	
    	FileReader inOne1=new FileReader(file2);
    	BufferedReader inTwo1=new BufferedReader(inOne1);
    	String s1=null;
    	String s2=null;
    	String a1=null;
    	String a2=null;
    	while((s1=inTwo.readLine())!=null) {
    		System.out.println(s1);
    		 a1=s1;
    	}
    	while((s2=inTwo1.readLine())!=null) {
    		System.out.println(s2);
    		a2=s2;
    	}
    	
    	 JiebaSegmenter segmenter=new JiebaSegmenter(); //利用jieba进行分词
		System.out.println(segmenter.sentenceProcess(a1));
		System.out.println(segmenter.sentenceProcess(a2));
    	inOne.close();
    	inTwo.close();
    	inOne1.close();
    	inTwo1.close();
    	 List<String> list1 =segmenter.sentenceProcess(a1);
    	 List<String> list2 = segmenter.sentenceProcess(a2);
    	
    	 double similarity = CosineSimilarityCalculator.calculateCosineSimilarity(list1, list2);
    	//用余弦计算相似度
         System.out.printf("余弦相似度: %.2f%n ",similarity);
         File file3=new File("D:\\ans.txt");
         DecimalFormat df = new DecimalFormat("0.00"); // 设置格式化模式，保留两位小数
         df.setDecimalSeparatorAlwaysShown(true);

         try {
             BufferedWriter writer = new BufferedWriter(new FileWriter(file3));
             writer.write(df.format(similarity));  // 将格式化后的字符串写入文件
             writer.close();  // 关闭文件写入
             System.out.println("数据已成功写入文件。");
         } catch (IOException e) {
             e.printStackTrace();
         }
    }	 
        
        
    
    catch(IOException e) {
    	System.out.println(e);
    }
    
	}


}
