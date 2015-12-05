package cn.adwalker;

import java.io.File;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import cn.adwalker.core.util.DateUtil;

/**
 * 
 * <p>
 * Title: TestPlacementType
 * </p>
 * <p>
 * Description:生成投放广告形式历史数据
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-24
 */
public class TestPlacementType extends TestCase {

	public void testtable() throws Exception {
		List<String> list = FileUtils.readLines(new File("D://haha/P_TYPE.txt"));
		for (String s : list) {
			String id = s.split("\\t")[0];
			StringBuilder sb = new StringBuilder();
			sb.append(" merge into T_PLACEMENT_TYPE_REL c ");
			sb.append(" using (select " + id + " as PLACEMENT_ID,column_value as type_id from table( split('0,1,4,5') ) t1) t ");
			sb.append(" on (c.PLACEMENT_ID=t.PLACEMENT_ID and c.type_id=t.type_id)");
			sb.append(" when not matched then ");
			sb.append(" insert  (id,PLACEMENT_ID,TYPE_ID,CREATE_TIME,status)values (SEQ_PLACEMENT_TYPE_REL.NEXTVAL,t.PLACEMENT_ID,t.type_id,DATE_FORMAT('" + DateUtil.formatDate(new Date()) + " 00:00:00', '%Y-%m-%d %T'),-1); ");
			System.out.println(sb.toString());

		}

	}

}
