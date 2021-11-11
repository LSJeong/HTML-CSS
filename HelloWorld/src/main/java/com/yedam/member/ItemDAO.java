package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends DAO{
	// 상품 아이디로 한건 조회
	public ItemVO searchItem(String id) {
		connect();
		String sql = "select * from item where prod_id = ?";
		ItemVO vo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ItemVO();
				vo.setLikeIt(rs.getDouble("like_it"));
				vo.setOriginPrice(rs.getInt("origin_price"));
				vo.setProdDesc(rs.getString("prod_desc"));
				vo.setProdId(rs.getInt("prod_id"));
				vo.setProdImage(rs.getString("prod_image"));
				vo.setProdName(rs.getString("prod_item"));
				vo.setSalePrice(rs.getInt("sale_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return vo;
	}
	
	// 상품 업로드
	public ItemVO uploadProduct(ItemVO vo) {
		connect();
		String sql = "insert into item values(?,?,?,?,?,?,?)";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(prod_id) + 1 from item");
			int nextId = -1;
			if(rs.next()) {
				nextId = rs.getInt(1); // 첫번째 칼럼
				vo.setProdId(nextId); //매개값의 참조변수의 값을 변경
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextId);
			psmt.setString(2, vo.getProdName());
			psmt.setString(3, vo.getProdDesc());
			psmt.setDouble(4, vo.getLikeIt());
			psmt.setInt(5, vo.getOriginPrice());
			psmt.setInt(6, vo.getSalePrice());
			psmt.setString(7, vo.getProdImage());
			int r = psmt.executeUpdate(); //실제 쿼리 실행
			System.out.println(r + "건 입력"); //처리 후 메세지 출력
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return vo;
	}
	
	
	// getItemList
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
				vo.setLikeIt(rs.getDouble("like_it"));
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
