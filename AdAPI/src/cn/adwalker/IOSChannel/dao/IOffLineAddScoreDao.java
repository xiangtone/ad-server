package cn.adwalker.IOSChannel.dao;

import java.util.List;

import cn.adwalker.ad.bean.OffLineData;

/**
 * <p>线下数据操作dao</p>
 * @author jief
 * @version 1.0
 */
public interface IOffLineAddScoreDao {
  /**
   * <p>获取状态的</p>
   * @param status
   * @return
   */
  public List<OffLineData> getDatasByStatus(int status);
  /**
   * <p>更新状态</p>
   * @param id
   * @param stauts
   */
  public void update(long id,int stauts);
}
