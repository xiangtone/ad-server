package cn.adwalker.ad.pool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.adwalker.IOSChannel.picker.util.Config;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.ad.util.AppConstant;
/**
 * <p>保存各渠道的点击</p>
 * @author jief
 *
 */
public class ChannelClickPool {
	
	public static final Logger log= Logger.getLogger(ChannelClickPool.class);
	
	public static ChannelClickPool pool=null;
	
	private Queue<IosClick> queue=null;
	
	private int maxNum=AppConstant.MAX_NUM;
	//私有化
	private ChannelClickPool (){
		queue=new ArrayDeque<IosClick>(10000);
		String queue_max_num=Config.getValue("queue_max_num");
		if(StringUtils.isNotBlank(queue_max_num)){
		  try{
			maxNum=Integer.parseInt(queue_max_num);
		   }catch(NumberFormatException e){
			   log.error("一次获取队列最大数据出错",e);
		   }
		}
	}
	/**
	 * <p>获取池实例</p>
	 * @return
	 */
	public static ChannelClickPool getInstance(){
		if(pool==null){
			synchronized (ChannelClickPool.class) {
				if(pool==null){
					pool=new ChannelClickPool();
				}
			}
		}
		return pool;
	}
	/**
	 * <p>添加</p>
	 * @param result
	 */
	public void add(IosClick result){
		synchronized (queue) {
			this.queue.add(result);
		}
	}
	/**
	 * <p>当前池数据量大小</p>
	 * @return
	 */
	public int getPoolSize(){
		synchronized (queue) {
			return queue.size();
		}
	}
	/**
	 * <p>每次获取批量的点击数据</p>
	 * @return
	 */
	public List<IosClick> getBattchList(){
	  synchronized (queue) {
		int size=queue.size();
		if(size>0){
//			System.out.println("队列大小是："+size);
			List<IosClick> crList=new ArrayList<IosClick>();
			for(int i=1;i<=size;i++){
				if(i>maxNum){
					return crList;
				}
				crList.add(this.queue.poll());
			}
			return crList;
		}
	  }
	  return  null;
	}
}
