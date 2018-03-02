public class TzGatherUtil {

	public static void donwload(String netUrl,String path,String fileName) {
		try {
			URL url = new URL(netUrl);
			URLConnection connection = url.openConnection();
			//文本和内容都是字符流
			//图片，音频，视频--字节流
			InputStream in = connection.getInputStream();//打开程序和网络的一个连接
			//开始下载，一4KB下载图片
			byte[] bs = new byte[4048];
			//记录下载的位置
			int len = 0;
			int clen = 0;
			//写入磁盘--输出 outputstream
			int tlen = connection.getContentLength();
			FileOutputStream outputStream = new FileOutputStream(new File(path,fileName));
			while((len = in.read(bs))!=-1){//==-1代表文件下载完毕了
				clen+=len;
				System.out.println("====你已经下载了："+clen+"，还剩余："+(tlen-clen)+",百分比:"+((clen/(float)tlen))*100);
				outputStream.write(bs, 0, len);//写入磁盘
			}
			outputStream.close();
			in.close();
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
//		donwload("http://img-cdn2.luoo.net/pics/albums/10841/cover.jpg!/fwfh/580x580", "d:/", new Date().getTime()+".jpg");
		for (int i = 1; i < 18; i++) {
			String mark = i+"";
			if(i<10)mark = "0"+i;
			String href ="http://mp3-cdn2.luoo.net/low/luoo/radio957/"+mark+".mp3";
			System.out.println(href);
			donwload(href, "d:/", mark+".mp3");
			int j=i-1;
			donwload("http://img-cdn2.luoo.net/pics/albums/1438"+j+"/59f21aa656de7.jpg!/fwfh/580x580", "d:/", new Date().getTime()+".jpg");
		}
	}
}
