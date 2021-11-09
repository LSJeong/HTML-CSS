package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends DAO{

	//getItemList
	public List<ItemVO> getItemList() {
		connect();
		String sql = "select * from item order by 1";
		List<ItemVO> list = new ArrayList<ItemVO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ItemVO vo = new ItemVO();
				vo.setProdId(rs.getInt("prod_id"));
				vo.setProdName(rs.getString("prod_item"));
				vo.setProdDesc(rs.getString("prod_desc"));
				vo.setLikeIt(rs.getFloat("like_it"));
				vo.setOriginPrice(rs.getInt("origin_price"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setProdImage(rs.getString("prod_image"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
}
