package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddUserDAO extends DAO {
	//한명 등록
	public void insertUserTable(UserVO vo) {
		String sql = "insert into users values(?,?,?,?,?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getBirth());
			
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
	}
	
	//전체리스트
	public List<UserVO> getUserList() {
		String sql = "select * from users order by 1";
		connect();
		List<UserVO> list = new ArrayList<UserVO>();
		try {
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserVO vo = new UserVO();

				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setBirth(rs.getString("birth"));
				list.add(vo);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
